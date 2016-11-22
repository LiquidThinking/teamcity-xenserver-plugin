package com.spawtz.teamcity.xenServer;

import com.xensource.xenapi.Connection;
import com.xensource.xenapi.Network;
import com.xensource.xenapi.Types;
import com.xensource.xenapi.VM;
import jetbrains.buildServer.clouds.CloudErrorInfo;
import jetbrains.buildServer.clouds.CloudImage;
import jetbrains.buildServer.clouds.CloudInstance;
import jetbrains.buildServer.clouds.InstanceStatus;
import jetbrains.buildServer.serverSide.AgentDescription;
import org.apache.xmlrpc.XmlRpcException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class XenServerCloudInstance implements CloudInstance {
    private Connection _connection;
    private VM _vm;


    XenServerCloudInstance(Connection connection, VM vm){
        _connection = connection;
        _vm = vm;
    }

    void restart(){
        try {
            _vm.hardReboot(_connection);
        } catch (Types.XenAPIException e) {
            e.printStackTrace();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
    }

    void stop(){
        try {
            _vm.hardShutdown(_connection);
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
            return "";
        }
    }

    @NotNull
    @Override
    public String getName() {
        try {
            return _vm.getNameLabel(_connection);
        } catch (Exception e) {
            return "";
        }
    }

    @NotNull
    @Override
    public String getImageId() {
        try {
            return (String)_vm.getTags(_connection).toArray()[0];
        } catch (Exception e) {
            return "";
        }
    }

    @NotNull
    @Override
    public CloudImage getImage() {
        return new CloudImage() {
            @NotNull
            @Override
            public String getId() {
                return "";
            }

            @NotNull
            @Override
            public String getName() {
                return "";
            }

            @NotNull
            @Override
            public Collection<? extends CloudInstance> getInstances() {
                return new LinkedList<XenServerCloudInstance>();
            }

            @Nullable
            @Override
            public CloudInstance findInstanceById(@NotNull String s) {
                return null;
            }

            @Nullable
            @Override
            public Integer getAgentPoolId() {
                return null;
            }

            @Nullable
            @Override
            public CloudErrorInfo getErrorInfo() {
                return null;
            }
        };
    }

    @NotNull
    @Override
    public Date getStartedTime() {
        return new Date();
    }

    @Nullable
    @Override
    public String getNetworkIdentity() {
        return "";
    }

    @NotNull
    @Override
    public InstanceStatus getStatus() {
        try {
            Types.VmPowerState powerState = _vm.getPowerState(_connection);
            if(powerState == Types.VmPowerState.RUNNING)
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
        return true;
    }
}
