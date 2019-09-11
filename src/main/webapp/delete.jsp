<%@page import="kr.co.itcen.guestbook.vo.GuestbookVo"%>
<%@page import="kr.co.itcen.guestbook.dao.GuestbookDao"%>
<%
	request.setCharacterEncoding("UTF-8");
	String no = request.getParameter("no");
	String password = request.getParameter("password");
	
	new GuestbookDao().delete(no, password);
	response.sendRedirect(request.getContextPath());
%>