<#-- @ftlvariable name="family" type="ru.mray.core.model.Family" -->
<#-- @ftlvariable name="paidUntil" type="java.lang.String" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="Renew family">
<form method="POST" action="/admin/families/${family.id}/renew">
    <table>
        <tr>
            <td>
                Family login: ${family.login}
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