<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<p>Привет, ${account.email}.</p>

<p>Кажется, пришло время продлить подписку на Spotify. Она закончится ${account.activeUntil}</p>

<p>Вы можете сделать это в своем <a href="https://music-ray.ru/status/${account.id}">личном кабинете</a>.</p>

<p>Спасибо, что выбрали <a href="http://music-ray.ru">MusicRay</a></p>
</body>
</html>