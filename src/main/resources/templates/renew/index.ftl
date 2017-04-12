<#-- @ftlvariable name="showRenewForm" type="java.lang.Boolean" -->
<#-- @ftlvariable name="transaction" type="ru.mray.core.model.Transaction" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Продление подписки">
<main>
    <div class="container">
<div>Email: ${account.email}</div>
<div>Регион: ${account.region}</div>
<div>ID: ${account.id}</div>
<div>ID токена в семье: ${account.familyToken!"Вы еще не присоединены к семье"}</div>
<div>Подписка активна до: ${account.activeUntil!"Дата окончания подписки появится после присоединения к семье"}</div>
<p>
    <#if showRenewForm>
    <form method="post">
        <label for="renewPeriod">Продлить подписку на: </label>
        <select id="renewPeriod" name="renewPeriod">
            <option value="1">1 месяц</option>
            <#--<option value="2">2 месяца</option>-->
        </select>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Продлить"/>
    </form>
    </#if>
<p>
    <#if transaction??>
    <table border="0" cellspacing="5px">
        <tr>
            <td><b>Последняя операция:</b></td>
        </tr>
        <tr>
            <td>Период</td>
            <td>${transaction.period.months} мес.</td>
        </tr>
        <tr>
            <td>Дата создания</td>
            <td>${transaction.issueDate}</td>
        </tr>
        <tr>
            <td>Дата оплаты</td>
            <td>
                <#if transaction.paidAt??>
                ${transaction.paidAt}
                <#else>
                    <span>Еще не оплачена</span>
                    <a href="/pay/${transaction.id}">Оплатить</a> <a href="/pay/cancel/${transaction.id}">Отменить</a>
                </#if>
            </td>
        </tr>
        <#if transaction.paidAt??>
            <tr>
                <td>Дата начала действия</td>
                <td>${transaction.activeSince!}</td>
            </tr>
            <tr>
                <td>Дата окончания действия</td>
                <td>${transaction.activeUntil!}</td>
            </tr>
        </#if>
        <tr>
            <td>ID</td>
            <td>${transaction.id}</td>
        </tr>
    </table>
    </#if>
    </div>
</main>
</@standardPage>