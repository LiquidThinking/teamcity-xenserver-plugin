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
 * network-sriov which connects logical pif and physical pif
 * First published in Unreleased.
 *
 * @author Citrix Systems, Inc.
 */
public class NetworkSriov extends XenAPIObject {

    /**
     * The XenAPI reference (OpaqueRef) to this object.
     */
    protected final String ref;

    /**
     * For internal use only.
     */
    NetworkSriov(String ref) {
       this.ref = ref;
    }

    /**
     * @return The XenAPI reference (OpaqueRef) to this object.
     */
    public String toWireString() {
       return this.ref;
    }

    /**
     * If obj is a NetworkSriov, compares XenAPI references for equality.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj != null && obj instanceof NetworkSriov)
        {
            NetworkSriov other = (NetworkSriov) obj;
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
     * Represents all the fields in a NetworkSriov
     */
    public static class Record implements Types.Record {
        public String toString() {
            StringWriter writer = new StringWriter();
            PrintWriter print = new PrintWriter(writer);
            print.printf("%1$20s: %2$s\n", "uuid", this.uuid);
            print.printf("%1$20s: %2$s\n", "physicalPIF", this.physicalPIF);
            print.printf("%1$20s: %2$s\n", "logicalPIF", this.logicalPIF);
            print.printf("%1$20s: %2$s\n", "requiresReboot", this.requiresReboot);
            print.printf("%1$20s: %2$s\n", "configurationMode", this.configurationMode);
            return writer.toString();
        }

        /**
         * Convert a network_sriov.Record to a Map
         */
        public Map<String,Object> toMap() {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("uuid", this.uuid == null ? "" : this.uuid);
            map.put("physical_PIF", this.physicalPIF == null ? new PIF("OpaqueRef:NULL") : this.physicalPIF);
            map.put("logical_PIF", this.logicalPIF == null ? new PIF("OpaqueRef:NULL") : this.logicalPIF);
            map.put("requires_reboot", this.requiresReboot == null ? false : this.requiresReboot);
            map.put("configuration_mode", this.configurationMode == null ? Types.SriovConfigurationMode.UNRECOGNIZED : this.configurationMode);
            return map;
        }

        /**
         * Unique identifier/object reference
         */
        public String uuid;
        /**
         * The PIF that has SR-IOV enabled
         */
        public PIF physicalPIF;
        /**
         * The logical PIF to connect to the SR-IOV network after enable SR-IOV on the physical PIF
         */
        public PIF logicalPIF;
        /**
         * Indicates whether the host need to be rebooted before SR-IOV is enabled on the physical PIF
         */
        public Boolean requiresReboot;
        /**
         * The mode for configure network sriov
         */
        public Types.SriovConfigurationMode configurationMode;
    }

    /**
     * Get a record containing the current state of the given network_sriov.
     * First published in Unreleased.
     *
     * @return all fields from the object
     */
    public NetworkSriov.Record getRecord(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "network_sriov.get_record";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toNetworkSriovRecord(result);
    }

    /**
     * Get a reference to the network_sriov instance with the specified UUID.
     * First published in Unreleased.
     *
     * @param uuid UUID of object to return
     * @return reference to the object
     */
    public static NetworkSriov getByUuid(Connection c, String uuid) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "network_sriov.get_by_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(uuid)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toNetworkSriov(result);
    }

    /**
     * Get the uuid field of the given network_sriov.
     * First published in Unreleased.
     *
     * @return value of the field
     */
    public String getUuid(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "network_sriov.get_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the physical_PIF field of the given network_sriov.
     * First published in Unreleased.
     *
     * @return value of the field
     */
    public PIF getPhysicalPIF(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "network_sriov.get_physical_PIF";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPIF(result);
    }

    /**
     * Get the logical_PIF field of the given network_sriov.
     * First published in Unreleased.
     *
     * @return value of the field
     */
    public PIF getLogicalPIF(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "network_sriov.get_logical_PIF";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPIF(result);
    }

    /**
     * Get the requires_reboot field of the given network_sriov.
     * First published in Unreleased.
     *
     * @return value of the field
     */
    public Boolean getRequiresReboot(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "network_sriov.get_requires_reboot";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toBoolean(result);
    }

    /**
     * Get the configuration_mode field of the given network_sriov.
     * First published in Unreleased.
     *
     * @return value of the field
     */
    public Types.SriovConfigurationMode getConfigurationMode(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "network_sriov.get_configuration_mode";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSriovConfigurationMode(result);
    }

    /**
     * Enable SR-IOV on the specific PIF. It will create a network-sriov based on the specific PIF and automatically create a logical PIF to connect the specific network.
     * First published in Unreleased.
     *
     * @param pif PIF on which to enable SR-IOV
     * @param network Network to connect SR-IOV virtual functions with VM VIFs
     * @return Task
     */
    public static Task createAsync(Connection c, PIF pif, Network network) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.network_sriov.create";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(pif), Marshalling.toXMLRPC(network)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Enable SR-IOV on the specific PIF. It will create a network-sriov based on the specific PIF and automatically create a logical PIF to connect the specific network.
     * First published in Unreleased.
     *
     * @param pif PIF on which to enable SR-IOV
     * @param network Network to connect SR-IOV virtual functions with VM VIFs
     * @return The reference of the created network_sriov object
     */
    public static NetworkSriov create(Connection c, PIF pif, Network network) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "network_sriov.create";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(pif), Marshalling.toXMLRPC(network)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toNetworkSriov(result);
    }

    /**
     * Disable SR-IOV on the specific PIF. It will destroy the network-sriov and the logical PIF accordingly.
     * First published in Unreleased.
     *
     * @return Task
     */
    public Task destroyAsync(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.network_sriov.destroy";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Disable SR-IOV on the specific PIF. It will destroy the network-sriov and the logical PIF accordingly.
     * First published in Unreleased.
     *
     */
    public void destroy(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "network_sriov.destroy";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Get the number of free SR-IOV VFs on the associated PIF
     * First published in Unreleased.
     *
     * @return Task
     */
    public Task getRemainingCapacityAsync(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.network_sriov.get_remaining_capacity";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Get the number of free SR-IOV VFs on the associated PIF
     * First published in Unreleased.
     *
     * @return The number of free SR-IOV VFs on the associated PIF
     */
    public Long getRemainingCapacity(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "network_sriov.get_remaining_capacity";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toLong(result);
    }

    /**
     * Return a list of all the network_sriovs known to the system.
     * First published in Unreleased.
     *
     * @return references to all objects
     */
    public static Set<NetworkSriov> getAll(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "network_sriov.get_all";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfNetworkSriov(result);
    }

    /**
     * Return a map of network_sriov references to network_sriov records for all network_sriovs known to the system.
     * First published in Unreleased.
     *
     * @return records of all objects
     */
    public static Map<NetworkSriov, NetworkSriov.Record> getAllRecords(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "network_sriov.get_all_records";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toMapOfNetworkSriovNetworkSriovRecord(result);
    }

}