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
 * A group of compatible GPUs across the resource pool
 * First published in XenServer 6.0.
 *
 * @author Citrix Systems, Inc.
 */
public class GPUGroup extends XenAPIObject {

    /**
     * The XenAPI reference (OpaqueRef) to this object.
     */
    protected final String ref;

    /**
     * For internal use only.
     */
    GPUGroup(String ref) {
       this.ref = ref;
    }

    /**
     * @return The XenAPI reference (OpaqueRef) to this object.
     */
    public String toWireString() {
       return this.ref;
    }

    /**
     * If obj is a GPUGroup, compares XenAPI references for equality.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj != null && obj instanceof GPUGroup)
        {
            GPUGroup other = (GPUGroup) obj;
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
     * Represents all the fields in a GPUGroup
     */
    public static class Record implements Types.Record {
        public String toString() {
            StringWriter writer = new StringWriter();
            PrintWriter print = new PrintWriter(writer);
            print.printf("%1$20s: %2$s\n", "uuid", this.uuid);
            print.printf("%1$20s: %2$s\n", "nameLabel", this.nameLabel);
            print.printf("%1$20s: %2$s\n", "nameDescription", this.nameDescription);
            print.printf("%1$20s: %2$s\n", "PGPUs", this.PGPUs);
            print.printf("%1$20s: %2$s\n", "VGPUs", this.VGPUs);
            print.printf("%1$20s: %2$s\n", "GPUTypes", this.GPUTypes);
            print.printf("%1$20s: %2$s\n", "otherConfig", this.otherConfig);
            print.printf("%1$20s: %2$s\n", "allocationAlgorithm", this.allocationAlgorithm);
            print.printf("%1$20s: %2$s\n", "supportedVGPUTypes", this.supportedVGPUTypes);
            print.printf("%1$20s: %2$s\n", "enabledVGPUTypes", this.enabledVGPUTypes);
            return writer.toString();
        }

        /**
         * Convert a GPU_group.Record to a Map
         */
        public Map<String,Object> toMap() {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("uuid", this.uuid == null ? "" : this.uuid);
            map.put("name_label", this.nameLabel == null ? "" : this.nameLabel);
            map.put("name_description", this.nameDescription == null ? "" : this.nameDescription);
            map.put("PGPUs", this.PGPUs == null ? new LinkedHashSet<PGPU>() : this.PGPUs);
            map.put("VGPUs", this.VGPUs == null ? new LinkedHashSet<VGPU>() : this.VGPUs);
            map.put("GPU_types", this.GPUTypes == null ? new LinkedHashSet<String>() : this.GPUTypes);
            map.put("other_config", this.otherConfig == null ? new HashMap<String, String>() : this.otherConfig);
            map.put("allocation_algorithm", this.allocationAlgorithm == null ? Types.AllocationAlgorithm.UNRECOGNIZED : this.allocationAlgorithm);
            map.put("supported_VGPU_types", this.supportedVGPUTypes == null ? new LinkedHashSet<VGPUType>() : this.supportedVGPUTypes);
            map.put("enabled_VGPU_types", this.enabledVGPUTypes == null ? new LinkedHashSet<VGPUType>() : this.enabledVGPUTypes);
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
         * List of pGPUs in the group
         */
        public Set<PGPU> PGPUs;
        /**
         * List of vGPUs using the group
         */
        public Set<VGPU> VGPUs;
        /**
         * List of GPU types (vendor+device ID) that can be in this group
         */
        public Set<String> GPUTypes;
        /**
         * Additional configuration
         */
        public Map<String, String> otherConfig;
        /**
         * Current allocation of vGPUs to pGPUs for this group
         * First published in XenServer 6.2 SP1 Tech-Preview.
         */
        public Types.AllocationAlgorithm allocationAlgorithm;
        /**
         * vGPU types supported on at least one of the pGPUs in this group
         * First published in XenServer 6.2 SP1.
         */
        public Set<VGPUType> supportedVGPUTypes;
        /**
         * vGPU types supported on at least one of the pGPUs in this group
         * First published in XenServer 6.2 SP1.
         */
        public Set<VGPUType> enabledVGPUTypes;
    }

    /**
     * Get a record containing the current state of the given GPU_group.
     * First published in XenServer 6.0.
     *
     * @return all fields from the object
     */
    public GPUGroup.Record getRecord(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_record";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toGPUGroupRecord(result);
    }

    /**
     * Get a reference to the GPU_group instance with the specified UUID.
     * First published in XenServer 6.0.
     *
     * @param uuid UUID of object to return
     * @return reference to the object
     */
    public static GPUGroup getByUuid(Connection c, String uuid) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_by_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(uuid)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toGPUGroup(result);
    }

    /**
     * Get all the GPU_group instances with the given label.
     * First published in XenServer 6.0.
     *
     * @param label label of object to return
     * @return references to objects with matching names
     */
    public static Set<GPUGroup> getByNameLabel(Connection c, String label) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_by_name_label";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(label)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfGPUGroup(result);
    }

    /**
     * Get the uuid field of the given GPU_group.
     * First published in XenServer 6.0.
     *
     * @return value of the field
     */
    public String getUuid(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_uuid";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the name/label field of the given GPU_group.
     * First published in XenServer 6.0.
     *
     * @return value of the field
     */
    public String getNameLabel(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_name_label";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the name/description field of the given GPU_group.
     * First published in XenServer 6.0.
     *
     * @return value of the field
     */
    public String getNameDescription(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_name_description";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toString(result);
    }

    /**
     * Get the PGPUs field of the given GPU_group.
     * First published in XenServer 6.0.
     *
     * @return value of the field
     */
    public Set<PGPU> getPGPUs(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_PGPUs";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfPGPU(result);
    }

    /**
     * Get the VGPUs field of the given GPU_group.
     * First published in XenServer 6.0.
     *
     * @return value of the field
     */
    public Set<VGPU> getVGPUs(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_VGPUs";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfVGPU(result);
    }

    /**
     * Get the GPU_types field of the given GPU_group.
     * First published in XenServer 6.0.
     *
     * @return value of the field
     */
    public Set<String> getGPUTypes(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_GPU_types";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfString(result);
    }

    /**
     * Get the other_config field of the given GPU_group.
     * First published in XenServer 6.0.
     *
     * @return value of the field
     */
    public Map<String, String> getOtherConfig(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_other_config";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toMapOfStringString(result);
    }

    /**
     * Get the allocation_algorithm field of the given GPU_group.
     * First published in XenServer 6.2 SP1 Tech-Preview.
     *
     * @return value of the field
     */
    public Types.AllocationAlgorithm getAllocationAlgorithm(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_allocation_algorithm";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toAllocationAlgorithm(result);
    }

    /**
     * Get the supported_VGPU_types field of the given GPU_group.
     * First published in XenServer 6.2 SP1.
     *
     * @return value of the field
     */
    public Set<VGPUType> getSupportedVGPUTypes(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_supported_VGPU_types";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfVGPUType(result);
    }

    /**
     * Get the enabled_VGPU_types field of the given GPU_group.
     * First published in XenServer 6.2 SP1.
     *
     * @return value of the field
     */
    public Set<VGPUType> getEnabledVGPUTypes(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_enabled_VGPU_types";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfVGPUType(result);
    }

    /**
     * Set the name/label field of the given GPU_group.
     * First published in XenServer 6.0.
     *
     * @param label New value to set
     */
    public void setNameLabel(Connection c, String label) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.set_name_label";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(label)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Set the name/description field of the given GPU_group.
     * First published in XenServer 6.0.
     *
     * @param description New value to set
     */
    public void setNameDescription(Connection c, String description) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.set_name_description";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(description)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Set the other_config field of the given GPU_group.
     * First published in XenServer 6.0.
     *
     * @param otherConfig New value to set
     */
    public void setOtherConfig(Connection c, Map<String, String> otherConfig) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.set_other_config";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(otherConfig)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Add the given key-value pair to the other_config field of the given GPU_group.
     * First published in XenServer 6.0.
     *
     * @param key Key to add
     * @param value Value to add
     */
    public void addToOtherConfig(Connection c, String key, String value) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.add_to_other_config";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(key), Marshalling.toXMLRPC(value)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Remove the given key and its corresponding value from the other_config field of the given GPU_group.  If the key is not in that Map, then do nothing.
     * First published in XenServer 6.0.
     *
     * @param key Key to remove
     */
    public void removeFromOtherConfig(Connection c, String key) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.remove_from_other_config";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(key)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * Set the allocation_algorithm field of the given GPU_group.
     * First published in XenServer 6.2 SP1 Tech-Preview.
     *
     * @param allocationAlgorithm New value to set
     */
    public void setAllocationAlgorithm(Connection c, Types.AllocationAlgorithm allocationAlgorithm) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.set_allocation_algorithm";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(allocationAlgorithm)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * 
     * First published in XenServer 6.0.
     *
     * @param nameLabel 
     * @param nameDescription 
     * @param otherConfig 
     * @return Task
     */
    public static Task createAsync(Connection c, String nameLabel, String nameDescription, Map<String, String> otherConfig) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.GPU_group.create";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(nameLabel), Marshalling.toXMLRPC(nameDescription), Marshalling.toXMLRPC(otherConfig)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * 
     * First published in XenServer 6.0.
     *
     * @param nameLabel 
     * @param nameDescription 
     * @param otherConfig 
     * @return 
     */
    public static GPUGroup create(Connection c, String nameLabel, String nameDescription, Map<String, String> otherConfig) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.create";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(nameLabel), Marshalling.toXMLRPC(nameDescription), Marshalling.toXMLRPC(otherConfig)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toGPUGroup(result);
    }

    /**
     * 
     * First published in XenServer 6.0.
     *
     * @return Task
     */
    public Task destroyAsync(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.GPU_group.destroy";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * 
     * First published in XenServer 6.0.
     *
     */
    public void destroy(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.destroy";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref)};
        Map response = c.dispatch(method_call, method_params);
        return;
    }

    /**
     * 
     * First published in XenServer 6.2 SP1 Tech-Preview.
     *
     * @param vgpuType The VGPU_type for which the remaining capacity will be calculated
     * @return Task
     */
    public Task getRemainingCapacityAsync(Connection c, VGPUType vgpuType) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "Async.GPU_group.get_remaining_capacity";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(vgpuType)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
        return Types.toTask(result);
    }

    /**
     * 
     * First published in XenServer 6.2 SP1 Tech-Preview.
     *
     * @param vgpuType The VGPU_type for which the remaining capacity will be calculated
     * @return The number of VGPUs of the given type which can still be started on the PGPUs in the group
     */
    public Long getRemainingCapacity(Connection c, VGPUType vgpuType) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_remaining_capacity";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(vgpuType)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toLong(result);
    }

    /**
     * Return a list of all the GPU_groups known to the system.
     * First published in XenServer 6.0.
     *
     * @return references to all objects
     */
    public static Set<GPUGroup> getAll(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_all";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toSetOfGPUGroup(result);
    }

    /**
     * Return a map of GPU_group references to GPU_group records for all GPU_groups known to the system.
     * First published in XenServer 6.0.
     *
     * @return records of all objects
     */
    public static Map<GPUGroup, GPUGroup.Record> getAllRecords(Connection c) throws
       BadServerResponse,
       XenAPIException,
       XmlRpcException {
        String method_call = "GPU_group.get_all_records";
        String session = c.getSessionReference();
        Object[] method_params = {Marshalling.toXMLRPC(session)};
        Map response = c.dispatch(method_call, method_params);
        Object result = response.get("Value");
            return Types.toMapOfGPUGroupGPUGroupRecord(result);
    }

}