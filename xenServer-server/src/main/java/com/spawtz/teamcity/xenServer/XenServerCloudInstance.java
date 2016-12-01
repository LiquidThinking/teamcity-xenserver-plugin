package com.spawtz.teamcity.xenServer;

import com.xensource.xenapi.*;
import jetbrains.buildServer.clouds.CloudErrorInfo;
import jetbrains.buildServer.clouds.CloudImage;
import jetbrains.buildServer.clouds.CloudInstance;
import jetbrains.buildServer.clouds.InstanceStatus;
import jetbrains.buildServer.serverSide.AgentDescription;
import org.apache.xmlrpc.XmlRpcException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class XenServerCloudInstance implements CloudInstance {
    private Connection _connection;
    private VM _vm;


    XenServerCloudInstance(Connection connection, VM vm) {
        _connection = connection;
        _vm = vm;
    }

    void restart() {
        try {
            _vm.hardReboot(_connection);
        } catch (Types.XenAPIException e) {
            e.printStackTrace();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
    }

    void stop() {
        try {
            _vm.hardShutdown(_connection);
            Set<VBD> vbds = _vm.getVBDs(_connection);
            for (VBD vbd : vbds) {
                try {
                    vbd.getVDI(_connection).destroy(_connection);

                } catch (Exception ignored) {
                }
                try {
                    vbd.destroy(_connection);
                } catch (Exception ignored) {
                }
            }

            _vm.destroy(_connection);
        } catch (Types.XenAPIException e) {
            e.printStackTrace();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    @Override
    public String getInstanceId() {
        try {
            return _vm.getUuid(_connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public String getName() {
        try {
            return "buildagent_" + getNetworkIdentity();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public String getImageId() {
        try {
            return (String) _vm.getTags(_connection).toArray()[0];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public CloudImage getImage() {
        try {
            return new XenServerCloudImage(_connection, VM.getByUuid(_connection, getImageId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Date getStartedTime() {
        return new Date();
    }

    @Nullable
    @Override
    public String getNetworkIdentity() {
        try {
            VMGuestMetrics metrics = _vm.getGuestMetrics(_connection);
            Map<String, String> networks = metrics.getNetworks(_connection);
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\agent.txt", true)));
                for (String s : networks.keySet()) {
                    out.println(s + ": " + networks.get(s));
                }

                out.close();
            } catch (Exception e) {
            }
            String key = (String) networks.keySet().toArray()[0];
            return networks.get(key);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static HashSet<String> _runningVms = new HashSet<String>();

    @NotNull
    @Override
    public InstanceStatus getStatus() {
        try {
            if(!_runningVms.contains(_vm.getUuid(_connection))){
                _runningVms.add(_vm.getUuid(_connection));
                return InstanceStatus.STARTING;
            }

            Types.VmPowerState powerState = _vm.getPowerState(_connection);
            if (powerState == Types.VmPowerState.RUNNING)
                return InstanceStatus.RUNNING;
            return InstanceStatus.STOPPED;
        } catch (Types.XenAPIException e) {
            e.printStackTrace();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return InstanceStatus.STOPPED;
    }

    @Nullable
    @Override
    public CloudErrorInfo getErrorInfo() {
        return null;
    }

    @Override
    public boolean containsAgent(@NotNull AgentDescription agentDescription) {
        String currentAgentName = "buildagent_" + getNetworkIdentity();
        String agentName = agentDescription.toString().split(" ")[0];

        return currentAgentName.equals(agentName);
    }
}
