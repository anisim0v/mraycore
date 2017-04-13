<#-- @ftlvariable name="email" type="java.lang.String" -->
<#-- @ftlvariable name="transactionId" type="java.lang.String" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Получилось!">
<main>
    <div class="container">
<p>Готово! Мы отправили письмо со ссылкой на личный кабинет на ${email}</p>
<p>Вы можете <a href="/pay/${transactionId}">оплатить свою подписку прямо сейчас</a> или сделать это позже,перейдя по ссылке в письме</p>
    </div>
</main>
</@standardPage>