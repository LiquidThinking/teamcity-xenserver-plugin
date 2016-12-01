package com.spawtz.teamcity.xenServer;

import com.intellij.openapi.diagnostic.Logger;
import jetbrains.buildServer.agent.*;
import jetbrains.buildServer.clouds.CloudInstanceUserData;
import jetbrains.buildServer.util.EventDispatcher;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static jetbrains.buildServer.clouds.CloudConstants.AGENT_TERMINATE_AFTER_BUILD;

public class XenServerPropertiesReader {
    private static final Logger LOG = Logger.getInstance(XenServerPropertiesReader.class.getName());

    private final BuildAgentConfigurationEx myAgentConfiguration;

    public XenServerPropertiesReader(final BuildAgentConfigurationEx agentConfiguration,
                                     @NotNull EventDispatcher<AgentLifeCycleListener> events) {
        myAgentConfiguration = agentConfiguration;
        LOG.info("Running xenServer plugin");
        myAgentConfiguration.addConfigurationParameter(AGENT_TERMINATE_AFTER_BUILD, "true");
        events.addListener(new AgentLifeCycleAdapter() {
            @Override
            public void afterAgentConfigurationLoaded(@NotNull final BuildAgent agent) {
               myAgentConfiguration.addConfigurationParameter(AGENT_TERMINATE_AFTER_BUILD, "true");
            }
        });
    }
}
