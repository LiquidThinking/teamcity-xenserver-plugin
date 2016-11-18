package com.spawtz.teamcity.xenServer;

import jetbrains.buildServer.clouds.*;
import jetbrains.buildServer.serverSide.AgentDescription;
import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class XenServerCloudClientFactory implements CloudClientFactory {
    @NotNull private final String cloudProfileSettings;

    XenServerCloudClientFactory(@NotNull final CloudRegistrar cloudRegistrar, @NotNull final PluginDescriptor pluginDescriptor){
        cloudProfileSettings = pluginDescriptor.getPluginResourcesPath("profile-settings.jsp");
        cloudRegistrar.registerCloudFactory(this);
    }

    @NotNull
    @Override
    public String getCloudCode() {
        return "xs";
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "XenServer";
    }

    @Nullable
    @Override
    public String getEditProfileUrl() {
        return cloudProfileSettings;
    }

    @NotNull
    @Override
    public Map<String, String> getInitialParameterValues() {
        return new HashMap<String, String>();
    }

    @NotNull
    @Override
    public PropertiesProcessor getPropertiesProcessor() {
        return new PropertiesProcessor() {
            @Override
            public Collection<InvalidProperty> process(Map<String, String> map) {
                return new LinkedList<InvalidProperty>();
            }
        };
    }

    @Override
    public boolean canBeAgentOfType(@NotNull AgentDescription agentDescription) {
        return false;
    }

    @NotNull
    @Override
    public CloudClientEx createNewClient(@NotNull CloudState cloudState, @NotNull CloudClientParameters cloudClientParameters) {
        return new XenServerCloudClientEx();
    }
}
