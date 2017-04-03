<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="Unlink account">
<form method="POST" action="/admin/families/unlink">
    <table>
        <tr>
            <td>
                <label for="email">Account email (to unlink)</label>
                <input required name="email" id="email"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="newToken">New token</label>
                <input required name="newToken" id="newToken"/>
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