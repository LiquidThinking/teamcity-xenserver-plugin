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
 * Describes the SDN controller that is to connect with the pool
 * First published in XenServer 7.2.
 *
 * @author Citrix Systems, Inc.
 */
public class SDNController extends XenAPIObject {

    /**
     * The XenAPI reference (OpaqueRef) to this object.
     */
    protected final String ref;

    /**
     * For internal use only.
     */
    SDNController(String ref) {
       this.ref = ref;
    }

    /**
     * @return The XenAPI reference (OpaqueRef) to this object.
     */
    public String toWireString() {
       return this.ref;
    }

    /**
     * If obj is a SDNController, compares XenAPI references for equality.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj != null && obj instanceof SDNController)
        {
            SDNController other = (SDNController) obj;
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
     * Represents all the fields in a SDNController
     */
    public static class Record implements Types.Record {
        public String toString() {
            StringWriter writer = new StringWriter();
            PrintWriter print = new PrintWriter(writer);
            print.printf("%1$20s: %2$s\n", "uuid", this.uuid);
            print.printf("%1$20s: %2$s\n", "protocol", this.protocol);
            print.printf("%1$20s: %2$s\n", "address", this.address);
            print.printf("%1$20s: %2$s\n", "port", this.port);
            return writer.toString();
        }

        /**
         * Convert a SDN_controller.Record to a Map
         */
        public Map<String,Object> toMap() {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("uuid", this.uuid == null ? "" : this.uuid);
            map.put("protocol", this.protocol == null ? Types.SdnControllerProtocol.UNRECOGNIZED : this.protocol);
            map.put("address", this.address == null ? "" : this.address);
            map.put("port", this.port == null ? 0 : this.port);
            return map;
        }

        /**
         * Unique identifier/object reference
         */
        public String uuid;
        /**
         * Protocol to connect with SDN controller
         */
        public Types.SdnControllerProtocol protocol;
        /**
         * IP address of the controller
         */
        public String address;
        /**
         * TCP port of the controller
         */
        public Long port;
    }

    /**
     * Get a record containing the current state of the given SDN_controller.
     * First published in XenServer 7.2.
     *
     * @return all fields from the object
     */
    public SDNController.Record getRecord(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "SDN_controller.get_record";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSDNControllerRecord(result);
    }

    /**
     * Get a reference to the SDN_controller instance with the specified UUID.
     * First published in XenServer 7.2.
     *
     * @param uuid UUID of object to return
     * @return reference to the object
     */
    public static SDNController getByUuid(Connection c, String uuid) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "SDN_controller.get_by_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(uuid)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSDNController(result);
    }

    /**
     * Get the uuid field of the given SDN_controller.
     * First published in XenServer 7.2.
     *
     * @return value of the field
     */
    public String getUuid(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "SDN_controller.get_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the protocol field of the given SDN_controller.
     * First published in XenServer 7.2.
     *
     * @return value of the field
     */
    public Types.SdnControllerProtocol getProtocol(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "SDN_controller.get_protocol";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSdnControllerProtocol(result);
    }

    /**
     * Get the address field of the given SDN_controller.
     * First published in XenServer 7.2.
     *
     * @return value of the field
     */
    public String getAddress(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "SDN_controller.get_address";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the port field of the given SDN_controller.
     * First published in XenServer 7.2.
     *
     * @return value of the field
     */
    public Long getPort(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "SDN_controller.get_port";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toLong(result);
    }

    /**
     * Introduce an SDN controller to the pool.
     * First published in XenServer 7.2.
     *
     * @param protocol Protocol to connect with the controller.
     * @param address IP address of the controller.
     * @param port TCP port of the controller.
     * @return Task
     */
    public static Task introduceAsync(Connection c, Types.SdnControllerProtocol protocol, String address, Long port) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.SDN_controller.introduce";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(protocol), Marshalling.toXMLRPC(address), Marshalling.toXMLRPC(port)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Introduce an SDN controller to the pool.
     * First published in XenServer 7.2.
     *
     * @param protocol Protocol to connect with the controller.
     * @param address IP address of the controller.
     * @param port TCP port of the controller.
     * @return the introduced SDN controller
     */
    public static SDNController introduce(Connection c, Types.SdnControllerProtocol protocol, String address, Long port) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "SDN_controller.introduce";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(protocol), Marshalling.toXMLRPC(address), Marshalling.toXMLRPC(port)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSDNController(result);
    }

    /**
     * Remove the OVS manager of the pool and destroy the db record.
     * First published in XenServer 7.2.
     *
     * @return Task
     */
    public Task forgetAsync(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.SDN_controller.forget";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * Remove the OVS manager of the pool and destroy the db record.
     * First published in XenServer 7.2.
     *
     */
    public void forget(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "SDN_controller.forget";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Return a list of all the SDN_controllers known to the system.
     * First published in XenServer 7.2.
     *
     * @return references to all objects
     */
    public static Set<SDNController> getAll(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "SDN_controller.get_all";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfSDNController(result);
    }

    /**
     * Return a map of SDN_controller references to SDN_controller records for all SDN_controllers known to the system.
     * First published in XenServer 7.2.
     *
     * @return records of all objects
     */
    public static Map<SDNController, SDNController.Record> getAllRecords(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "SDN_controller.get_all_records";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toMapOfSDNControllerSDNControllerRecord(result);
    }

}