<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		//자바코드 작성
		//dom_ob6.html(요청하는 페이지) -> index.jsp(요청을 받는 서버 페이지)
		//요청을 하는 순간? request내장객체 메모리 영역이 새로이 만들어지면서 요청값들이 저장되어 유지됨
		//요청한 값들을 request메모리에서 꺼내오기
		
		String chk1 = request.getParameter("subject1");
		
		out.print("dom_ob6.html페이지로부터 요청 받은 값 :" + chk1);
	%>
	

	dom_ob6.html페이지로부터 요청 받은 서버페이지 입니다.

</body>
</html>