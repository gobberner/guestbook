<%@page import="kr.co.itcen.guestbook.dao.GuestbookDao"%>
<%@page import="kr.co.itcen.guestbook.vo.GuestbookVo"%>
<%
	request.setCharacterEncoding("UTF-8");
	GuestbookVo vo = new GuestbookVo();
	vo.setName(request.getParameter("name"));
	vo.setPassword(request.getParameter("password"));
	vo.setContents(request.getParameter("contents"));
	
	GuestbookDao dao = new GuestbookDao();
	new GuestbookDao().insert(vo);
	response.sendRedirect(request.getContextPath());
%>