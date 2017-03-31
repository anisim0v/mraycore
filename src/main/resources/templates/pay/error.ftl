<#-- @ftlvariable name="transactionId" type="java.lang.String" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Что-то пошло не так">
<span>Что-то пошло не так. <a href="/pay/${transactionId}">Попробуйте еще раз</a>.</span>
</@standardPage>