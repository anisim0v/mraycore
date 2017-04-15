<#-- @ftlvariable name="token" type="ru.mray.core.model.FamilyToken" -->
<#-- @ftlvariable name="family" type="ru.mray.core.model.Family" -->
<#-- @ftlvariable name="account" type="ru.mray.core.model.Account" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<p>Привет, ${account.email}.</p>

<table border="0" cellspacing="5px">
    <tr>
        <td><b>Вот ваш инвайт:</b></td>
    </tr>
    <tr>
        <td>Ссылка</td>
        <td>
            <a href="https://www.spotify.com/int/family/redeem/?token=${token.token}">
                https://www.spotify.com/int/family/redeem/?token=${token.token}
            </a>
        </td>
    </tr>
    <tr>
        <td>Full name</td>
        <td>Имя и фамилия, обязательно латиницей. Можно ваши, можно случайные.</td>
    </tr>
    <tr>
        <td>Street name</td>
        <td>${family.streetName}</td>
    </tr>
    <tr>
        <td>Street number</td>
        <td>${family.streetNumber}</td>
    </tr>
    <tr>
        <td>Zip code</td>
        <td>${family.zipCode}</td>
    </tr>
    <tr>
        <td>City</td>
        <td>${family.city}</td>
    </tr>
</table>

<p>Спасибо, что выбрали <a href="http://music-ray.ru">MusicRay</a></p>
</body>
</html>