<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <title>Home</title>

  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

  <!-- Optional theme -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>
<body>


<ul class="nav nav-pills">
  <li role="presentation" class="active"><a href="/">Home</a></li>
<#if username??>
  <li role="presentation"><a href="/logout">Logout</a></li>
<#else>
  <li role="presentation"><a href="/login">Login</a></li>
  <li role="presentation"><a href="/signup">Sign Up</a></li>
</#if>
</ul>

<h1><#if username??>Welcome, ${username}</#if></h1>


</body>
</html>
