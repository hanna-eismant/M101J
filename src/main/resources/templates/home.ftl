<#import "common.ftl" as layout />

<@layout.masterTemplate>

    <#list posts as post>
    <h4><a href="/posts/${post.permalink}">${post.title}</a></h4>
    <p style="padding-bottom: 20px;">
        <#assign minitext=(post.text!"")>
        <#if minitext?length &lt; 200>
        ${minitext}
        <#else>
        ${minitext?substring(0,199)}…
        </#if>

    </p>
    </#list>

</@layout.masterTemplate>
