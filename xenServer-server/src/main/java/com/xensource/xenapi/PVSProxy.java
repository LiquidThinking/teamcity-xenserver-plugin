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
 * a proxy connects a VM/VIF with a PVS site
 * First published in XenServer 7.1.
 *
 * @author Citrix Systems, Inc.
 */
public class PVSProxy extends XenAPIObject {

    /**
     * The XenAPI reference (OpaqueRef) to this object.
     */
    protected final String ref;

    /**
     * For internal use only.
     */
    PVSProxy(String ref) {
       this.ref = ref;
    }

    /**
     * @return The XenAPI reference (OpaqueRef) to this object.
     */
    public String toWireString() {
       return this.ref;
    }

    /**
     * If obj is a PVSProxy, compares XenAPI references for equality.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj != null && obj instanceof PVSProxy)
        {
            PVSProxy other = (PVSProxy) obj;
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
     * Represents all the fields in a PVSProxy
     */
    public static class Record implements Types.Record {
        public String toString() {
            StringWriter writer = new StringWriter();
            PrintWriter print = new PrintWriter(writer);
            print.printf("%1$20s: %2$s\n", "uuid", this.uuid);
            print.printf("%1$20s: %2$s\n", "site", this.site);
            print.printf("%1$20s: %2$s\n", "VIF", this.VIF);
            print.printf("%1$20s: %2$s\n", "currentlyAttached", this.currentlyAttached);
            print.printf("%1$20s: %2$s\n", "status", this.status);
            return writer.toString();
        }

        /**
         * Convert a PVS_proxy.Record to a Map
         */
        public Map<String,Object> toMap() {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("uuid", this.uuid == null ? "" : this.uuid);
            map.put("site", this.site == null ? new PVSSite("OpaqueRef:NULL") : this.site);
            map.put("VIF", this.VIF == null ? new VIF("OpaqueRef:NULL") : this.VIF);
            map.put("currently_attached", this.currentlyAttached == null ? false : this.currentlyAttached);
            map.put("status", this.status == null ? Types.PvsProxyStatus.UNRECOGNIZED : this.status);
            return map;
        }

        /**
         * Unique identifier/object reference
         */
        public String uuid;
        /**
         * PVS site this proxy is part of
         */
        public PVSSite site;
        /**
         * VIF of the VM using the proxy
         */
        public VIF VIF;
        /**
         * true = VM is currently proxied
         */
        public Boolean currentlyAttached;
        /**
         * The run-time status of the proxy
         */
        public Types.PvsProxyStatus status;
    }

    /**
     * Get a record containing the current state of the given PVS_proxy.
     * First published in XenServer 7.1.
     *
     * @return all fields from the object
     */
    public PVSProxy.Record getRecord(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_proxy.get_record";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPVSProxyRecord(result);
    }

    /**
     * Get a reference to the PVS_proxy instance with the specified UUID.
     * First published in XenServer 7.1.
     *
     * @param uuid UUID of object to return
     * @return reference to the object
     */
    public static PVSProxy getByUuid(Connection c, String uuid) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_proxy.get_by_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(uuid)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPVSProxy(result);
    }

    /**
     * Get the uuid field of the given PVS_proxy.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public String getUuid(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_proxy.get_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the site field of the given PVS_proxy.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public PVSSite getSite(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_proxy.get_site";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPVSSite(result);
    }

    /**
     * Get the VIF field of the given PVS_proxy.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public VIF getVIF(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_proxy.get_VIF";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toVIF(result);
    }

    /**
     * Get the currently_attached field of the given PVS_proxy.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public Boolean getCurrentlyAttached(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_proxy.get_currently_attached";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toBoolean(result);
    }

    /**
     * Get the status field of the given PVS_proxy.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public Types.PvsProxyStatus getStatus(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_proxy.get_status";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPvsProxyStatus(result);
    }

    /**
     * Configure a VM/VIF to use a PVS proxy
     * First published in XenServer 7.1.
     *
     * @param site PVS site that we proxy for
     * @param VIF VIF for the VM that needs to be proxied
     * @return Task
     */
    public static Task createAsync(Connection c, PVSSite site, VIF VIF) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.PVS_proxy.create";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(site), Marshalling.toXMLRPC(VIF)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Configure a VM/VIF to use a PVS proxy
     * First published in XenServer 7.1.
     *
     * @param site PVS site that we proxy for
     * @param VIF VIF for the VM that needs to be proxied
     * @return the new PVS proxy
     */
    public static PVSProxy create(Connection c, PVSSite site, VIF VIF) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_proxy.create";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(site), Marshalling.toXMLRPC(VIF)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPVSProxy(result);
    }

    /**
     * remove (or switch off) a PVS proxy for this VM
     * First published in XenServer 7.1.
     *
     * @return Task
     */
    public Task destroyAsync(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.PVS_proxy.destroy";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * remove (or switch off) a PVS proxy for this VM
     * First published in XenServer 7.1.
     *
     */
    public void destroy(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_proxy.destroy";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Return a list of all the PVS_proxys known to the system.
     * First published in XenServer 7.1.
     *
     * @return references to all objects
     */
    public static Set<PVSProxy> getAll(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_proxy.get_all";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfPVSProxy(result);
    }

    /**
     * Return a map of PVS_proxy references to PVS_proxy records for all PVS_proxys known to the system.
     * First published in XenServer 7.1.
     *
     * @return records of all objects
     */
    public static Map<PVSProxy, PVSProxy.Record> getAllRecords(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_proxy.get_all_records";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toMapOfPVSProxyPVSProxyRecord(result);
    }

}