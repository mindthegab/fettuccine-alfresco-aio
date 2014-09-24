<@markup id="widgets">
<@createWidgets group="dashlets"/>
</@>
<@markup id="html">
<@uniqueIdDiv>
<#assign el=args.htmlid?html>
<div class="dashlet">
<div class="title">${title}</div>
<div class="body scrollableList">
<div class="detail-list-item first-item last-item">
<span>${instructions}</span><br/><br/>
<span><a href="${recipeUrl}">Read more...</a></span>
</div>
</div>
</div>
</@>
</@>