<#include 'library/standardPage.ftl'>

<@standardPage title="Log in">
<form action="/login" method="post">
    <label for="username">Username</label>:
    <input type="text" id="username" name="username" autofocus="autofocus"/> <br/>
    <label for="password">Password</label>:
    <input type="password" id="password" name="password"/> <br/>
    <input type="submit" value="Log in"/>
</form>
</@standardPage>