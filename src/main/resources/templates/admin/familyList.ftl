<#-- @ftlvariable name="families" type="ru.mray.core.model.Family[]" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="Families">
<p><a href="/admin/families/add">Add family</a></p>
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
            <td><a href="/admin/families/${family.id}">${family.login}</a></td>
            <td>${family.region}</td>
            <td>${family.password}</td>
            <td><a href="/admin/families/${family.id}/renew">${family.paidUntil}</a></td>
            <td>${family.streetName}</td>
            <td>${family.streetNumber}</td>
            <td>${family.zipCode}</td>
            <td>${family.city}</td>
        </tr>
    </#list>
</table>
</@standardAdminPage>