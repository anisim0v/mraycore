<#macro standardPage title="">
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="apple-touch-icon" sizes="180x180" href="favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" href="favicon/favicon-32x32.png" sizes="32x32">
    <link rel="icon" type="image/png" href="favicon/favicon-16x16.png" sizes="16x16">
    <link rel="manifest" href="favicon/manifest.json">
    <link rel="mask-icon" href="favicon/safari-pinned-tab.svg" color="#5bbad5">
    <link rel="shortcut icon" href="favicon/favicon.ico">
    <meta name="msapplication-config" content="favicon/browserconfig.xml">
    <meta name="theme-color" content="#ffffff">
    <link rel="stylesheet" href="../../css/main.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://use.fontawesome.com/205197df0c.js"></script>
    <script src="../../js/main.js"></script>
</head>
<body>

<header>
    <a href="../" class="logo" data-scroll>MusicRay</a>
    <nav class="nav-collapse">
        <ul>
            <li class="menu-item"><a href="../" data-scroll>Главная</a></li>
            <li class="menu-item"><a href="../join" data-scroll>Купить</a></li>
            <li class="menu-item"><a href="../faq" data-scroll>Помощь</a></li>
            <li class="menu-item"><a href="../donate" data-scroll>Помочь проекту</a></li>

        </ul>
    </nav>
</header>

    <#nested/>
<footer><div class="contacts"><a href="https://vk.com/spotify_musicray" target="_blank"><i class="fa fa-vk" aria-hidden="true"></i></a><a href="https://t.me/music_ray" style="margin-left:20px;" target="_blank"><i class="fa fa-telegram" aria-hidden="true"></i></a><a href="mailto:support@music-ray.ru" style="margin-left:20px;" target="_blank"><i class="fa fa-envelope" aria-hidden="true"></i><span class="mail"> support@music-ray.ru</span></a></footer>


<script>
    var nav = responsiveNav(".nav-collapse");
</script>
</body>
</html>
</#macro>