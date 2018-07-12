/*
 * Copyright (c) Citrix Systems, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   1) Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *
 *   2) Redistributions in binary form must reproduce the above
 *      copyright notice, this list of conditions and the following
 *      disclaimer in the documentation and/or other materials
 *      provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package com.xensource.xenapi;

import com.xensource.xenapi.Types.BadServerResponse;
import com.xensource.xenapi.Types.VersionException;
import com.xensource.xenapi.Types.XenAPIException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;

/**
 * Cluster-wide Cluster metadata
 *
 * @author Citrix Systems, Inc.
 */
public class Cluster extends XenAPIObject {

    /**
     * The XenAPI reference (OpaqueRef) to this object.
     */
    protected final String ref;

    /**
     * For internal use only.
     */
    Cluster(String ref) {
       this.ref = ref;
    }

    /**
     * @return The XenAPI reference (OpaqueRef) to this object.
     */
    public String toWireString() {
       return this.ref;
    }

    /**
     * If obj is a Cluster, compares XenAPI references for equality.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj != null && obj instanceof Cluster)
        {
            Cluster other = (Cluster) obj;
            return other.ref.equals(this.ref);
        } else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return ref.hashCode();
    }

    /**
     * Represents all the fields in a Cluster
     */
    public static class Record implements Types.Record {
        public String toString() {
            StringWriter writer = new StringWriter();
            PrintWriter print = new PrintWriter(writer);
            print.printf("%1$20s: %2$s\n", "uuid", this.uuid);
            print.printf("%1$20s: %2$s\n", "clusterHosts", this.clusterHosts);
            print.printf("%1$20s: %2$s\n", "network", this.network);
            print.printf("%1$20s: %2$s\n", "clusterToken", this.clusterToken);
            print.printf("%1$20s: %2$s\n", "clusterStack", this.clusterStack);
            print.printf("%1$20s: %2$s\n", "allowedOperations", this.allowedOperations);
            print.printf("%1$20s: %2$s\n", "currentOperations", this.currentOperations);
            print.printf("%1$20s: %2$s\n", "poolAutoJoin", this.poolAutoJoin);
            print.printf("%1$20s: %2$s\n", "tokenTimeout", this.tokenTimeout);
            print.printf("%1$20s: %2$s\n", "tokenTimeoutCoefficient", this.tokenTimeoutCoefficient);
            print.printf("%1$20s: %2$s\n", "clusterConfig", this.clusterConfig);
            print.printf("%1$20s: %2$s\n", "otherConfig", this.otherConfig);
            return writer.toString();
        }

        /**
         * Convert a Cluster.Record to a Map
         */
        public Map<String,Object> toMap() {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("uuid", this.uuid == null ? "" : this.uuid);
            map.put("cluster_hosts", this.clusterHosts == null ? new LinkedHashSet<ClusterHost>() : this.clusterHosts);
            map.put("network", this.network == null ? new Network("OpaqueRef:NULL") : this.network);
            map.put("cluster_token", this.clusterToken == null ? "" : this.clusterToken);
            map.put("cluster_stack", this.clusterStack == null ? "" : this.clusterStack);
            map.put("allowed_operations", this.allowedOperations == null ? new LinkedHashSet<Types.ClusterOperation>() : this.allowedOperations);
            map.put("current_operations", this.currentOperations == null ? new HashMap<String, Types.ClusterOperation>() : this.currentOperations);
            map.put("pool_auto_join", this.poolAutoJoin == null ? false : this.poolAutoJoin);
            map.put("token_timeout", this.tokenTimeout == null ? 0 : this.tokenTimeout);
            map.put("token_timeout_coefficient", this.tokenTimeoutCoefficient == null ? 0 : this.tokenTimeoutCoefficient);
            map.put("cluster_config", this.clusterConfig == null ? new HashMap<String, String>() : this.clusterConfig);
            map.put("other_config", this.otherConfig == null ? new HashMap<String, String>() : this.otherConfig);
            return map;
        }

        /**
         * Unique identifier/object reference
         * Experimental. First published in Unreleased.
         */
        public String uuid;
        /**
         * A list of the cluster_host objects associated with the Cluster
         * Experimental. First published in Unreleased.
         */
        public Set<ClusterHost> clusterHosts;
        /**
         * Reference to the single network on which corosync carries out its inter-host communications
         * Experimental. First published in Unreleased.
         */
        public Network network;
        /**
         * The secret key used by xapi-clusterd when it talks to itself on other hosts
         * Experimental. First published in Unreleased.
         */
        public String clusterToken;
        /**
         * Simply the string 'corosync'. No other cluster stacks are currently supported
         * Experimental. First published in Unreleased.
         */
        public String clusterStack;
        /**
         * list of the operations allowed in this state. This list is advisory only and the server state may have changed by the time this field is read by a client.
         */
        public Set<Types.ClusterOperation> allowedOperations;
        /**
         * links each of the running tasks using this object (by reference) to a current_operation enum which describes the nature of the task.
         */
        public Map<String, Types.ClusterOperation> currentOperations;
        /**
         * True if xapi is automatically joining new pool members to the cluster. This will be `true` in the first release
         * Experimental. First published in Unreleased.
         */
        public Boolean poolAutoJoin;
        /**
         * The corosync token timeout in ms
         * Experimental. First published in Unreleased.
         */
        public Long tokenTimeout;
        /**
         * The corosync token timeout coefficient in ms
         * Experimental. First published in Unreleased.
         */
        public Long tokenTimeoutCoefficient;
        /**
         * Contains read-only settings for the cluster, such as timeouts and other options. It can only be set at cluster create time
         * Experimental. First published in Unreleased.
         */
        public Map<String, String> clusterConfig;
        /**
         * Additional configuration
         * Experimental. First published in Unreleased.
         */
        public Map<String, String> otherConfig;
    }

    /**
     * Get a record containing the current state of the given Cluster.
     * Experimental. First published in Unreleased.
     *
     * @return all fields from the object
     */
    public Cluster.Record getRecord(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_record";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toClusterRecord(result);
    }

    /**
     * Get a reference to the Cluster instance with the specified UUID.
     * Experimental. First published in Unreleased.
     *
     * @param uuid UUID of object to return
     * @return reference to the object
     */
    public static Cluster getByUuid(Connection c, String uuid) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_by_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(uuid)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toCluster(result);
    }

    /**
     * Get the uuid field of the given Cluster.
     * Experimental. First published in Unreleased.
     *
     * @return value of the field
     */
    public String getUuid(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the cluster_hosts field of the given Cluster.
     * Experimental. First published in Unreleased.
     *
     * @return value of the field
     */
    public Set<ClusterHost> getClusterHosts(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_cluster_hosts";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfClusterHost(result);
    }

    /**
     * Get the network field of the given Cluster.
     * Experimental. First published in Unreleased.
     *
     * @return value of the field
     */
    public Network getNetwork(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_network";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toNetwork(result);
    }

    /**
     * Get the cluster_token field of the given Cluster.
     * Experimental. First published in Unreleased.
     *
     * @return value of the field
     */
    public String getClusterToken(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_cluster_token";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the cluster_stack field of the given Cluster.
     * Experimental. First published in Unreleased.
     *
     * @return value of the field
     */
    public String getClusterStack(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_cluster_stack";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the allowed_operations field of the given Cluster.
     *
     * @return value of the field
     */
    public Set<Types.ClusterOperation> getAllowedOperations(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_allowed_operations";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfClusterOperation(result);
    }

    /**
     * Get the current_operations field of the given Cluster.
     *
     * @return value of the field
     */
    public Map<String, Types.ClusterOperation> getCurrentOperations(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_current_operations";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toMapOfStringClusterOperation(result);
    }

    /**
     * Get the pool_auto_join field of the given Cluster.
     * Experimental. First published in Unreleased.
     *
     * @return value of the field
     */
    public Boolean getPoolAutoJoin(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_pool_auto_join";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toBoolean(result);
    }

    /**
     * Get the token_timeout field of the given Cluster.
     * Experimental. First published in Unreleased.
     *
     * @return value of the field
     */
    public Long getTokenTimeout(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_token_timeout";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toLong(result);
    }

    /**
     * Get the token_timeout_coefficient field of the given Cluster.
     * Experimental. First published in Unreleased.
     *
     * @return value of the field
     */
    public Long getTokenTimeoutCoefficient(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_token_timeout_coefficient";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toLong(result);
    }

    /**
     * Get the cluster_config field of the given Cluster.
     * Experimental. First published in Unreleased.
     *
     * @return value of the field
     */
    public Map<String, String> getClusterConfig(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_cluster_config";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toMapOfStringString(result);
    }

    /**
     * Get the other_config field of the given Cluster.
     * Experimental. First published in Unreleased.
     *
     * @return value of the field
     */
    public Map<String, String> getOtherConfig(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_other_config";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toMapOfStringString(result);
    }

    /**
     * Set the other_config field of the given Cluster.
     * Experimental. First published in Unreleased.
     *
     * @param otherConfig New value to set
     */
    public void setOtherConfig(Connection c, Map<String, String> otherConfig) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.set_other_config";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(otherConfig)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Add the given key-value pair to the other_config field of the given Cluster.
     * Experimental. First published in Unreleased.
     *
     * @param key Key to add
     * @param value Value to add
     */
    public void addToOtherConfig(Connection c, String key, String value) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.add_to_other_config";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(key), Marshalling.toXMLRPC(value)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Remove the given key and its corresponding value from the other_config field of the given Cluster.  If the key is not in that Map, then do nothing.
     * Experimental. First published in Unreleased.
     *
     * @param key Key to remove
     */
    public void removeFromOtherConfig(Connection c, String key) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.remove_from_other_config";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(key)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Creates a Cluster object and one Cluster_host object as its first member
     * Experimental. First published in Unreleased.
     *
     * @param network the single network on which corosync carries out its inter-host communications
     * @param clusterStack simply the string 'corosync'. No other cluster stacks are currently supported
     * @param poolAutoJoin true if xapi is automatically joining new pool members to the cluster
     * @param tokenTimeout Corosync token timeout in seconds
     * @param tokenTimeoutCoefficient Corosync token timeout coefficient in seconds
     * @return Task
     */
    public static Task createAsync(Connection c, Network network, String clusterStack, Boolean poolAutoJoin, Double tokenTimeout, Double tokenTimeoutCoefficient) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.Cluster.create";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(network), Marshalling.toXMLRPC(clusterStack), Marshalling.toXMLRPC(poolAutoJoin), Marshalling.toXMLRPC(tokenTimeout), Marshalling.toXMLRPC(tokenTimeoutCoefficient)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Creates a Cluster object and one Cluster_host object as its first member
     * Experimental. First published in Unreleased.
     *
     * @param network the single network on which corosync carries out its inter-host communications
     * @param clusterStack simply the string 'corosync'. No other cluster stacks are currently supported
     * @param poolAutoJoin true if xapi is automatically joining new pool members to the cluster
     * @param tokenTimeout Corosync token timeout in seconds
     * @param tokenTimeoutCoefficient Corosync token timeout coefficient in seconds
     * @return the new Cluster
     */
    public static Cluster create(Connection c, Network network, String clusterStack, Boolean poolAutoJoin, Double tokenTimeout, Double tokenTimeoutCoefficient) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.create";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(network), Marshalling.toXMLRPC(clusterStack), Marshalling.toXMLRPC(poolAutoJoin), Marshalling.toXMLRPC(tokenTimeout), Marshalling.toXMLRPC(tokenTimeoutCoefficient)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toCluster(result);
    }

    /**
     * Destroys a Cluster object and the one remaining Cluster_host member
     * Experimental. First published in Unreleased.
     *
     * @return Task
     */
    public Task destroyAsync(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.Cluster.destroy";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Destroys a Cluster object and the one remaining Cluster_host member
     * Experimental. First published in Unreleased.
     *
     */
    public void destroy(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.destroy";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Attempt to create a Cluster from the entire pool
     * Experimental. First published in Unreleased.
     *
     * @param network the single network on which corosync carries out its inter-host communications
     * @param clusterStack simply the string 'corosync'. No other cluster stacks are currently supported
     * @param tokenTimeout Corosync token timeout in seconds
     * @param tokenTimeoutCoefficient Corosync token timeout coefficient in seconds
     * @return Task
     */
    public static Task poolCreateAsync(Connection c, Network network, String clusterStack, Double tokenTimeout, Double tokenTimeoutCoefficient) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.Cluster.pool_create";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(network), Marshalling.toXMLRPC(clusterStack), Marshalling.toXMLRPC(tokenTimeout), Marshalling.toXMLRPC(tokenTimeoutCoefficient)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Attempt to create a Cluster from the entire pool
     * Experimental. First published in Unreleased.
     *
     * @param network the single network on which corosync carries out its inter-host communications
     * @param clusterStack simply the string 'corosync'. No other cluster stacks are currently supported
     * @param tokenTimeout Corosync token timeout in seconds
     * @param tokenTimeoutCoefficient Corosync token timeout coefficient in seconds
     * @return the new Cluster
     */
    public static Cluster poolCreate(Connection c, Network network, String clusterStack, Double tokenTimeout, Double tokenTimeoutCoefficient) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.pool_create";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(network), Marshalling.toXMLRPC(clusterStack), Marshalling.toXMLRPC(tokenTimeout), Marshalling.toXMLRPC(tokenTimeoutCoefficient)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toCluster(result);
    }

    /**
     * Attempt to force destroy the Cluster_host objects, and then destroy the Cluster.
     * Experimental. First published in Unreleased.
     *
     * @return Task
     */
    public Task poolForceDestroyAsync(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.Cluster.pool_force_destroy";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Attempt to force destroy the Cluster_host objects, and then destroy the Cluster.
     * Experimental. First published in Unreleased.
     *
     */
    public void poolForceDestroy(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.pool_force_destroy";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Attempt to destroy the Cluster_host objects for all hosts in the pool and then destroy the Cluster.
     * Experimental. First published in Unreleased.
     *
     * @return Task
     */
    public Task poolDestroyAsync(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.Cluster.pool_destroy";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Attempt to destroy the Cluster_host objects for all hosts in the pool and then destroy the Cluster.
     * Experimental. First published in Unreleased.
     *
     */
    public void poolDestroy(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.pool_destroy";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Resynchronise the cluster_host objects across the pool. Creates them where they need creating and then plugs them
     * Experimental. First published in Unreleased.
     *
     * @return Task
     */
    public Task poolResyncAsync(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.Cluster.pool_resync";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Resynchronise the cluster_host objects across the pool. Creates them where they need creating and then plugs them
     * Experimental. First published in Unreleased.
     *
     */
    public void poolResync(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.pool_resync";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Return a list of all the Clusters known to the system.
     * Experimental. First published in Unreleased.
     *
     * @return references to all objects
     */
    public static Set<Cluster> getAll(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_all";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfCluster(result);
    }

    /**
     * Return a map of Cluster references to Cluster records for all Clusters known to the system.
     * Experimental. First published in Unreleased.
     *
     * @return records of all objects
     */
    public static Map<Cluster, Cluster.Record> getAllRecords(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Cluster.get_all_records";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toMapOfClusterClusterRecord(result);
    }

}