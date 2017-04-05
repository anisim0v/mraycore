<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<#-- @ftlvariable name="text" type="java.lang.String" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Продление подписки">
<div>Email: ${account.email}</div>
<div>ID: ${account.id}</div>
<div>ID токена в семье: ${account.familyToken!"Вы не присоединены к семье"}</div>
<div>Подписка активна до: ${account.activeUntil!"Вы пока еще не присоединены к семье. Дата окончания подписки появится позже"}</div>
<p>
<form method="post">
    <label for="renewPeriod">Продлить подписку на: </label>
    <select id="renewPeriod" name="renewPeriod">
        <option value="1">1 месяц</option>
        <option value="2">2 месяца</option>
    </select>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Продлить"/>
</form>
</@standardPage>