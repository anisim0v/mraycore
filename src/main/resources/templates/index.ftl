<#-- @ftlvariable name="provisioningCount" type="java.lang.Number" -->
<#-- @ftlvariable name="accountsCount" type="java.lang.Number" -->
<#include "library/standardPage.ftl" />

<@standardPage title="MusicRay">
<main>
<section class="parallax-window" data-parallax="scroll" data-image-src="../img/pic-min.jpg" id="one">
    <div>
        <h1 class="main">Spotify Premium</h1>
        <h4 class="main">От 80 р/мес.</h4><br>
        <a href="/join">
            <button class="want">Хочу!</button>
        </a>
    </div>
</section>
<section id="two">
    <div class="why">
        <h3>Почему Spotify?</h3>
        <div class="container">
            <div class="row">
                <div class="feature four columns"><h5>Это удобно</h5>
                    <p>Spotify – крупнейший музыкальный сервис во всем мире, которым пользуются больше 100 миллионов
                        человек. Доступен на множестве платформ, что позволяет слушать музыку, когда тебе удобно
                        (Windows, Linux, Android, iOS <a href="https://www.spotify.com/int/download/other/">и
                            др.</a>)<br><br><br></p></div>
                <div class="feature four columns"><h5>Рапространён <u>почти</u> во всем мире</h5>
                    <p>Почему "почти"? Регистрация аккаунта Spotify невозможна в некоторых странах (например, в
                        России).<br>Но мы можем зарегистрировать аккаунт за тебя!<br><br></p></div>
                <div class="feature four columns"><h5>Огромное количество музыки</h5>
                    <p>В медиатеке Spotify больше 30 миллионов треков, и эта цифра растет с каждым днем. А с
                        подпиской Premium (при наличии достаточного объема памяти) ты можешь скачать ее всю на
                        телефон!</p></div>
            </div>
        </div>
    </div>
</section>
<br><br><br><br><br>
<section id="three" class="parallax-window" data-parallax="scroll" data-image-src="./img/pic2-min.jpg">
    <div class="container">
        <div class="class"><h3>Чем различаются аккаунты разных стран в Spotify?</h3></div>
        <div class="row">
            <div class="diff twelwe columns">
                <p>На самом деле все просто! Отличия разных стран - чарты и рекомендации. Spotify составляет
                    подборки популярной музыки для страны и для мира, т.е., обладая Филиппинским аккаунтом, ты
                    будешь видеть Филлипинский и всемирный топы. Также сервис предлагает плейлисты, которые тоже
                    имеют региональную тематику.</p>
            </div>

            <a href="/join">
                <button class="want">Купить!</button>
            </a><br><br>
        </div>
</section>
<section id="four">
    <div class="why">
        <div class="container">
            <h3>Почему мы?</h3>
            <div class="row">
                <div class="feature four columns"><h5 class="class2">Простота</h5>
                    <p>Тебе не надо лазить по настройкам со словарями. Просто поставь галочку "зарегистрируйте мне
                        аккаунт". И в течение нескольких часов вся информация придет на почту.</p></div>
                <div class="feature four columns"><h5 class="class2">Качество</h5>
                    <p>Мы дадим бесплатно протестировать сервис до 20:00. В письме будут содержаться подробные
                        инструкции по подключению. Всегда можно написать нам на почту support@music-ray.ru</p></div>
                <div class="feature four columns"><h5 class="class2">Цена</h5>
                    <p>Мы не берем комиссию. Мы стараемся корректировать цену в зависимости от курса, чтобы она
                        всегда была минимальной.</p></div>
                <br>
            </div>
        </div>
    </div>
</section>
</main>
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
</@standardPage>