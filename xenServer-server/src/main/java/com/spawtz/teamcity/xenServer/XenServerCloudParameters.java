package com.spawtz.teamcity.xenServer;

public interface XenServerCloudParameters {
    String CLOUD_TYPE = "xs";  //that should be equal or less than 6 symbols, thanks for brainfuck debugging jetbrains guys!
    String CLOUD_DISPLAY_NAME = "XenServer";
    String PLUGIN_SHORT_NAME = "xenserver";

    String SERVER = "clouds.xenserver.server";
    String USER_NAME = "clouds.xenserver.userName";
    String PASSWORD = "clouds.xenserver.password";
    String TEMPLATE = "clouds.xenserver.template";

    String AGENT_CLOUD_TYPE = "agent.cloud.type";
}
