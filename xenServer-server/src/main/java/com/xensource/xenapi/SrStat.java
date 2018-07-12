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
 * A set of high-level properties associated with an SR.
 *
 * @author Citrix Systems, Inc.
 */
public class SrStat extends XenAPIObject {

    /**
     * The XenAPI reference (OpaqueRef) to this object.
     */
    protected final String ref;

    /**
     * For internal use only.
     */
    SrStat(String ref) {
       this.ref = ref;
    }

    /**
     * @return The XenAPI reference (OpaqueRef) to this object.
     */
    public String toWireString() {
       return this.ref;
    }

    /**
     * If obj is a SrStat, compares XenAPI references for equality.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj != null && obj instanceof SrStat)
        {
            SrStat other = (SrStat) obj;
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
     * Represents all the fields in a SrStat
     */
    public static class Record implements Types.Record {
        public String toString() {
            StringWriter writer = new StringWriter();
            PrintWriter print = new PrintWriter(writer);
            print.printf("%1$20s: %2$s\n", "uuid", this.uuid);
            print.printf("%1$20s: %2$s\n", "nameLabel", this.nameLabel);
            print.printf("%1$20s: %2$s\n", "nameDescription", this.nameDescription);
            print.printf("%1$20s: %2$s\n", "freeSpace", this.freeSpace);
            print.printf("%1$20s: %2$s\n", "totalSpace", this.totalSpace);
            print.printf("%1$20s: %2$s\n", "clustered", this.clustered);
            print.printf("%1$20s: %2$s\n", "health", this.health);
            return writer.toString();
        }

        /**
         * Convert a sr_stat.Record to a Map
         */
        public Map<String,Object> toMap() {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("uuid", this.uuid == null ? null : this.uuid);
            map.put("name_label", this.nameLabel == null ? "" : this.nameLabel);
            map.put("name_description", this.nameDescription == null ? "" : this.nameDescription);
            map.put("free_space", this.freeSpace == null ? 0 : this.freeSpace);
            map.put("total_space", this.totalSpace == null ? 0 : this.totalSpace);
            map.put("clustered", this.clustered == null ? false : this.clustered);
            map.put("health", this.health == null ? Types.SrHealth.UNRECOGNIZED : this.health);
            return map;
        }

        /**
         * Uuid that uniquely identifies this SR, if one is available.
         * Experimental. First published in Unreleased.
         */
        public String uuid;
        /**
         * Short, human-readable label for the SR.
         * Experimental. First published in Unreleased.
         */
        public String nameLabel;
        /**
         * Longer, human-readable description of the SR. Descriptions are generally only displayed by clients when the user is examining SRs in detail.
         * Experimental. First published in Unreleased.
         */
        public String nameDescription;
        /**
         * Number of bytes free on the backing storage (in bytes)
         * Experimental. First published in Unreleased.
         */
        public Long freeSpace;
        /**
         * Total physical size of the backing storage (in bytes)
         * Experimental. First published in Unreleased.
         */
        public Long totalSpace;
        /**
         * Indicates whether the SR uses clustered local storage.
         * Experimental. First published in Unreleased.
         */
        public Boolean clustered;
        /**
         * The health status of the SR.
         * Experimental. First published in Unreleased.
         */
        public Types.SrHealth health;
    }

}