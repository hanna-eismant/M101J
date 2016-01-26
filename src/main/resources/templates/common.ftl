<#macro masterTemplate title="Blog">
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <title>${title}</title>

  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>
<body style="padding: 15px;">

<ul class="nav nav-pills">
  <li role="presentation"><a href="/">Home</a></li>
    <#if username??>
      <li role="presentation"><a href="/logout">Logout</a></li>
    <#else>
      <li role="presentation"><a href="/login">Login</a></li>
      <li role="presentation"><a href="/signup">Sign Up</a></li>
    </#if>
</ul>

<h3>
    <#if username??>Welcome, ${username}</#if>
</h3>

    <#if error??>
    <p class="bg-danger" style="padding: 15px;">${error}</p>
    </#if>
    <#nested />
</body>
</html>
</#macro>
