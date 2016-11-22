<%@ page import="com.spawtz.teamcity.xenServer.XenServerCloudParameters" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="paramUrl" value="<%=XenServerCloudParameters.SERVER%>"/>
<tr>
    <th><label for="${paramUrl}">Server IP: <l:star/></label></th>
    <td><props:textProperty name="${paramUrl}" className="longField"/>
        <span id="error_clouds.xenserver.server" class="error"></span>
    </td>
</tr>

<c:set var="paramIdentity" value="<%=XenServerCloudParameters.USER_NAME%>"/>
<tr>
    <th><label for="${paramIdentity}">User name: <l:star/></label></th>
    <td><props:textProperty name="${paramIdentity}" className="longField"/>
        <span id="error_clouds.xenserver.userName" class="error"></span>
    </td>
</tr>

<c:set var="paramPassword" value="<%=XenServerCloudParameters.PASSWORD%>"/>
<tr>
    <th><label for="${paramPassword}">Password: <l:star/></label></th>
    <td>
        <props:passwordProperty name="${paramPassword}" className="longField"/>
        <span id="error_clouds.xenserver.password" class="error"></span>
    </td>
</tr>

<c:set var="paramTemplate" value="<%=XenServerCloudParameters.TEMPLATE%>"/>
<tr>
    <th><label for="${paramTemplate}">Template Id: <l:star/></label></th>
    <td><props:textProperty name="${paramTemplate}" className="longField"/>
        <span id="error_clouds.xenserver.template" class="error"></span>
    </td>
</tr>
