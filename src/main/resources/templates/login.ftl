<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#include 'library/standardPage.ftl'>

<@standardPage title="Log in">
<main>
    <div class="container">
        <h3>Log in</h3>
        <div class="row">
<form action="/login" method="post">
    <input type="text" id="username" name="username" autofocus="autofocus" placeholder="Login"/> <br/>
    <input type="password" id="password" name="password" placeholder="Password"/> <br/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Log in"/>
</form>
    </div></div>
</main>
</@standardPage>