<#-- @ftlvariable name="WMI_SIGNATURE" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_PAYMENT_NO" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_FAIL_URL" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_SUCCESS_URL" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_DESCRIPTION" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_CURRENCY_ID" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_PAYMENT_AMOUNT" type="java.lang.String" -->
<#-- @ftlvariable name="WMI_MERCHANT_ID" type="java.lang.String" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Что-то пошло не так">
<div>
    <span>К оплате: ${WMI_PAYMENT_AMOUNT}</span>
    <form method="post" action="https://wl.walletone.com/checkout/checkout/Index">
        <input name="WMI_MERCHANT_ID" type="hidden" value="${WMI_MERCHANT_ID}"/>
        <input name="WMI_PAYMENT_AMOUNT" type="hidden" value="${WMI_PAYMENT_AMOUNT}"/>
        <input name="WMI_CURRENCY_ID" type="hidden" value="${WMI_CURRENCY_ID}"/>
        <input name="WMI_DESCRIPTION" type="hidden" value='${WMI_DESCRIPTION}'/>
        <input name="WMI_SUCCESS_URL" type="hidden" value="${WMI_SUCCESS_URL}"/>
        <input name="WMI_FAIL_URL" type="hidden" value="${WMI_FAIL_URL}"/>
        <input name="WMI_PAYMENT_NO" type="hidden" value="${WMI_PAYMENT_NO}"/>
        <input name="WMI_SIGNATURE" type="hidden" value="${WMI_SIGNATURE}"/>
        <input type="submit"/>
    </form>
</div>
</@standardPage>