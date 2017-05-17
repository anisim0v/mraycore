<#-- @ftlvariable name="isAdmin" type="boolean" -->
<#-- @ftlvariable name="family" type="ru.mray.core.model.Family" -->
<#-- @ftlvariable name="queueSize" type="java.lang.Number" -->
<#-- @ftlvariable name="showRenewForm" type="java.lang.Boolean" -->
<#-- @ftlvariable name="paidTransaction" type="ru.mray.core.model.Transaction" -->
<#-- @ftlvariable name="unpaidTransaction" type="ru.mray.core.model.Transaction" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Состояние аккаунта">
<main>
    <div class="container">
        <div>Email: ${account.email}</div>
        <div>Регион: ${account.region}</div>
        <div>ID:
            <#if isAdmin>
                <a href="/admin/accounts/${account.id}">${account.id}</a>
            <#else>
                <span>${account.id}</span>
            </#if>
        </div>
        <div>
            Статус:
            <#if paidTransaction?? && paidTransaction.paidAt?? && !paidTransaction.activeSince??>
                Подписка оплачена, но приглашение еще не выслано. Вы получите его в порядке очереди.
                <br>Заявок в очереди перед вами: ${queueSize}
                <br>Текущая статистика проекта доступна на <a href="/stats">этой странице</a>
            <#elseif account.familyToken??>
                Подписка оплачена до ${account.activeUntil!"<неизвестно>"}. Приглашение было отправлено на почту.
            <#elseif !paidTransaction?? || !paidTransaction.paidAt??>
                Подписка не оплачена
            <#else>
                Неизвестно. Обратитесь в поддержку.
            </#if>
        </div>
        <#if showRenewForm>
            <br>
            <form method="post" action="/status/${account.id}/renew">
                <label for="renewPeriod">Продлить подписку на: </label>
                <select id="renewPeriod" name="renewPeriod">
                    <#if isAdmin>
                        <option value="0">Тестовый платеж</option>
                    </#if>
                    <option value="1">1 месяц</option>
                <#--<option value="2">2 месяца</option>-->
                </select>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Продлить"/>
            </form>
        <#else>
            <#if paidTransaction?? && paidTransaction.activeUntil??>
                <div>Продлить подписку можно будет когда до ее истечения останется менее 10 дней</div>
            </#if>
        </#if>

        <#if unpaidTransaction??>
            <table border="0" cellspacing="5px">
                <tr>
                    <td><b>Неоплаченная операция:</b></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Период</td>
                    <td>${unpaidTransaction.period.months} мес.</td>
                </tr>
                <tr>
                    <td>Ссылка для оплаты</td>
                    <td>
                        <a href="/pay/${unpaidTransaction.id}">Оплатить</a>
                    </td>
                </tr>
                <#if isAdmin>
                    <tr>
                        <td>Admin action</td>
                        <td>
                            <a href="/admin/transactions/${unpaidTransaction.id}/remove">Remove</a>
                        </td>
                    </tr>
                </#if>
                <tr>
                    <td>ID транзакции</td>
                    <td>${unpaidTransaction.id}</td>
                </tr>
            </table>
        </#if>


        <#if paidTransaction??>
            <table border="0" cellspacing="5px">
                <tr>
                    <td><b>Информация о последней оплате:</b></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Период</td>
                    <td>${paidTransaction.period.months} мес.</td>
                </tr>
                <tr>
                    <td>Дата оплаты</td>
                    <td>${paidTransaction.paidAt}</td>
                </tr>
                <tr>
                    <td>Дата начала действия</td>
                    <td>${paidTransaction.activeSince!"Появится после присоединения к семье"}</td>
                </tr>
                <tr>
                    <td>Дата окончания действия</td>
                    <td>${paidTransaction.activeUntil!"Появится после присоединения к семье"}</td>
                </tr>
                <tr>
                    <td>ID транзакции</td>
                    <td>${paidTransaction.id}</td>
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