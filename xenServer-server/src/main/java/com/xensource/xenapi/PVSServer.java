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
 * individual machine serving provisioning (block) data
 * First published in XenServer 7.1.
 *
 * @author Citrix Systems, Inc.
 */
public class PVSServer extends XenAPIObject {

    /**
     * The XenAPI reference (OpaqueRef) to this object.
     */
    protected final String ref;

    /**
     * For internal use only.
     */
    PVSServer(String ref) {
       this.ref = ref;
    }

    /**
     * @return The XenAPI reference (OpaqueRef) to this object.
     */
    public String toWireString() {
       return this.ref;
    }

    /**
     * If obj is a PVSServer, compares XenAPI references for equality.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj != null && obj instanceof PVSServer)
        {
            PVSServer other = (PVSServer) obj;
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
     * Represents all the fields in a PVSServer
     */
    public static class Record implements Types.Record {
        public String toString() {
            StringWriter writer = new StringWriter();
            PrintWriter print = new PrintWriter(writer);
            print.printf("%1$20s: %2$s\n", "uuid", this.uuid);
            print.printf("%1$20s: %2$s\n", "addresses", this.addresses);
            print.printf("%1$20s: %2$s\n", "firstPort", this.firstPort);
            print.printf("%1$20s: %2$s\n", "lastPort", this.lastPort);
            print.printf("%1$20s: %2$s\n", "site", this.site);
            return writer.toString();
        }

        /**
         * Convert a PVS_server.Record to a Map
         */
        public Map<String,Object> toMap() {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("uuid", this.uuid == null ? "" : this.uuid);
            map.put("addresses", this.addresses == null ? new LinkedHashSet<String>() : this.addresses);
            map.put("first_port", this.firstPort == null ? 0 : this.firstPort);
            map.put("last_port", this.lastPort == null ? 0 : this.lastPort);
            map.put("site", this.site == null ? new PVSSite("OpaqueRef:NULL") : this.site);
            return map;
        }

        /**
         * Unique identifier/object reference
         */
        public String uuid;
        /**
         * IPv4 addresses of this server
         */
        public Set<String> addresses;
        /**
         * First UDP port accepted by this server
         */
        public Long firstPort;
        /**
         * Last UDP port accepted by this server
         */
        public Long lastPort;
        /**
         * PVS site this server is part of
         */
        public PVSSite site;
    }

    /**
     * Get a record containing the current state of the given PVS_server.
     * First published in XenServer 7.1.
     *
     * @return all fields from the object
     */
    public PVSServer.Record getRecord(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_server.get_record";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPVSServerRecord(result);
    }

    /**
     * Get a reference to the PVS_server instance with the specified UUID.
     * First published in XenServer 7.1.
     *
     * @param uuid UUID of object to return
     * @return reference to the object
     */
    public static PVSServer getByUuid(Connection c, String uuid) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_server.get_by_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(uuid)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPVSServer(result);
    }

    /**
     * Get the uuid field of the given PVS_server.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public String getUuid(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_server.get_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the addresses field of the given PVS_server.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public Set<String> getAddresses(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_server.get_addresses";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfString(result);
    }

    /**
     * Get the first_port field of the given PVS_server.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public Long getFirstPort(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_server.get_first_port";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toLong(result);
    }

    /**
     * Get the last_port field of the given PVS_server.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public Long getLastPort(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_server.get_last_port";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toLong(result);
    }

    /**
     * Get the site field of the given PVS_server.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public PVSSite getSite(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_server.get_site";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPVSSite(result);
    }

    /**
     * introduce new PVS server
     * First published in XenServer 7.1.
     *
     * @param addresses IPv4 addresses of the server
     * @param firstPort first UDP port accepted by this server
     * @param lastPort last UDP port accepted by this server
     * @param site PVS site this server is a part of
     * @return Task
     */
    public static Task introduceAsync(Connection c, Set<String> addresses, Long firstPort, Long lastPort, PVSSite site) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.PVS_server.introduce";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(addresses), Marshalling.toXMLRPC(firstPort), Marshalling.toXMLRPC(lastPort), Marshalling.toXMLRPC(site)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * introduce new PVS server
     * First published in XenServer 7.1.
     *
     * @param addresses IPv4 addresses of the server
     * @param firstPort first UDP port accepted by this server
     * @param lastPort last UDP port accepted by this server
     * @param site PVS site this server is a part of
     * @return the new PVS server
     */
    public static PVSServer introduce(Connection c, Set<String> addresses, Long firstPort, Long lastPort, PVSSite site) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_server.introduce";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(addresses), Marshalling.toXMLRPC(firstPort), Marshalling.toXMLRPC(lastPort), Marshalling.toXMLRPC(site)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPVSServer(result);
    }

    /**
     * forget a PVS server
     * First published in XenServer 7.1.
     *
     * @return Task
     */
    public Task forgetAsync(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.PVS_server.forget";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * forget a PVS server
     * First published in XenServer 7.1.
     *
     */
    public void forget(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_server.forget";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Return a list of all the PVS_servers known to the system.
     * First published in XenServer 7.1.
     *
     * @return references to all objects
     */
    public static Set<PVSServer> getAll(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_server.get_all";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfPVSServer(result);
    }

    /**
     * Return a map of PVS_server references to PVS_server records for all PVS_servers known to the system.
     * First published in XenServer 7.1.
     *
     * @return records of all objects
     */
    public static Map<PVSServer, PVSServer.Record> getAllRecords(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_server.get_all_records";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toMapOfPVSServerPVSServerRecord(result);
    }

}