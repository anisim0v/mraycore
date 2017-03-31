<#-- @ftlvariable name="provisioningCount" type="java.lang.Number" -->
<#-- @ftlvariable name="accountsCount" type="java.lang.Number" -->
<#include "library/standardPage.ftl" />

<@standardPage title="Welcome">
<div>
    <p>Всего аккаунтов: <span>${accountsCount}</span>.</p>
    <p>Запросов в обработке: <span>${provisioningCount}</span>.</p>
</div>
</@standardPage>