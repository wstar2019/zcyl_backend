/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.29
 * Generated at: 2019-04-27 06:18:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.static_;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class standard_005feditor_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("jar:file:/G:/EclipsePhotonWorkArray/jee-photon/ZCYL/src/main/webapp/WEB-INF/lib/jstl-1.2.jar!/META-INF/fmt.tld", Long.valueOf(1153356282000L));
    _jspx_dependants.put("jar:file:/G:/EclipsePhotonWorkArray/jee-photon/ZCYL/src/main/webapp/WEB-INF/lib/jstl-1.2.jar!/META-INF/fn.tld", Long.valueOf(1153356282000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1536948270000L));
    _jspx_dependants.put("jar:file:/G:/EclipsePhotonWorkArray/jee-photon/ZCYL/src/main/webapp/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153356282000L));
  }

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n");
      out.write("<HTML><HEAD><TITLE></TITLE></HEAD>\r\n");
      out.write("<META http-equiv=content-type content=\"text/html; charset=utf-8\">\r\n");
      out.write("<BODY leftMargin=0 topMargin=0 style=\"margin:0px;\">\r\n");
      out.write("<SCRIPT language=JavaScript>\r\n");
      out.write("<!--\r\n");
      out.write("var bokecc_id='';\r\n");
      out.write("//-->\r\n");
      out.write(" </SCRIPT>\r\n");
      out.write("<SCRIPT src=\"ewebeditor/KindEditor.js\" type=\"text/javascript\"></SCRIPT>\r\n");
      out.write("<INPUT id=content type=hidden name=content>\r\n");
      out.write("<SCRIPT type=text/javascript>\r\n");
      out.write("\r\n");
      out.write("var editor = new KindEditor(\"editor\");\r\n");
      out.write("\teditor.hiddenName = \"content\";\r\n");
      out.write("\teditor.skinPath = \"ewebeditor/skins/default/\";\r\n");
      out.write("\teditor.iconPath = \"ewebeditor/skins/faceicon/\";\r\n");
      out.write("\teditor.imageAttachPath = \"upload_files\";\r\n");
      out.write("\teditor.imageUploadCgi = \"upfile_eweb.jsp\";\r\n");
      out.write("\teditor.cssPath = \"ewebeditor/common.css\";\r\n");
      out.write("\teditor.editorWidth = \"100%\";\r\n");
      out.write("\teditor.formBorderColor = \"#416cc7\";\r\n");
      out.write("\teditor.parentID = \"editor_data\";\r\n");
      out.write("\r\n");
      out.write("function get_height(){\r\n");
      out.write("\tvar h = 0;\r\n");
      out.write("\tif(parent.document.getElementById(\"Web_Edit\") != null){\r\n");
      out.write("\t\th = (parent.document.getElementById(\"Web_Edit\").height - 60);\r\n");
      out.write("\t}\r\n");
      out.write("\tif(h<10){\r\n");
      out.write("\t\th = 100;\r\n");
      out.write("\t}\r\n");
      out.write("\treturn (h + 'px');\r\n");
      out.write("}\r\n");
      out.write("function show_edit(){\r\n");
      out.write("\teditor.editorHeight = get_height();\r\n");
      out.write("\teditor.show();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("setcode();\r\n");
      out.write("show_edit();\r\n");
      out.write("\r\n");
      out.write("function setcode(){\t\r\n");
      out.write("\tdocument.getElementById('content').value=parent.document.getElementById(editor.parentID).value;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("document.onmousemove=editor.code;\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function set_input(){\r\n");
      out.write("\teditor.code();\t\r\n");
      out.write("\tsetTimeout(\"set_input()\",30);\r\n");
      out.write("}\r\n");
      out.write("set_input();\r\n");
      out.write("\r\n");
      out.write("</SCRIPT>\r\n");
      out.write("</BODY></HTML>\r\n");
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
