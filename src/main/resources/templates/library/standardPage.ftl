<#macro standardPage title="">
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/main.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://use.fontawesome.com/205197df0c.js"></script>
    <script src="../js/main.js"></script>
</head>
<body>

<header>
    <a href="../" class="logo" data-scroll>MusicRay</a>
    <nav class="nav-collapse">
        <ul>
            <li class="menu-item"><a href="../" data-scroll>Главная</a></li>
            <li class="menu-item"><a href="../join" data-scroll>Купить</a></li>
            <li class="menu-item"><a href="../faq" data-scroll>Помощь</a></li>
        </ul>
    </nav>
</header>

    <#nested/>
<footer><div class="contacts"><a href="https://vk.com/spotify_musicray" target="_blank"><i class="fa fa-vk" aria-hidden="true"></i></a><a href="https://t.me/music_ray" style="margin-left:20px;" target="_blank"><i class="fa fa-telegram" aria-hidden="true"></i></a><a href="mailto:support@music-ray.ru" style="margin-left:20px;" target="_blank"><i class="fa fa-envelope" aria-hidden="true"></i><span class="mail"> support@music-ray.ru</span></a></footer>

<script>
</script>
</body>
</html>
</#macro>