<#-- @ftlvariable name="transactionId" type="java.lang.String" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Что-то пошло не так">
<main>
<span>Что-то пошло не так. <a href="/pay/${transactionId}">Попробуйте еще раз</a>.</span>
</main>
</@standardPage>