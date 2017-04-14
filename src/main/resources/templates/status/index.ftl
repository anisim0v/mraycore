<#-- @ftlvariable name="showRenewForm" type="java.lang.Boolean" -->
<#-- @ftlvariable name="transaction" type="ru.mray.core.model.Transaction" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Проверка состояния аккаунта">
<main>
    <div class="container">
        <form method="get" action="/status/byEmail">
            <label for="email">Ваш email в MusicRay: </label>
            <input type="email" id="email" name="email">
            <input type="submit" value="Продолжить"/>
        </form>
    </div>
</main>
</@standardPage>