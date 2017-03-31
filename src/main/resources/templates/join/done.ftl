<#-- @ftlvariable name="email" type="java.lang.String" -->
<#-- @ftlvariable name="transactionId" type="java.lang.String" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Получилось!">
<p>Готово! Мы отправили письмо со ссылкой на оплату на <span>${email}</span></p>
<p>Кроме того, вы можете <a href="/pay/${transactionId}">оплатить подписку прямо сейчас</a></p>
</@standardPage>