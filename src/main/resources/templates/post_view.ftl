<#import "common.ftl" as layout />

<@layout.masterTemplate>

<h4>${post.title}</h4>
<small>Author: post.author}</small>
<p style="padding-bottom: 20px;">
  ${post.text}
</p>

</@layout.masterTemplate>
