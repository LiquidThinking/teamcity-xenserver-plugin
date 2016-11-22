package com.spawtz.teamcity.xenServer;

import com.xensource.xenapi.APIVersion;
import com.xensource.xenapi.Connection;
import com.xensource.xenapi.Session;
import com.xensource.xenapi.VM;
import jetbrains.buildServer.clouds.*;
import jetbrains.buildServer.serverSide.AgentDescription;
import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.*;

public class XenServerCloudClientFactory implements CloudClientFactory {
    @NotNull
    private final String cloudProfileSettings;

    XenServerCloudClientFactory(@NotNull final CloudRegistrar cloudRegistrar, @NotNull final PluginDescriptor pluginDescriptor) {
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
                LinkedList<InvalidProperty> result = new LinkedList<InvalidProperty>();
                for (String key : map.keySet()) {
                    if (key.startsWith("clouds.xenserver") && (map.get(key) == null || map.get(key).equals("")))
                        result.add(new InvalidProperty(key, "Cannot be empty"));
                }
                if (result.size() == 0) {
                    try {
                        HostnameVerifier hv = new HostnameVerifier()
                        {
                            public boolean verify(String hostname, SSLSession session)
                            {
                                return session.getPeerHost().equals(hostname);
                            }
                        };

                        HttpsURLConnection.setDefaultHostnameVerifier(hv);
                        TrustManager[] trustAllCerts = new TrustManager[]{
                                new X509TrustManager(){
                                    public X509Certificate[] getAcceptedIssuers(){ return null; }
                                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                                }
                        };

                        try {
                            SSLContext sslContext = SSLContext.getInstance("SSL");
                            sslContext.init(null, trustAllCerts, new SecureRandom());
                            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        Connection connection = new Connection(new URL("https://" + map.get("clouds.xenserver.server")));
                        Session session = Session.loginWithPassword(connection, map.get("clouds.xenserver.userName"), map.get("clouds.xenserver.password"), APIVersion.latest().toString());
                        session.getLastActive(connection);
                    } catch (Exception e) {
                        result.add(new InvalidProperty("clouds.xenserver.userName", "Invalid credentials"));
                    }
                }
                return result;
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
        try {
            return new XenServerCloudClientEx(cloudClientParameters);
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
