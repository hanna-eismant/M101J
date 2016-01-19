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
  <li role="presentation"><a href="/">Home</a></li>
  <li role="presentation" class="active"><a href="/login">Login</a></li>
  <li role="presentation"><a href="/signup">Sign Up</a></li>
</ul>

<form class="login-form col-xs-4 col-xs-offset-4" method="post">
  <h1>${title}</h1>

  <input class="form-control form-group" id="username" placeholder="Username"/>
  <input class="form-control form-group" id="password" placeholder="Password"/>

  <button class="btn btn-primary form-control form-group" type="submit">Log In</button>
</form>

</body>
</html>
