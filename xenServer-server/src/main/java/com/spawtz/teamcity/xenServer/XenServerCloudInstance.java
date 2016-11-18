package com.spawtz.teamcity.xenServer;

import jetbrains.buildServer.clouds.CloudErrorInfo;
import jetbrains.buildServer.clouds.CloudImage;
import jetbrains.buildServer.clouds.CloudInstance;
import jetbrains.buildServer.clouds.InstanceStatus;
import jetbrains.buildServer.serverSide.AgentDescription;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class XenServerCloudInstance implements CloudInstance {
    @NotNull
    @Override
    public String getInstanceId() {
        return "1";
    }

    @NotNull
    @Override
    public String getName() {
        return "one";
    }

    @NotNull
    @Override
    public String getImageId() {
        return "";
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
        return null;
    }

    @NotNull
    @Override
    public InstanceStatus getStatus() {
        return InstanceStatus.RUNNING;
    }

    @Nullable
    @Override
    public CloudErrorInfo getErrorInfo() {
        return null;
    }

    @Override
    public boolean containsAgent(@NotNull AgentDescription agentDescription) {
        return false;
    }
}
