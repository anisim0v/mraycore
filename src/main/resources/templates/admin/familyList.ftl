<#-- @ftlvariable name="families" type="ru.mray.core.model.Family[]" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="Families">
<table border="1">
    <tr>
        <td>Login</td>
        <td>Region</td>
        <td>Password</td>
        <td>Paid until</td>
        <td>Street name</td>
        <td>Street number</td>
        <td>Zip code</td>
        <td>City</td>
    </tr>
    <#list families as family>
        <tr>
            <td>${family.login}</td>
            <td>${family.region}</td>
            <td>${family.password}</td>
            <td><a href="/admin/families/${family.login}/renew">${family.paidUntil}</a></td>
            <td>${family.streetName}</td>
            <td>${family.streetNumber}</td>
            <td>${family.zipCode}</td>
            <td>${family.city}</td>
        </tr>
    </#list>
</table>
</@standardAdminPage>