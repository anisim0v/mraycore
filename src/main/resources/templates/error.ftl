<#-- @ftlvariable name="exception" type="java.lang.Exception" -->
<#-- @ftlvariable name="status" type="java.lang.Number" -->
<#-- @ftlvariable name="message" type="java.lang.String" -->
<#include 'library/standardPage.ftl'>

<@standardPage title="Что-то пошло не так">
<p>Ошибка: ${status!}</p>
<p>${exception!}</p>
<p>${message!}</p>
</@standardPage>
