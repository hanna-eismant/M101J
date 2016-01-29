<#import "common.ftl" as layout />

<@layout.masterTemplate>
<form class="col-xs-6" method="post">
  <h2>${title}</h2>

  <input class="form-control form-group" id="title" name="title" placeholder="New Post Title"/>
  <textarea class="form-control form-group" id="text" name="text" placeholder="Write Post Body Here"></textarea>
  <input class="form-control form-group" id="tags" name="tags" placeholder="Tags List"/>

  <button class="btn btn-primary form-control form-group" type="submit">Create</button>
</form>
</@layout.masterTemplate>
