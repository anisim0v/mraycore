<#-- @ftlvariable name="familyTokens" type="ru.mray.core.model.FamilyToken[]" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="Family tokens">
<table border="1">
    <tr>
        <td>Token</td>
        <td>Region</td>
        <td>Family login</td>
        <td>Account id</td>
    </tr>
    <#list familyTokens as token>
        <tr>
            <td>${token.token}</td>
            <td>${token.region}</td>
            <td>${token.familyLogin}</td>
            <td>
                <#if token.account??>
                <a href="/admin/accounts/${token.account}">${token.account}</a>
                </#if>
            </td>
        </tr>
    </#list>
</table>
</@standardAdminPage>