<#macro standardPage title="">
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="apple-touch-icon" sizes="180x180" href="/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" href="/favicon/favicon-32x32.png" sizes="32x32">
    <link rel="icon" type="image/png" href="/favicon/favicon-16x16.png" sizes="16x16">
    <link rel="manifest" href="/favicon/manifest.json">
    <link rel="mask-icon" href="/favicon/safari-pinned-tab.svg" color="#5bbad5">
    <link rel="shortcut icon" href="/favicon/favicon.ico">
    <meta name="msapplication-config" content="/favicon/browserconfig.xml">
    <meta name="theme-color" content="#ffffff">
    <link rel="stylesheet" href="/css/main.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://use.fontawesome.com/205197df0c.js"></script>
    <script src="/js/main.js"></script>
</head>
<body>

<header>
    <a href="/" class="logo" data-scroll>MusicRay</a>
    <nav class="nav-collapse">
        <ul>
            <li class="menu-item"><a href="/" data-scroll>Главная</a></li>
            <li class="menu-item"><a href="/join" data-scroll>Присоединиться</a></li>
            <li class="menu-item"><a href="/status" data-scroll>Состояние подписки</a></li>
            <li class="menu-item"><a href="/faq" data-scroll>FAQ</a></li>
            <li class="menu-item"><a href="/donate" data-scroll>Поддержать проект</a></li>

        </ul>
    </nav>
</header>

    <#nested/>
<footer>
    <div class="contacts"><a href="https://vk.com/spotify_musicray" target="_blank"><i class="fa fa-vk"
                                                                                       aria-hidden="true"></i></a><a
            href="https://t.me/music_ray" style="margin-left:20px;" target="_blank"><i class="fa fa-telegram"
                                                                                       aria-hidden="true"></i></a><a
            href="mailto:support@music-ray.ru" style="margin-left:20px;" target="_blank"><i class="fa fa-envelope"
                                                                                            aria-hidden="true"></i><span
            class="mail"> support@music-ray.ru</span></a>
</footer>


<script>
    var nav = responsiveNav(".nav-collapse");
</script>
<!-- Yandex.Metrika counter -->
<script type="text/javascript"> (function (d, w, c) {
    (w[c] = w[c] || []).push(function () {
        try {
            w.yaCounter42258604 = new Ya.Metrika({
                id: 42258604,
                clickmap: true,
                trackLinks: true,
                accurateTrackBounce: true,
                webvisor: true
            });
        } catch (e) {
        }
    });
    var n = d.getElementsByTagName("script")[0], s = d.createElement("script"), f = function () {
        n.parentNode.insertBefore(s, n);
    };
    s.type = "text/javascript";
    s.async = true;
    s.src = "https://mc.yandex.ru/metrika/watch.js";
    if (w.opera == "[object Opera]") {
        d.addEventListener("DOMContentLoaded", f, false);
    } else {
        f();
    }
})(document, window, "yandex_metrika_callbacks"); </script>
<noscript>
    <div><img src="https://mc.yandex.ru/watch/42258604" style="position:absolute; left:-9999px;" alt=""/></div>
</noscript> <!-- /Yandex.Metrika counter -->
<script>
    (function (i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
        a = s.createElement(o),
                m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');
    ga('create', 'UA-57176532-12', 'auto');
    ga('send', 'pageview');
</script>
</body>
</html>
</#macro>