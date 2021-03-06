<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/setting/design_setting_upper.jsp" %>
<%@ include file="setting.jsp" %>
<%@ include file="../setting/setting.jsp" %>


<%
 
    request.setCharacterEncoding("UTF-8");
 
%>

<div class="container">
    <h2>${page_content}</h2>
<table border="1">
	<input type="hidden" name="boardParentId" value="${boardDto.boardParentId}">
	<input type="hidden" name="boardId" value="${boardDto.boardId}">
<c:if test="${sessionScope.memType ne 3}">
		<thead>
		<tr>
			<th>신고자 </th>
			<th align="center">${boardDto.userId} </th>
		</tr>
		</thead>
</c:if>
<c:if test="${sessionScope.memType eq 3}">
		<thead>
		<tr>
			<th>신고자 </th>
			<th align="center"> ${boardDto.userId}</th>
		</tr>
		</thead>
	<!--<tr>		
			<th>답변자</th>
			<td align="center"> </td> 	신고글인데 답변자가 있을 필요가 없어서 지웠습니다. 
	  	
		</tr>
	-->
</c:if>
	
	
	<tr>		
		<th> 글쓴 날짜 </th>
		<td align="center">
			${boardDto.stime}	
		</td>
	</tr>	
	
	<tr>
		<th> 내용 </th>
		<td colspan="3"> <pre>${boardDto.boardContent}</pre> </td>	
	</tr>	
	<tr>
		<th colspan="4">
		<c:if test="${sessionScope.memid eq boardDto.userId}">
			<input class="inputbutton" type="button" value="${btn_modify}"
				onclick="location='boardMod.do?boardId=${boardDto.boardId}&pageNum=${pageNum}'">
			<input class="inputbutton" type="button" value="${btn_delete}"
				onclick="location='boardDel.do?boardId=${boardDto.boardId}&pageNum=${pageNum}'">
		</c:if>
		<c:if test="${sessionScope.memType eq 3}">
			<input class="inputbutton" type="button" value="${btn_reply}"
				onclick="location='boardWrt.do?boardId=${boardDto.boardId}&boardParentId=${boardDto.boardParentId}&pageNum=${pageNum}'">
		</c:if>
			<input class="inputbutton" type="button" value="${btn_list}"
				onclick="hBack()">
		<!--  	onclick="location='boardList.do?pageNum=${pageNum}'">  -->		
		</th>
	</tr>		
</table>
</div>
<%@ include file="/setting/design_setting_footer.jsp" %>