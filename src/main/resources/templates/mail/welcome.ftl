<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<#-- @ftlvariable name="transactionId" type="java.lang.String" -->
<#-- @ftlvariable name="email" type="java.lang.String" -->
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<p>Привет, ${account.email}.</p>

<p>Вот <a href="https://music-ray.ru/status/${account.id}">ссылка на ваш личный кабинет.</a> В нем вы можете посмотреть
    информацию о состоянии своей подписки, а так же продлить ее</p>

<p>Спасибо, что пользуетесь нами,<br><a href="http://music-ray.ru">MusicRay</a></p>
</body>
</html>