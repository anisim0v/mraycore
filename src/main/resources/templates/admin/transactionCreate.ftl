<#-- @ftlvariable name="defaultStartTime" type="java.lang.String" -->
<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="Create transaction">
Target account: <a href="/admin/accounts/${account.id}">${account.id}</a>
<form method="POST" action="/admin/accounts/${account.id}/createTransaction">
    <table>
        <tr>
            <td>
                <label for="transactionType">Type</label>
            </td>
            <td>
                <select name="type" id="transactionType">
                    <option value="BONUS">BONUS</option>
                    <option value="PAYMENT">PAYMENT</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <label for="period">Period</label>
            </td>
            <td>
                <input name="period" id="period" value="P0D">
            </td>
        </tr>
        <tr>
            <td>
                <label for="startInstant">Start instant</label>
            </td>
            <td>
                <input name="startInstant" id="startInstant" value="${defaultStartTime}" size="25">
            </td>
        </tr>
        <tr>
            <td>
                <label for="paid">Paid</label>
            </td>
            <td>
                <input type="checkbox" name="paid" id="paid" checked>
            </td>
        </tr>
        <tr>
            <td>
                <label for="forceRefresh">Force refresh</label>
            </td>
            <td>
                <input type="checkbox" name="forceRefresh" id="forceRefresh">
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