<#-- @ftlvariable name="familyTokens" type="ru.mray.core.model.FamilyToken[]" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="Family tokens">
<table border="1">
    <tr>
        <td>Family</td>
        <td>Region</td>
        <td>ID</td>
        <td>Slot (0-based)</td>
        <td>Assign manually</td>
        <td>Token</td>
        <td>Family paid until</td>
        <td>Account id</td>
    </tr>
    <#list familyTokens as token>
        <tr>
            <td><a href="/admin/families/${token.family}">${token.family}</a></td>
            <td>${token.region}</td>
            <td>${token.id}</td>
            <td>${token.slot}</td>
            <td>${token.assignManually?c}</td>
            <td>${token.token}</td>
            <td>${token.paidUntil}</td>
            <td>
                <#if token.account??>
                <a href="/admin/accounts/${token.account}">${token.account}</a>
                </#if>
            </td>
        </tr>
    </#list>
</table>
</@standardAdminPage>