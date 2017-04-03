<#-- @ftlvariable name="paidUntil" type="java.lang.String" -->
<#-- @ftlvariable name="familyLogin" type="java.lang.String" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="Renew family">
<form method="POST" action="/admin/families/${familyLogin}/renew">
    <table>
        <tr>
            <td>
                Family login: ${familyLogin}
            </td>
        </tr>
        <tr>
            <td>
                <label for="dateUntil">Renew until</label>
                <input required name="paidUntil" type="date"id="paidUntil" value="${paidUntil}"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="button" id="submit">Выполнить</button>
            </td>
        </tr>
    </table>
</form>
</@standardAdminPage>