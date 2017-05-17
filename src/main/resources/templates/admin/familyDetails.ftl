<#-- @ftlvariable name="family" type="ru.mray.core.model.Family" -->
<#-- @ftlvariable name="familyTokens" type="ru.mray.core.model.FamilyToken[]" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="Family details">
<table border="1">
    <tr>
        <td>Login</td>
        <td>${family.login}</td>
    </tr>
    <tr>
        <td>Region</td>
        <td>${family.region}</td>
    </tr>
    <tr>
        <td>Password</td>
        <td>${family.password}</td>
    </tr>
    <tr>
        <td>Paid until</td>
        <td><a href="/admin/families/${family.id}/renew">${family.paidUntil}</a></td>
    </tr>
    <tr>
        <td>Street name</td>
        <td>${family.streetName}</td>
    </tr>
    <tr>
        <td>Street number</td>
        <td>${family.streetNumber}</td>
    </tr>
    <tr>
        <td>Zip code</td>
        <td>${family.zipCode}</td>
    </tr>
    <tr>
        <td>City</td>
        <td>${family.city}</td>
    </tr>
</table>
<p>
<table border="1">
    <tr>
        <td>ID</td>
        <td>Token</td>
        <td>Account id</td>
        <td>Assign manually</td>
    </tr>
    <#list familyTokens as token>
        <tr>
            <td>${token.id}</td>
            <td>${token.token}</td>
            <td>
                <#if token.account??>
                <a href="/admin/accounts/${token.account.id}">${token.account.id}</a>
                </#if>
            </td>
            <td><a href="/admin/familyTokens/${token.id}/setAssignManually?enabled=${(!token.assignManually)?c}">${token.assignManually?c}</a></td>
        </tr>
    </#list>
</table>
</@standardAdminPage>