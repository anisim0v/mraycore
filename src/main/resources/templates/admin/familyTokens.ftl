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
            <td>${token.account!}</td>
        </tr>
    </#list>
</table>
</@standardAdminPage>