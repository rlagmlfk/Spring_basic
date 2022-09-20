<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹서버에서 쿠키 생성하기</title>
</head>
<body>
<%
	Cookie cook1 = new Cookie("notebook", "갤럭시");
	cook1.setMaxAge(60); // 1분
	// 생성자는 메소드 오버로딩 - 같은 이름의 생성자 여러개 가능함
	// 파라미터의 개수나 타입으로 구분함
	//Cookie cook2 = new Cookie("age", 30); String타입이 아니라 에러
	//Cookie cook2 = new Cookie("age", new Integer(30)); String타입이 아니라 에러
	Cookie cook2 = new Cookie("age", String.valueOf(30));
	List<String> names = new ArrayList<>();
	// cook2 = new Cookie("names", names); 세션은 가능하지만 쿠키는 타입이 다르면 안됨
	session.setAttribute("s_age", 30); // 세션은 Object라 숫자를 넣을 수 있음
	session.setAttribute("s_names", names);
	cook2.setMaxAge(60); // 1분
	cook2.setPath("/");
	Cookie cook3 = new Cookie("coffee", "콜드브루");
	cook3.setMaxAge(60); // 1분
	response.addCookie(cook1);
	response.addCookie(cook2);
	response.addCookie(cook3);
%>
</body>
</html>