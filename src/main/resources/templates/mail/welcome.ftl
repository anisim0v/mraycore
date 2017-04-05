<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<#-- @ftlvariable name="transactionId" type="java.lang.String" -->
<#-- @ftlvariable name="email" type="java.lang.String" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<p>Привет, ${account.email}.</p>

<p>Вот <a href="https://music-ray.ru/renew/${account.id}">обещанная ссылка на оплату</a>. В дальшейшем ее же можно будет
использовать для продления подписки.</p>
</body>
</html>