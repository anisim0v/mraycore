<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<#-- @ftlvariable name="text" type="java.lang.String" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Оплачено!">
<main>
    <div class="container">
        <p>Спасибо, оплата прошла успешно.</p>
        <p>Если вы еще не были присоединены к семье и у нас есть свободные слоты - в течение нескольких минут вы
            получите инвайт на почту.
            В противном случае вам придется совсем немного подождать.</p>
        <p>Посмотреть информацию о своем аккаунте в MusicRay можно
            <a href="/renew/${account.id}">здесь</a>
        </p>
    </div>
</main>
</@standardPage>