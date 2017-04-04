<#-- @ftlvariable name="tokenCountToAssign" type="java.lang.Number" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="unassignedTokens" type="java.lang.Number" -->
<#-- @ftlvariable name="pendingCount" type="java.lang.Number" -->
<#-- @ftlvariable name="accountsCount" type="java.lang.Number" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="Admin index">
<table>
    <tr>
        <td>Total accounts: ${accountsCount}</td>
    </tr>
    <tr>
        <td>Pending accounts: ${pendingCount}</td>
    </tr>
    <tr>
        <td>Unassigned tokens: ${unassignedTokens}</td>
    </tr>
</table>
<p>
<form>
    <label for="tokenCountToAssign">Token count to assign:</label>
    <input id="tokenCountToAssign" name="tokenCountToAssign"
           value="${tokenCountToAssign}" type="number" min="1" max="${tokenCountToAssign}"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Assign tokens"/>
</form>
<p>
<table>
    <tr>
        <td><a href="/admin/accounts">Accounts</a></td>
    </tr>
    <tr>
        <td><a href="/admin/accounts/pending">Pending accounts</a></td>
    </tr>
    <tr>
        <td><a href="/admin/families">Families</a></td>
    </tr>
    <tr>
        <td><a href="/admin/families/add">Add family</a></td>
    </tr>
    <tr>
        <td><a href="/admin/families/tokens">Family tokens</a></td>
    </tr>
    <tr>
        <td><a href="/admin/families/unlink">Unlink account</a></td>
    </tr>
</table>
</@standardAdminPage>
