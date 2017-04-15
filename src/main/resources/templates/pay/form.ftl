<#-- @ftlvariable name="paymentsEnabled" type="java.lang.Boolean" -->
<#-- @ftlvariable name="pendingAccounts" type="java.lang.Number" -->
<#-- @ftlvariable name="unassignedTokensCount" type="java.lang.Number" -->
<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<#-- @ftlvariable name="WMI_SIGNATURE" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_PAYMENT_NO" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_FAIL_URL" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_SUCCESS_URL" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_DESCRIPTION" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_CURRENCY_ID" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_PAYMENT_AMOUNT" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_MERCHANT_ID" type="java.lang.String" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Оплата подписки">
<main>

    <div class="container">
        <div>К оплате: ${WMI_PAYMENT_AMOUNT}</div>
        <br>
        <#if !account.familyToken??>
            <div>
                <#if unassignedTokensCount gt 0>
                    В данный момент у нас свободно слотов: ${unassignedTokensCount}. Если вы оплатите прямо сейчас,
                    то, вероятно, сразу же получите приглашение в семью.
                <#else>
                    К сожалению для выбранной страны у нас временно нет свободных слотов. Но если вы оплатите сейчас, то
                    одним из первых (в порядке очереди среди оплативших) получите приглашение, когда мы добавим новые
                    семьи.<br><br>
                    Количество человек в очереди перед вами: ${pendingAccounts}.<br><br>
                    Следить за наличием свободных слотов можно на <a href="/stats">этой странице</a>
                </#if>
            </div>
            <br>
        </#if>
        <form method="post" action="https://wl.walletone.com/checkout/checkout/Index">
            <input name="WMI_MERCHANT_ID" type="hidden" value="${WMI_MERCHANT_ID}"/>
            <input name="WMI_PAYMENT_AMOUNT" type="hidden" value="${WMI_PAYMENT_AMOUNT}"/>
            <input name="WMI_CURRENCY_ID" type="hidden" value="${WMI_CURRENCY_ID}"/>
            <input name="WMI_DESCRIPTION" type="hidden" value='${WMI_DESCRIPTION}'/>
            <input name="WMI_SUCCESS_URL" type="hidden" value="${WMI_SUCCESS_URL}"/>
            <input name="WMI_FAIL_URL" type="hidden" value="${WMI_FAIL_URL}"/>
            <input name="WMI_PAYMENT_NO" type="hidden" value="${WMI_PAYMENT_NO}"/>
            <input name="WMI_SIGNATURE" type="hidden" value="${WMI_SIGNATURE}"/>
            <input type="submit" <#if !paymentsEnabled>style="display: none"</#if> value="Оплатить">

            <#if !paymentsEnabled>
                <div>Оплата пока недоступна. Полноценный запуск новой платформы MusicRay состоится в ближайшее время.
                    Следите за новостями в <a href="https://t.me/music_ray">Telegram</a></div>
            </#if>
        </form>
    </div>
</main>
</@standardPage>