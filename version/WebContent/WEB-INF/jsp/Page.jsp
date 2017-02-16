<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<c:if test="${pages>1}">    
<form id="i_pageform" action="${url }" method="post">
	<c:forEach items="${queryMap}" var="entry">
		<input type="hidden" name="${entry.key }" value="${entry.value }"/>
	</c:forEach>
	<input type="hidden" name="page" value="${page }"/>
	<input type="hidden" name="pages" value="${pages }"/>
</form>
<div class="page" style="float:right">
 <button class="btn" onclick="firstpage()" >首页</button>
 <button class="btn" onclick="previouspage()">上一页</button>
 <input type="text" class="new-input" value="${page}" placeholder="">
 <button class="btn" onclick="gotopage()" >跳转</button>
 <button class="btn" onclick="nextpage()" >下一页</button>
 <button class="btn" onclick="lastpage()" >末页</button>  
  共 ${pages } 页
 <!-- <button class="btn" type="button" disabled>禁用</button> -->
</div>
<div style="clear:both"></div>
<script type="text/javascript">
function pageList(page){
	var pf=$("#i_pageform");
	var pn=$("input[name='page']");
	pn.attr("value",page);
	
	pf.submit();
}
function firstpage(){
	pageList(1);
}
function lastpage(){
	var pages = $("input[name='pages']").val();
	pageList(pages);
}
function gotopage(){
	var page = $(".new-input").val();
	pageList(page);
}
function nextpage(){
	var page = $("input[name='page']").val();
	var pages = $("input[name='pages']").val();
	page++;
	if(page<=pages){
		pageList(page);
	}
}
function previouspage(){
	var page = $("input[name='page']").val();
	var pages = $("input[name='pages']").val();
	page--;
	if(page>=1){
		pageList(page);
	}
}

</script>
</c:if>