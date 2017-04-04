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
