<#import "common.ftl" as layout />

<@layout.masterTemplate>
<form class="signup-form col-xs-4" method="post">

  <h2>${title}</h2>

  <input class="form-control form-group" id="username" name="username" placeholder="Username"/>
  <input class="form-control form-group" id="password" name="password" placeholder="Password"/>
  <button class="btn btn-primary form-control form-group" type="submit">Sign Up</button>

</form>
</@layout.masterTemplate>
