<#macro standardPage title="">
<!DOCTYPE html>
<html lang="en">
<head>

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <link rel="apple-touch-icon" sizes="180x180" href="/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" href="/favicon/favicon-32x32.png" sizes="32x32">
    <link rel="icon" type="image/png" href="/favicon/favicon-16x16.png" sizes="16x16">
    <link rel="manifest" href="/favicon/manifest.json">
    <link rel="mask-icon" href="/favicon/safari-pinned-tab.svg" color="#5bbad5">
    <link rel="shortcut icon" href="/favicon/favicon.ico">
    <meta name="msapplication-config" content="/favicon/browserconfig.xml">
    <meta name="theme-color" content="#222">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="css/index.css">
    <script src="js/index.js"></script>
    <link rel="stylesheet" href="css/skeleton.css">
    <script src="https://use.fontawesome.com/205197df0c.js"></script>
    <title>${title}</title>
</head>
<body>
<header>
    <a href="#one" class="logo" data-scroll>MusicRay</a>
    <nav class="nav-collapse">
        <ul>
            <li class="menu-item"><a href="/" data-scroll>Главная</a></li>
            <li class="menu-item"><a href="/join" data-scroll>Купить</a></li>
            <li class="menu-item"><a href="#two" data-scroll>Почему Spotify?</a></li>
            <li class="menu-item"><a href="/faq" data-scroll>Помощь</a></li>
        </ul>
    </nav>
</header>
<main>
    <#nested/>
</main>
</body>
</html>
</#macro>