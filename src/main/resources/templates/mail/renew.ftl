<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
Привет, ${account.email}.

Кажется пришло время продлить подписку на Spotify. Как и всегда,
<a href="https://music-ray.ru/pay/renew/${account.id}">вот ваша ссылка на продление</a>.

Спасибо,что пользуетесь нами.
</body>
</html>