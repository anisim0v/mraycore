<#-- @ftlvariable name="token" type="ru.mray.core.model.FamilyToken" -->
<#-- @ftlvariable name="family" type="ru.mray.core.model.Family" -->
<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="Assign token to account">
<form method="POST" action="/admin/accounts/${account.id}/unlink">
    <table>
        <tr>
            <td>
                Account: <a href="/admin/accounts/${account.id}">${account.id}</a>
            </td>
        </tr>
        <tr>
            <td>
                Account email: ${account.email}
            </td>
        </tr>
        <tr>
            <td>
                <label for="token">Family token ID</label>
                <input required name="token" id="token"/>
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