<#-- @ftlvariable name="exception" type="java.lang.Exception" -->
<#-- @ftlvariable name="status" type="java.lang.Number" -->
<#-- @ftlvariable name="message" type="java.lang.String" -->
<#include 'library/standardPage.ftl'>

<@standardPage title="Что-то пошло не так">
<main>
    <div class="container">
        <p>Ошибка: ${message}</p>
        <p>Exception: ${exception!}</p>
        <p>Status: ${status!}</p>
    </div>
</main>
</@standardPage>
