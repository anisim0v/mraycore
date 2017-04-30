<#macro standardAdminPage title="">
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${title}</title>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="admin/css/materialize.min.css"  media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<p><a href="/admin">Back to /admin</a></p>

    <#nested/>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="admin/js/materialize.min.js"></script>
</body>
</body>
</html>
</#macro>