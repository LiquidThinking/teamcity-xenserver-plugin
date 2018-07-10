package com.spawtz.teamcity.xenServer;

import com.xensource.xenapi.*;
import jetbrains.buildServer.agentServer.Agent;
import jetbrains.buildServer.clouds.*;
import jetbrains.buildServer.serverSide.AgentDescription;
import jetbrains.buildServer.serverSide.BuildAgentManager;
import jetbrains.buildServer.serverSide.SBuildAgent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.net.ssl.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.*;

public class XenServerCloudClientEx implements CloudClientEx {
    private CloudClientParameters _parameters;
    private BuildAgentManager _agentFinder;

    XenServerCloudClientEx(CloudClientParameters parameters, BuildAgentManager agentFinder) {
        _parameters = parameters;
        _agentFinder = agentFinder;
    }

    @NotNull
    @Override
    public CloudInstance startNewInstance(@NotNull CloudImage cloudImage, @NotNull CloudInstanceUserData cloudInstanceUserData) throws QuotaException {
        cloudInstanceUserData.addAgentConfigurationParameter("jetbrains.buildServer.clouds.CloudConstants#AGENT_TERMINATE_AFTER_BUILD", "true");
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
            Connection connection = new Connection(new URL("https://" + _parameters.getParameter("clouds.xenserver.server")));
            Session.loginWithPassword(connection, _parameters.getParameter("clouds.xenserver.userName"), _parameters.getParameter("clouds.xenserver.password"), APIVersion.latest().toString());

            VM template = VM.getByUuid(connection, cloudImage.getId());
            VM clone = template.createClone(connection, "buildagent-" + UUID.randomUUID().toString());
            clone.setIsATemplate(connection, false);
            HashSet<String> tags = new HashSet<String>();
            tags.add(cloudImage.getId());
            clone.setTags(connection,tags);
            clone.start(connection,false,true);
            return new XenServerCloudInstance(connection, clone);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void restartInstance(@NotNull CloudInstance cloudInstance) {
        XenServerCloudInstance instance = (XenServerCloudInstance)cloudInstance;
        instance.restart();
    }

    @Override
    public void terminateInstance(@NotNull CloudInstance cloudInstance) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\agent.txt", true)));
            out.println("stopping instance "+ cloudInstance.getImageId());
            out.close();
        } catch (Exception e) {
            //exception handling left as an exercise for the reader
        }
        XenServerCloudInstance instance = (XenServerCloudInstance)cloudInstance;
        String agentName = "buildagent_" + instance.getNetworkIdentity();
        SBuildAgent agent = _agentFinder.findAgentByName(agentName, false);
        assert agent != null;
        agent.setAuthorized(false, null, "");

        instance.stop();
        //noinspection ConstantConditions
        //_agentFinder.
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean isInitialized() {
        return true;
    }

    @Nullable
    @Override
    public CloudImage findImageById(@NotNull String s) throws CloudException {
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
            Connection connection = new Connection(new URL("https://" + _parameters.getParameter("clouds.xenserver.server")));
            Session.loginWithPassword(connection, _parameters.getParameter("clouds.xenserver.userName"), _parameters.getParameter("clouds.xenserver.password"), APIVersion.latest().toString());
            return new XenServerCloudImage(connection, VM.getByUuid(connection, s));
        } catch (Exception ex) {
            return null;
        }
    }

    @Nullable
    @Override
    public CloudInstance findInstanceByAgent(@NotNull AgentDescription agentDescription) {
        CloudImage cloudImage = (CloudImage) getImages().toArray()[0];
        for (CloudInstance cloudInstance : cloudImage.getInstances()) {
            if(cloudInstance.containsAgent(agentDescription))
                return cloudInstance;
        }
        return null;
    }

    @NotNull
    @Override
    public Collection<? extends CloudImage> getImages() throws CloudException {
        LinkedList<CloudImage> result = new LinkedList<CloudImage>();
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
            Connection connection = new Connection(new URL("https://" + _parameters.getParameter("clouds.xenserver.server")));
            Session.loginWithPassword(connection, _parameters.getParameter("clouds.xenserver.userName"), _parameters.getParameter("clouds.xenserver.password"), APIVersion.latest().toString());

            result.add(new XenServerCloudImage(connection, VM.getByUuid(connection, _parameters.getParameter("clouds.xenserver.template"))));
        } catch (Exception ignored) {}
        return result;
    }

    @Nullable
    @Override
    public CloudErrorInfo getErrorInfo() {
        return null;
    }

    @Override
    public boolean canStartNewInstance(@NotNull CloudImage cloudImage) {
        return true;
    }

    @Nullable
    @Override
    public String generateAgentName(@NotNull AgentDescription agentDescription) {
        return null;
    }
}
