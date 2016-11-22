package com.spawtz.teamcity.xenServer;

import com.xensource.xenapi.Connection;
import com.xensource.xenapi.Types;
import com.xensource.xenapi.VM;
import jetbrains.buildServer.clouds.CloudErrorInfo;
import jetbrains.buildServer.clouds.CloudImage;
import jetbrains.buildServer.clouds.CloudInstance;
import org.apache.xmlrpc.XmlRpcException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

public class XenServerCloudImage implements CloudImage {
    private Connection _connection;
    private VM _vm;

    XenServerCloudImage(Connection connection, VM vm) {
        _connection = connection;
        _vm = vm;
    }

    @NotNull
    @Override
    public String getId() {
        try {
            return _vm.getUuid(_connection);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @NotNull
    @Override
    public String getName() {
        try {
            return _vm.getNameLabel(_connection);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @NotNull
    @Override
    public Collection<? extends CloudInstance> getInstances() {
        try {
            LinkedList<XenServerCloudInstance> result = new LinkedList<XenServerCloudInstance>();
            Set<VM> children = VM.getAll(_connection);
            final String templateId = _vm.getUuid(_connection);
            for (VM child : children) {
                for (String tag : child.getTags(_connection)) {
                    if (tag.equals(templateId)) {
                        result.add(new XenServerCloudInstance(_connection, child));
                    }
                }
            }
            return result;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Nullable
    @Override
    public CloudInstance findInstanceById(@NotNull String s) {
        try {
            VM vm = VM.getByUuid(_connection, s);
            return new XenServerCloudInstance(_connection, vm);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
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
}
