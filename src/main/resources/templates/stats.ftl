<#-- @ftlvariable name="totalCount" type="java.lang.Number" -->
<#include 'library/standardPage.ftl'>
<#-- @ftlvariable name="regionsStats" type="ru.mray.core.controller.admin.AdminIndexController.RegionStats[]" -->
<#-- @ftlvariable name="activeCount" type="java.lang.Number" -->

<@standardPage title="Текущая статистика">
<main class="buy">
    <div class="container">
        <div class="row">
            <div class="twelwe columns" id="stats">
                <h3>Текущая статистика:</h3>
                <div>Зарегистрировано аккаунтов: ${totalCount}</div>
                <div>Активных аккаунтов: ${activeCount}</div>
                <#list regionsStats as regionStats>
                    <p>
                <div>Заявок в обработке [${regionStats.region}]: ${regionStats.pendingCount}</div>
                <div>Свободных токенов [${regionStats.region}]: ${regionStats.unassignedTokenCount}</div>
                </#list>
            </div>
        </div>
    </div>
</main>
</@standardPage>