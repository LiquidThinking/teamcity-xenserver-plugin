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

public enum APIVersion
{
    API_1_1,
    API_1_2,
    API_1_3,
    API_1_4,
    API_1_5,
    API_1_6,
    API_1_7,
    API_1_8,
    API_1_9,
    API_1_10,
    API_2_0,
    API_2_1,
    API_2_2,
    API_2_3,
    API_2_4,
    API_2_5,
    API_2_6,
    API_2_7,
    API_2_8,
    API_2_9,
    API_2_10,
    UNKNOWN;

    public static APIVersion latest()
    {
        return API_2_10;
    }

    public static APIVersion fromMajorMinor(long major, long minor)
    {
        if (major == 1 && minor == 1)
            return API_1_1;
        if (major == 1 && minor == 2)
            return API_1_2;
        if (major == 1 && minor == 3)
            return API_1_3;
        if (major == 1 && minor == 4)
            return API_1_4;
        if (major == 1 && minor == 5)
            return API_1_5;
        if (major == 1 && minor == 6)
            return API_1_6;
        if (major == 1 && minor == 7)
            return API_1_7;
        if (major == 1 && minor == 8)
            return API_1_8;
        if (major == 1 && minor == 9)
            return API_1_9;
        if (major == 1 && minor == 10)
            return API_1_10;
        if (major == 2 && minor == 0)
            return API_2_0;
        if (major == 2 && minor == 1)
            return API_2_1;
        if (major == 2 && minor == 2)
            return API_2_2;
        if (major == 2 && minor == 3)
            return API_2_3;
        if (major == 2 && minor == 4)
            return API_2_4;
        if (major == 2 && minor == 5)
            return API_2_5;
        if (major == 2 && minor == 6)
            return API_2_6;
        if (major == 2 && minor == 7)
            return API_2_7;
        if (major == 2 && minor == 8)
            return API_2_8;
        if (major == 2 && minor == 9)
            return API_2_9;
        if (major == 2 && minor == 10)
            return API_2_10;
        return UNKNOWN;
    }

    @Override
    public String toString()
    {
        switch (this) {
            case API_1_1:
                return "1.1";
            case API_1_2:
                return "1.2";
            case API_1_3:
                return "1.3";
            case API_1_4:
                return "1.4";
            case API_1_5:
                return "1.5";
            case API_1_6:
                return "1.6";
            case API_1_7:
                return "1.7";
            case API_1_8:
                return "1.8";
            case API_1_9:
                return "1.9";
            case API_1_10:
                return "1.10";
            case API_2_0:
                return "2.0";
            case API_2_1:
                return "2.1";
            case API_2_2:
                return "2.2";
            case API_2_3:
                return "2.3";
            case API_2_4:
                return "2.4";
            case API_2_5:
                return "2.5";
            case API_2_6:
                return "2.6";
            case API_2_7:
                return "2.7";
            case API_2_8:
                return "2.8";
            case API_2_9:
                return "2.9";
            case API_2_10:
                return "2.10";
            default:
                return "Unknown";
        }
    }
}
