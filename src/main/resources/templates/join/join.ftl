<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#include '../library/standardPage.ftl'>

<@standardPage title="Регистрация">
<main class="buy">
    <join>
        <div class="container">
            <h3>Купить</h3>
            <div class="row">
                <div class="six columns" id="buy-form">
                    <form method="POST" th:action="@{/join}">
                        <div>
                            <label for="email">email</label>
                            <input type="email" name="email" id="email" placeholder="Email" required/>
                        </div>
                        <div>
                            <label for="region">Страна </label>
                            <select id="region" name="region">
                                <option value="PH" selected>Филиппины</option>
                                <option value="US">США</option>
                            </select>
                        </div>
                        <div>
                            <label for="period">Период оплаты</label>
                            <select id="period" name="period">
                                <option value="1">Один месяц</option>
                            </select>
                        </div>
                        <div>
                            <label for="agree" class="agree"><input type="checkbox" id="agree" required/>
                            Я подтверждаю, что мой аккаунт Spotify зарегистрирован в выбранной стране.</label>
                        </div><br>
                        <div>
                            <label for="twiceayear"><input type="checkbox" id="twiceayear" required/>
                            Я подтверждаю, что осведомлён о том, что менять семью в Spotify можно не более раза в
                                год</label>
                        </div>
                        <div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="button" id="submit">Купить! (100р.)</button>
                        </div>
                    </form>
                </div>
                <#--<div class="offset-by-six">-->
                    <#--<h5>Наши цены:</h5>-->
                    <#--<span>Филиппины - 100 р.</span><br>-->
                    <#--<span>США - 220 р.</span>-->
                <#--</div>-->
            </div>
        </div>
    </join>
</main>

<script>
    $("#region").change(function() {
        switch($("#region option:checked").val()){
            case "US":
                $("#submit").html("Купить! (220р.)");
                break;
            case "PH":
                $("#submit").html("Купить! (100р.)");
                break;
        }
    });
</script>

</@standardPage>
