<#-- @ftlvariable name="title" type="java.lang.String" -->
<#-- @ftlvariable name="accounts" type="ru.mray.core.model.Account[]" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="${title}">
<table border="1">
    <tr>
        <td>#</td>
        <td>Email</td>
        <td>Id</td>
        <td>Period</td>
        <td>Region</td>
        <td>Token</td>
        <td>Active until</td>
        <td>Renew notification sent at</td>
        <td>Registered at</td>
    </tr>
    <#list accounts as account>
        <tr>
            <td>${account?index}</td>
            <td>${account.email}</td>
            <td><a href="/admin/accounts/${account.id}">${account.id}</a></td>
            <td>${account.renewPeriod}</td>
            <td>${account.region}</td>
            <td>
                <#if account.familyToken??>
                    ${account.familyToken.id}
                </#if>
            </td>
            <td>${account.activeUntil!}</td>
            <td>${account.renewNotificationSentAt!}</td>
            <td>${account.registeredAt}</td>
        </tr>
    </#list>
</table>
</@standardAdminPage>