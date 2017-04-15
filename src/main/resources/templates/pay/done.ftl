<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<#-- @ftlvariable name="text" type="java.lang.String" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Оплачено!">
<main>
    <div class="container">
        <p>Спасибо, оплата прошла успешно. Информация в личном кабинете может обновиться с задержкой в пару минут, это нормально.</p>

        <p><a href="/status/${account.id}">Вернуться в личный кабинет</a></p>
    </div>
</main>
</@standardPage>