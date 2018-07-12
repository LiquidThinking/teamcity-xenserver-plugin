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
 * machines serving blocks of data for provisioning VMs
 * First published in XenServer 7.1.
 *
 * @author Citrix Systems, Inc.
 */
public class PVSSite extends XenAPIObject {

    /**
     * The XenAPI reference (OpaqueRef) to this object.
     */
    protected final String ref;

    /**
     * For internal use only.
     */
    PVSSite(String ref) {
       this.ref = ref;
    }

    /**
     * @return The XenAPI reference (OpaqueRef) to this object.
     */
    public String toWireString() {
       return this.ref;
    }

    /**
     * If obj is a PVSSite, compares XenAPI references for equality.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj != null && obj instanceof PVSSite)
        {
            PVSSite other = (PVSSite) obj;
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
     * Represents all the fields in a PVSSite
     */
    public static class Record implements Types.Record {
        public String toString() {
            StringWriter writer = new StringWriter();
            PrintWriter print = new PrintWriter(writer);
            print.printf("%1$20s: %2$s\n", "uuid", this.uuid);
            print.printf("%1$20s: %2$s\n", "nameLabel", this.nameLabel);
            print.printf("%1$20s: %2$s\n", "nameDescription", this.nameDescription);
            print.printf("%1$20s: %2$s\n", "PVSUuid", this.PVSUuid);
            print.printf("%1$20s: %2$s\n", "cacheStorage", this.cacheStorage);
            print.printf("%1$20s: %2$s\n", "servers", this.servers);
            print.printf("%1$20s: %2$s\n", "proxies", this.proxies);
            return writer.toString();
        }

        /**
         * Convert a PVS_site.Record to a Map
         */
        public Map<String,Object> toMap() {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("uuid", this.uuid == null ? "" : this.uuid);
            map.put("name_label", this.nameLabel == null ? "" : this.nameLabel);
            map.put("name_description", this.nameDescription == null ? "" : this.nameDescription);
            map.put("PVS_uuid", this.PVSUuid == null ? "" : this.PVSUuid);
            map.put("cache_storage", this.cacheStorage == null ? new LinkedHashSet<PVSCacheStorage>() : this.cacheStorage);
            map.put("servers", this.servers == null ? new LinkedHashSet<PVSServer>() : this.servers);
            map.put("proxies", this.proxies == null ? new LinkedHashSet<PVSProxy>() : this.proxies);
            return map;
        }

        /**
         * Unique identifier/object reference
         */
        public String uuid;
        /**
         * a human-readable name
         */
        public String nameLabel;
        /**
         * a notes field containing human-readable description
         */
        public String nameDescription;
        /**
         * Unique identifier of the PVS site, as configured in PVS
         */
        public String PVSUuid;
        /**
         * The SR used by PVS proxy for the cache
         */
        public Set<PVSCacheStorage> cacheStorage;
        /**
         * The set of PVS servers in the site
         */
        public Set<PVSServer> servers;
        /**
         * The set of proxies associated with the site
         */
        public Set<PVSProxy> proxies;
    }

    /**
     * Get a record containing the current state of the given PVS_site.
     * First published in XenServer 7.1.
     *
     * @return all fields from the object
     */
    public PVSSite.Record getRecord(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.get_record";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPVSSiteRecord(result);
    }

    /**
     * Get a reference to the PVS_site instance with the specified UUID.
     * First published in XenServer 7.1.
     *
     * @param uuid UUID of object to return
     * @return reference to the object
     */
    public static PVSSite getByUuid(Connection c, String uuid) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.get_by_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(uuid)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPVSSite(result);
    }

    /**
     * Get all the PVS_site instances with the given label.
     * First published in XenServer 7.1.
     *
     * @param label label of object to return
     * @return references to objects with matching names
     */
    public static Set<PVSSite> getByNameLabel(Connection c, String label) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.get_by_name_label";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(label)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfPVSSite(result);
    }

    /**
     * Get the uuid field of the given PVS_site.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public String getUuid(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.get_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the name/label field of the given PVS_site.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public String getNameLabel(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.get_name_label";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the name/description field of the given PVS_site.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public String getNameDescription(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.get_name_description";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the PVS_uuid field of the given PVS_site.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public String getPVSUuid(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.get_PVS_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the cache_storage field of the given PVS_site.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public Set<PVSCacheStorage> getCacheStorage(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.get_cache_storage";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfPVSCacheStorage(result);
    }

    /**
     * Get the servers field of the given PVS_site.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public Set<PVSServer> getServers(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.get_servers";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfPVSServer(result);
    }

    /**
     * Get the proxies field of the given PVS_site.
     * First published in XenServer 7.1.
     *
     * @return value of the field
     */
    public Set<PVSProxy> getProxies(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.get_proxies";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfPVSProxy(result);
    }

    /**
     * Set the name/label field of the given PVS_site.
     * First published in XenServer 7.1.
     *
     * @param label New value to set
     */
    public void setNameLabel(Connection c, String label) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.set_name_label";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(label)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Set the name/description field of the given PVS_site.
     * First published in XenServer 7.1.
     *
     * @param description New value to set
     */
    public void setNameDescription(Connection c, String description) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.set_name_description";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(description)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Introduce new PVS site
     * First published in XenServer 7.1.
     *
     * @param nameLabel name of the PVS site
     * @param nameDescription description of the PVS site
     * @param PVSUuid unique identifier of the PVS site
     * @return Task
     */
    public static Task introduceAsync(Connection c, String nameLabel, String nameDescription, String PVSUuid) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.PVS_site.introduce";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(nameLabel), Marshalling.toXMLRPC(nameDescription), Marshalling.toXMLRPC(PVSUuid)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Introduce new PVS site
     * First published in XenServer 7.1.
     *
     * @param nameLabel name of the PVS site
     * @param nameDescription description of the PVS site
     * @param PVSUuid unique identifier of the PVS site
     * @return the new PVS site
     */
    public static PVSSite introduce(Connection c, String nameLabel, String nameDescription, String PVSUuid) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.introduce";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(nameLabel), Marshalling.toXMLRPC(nameDescription), Marshalling.toXMLRPC(PVSUuid)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toPVSSite(result);
    }

    /**
     * Remove a site's meta data
     * First published in XenServer 7.1.
     *
     * @return Task
     */
    public Task forgetAsync(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException,
       Types.PvsSiteContainsRunningProxies,
       Types.PvsSiteContainsServers {
        String method_call = "Async.PVS_site.forget";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Remove a site's meta data
     * First published in XenServer 7.1.
     *
     */
    public void forget(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException,
       Types.PvsSiteContainsRunningProxies,
       Types.PvsSiteContainsServers {
        String method_call = "PVS_site.forget";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Update the PVS UUID of the PVS site
     * First published in XenServer 7.1.
     *
     * @param value PVS UUID to be used
     * @return Task
     */
    public Task setPVSUuidAsync(Connection c, String value) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.PVS_site.set_PVS_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Update the PVS UUID of the PVS site
     * First published in XenServer 7.1.
     *
     * @param value PVS UUID to be used
     */
    public void setPVSUuid(Connection c, String value) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.set_PVS_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Return a list of all the PVS_sites known to the system.
     * First published in XenServer 7.1.
     *
     * @return references to all objects
     */
    public static Set<PVSSite> getAll(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.get_all";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfPVSSite(result);
    }

    /**
     * Return a map of PVS_site references to PVS_site records for all PVS_sites known to the system.
     * First published in XenServer 7.1.
     *
     * @return records of all objects
     */
    public static Map<PVSSite, PVSSite.Record> getAllRecords(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "PVS_site.get_all_records";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toMapOfPVSSitePVSSiteRecord(result);
    }

}