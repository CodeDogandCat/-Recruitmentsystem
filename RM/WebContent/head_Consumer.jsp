<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�ޱ����ĵ�</title>
</head>
<%@page import="com.wy.form.ConsumerForm"%>

<%
ConsumerForm consumerFormPT=(ConsumerForm)session.getAttribute("form");
%>

<body>
		<%if(consumerFormPT.getManageLevel().equals("��ͨ")){%>

		  <table width="366" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="#F1BD36">
          <tr bgcolor="#FFFFFF">
            <td width="171" height="20">�û�����<%=consumerFormPT.getAccount()%></td>
          </tr>
		      <tr bgcolor="#FFFFFF">
            <td height="20">���䣺<%=consumerFormPT.getEMail()%></td>
            <td height="20" bgcolor="#F1BD36"><a href="head_consumerUpdate.jsp">�����û���Ϣ</a></td>
	        </tr>
          </table>
	<%} %>

</body>
</html>
