<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Регистрация">
<form method="POST" th:action="@{/join}">
    <div>
        <label for="email">email</label>
        <input type="email" name="email" id="email" placeholder="Email" required/>
    </div>
    <div>
        <label for="region">Страна </label>
        <select id="region" name="region">
            <option value="PH" selected>Филиппины</option>
            <option value="US">США</option>
        </select>
    </div>
    <div>
        <label for="period">Период оплаты</label>
        <select id="period" name="period">
            <option value="1">Один месяц</option>
        </select>
    </div>
    <div>
        <input type="checkbox" id="agree" required/>
        <label for="agree" class="agree">Я подтверждаю, что мой аккаунт Spotify зарегистрирован в выбранной стране.</label>
    </div>
    <div>
        <input type="checkbox" id="twiceayear" required/>
        <label for="twiceayear">Я подтверждаю, что осведомлён о том, что менять семью в Spotify можно не более раза в
            год</label>
    </div>
    <div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="button" id="submit">Попробовать!</button>
    </div>
</form>
</@standardPage>
