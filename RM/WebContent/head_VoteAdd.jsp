<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<jsp:directive.page import="com.wy.form.VoteForm"/>
<jsp:directive.page import="com.wy.form.ArticleTypeForm"/>
<jsp:useBean id="articleTypeDao" scope="request" class="com.wy.dao.ArticleTypeDao"></jsp:useBean>
<jsp:useBean id="discussDao" scope="request" class="com.wy.dao.DiscussDao"></jsp:useBean>
<jsp:useBean id="voteDao" class="com.wy.dao.VoteDao" scope="page"></jsp:useBean>
<jsp:useBean id="countTime" scope="page" class="com.wy.tool.CountTime"></jsp:useBean>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="CSS/style.css" type="text/css"  rel="stylesheet">
<title>ǰ̨-Ͷ����</title>
<%@ page language="java" import="java.util.*" %>
<script src="KingCalendar/jquery_1_7.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript" src="KingCalendar/King-Calendar.js" language="javascript"></script>
<script type="text/javascript">    
 function preview(file)  
 {  
	 var prevDiv = document.getElementById('preview');  
	 if (file.files && file.files[0])  
	 {  
		 var reader = new FileReader();  
		 reader.onload = function(evt)
		 {  
		 prevDiv.innerHTML = '<img  id="red" src="' + evt.target.result + '" />';  
		 }    
	 	 reader.readAsDataURL(file.files[0]);  
	 }  
	 else    
	 {  
	 	prevDiv.innerHTML = '<div id="red1" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
	 }  
 }  
 </script>
<% List list1=articleTypeDao.queryArticleType(); %>
 <script type="text/javascript">    
 function select1changed(locationid)  
 {  
	 
	  document.getElementById("ypzw").length   =   0;   //��ʼ�������б�   �����������     
	  document.getElementById("ypzw").options[0]   =   new   Option('==��ѡ��==','');  //����һ��ֵ  
	 var list2=<%=list1 %>;
	   
		 for(i =0;i<list2.size();i++){
			document.getElementById("ypzw").options.add(new Option(i,2));          
		 }
		
	  
	
 }  
 </script>
<style type="text/css">  
 #red
 {  
 width:117px;  
 height:156px;  
 } 
  #red1
 {  
 width:117px;  
 height:156px;  
 } 
 #preview
 {  
 width:117px;  
 height:156px;  
 }  
 #preview  
 {  
border:1px solid #000;  
}  
 </style> 

<style type="text/css">
<!--
body {
	background-image: url(images/bg_01.gif);
}
.style1 {
	color: #663403;
	font-weight: bold;
}
.style2 {color: #663403}
.style3 {color: #9a6400}
.style4 {
	color: #cc7800;
	font-size: 10pt;
}
.style5 {color: #663401}
-->
</style>
</head>

<body>

<!--��ҳͷ����-->
<jsp:include page="head_top.jsp" flush="true" />
<%@page import="com.wy.form.ConsumerForm"%>

<%
ConsumerForm consumerFormPT=(ConsumerForm)session.getAttribute("form");
%>
<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="74"><img src="images/head_06.jpg" width="74" height="846"></td>
    <td height="846" valign="top" background="images/head_07.jpg" width="407">

		<%out.println("<p align=left>&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/icon.gif width=10 height=10>&nbsp;&nbsp;�û�����Ͷ����</p>");%><br>
		<form name="form" method="post" action="VoteServlet?method=0" onSubmit="return voteAdd()" enctype="multipart/form-data">

         <table width="650" border="1">
    <tr>
      <td width="83"><div align="center">����</div></td>
      <td width="178"><label>
        <input name="xm" type="text" id="xm" />
        <input name="yhbh" type="hidden" id="yhbh" value=<%=consumerFormPT.getId()%> />
      </label></td>
      
      <td width="68"><div align="center">�Ա�</div></td>
      <td width="176"><label>
        <select name="xb" id="xb">
        <option>==��ѡ��==</option>
          <option>��</option>
          <option>Ů</option>
          <option>����</option>
        </select>
      </label></td>
      
      
      
      <td width="120" rowspan="7">
      
 <div align="right" id="preview"></div>  
 <input name="photo" style="width:70px; height:20px;" type="file" onchange="preview(this)" size="1" />
    </td>
    
    
    
    </tr>
    <tr>
      <td><div align="center">���֤��</div></td>
      <td><label>
        <input name="sfzh" type="text" id="sfzh" />
      </label></td>
      <td><div align="center">����</div></td>
      <td><label>
        <select name="mz" id="mz">
        <option>==��ѡ��==</option>
          <option>����</option>
          <option>׳��</option>
          <option>����</option>
          <option>����</option>
          <option>ά�����</option>
          <option>����</option>
          <option>����</option>
          <option>������</option>
          <option>����</option>
          <option>�ɹ���</option>
          <option>����</option>
        </select>
      </label></td>
    </tr>
    <tr>
    
      <td><div align="center">��������</div></td>
      <td><input name="csrq" id="csrq"  type="text" onFocus="StartCalendar({id:'csrq',lunarShow:true});" value="==��ѡ��==" size="24" id="csrq" ></td>
     
     
      <td><div align="center">������ò</div></td>
      <td><label>
        <select name="zzmm" id="zzmm">
        <option>==��ѡ��==</option>
          <option>Ⱥ��</option>
          <option>��Ա</option>
          <option>����</option>
        </select>
      </label></td>
    </tr>
    <tr>
      <td><div align="center">����</div></td>
      <td><label>
        <input name="jg" type="text" id="jg" />
      </label></td>
      <td><div align="center">��ҵʱ��</div></td>
      <td><input name="bysj" type="text" id="bysj"  onFocus="StartCalendar({id:'bysj',lunarShow:true});" value="==��ѡ��==" size="24"></td>
	  
	  
	  </td>
    </tr>
    <tr>
      <td><div align="center">���������</div></td>
      <td><label>
        <select name="jsjnl" id="jsjnl">
          <option>==��ѡ��==</option>
          <option>1��</option>
          <option>2��</option>
          <option>3��</option>
          <option>4��</option>
          <option>5��</option>
          <option>6��</option>
          <option>����</option>
        </select>
      </label></td>
      <td><div align="center">Ӣ������</div></td>
      <td><label>
        <select name="yynl" id="yynl">
        <option>==��ѡ��==</option>
          <option>4��</option>
          <option>6��</option>
          <option>����</option>
        </select>
      </label></td>
    </tr>
    <tr>
      <td><div align="center">ѧ��</div></td>
      <td><label>
        <select name="xl" id="xl">
        <option>==��ѡ��==</option>
          <option>����</option>
          <option>˶ʿ</option>
          <option>��ʿ</option>
          <option>����</option>
        </select>
      </label></td>
      <td><div align="center">��ϵ�绰</div></td>
      <td><label>
        <input name="lxdh" type="text" id="lxdh" />
      </label></td>
    </tr>
    <tr>
      <td><div align="center">ӦƸ����</div></td>
      <td><label>
        <select name="ypbm" id="ypbm" ">
        <option value="-1">==��ѡ��==</option>
<%
    List list=articleTypeDao.queryArticleType();
    for(int i=0;i<list.size();i++){
    ArticleTypeForm articleTypeForm=(ArticleTypeForm)list.get(i);
    %>
          <option value=<%=articleTypeForm.getId()%>><%=articleTypeForm.getTypeName() %></option>
          
    <%}%>
        </select>
      </label></td>
       <td><div align="center">ӦƸְλ</div></td>
      <td><label>
        <select name="ypzw" id="ypzw">
        <option>==��ѡ��==</option>
        <option>������Ա</option>
        <option>������Ա</option>
        <option>����ʦ</option>
        </select>
      </label></td>
    </tr>
    <tr>
      <td height="181"><div align="center">��������</div></td>
      <td colspan="4"><label>
        <textarea name="jybj" cols="90" rows="13" id="jybj"></textarea>
      </label></td>
    </tr>
    <tr>
      <td height="197"><div align="center">��������</div></td>
      <td colspan="4"><label>
        <textarea name="gzjl" cols="90" rows="14" id="gzjl"></textarea>
      </label></td>
    </tr>
    <tr>
      <td height="198"><div align="center">��������</div></td>
      <td colspan="4"><label>
        <textarea name="zwpj" cols="90" rows="14" id="zwpj"></textarea>
      </label></td>
    </tr>
  </table>
  <p>&nbsp;</p>
        
        <br>		
          <div align="center">
              <input type="image" class="inputinputinput" src="images/vote.jpg" width="51" height="20">
&nbsp;&nbsp;
   <a href="#" onClick="javascript:form.reset()"><img src="images/reset.gif"></a>&nbsp;&nbsp;
   <a href="#" onclick="javascript:history.go(-1);"><img src="images/back.gif"></a>
          </div>
		        </form>	        
		        <% %>
</td>
<!--     <td width="10"><img src="images/head_08.jpg" width="13" height="846"></td> -->
<!--     <td width="184" valign="top"> -->

<%-- 	  	<jsp:include page="head_right.jsp" flush="true" /> --%>
  
<!--     </td> -->
<!--     <td width="122"><img src="images/head_10.jpg" width="122" height="846"></td> -->
  </tr>
</table>
<!--��ҳβ����-->
<jsp:include page="head_down.jsp" flush="true" />




</body>
</html>
