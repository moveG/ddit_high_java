/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.37
 * Generated at: 2021-11-13 06:06:52 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class memberInsert_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n");
      out.write("\t\t<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<script src=\"//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js\"></script>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\r\n");
      out.write("\t\t<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n");
      out.write("\t\t<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n");
      out.write("\t\t<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<title>회원 등록 페이지</title>\r\n");
      out.write("\t\t<style>\r\n");
      out.write("\t\t\t.container{\r\n");
      out.write("\t\t \t\tmargin-left : 30%;\r\n");
      out.write("\t\t \t\tpadding-top : 16px;\r\n");
      out.write("\t\t \t}\r\n");
      out.write("\t\t \t.form-control, .custom-file-label{\r\n");
      out.write("\t\t \t\twidth : 500px;\r\n");
      out.write("\t\t \t\tdisplay : inline-block;\r\n");
      out.write("\t\t \t}\r\n");
      out.write("\t\t \t.star{\r\n");
      out.write("\t\t \t\tcolor : red;\r\n");
      out.write("\t\t \t}\r\n");
      out.write("\t\t \t.form-group{\r\n");
      out.write("\t\t \t\theight : 70px;\r\n");
      out.write("\t\t \t}\r\n");
      out.write("\t\t \t.btn-primary{\r\n");
      out.write("\t\t \t\tbackground : rgb(13, 110, 253);\r\n");
      out.write("\t\t \t\tborder-color : rgb(13, 110, 253);\r\n");
      out.write("\t\t \t}\r\n");
      out.write("\t\t \t.btn-success{\r\n");
      out.write("\t\t \t\tvertical-align : top;\r\n");
      out.write("\t\t \t\tbackground : rgb(25, 135, 84);\r\n");
      out.write("\t\t \t\tborder-color : rgb(25, 135, 84);\r\n");
      out.write("\t\t \t}\r\n");
      out.write("\t\t \t.btn-success:hover{\r\n");
      out.write("\t\t \t\tbackground : rgb(21, 115, 71);\r\n");
      out.write("\t\t \t\tborder-color : rgb(21, 115, 71);\r\n");
      out.write("\t\t \t}\r\n");
      out.write("\t\t</style>\r\n");
      out.write("\t\t<script>\r\n");
      out.write("\t\t\t$(function(){\r\n");
      out.write("\t\t\t\tidvalue = \"\";\r\n");
      out.write("\t\t\t\tidreg = \"\";\r\n");
      out.write("\t\t\t\tpwdvalue = \"\";\r\n");
      out.write("\t\t\t\tpwdreg = \"\";\r\n");
      out.write("\t\t\t\temailvalue = \"\";\r\n");
      out.write("\t\t\t\temailreg = \"\";\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//아이디 정규식\r\n");
      out.write("\t\t\t\t$('#id').on('keyup', function(){\r\n");
      out.write("\t\t\t\t\tidvalue= $(this).val();\r\n");
      out.write("\t\t\t\t\tidreg = /^[a-z][a-zA-Z0-9]{3,11}$/;\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tif(idreg.test(idvalue) == true){\r\n");
      out.write("\t\t\t\t\t\t$(this).css('border', '2px solid blue');\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\tif(pwdreg.test(pwdvalue) == true && emailreg.test(emailvalue) == true){\r\n");
      out.write("\t\t\t\t\t\t\t$('#insert').prop(\"disabled\", false);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}else if(idreg.test(idvalue) == false){\r\n");
      out.write("\t\t\t\t\t\t$(this).css('border', '2px solid red');\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t$('#insert').prop('disabled', true);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t})\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//아이디 공백 체크\r\n");
      out.write("\t\t\t\t$('#idcheck').on('click', function(){\r\n");
      out.write("\t\t\t\t\tidvalue = $('#id').val();\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t//공백 체크\r\n");
      out.write("\t\t\t\t\tif(idvalue.length < 1){\r\n");
      out.write("\t\t\t\t\t\t$('#idspan').html(\"아이디를 입력하세요.\").css('color', 'red');\r\n");
      out.write("\t\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\t         url : \"/ServletMemberBoard/IDCheck.do\",\r\n");
      out.write("\t\t\t\t\t         type : 'get',\r\n");
      out.write("\t\t\t\t\t         data : {'id' : idvalue},\r\n");
      out.write("\t\t\t\t\t         dataType : 'json',\r\n");
      out.write("\t\t\t\t\t         success : function(res){\r\n");
      out.write("\t\t\t\t\t            if(res.flag == \"사용\" && idreg.test(idvalue) == true){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$('#idspan').html(\"사용할 수 있는 아이디입니다.\").css('color', 'blue');\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$('#id').css('border', '2px solid blue');\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$('#email').prop(\"disabled\", false);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$('#pwd').prop(\"disabled\", false);\r\n");
      out.write("\t\t\t\t\t            }else{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$('#idspan').html(\"사용할 수 없는 ID입니다.\").css('color', 'red');\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$('#id').css('border', '2px solid red');\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$('#email').prop(\"disabled\", true);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$('#pwd').prop(\"disabled\", true);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$('#insert').prop(\"disabled\", true);\r\n");
      out.write("\t\t\t\t\t            }\r\n");
      out.write("\t\t\t\t\t         },\r\n");
      out.write("\t\t\t\t\t         error : function(xhr){\r\n");
      out.write("\t\t\t\t\t            alert('상태 : ' + xhr.status);\r\n");
      out.write("\t\t\t\t\t         }\r\n");
      out.write("\t\t\t\t\t      })\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t})\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//이름 정규식\r\n");
      out.write("\t\t\t\t$('#name').on('keyup', function(){\r\n");
      out.write("\t\t\t\t\tnamevalue = $(this).val().trim();\r\n");
      out.write("\t\t\t\t\tnamereg = /^[가-힣]{2,10}$/;\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tif(namereg.test(namevalue)){\r\n");
      out.write("\t\t\t\t\t\t$(this).css('border', '2px solid blue');\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t$(this).css('border', '2px solid red');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t})\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//비밀번호 정규식\r\n");
      out.write("\t\t\t\t$('#pwd').on('keyup', function(){\r\n");
      out.write("\t\t\t\t\tpwdvalue = $(this).val().trim();\r\n");
      out.write("\t\t\t\t\tpwdreg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#$@!]).{8,20}$/;\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tif(pwdreg.test(pwdvalue) == true){\r\n");
      out.write("\t\t\t\t\t\t$(this).css('border', '2px solid blue');\r\n");
      out.write("\t\t\t\t\t\t$('#pwdspan').html(\"사용할 수 있는 패스워드입니다.\").css('color', 'blue');\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\tif(idreg.test(idvalue) == true && emailreg.test(emailvalue) == true){\r\n");
      out.write("\t\t\t\t\t\t\t$('#insert').prop(\"disabled\", false);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}else if(pwdreg.test(pwdvalue) == false){\r\n");
      out.write("\t\t\t\t\t\t$(this).css('border', '2px solid red');\r\n");
      out.write("\t\t\t\t\t\t$('#pwdspan').html(\"패스워드는 대문자, 소문자, 숫자, 특수문자를 조합하여, 8 ~ 20자까지 가능합니다.\").css('color', 'red');\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t$('#insert').prop(\"disabled\", true);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t})\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//전화번호 정규식\r\n");
      out.write("\t\t\t\t$('#phone').on('keyup', function(){\r\n");
      out.write("\t\t\t\t\thpvalue = $(this).val().trim();\r\n");
      out.write("\t\t\t\t\thpreg = /^\\d{3}-\\d{3,4}-\\d{4}$/;\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tif(hpreg.test(hpvalue)){\r\n");
      out.write("\t\t\t\t\t\t$(this).css('border', '2px solid blue');\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t$(this).css('border', '2px solid red');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t})\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t//이메일 정규식\r\n");
      out.write("\t\t\t\t$('#email').on('keyup', function(){\r\n");
      out.write("\t\t\t\t\temailvalue = $(this).val().trim();\r\n");
      out.write("\t\t\t\t\temailreg = /^[a-zA-Z0-9-_]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,2}$/;\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tif(emailreg.test(emailvalue) == true){\r\n");
      out.write("\t\t\t\t\t\t$(this).css('border', '2px solid blue');\r\n");
      out.write("\t\t\t\t\t\t$('#emailspan').html(\"사용할 수 있는 이메일입니다.\").css('color', 'blue');\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\tif(idreg.test(idvalue) == true && pwdreg.test(pwdvalue) == true){\r\n");
      out.write("\t\t\t\t\t\t\t$('#insert').prop(\"disabled\", false);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}else if(emailreg.test(emailvalue) == false){\r\n");
      out.write("\t\t\t\t\t\t$(this).css('border', '2px solid red');\r\n");
      out.write("\t\t\t\t\t\t$('#emailspan').html(\"사용할 수 없는 이메일입니다.\").css('color', 'red');\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t$('#insert').prop(\"disabled\", true);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t})\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$('#insert').on('click', function(){\r\n");
      out.write("\t\t\t\t\tvar flag = confirm('등록하시겠습니까?'); \r\n");
      out.write("\t\t\t\t\tif(flag){\r\n");
      out.write("\t\t\t\t\t\t$('#insertform').submit();\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t})\r\n");
      out.write("\t\t\t})\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body id=\"body\">\r\n");
      out.write("\t\t<div class=\"container\">\r\n");
      out.write("\t\t\t<h2 style=\"cursor:pointer;\" onclick=\"location.href='MemberInsert.do'\">회원 등록 페이지</h2>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t<form id=\"insertform\" action=\"MemberInsert.do\" method=\"post\">\r\n");
      out.write("\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t<label for=\"id\"><span class=\"star\">*</span>아이디:</label>\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"id\" placeholder=\"Enter ID\" name=\"id\">\r\n");
      out.write("\t\t\t\t\t&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t<button id=\"idcheck\" type=\"button\" class=\"btn btn-success\">중복검사</button>\r\n");
      out.write("\t\t\t\t\t&nbsp;&nbsp;<span id=\"idspan\"></span>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t<label for=\"email\"><span class=\"star\">*</span>이메일:</label>\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"email\" placeholder=\"Enter email\" name=\"email\" disabled>\r\n");
      out.write("\t\t\t\t\t&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t<span id='emailspan'></span>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t<label for=\"pwd\"><span class=\"star\">*</span>패스워드:</label>\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<input type=\"password\" class=\"form-control\" id=\"pwd\" placeholder=\"Enter password\" name=\"pwd\" disabled>\r\n");
      out.write("\t\t\t\t\t&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t<span id='pwdspan'></span>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t<label for=\"name\">이름:</label>\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"name\" placeholder=\"Enter name\" name=\"name\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t<label for=\"phone\">연락처:</label>\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"phone\" placeholder=\"Enter phone-number\" name=\"phone\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t<label for=\"address\">주소:</label>\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"address\" placeholder=\"Enter address\" name=\"address\" readOnly>\r\n");
      out.write("\t\t\t\t\t&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-success\" onclick=\"sample6_execDaumPostcode()\">주소검색</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t<label for=\"regdate\">입사일:</label>\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<input style=\"cursor:pointer;\" type=\"date\" class=\"form-control\" id=\"regdate\" placeholder=\"Enter regdate\" name=\"regdate\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t<label for=\"picture\">사진:</label>\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<div class=\"custom-file mb-3\">\r\n");
      out.write("\t\t\t\t\t\t<input style=\"cursor:pointer;\" type=\"file\" class=\"form-control custom-file-input\" id=\"picture\" placeholder=\"Enter picture\" name=\"picture\">\r\n");
      out.write("\t\t\t\t\t\t<label class=\"custom-file-label\" for=\"picture\">Choose file</label>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t<label for=\"register\">등록자:</label>\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"register\" placeholder=\"Enter register\" name=\"register\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<button id=\"insert\" type=\"button\" class=\"btn btn-primary\" disabled>등록</button>\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" onclick=\"location.href='MemberList.do'\">취소</button>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<script>\r\n");
      out.write("\t\t\t$(\".custom-file-input\").on(\"change\", function() {\r\n");
      out.write("\t\t\t\tvar picture = $(this).val().split(\"\\\\\").pop();\r\n");
      out.write("\t\t\t\t$(this).siblings(\".custom-file-label\").addClass(\"selected\").html(picture);\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<script>\r\n");
      out.write("\t\t\tfunction sample6_execDaumPostcode() {\r\n");
      out.write("\t\t\t\tnew daum.Postcode({\r\n");
      out.write("\t\t\t\t\toncomplete: function(data) {\r\n");
      out.write("\t\t\t\t\t\t// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t// 각 주소의 노출 규칙에 따라 주소를 조합한다.\r\n");
      out.write("\t\t\t\t\t\t// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.\r\n");
      out.write("\t\t\t\t\t\tvar addr = ''; // 주소 변수\r\n");
      out.write("\t\t\t\t\t\tvar extraAddr = ''; // 참고항목 변수\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.\r\n");
      out.write("\t\t\t\t\t\tif (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우\r\n");
      out.write("\t\t\t\t\t\t    addr = data.roadAddress;\r\n");
      out.write("\t\t\t\t\t\t} else { // 사용자가 지번 주소를 선택했을 경우(J)\r\n");
      out.write("\t\t\t\t\t\t    addr = data.jibunAddress;\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.\r\n");
      out.write("\t\t\t\t\t\tif(data.userSelectedType === 'R'){\r\n");
      out.write("\t\t\t\t\t\t    // 법정동명이 있을 경우 추가한다. (법정리는 제외)\r\n");
      out.write("\t\t\t\t\t\t    // 법정동의 경우 마지막 문자가 \"동/로/가\"로 끝난다.\r\n");
      out.write("\t\t\t\t\t\t    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){\r\n");
      out.write("\t\t\t\t\t\t        extraAddr += data.bname;\r\n");
      out.write("\t\t\t\t\t\t    }\r\n");
      out.write("\t\t\t\t\t\t    // 건물명이 있고, 공동주택일 경우 추가한다.\r\n");
      out.write("\t\t\t\t\t\t    if(data.buildingName !== '' && data.apartment === 'Y'){\r\n");
      out.write("\t\t\t\t\t\t        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);\r\n");
      out.write("\t\t\t\t\t\t    }\r\n");
      out.write("\t\t\t\t\t\t    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.\r\n");
      out.write("\t\t\t\t\t\t    if(extraAddr !== ''){\r\n");
      out.write("\t\t\t\t\t\t        extraAddr = ' (' + extraAddr + ')';\r\n");
      out.write("\t\t\t\t\t\t    }\r\n");
      out.write("\t\t\t\t\t\t    // 조합된 참고항목을 해당 필드에 넣는다.\r\n");
      out.write("\t\t\t\t\t\t    document.getElementById(\"address\").value = \"(\" + data.zonecode + \") \" + addr + \" \" + extraAddr + \" \";\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\tdocument.getElementById(\"address\").value = \"(\" + data.zonecode + \") \" + addr + \" \";\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t// readonly 해제\r\n");
      out.write("\t\t\t\t\t\tdocument.getElementById(\"address\").readOnly = false;\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t// 커서를 상세주소 필드로 이동한다.\r\n");
      out.write("\t\t\t\t\t\tdocument.getElementById(\"address\").focus();\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}).open();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}