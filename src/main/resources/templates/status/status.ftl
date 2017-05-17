<#-- @ftlvariable name="family" type="ru.mray.core.model.Family" -->
<#-- @ftlvariable name="queueSize" type="java.lang.Number" -->
<#-- @ftlvariable name="showRenewForm" type="java.lang.Boolean" -->
<#-- @ftlvariable name="transaction" type="ru.mray.core.model.Transaction" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Состояние аккаунта">
<main>
    <div class="container">
        <div>Email: ${account.email}</div>
        <div>Регион: ${account.region}</div>
        <div>ID: ${account.id}</div>
        <div>
            Статус:
            <#if transaction?? && transaction.paidAt?? && !transaction.activeSince??>
                Подписка оплачена, но приглашение еще не выслано. Вы получите его в порядке очереди.
                <br>Заявок в очереди перед вами: ${queueSize}
                <br>Текущая статистика проекта доступна на <a href="/stats">этой странице</a>
            <#elseif account.familyToken??>
                Подписка оплачена до ${account.activeUntil!"<неизвестно>"}. Приглашение было отправлено на почту.
            <#elseif !transaction?? || !transaction.paidAt??>
                Подписка не оплачена
            <#else>
                Неизвестно. Обратитесь в поддержку.
            </#if>
        </div>
        <#if account.familyToken??>
            <div>ID токена в семье: ${account.familyToken.id}</div>
        </#if>
        <#if showRenewForm>
            <br>
            <form method="post" action="/status/${account.id}/renew">
                <label for="renewPeriod">Продлить подписку на: </label>
                <select id="renewPeriod" name="renewPeriod">
                    <option value="1">1 месяц</option>
                <#--<option value="2">2 месяца</option>-->
                </select>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Продлить"/>
            </form>
        <#else>
            <#if transaction?? && transaction.activeUntil??>
                <div>Продлить подписку можно будет когда до ее истечения останется менее 10 дней</div>
            </#if>
        </#if>

        <#if transaction??>
            <table border="0" cellspacing="5px">
                <tr>
                    <td><b>Последняя операция:</b></td>
                    <td></td>
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
                            <a href="/pay/${transaction.id}">Оплатить</a>
                        <#--<a href="/pay/cancel/${transaction.id}">Отменить</a>-->
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

        <#if family??>
            <table border="0" cellspacing="5px">
                <tr>
                    <td><b>Информация о семье:</b></td>
                </tr>
                <tr>
                    <td>Ссылка</td>
                    <td>Была выслана вам на почту. При необходимости напишите нам в поддержку, отправим повторно.</td>
                </tr>
                <tr>
                    <td>Full name</td>
                    <td>Имя и фамилия, обязательно латиницей. Можно ваши, можно случайные.</td>
                </tr>
                <tr>
                    <td>Street name</td>
                    <td>${family.streetName}</td>
                </tr>
                <tr>
                    <td>Street number</td>
                    <td>${family.streetNumber}</td>
                </tr>
                <tr>
                    <td>Zip code</td>
                    <td>${family.zipCode}</td>
                </tr>
                <tr>
                    <td>City</td>
                    <td>${family.city}</td>
                </tr>
            </table>
        </#if>
    </div>
</main>
</@standardPage>