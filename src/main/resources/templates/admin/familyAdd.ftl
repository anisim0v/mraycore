<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#include 'library/standardAdminPage.ftl'>

<@standardAdminPage title="Add family">
<form method="POST" action="/admin/families/add">
    <table>
        <tr>
            <td>
                <label for="login">Login</label>
                <input required name="login" id="login"/>

                <label for="password">Password</label>
                <input required name="password" id="password"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="region">Region</label>
                <select required id="region" name="region">
                    <option value="PH">PH</option>
                    <option value="US">US</option>
                </select>

                <label for="city">City</label>
                <input required name="city" id="city"/>

                <label for="streetName">Street name</label>
                <input required name="streetName" id="streetName"/>

                <label for="streetNumber">Street number</label>
                <input required name="streetNumber" id="streetNumber"/>

                <label for="zipCode">Zip code</label>
                <input required name="zipCode" id="zipCode"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="paidUntil">Paid until</label>
                <input required name="paidUntil" id="paidUntil" type="date"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="assignManually">Assign manually</label>
                <input type="checkbox" name="assignManually" id="assignManually">
            </td>
        </tr>
        <tr>
            <td>
                <label>Tokens</label>
                <input required name="tokens"/>
                <input required name="tokens"/>
                <input required name="tokens"/>
                <input required name="tokens"/>
                <input required name="tokens"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="button" id="submit">Добавить</button>
            </td>
        </tr>
    </table>
</form>
</@standardAdminPage>