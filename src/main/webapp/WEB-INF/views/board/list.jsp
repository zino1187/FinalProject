<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
	position:relative;
	left:0px;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
.pageStyle{
	font-size:15pt;
	font-weight:bold;
	color:blue;
}
a{text-decoration:none;}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	$("button").click(function(){
		location.href="/board/registForm";
	});
});
</script>
</head>
<body>
	<h1>상담게시판</h1>
	
	<table>
		<tr>
			<th width="5%">No</th>
			<th width="50%">제목</th>
			<th width="10%">작성자</th>
			<th width="20%">등록일</th>
			<th width="10%">조회수</th>
		</tr>
		<%int num=10; %>
		<%for(int i=1;i<=10;i++){ %>
		<%if(num<1)break;%>
		<% %>
		<tr>
			<td><%=num-- %></td>
			<td>
				<a href="/reboard/content.jsp?reboard_id=<%//=reboard.getReboard_id()%>"><%//=reboard.getTitle() %></a>
			</td>
			<td><%//=reboard.getWriter() %></td>
			<td><%//=reboard.getRegdate().substring(0,10) %></td>
			<td><%//=reboard.getHit() %></td>
		</tr>
		<%}%>
		<tr>
			<td colspan="5">
				<button>글등록</button>
			</td>
		</tr>
		<tr>
			<td colspan="5" style="text-align:center;">
				<a href="/reboard/list.jsp">◀</a>
				<%//for(int i=firstPage;i<=lastPage;i++){%>
				<%//if(i>totalPage)break; %>
				<a <%//if(i==currentPage){%>class="pageStyle"<%//}%> href="/reboard/list.jsp?currentPage=<%//=i%>">[<%//=i%>]</a>
				<%//} %>
				<a href="/reboard/list.jsp">▶</a>
			</td>
		</tr>
		
	</table>

</body>
</html>





