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
        <td>Total accounts: ${accountsCount}</td>
    </tr>
    <tr>
        <td>Active accounts: ${activeCount}</td>
    </tr>
    <tr>
        <td>Expired accounts: ${expiredCount}</td>
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
        <td><a href="/admin/accounts">Accounts</a></td>
    </tr>
    <tr>
        <td><a href="/admin/accounts/expired">Expired accounts</a></td>
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
        <td><a href="/admin/service/sendRenewNotifications">Send renew notifications</a></td>
    </tr>
    <tr>
        <td><a href="/admin/service/resetRenewNotifications">Reset renew notifications</a></td>
    </tr>
</table>
</@standardAdminPage>
