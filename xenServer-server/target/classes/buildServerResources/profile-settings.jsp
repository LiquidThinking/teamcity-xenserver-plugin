<%@ page import="com.spawtz.teamcity.xenServer.XenServerCloudParameters" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="paramUrl" value="<%=XenServerCloudParameters.SERVER%>"/>
<tr>
    <th><label for="${paramUrl}">Server IP: <l:star/></label></th>
    <td><props:textProperty name="${paramUrl}" className="longField"/>
    </td>
</tr>

<c:set var="paramIdentity" value="<%=XenServerCloudParameters.USER_NAME%>"/>
<tr>
    <th><label for="${paramIdentity}">User name: <l:star/></label></th>
    <td><props:textProperty name="${paramIdentity}" className="longField"/>
    </td>
</tr>

<c:set var="paramPassword" value="<%=XenServerCloudParameters.PASSWORD%>"/>
<tr>
    <th><label for="${paramPassword}">Password: <l:star/></label></th>
    <td><props:passwordProperty name="${paramPassword}" className="longField"/>
    </td>
</tr>

<tr>
<div>
    <button class="btn" id="xenServerCheckCredentialsButton" type="button" onclick="return checkCredentialsFetchOptions();">Check Credentials / Fetch options</button>
</div>
</tr>

<script type="text/javascript">
    function checkCredentialsFetchOptions(){
        var button = document.querySelector("#xenServerCheckCredentialsButton");
        button.disabled = true;
        button.value = "Checking..."
        alert("Clicked");
        button.disabled = false;
        button.value = "Check Credentials / Fetch options"
        return false;
    }
</script>