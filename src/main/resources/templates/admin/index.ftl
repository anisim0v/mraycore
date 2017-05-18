<#-- @ftlvariable name="configRegistrationEnabled" type="boolean" -->
<#-- @ftlvariable name="retiredCount" type="java.lang.Number" -->
<#-- @ftlvariable name="expireIn1Day" type="java.lang.Number" -->
<#-- @ftlvariable name="expireIn10Days" type="java.lang.Number" -->
<#-- @ftlvariable name="expireIn3Days" type="java.lang.Number" -->
<#-- @ftlvariable name="accountsToNotifyCount" type="java.lang.Number" -->
<#-- @ftlvariable name="regionsStats" type="ru.mray.core.controller.admin.AdminIndexController.RegionStats[]" -->
<#-- @ftlvariable name="expiredCount" type="java.lang.Number" -->
<#-- @ftlvariable name="activeCount" type="java.lang.Number" -->
<#-- @ftlvariable name="tokenCountToAssign" type="java.lang.Number" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="unassignedTokens" type="java.lang.Number" -->
<#-- @ftlvariable name="pendingCount" type="java.lang.Number" -->
<#-- @ftlvariable name="accountsCount" type="java.lang.Number" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="Admin index">
<table>
    <tr>
        <td><a href="/admin/accounts">Total accounts: ${accountsCount}</a></td>
    </tr>
    <tr>
        <td><a href="/admin/accounts/active">Active accounts: ${activeCount}</a></td>
    </tr>
    <tr>
        <td><a href="/admin/accounts/expiring/10">Expire in 10 days: ${expireIn10Days}</a></td>
    </tr>
    <tr>
        <td><a href="/admin/accounts/expiring/3">Expire in 3 days: ${expireIn3Days}</a></td>
    </tr>
    <tr>
        <td><a href="/admin/accounts/expiring/1">Expire in 1 day: ${expireIn1Day}</a></td>
    </tr>
    <tr>
        <td><a href="/admin/accounts/expired">Expired accounts: ${expiredCount}</a></td>
    </tr>
    <tr>
        <td><a href="/admin/accounts/retired">Retired accounts: ${retiredCount}</a></td>
    </tr>
    <tr>
        <td>Pending renew notifications: ${accountsToNotifyCount}</td>
    </tr>
</table>

    <#list regionsStats as regionStats>
    <p>
    <table>
        <tr>
            <td>Pending accounts [${regionStats.region}]: ${regionStats.pendingCount}</td>
        </tr>
        <tr>
            <td>Unassigned tokens [${regionStats.region}]: ${regionStats.unassignedTokenCount}</td>
        </tr>
        <tr>
            <td>
                <form method="post" action="/admin/accounts/assignTokens/${regionStats.region}">
                    <label for="tokenCountToAssign">Token count to assign [${regionStats.region}]:</label>
                    <input id="tokenCountToAssign" name="tokenCountToAssign"
                           value="${regionStats.tokenCountToAssign}" type="number" min="1"
                           max="${regionStats.tokenCountToAssign}"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="Assign ${regionStats.region} tokens"/>
                </form>
            </td>
        </tr>
        <tr>
            <td><a href="/admin/accounts/pending/${regionStats.region}">Pending accounts [${regionStats.region}]</a></td>
        </tr>
    </table>
    </#list>

<p>
<table>
    <tr>
        <td><a href="/admin/families">Families</a></td>
    </tr>
    <tr>
        <td><a href="/admin/families/add">Add family</a></td>
    </tr>
    <tr>
        <td><a href="/admin/families/tokens">Family tokens</a></td>
    </tr>
</table>
<p>
<table>
    <tr>
        <td>Registration enabled:  <a href="/admin/service/config/toggle/registrationEnabled">${configRegistrationEnabled?c}</a></td>
    </tr>
</table>
</@standardAdminPage>
