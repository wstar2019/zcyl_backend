/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.29
 * Generated at: 2019-04-27 06:23:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.system.sys;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class customer_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(6);
    _jspx_dependants.put("jar:file:/G:/EclipsePhotonWorkArray/jee-photon/ZCYL/src/main/webapp/WEB-INF/lib/jstl-1.2.jar!/META-INF/fmt.tld", Long.valueOf(1153356282000L));
    _jspx_dependants.put("/WEB-INF/jsp/system/sys/../index/top.jsp", Long.valueOf(1544232589518L));
    _jspx_dependants.put("jar:file:/G:/EclipsePhotonWorkArray/jee-photon/ZCYL/src/main/webapp/WEB-INF/lib/jstl-1.2.jar!/META-INF/fn.tld", Long.valueOf(1153356282000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1536948270000L));
    _jspx_dependants.put("jar:file:/G:/EclipsePhotonWorkArray/jee-photon/ZCYL/src/main/webapp/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153356282000L));
    _jspx_dependants.put("/WEB-INF/jsp/system/sys/../index/foot.jsp", Long.valueOf(1534542862000L));
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

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems;

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
    _005fjspx_005ftagPool_005fc_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fchoose.release();
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.release();
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
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"static/ace/css/chosen.css\" />\r\n");
      out.write("\r\n");
      out.write("\t\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\r\n");
      out.write("\t\t<meta charset=\"utf-8\" />\r\n");
      out.write("\t\t<title>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pd.SYSNAME}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</title>\r\n");
      out.write("\t\t<meta name=\"description\" content=\"\" />\r\n");
      out.write("\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\" />\r\n");
      out.write("\t\t<!-- bootstrap & fontawesome -->\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"static/ace/css/bootstrap.css\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"static/ace/css/font-awesome.css\" />\r\n");
      out.write("\t\t<!-- page specific plugin styles -->\r\n");
      out.write("\t\t<!-- text fonts -->\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"static/ace/css/ace-fonts.css\" />\r\n");
      out.write("\t\t<!-- ace styles -->\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"static/ace/css/ace.css\" class=\"ace-main-stylesheet\" id=\"main-ace-style\" />\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"static/ryn/auth.css\"/>\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"static/ryn/navbar.css\"/>\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"static/ryn/breadcrumbs.css\"/>\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"static/ryn/modal.css\"/>\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"static/ryn/main.css\"/>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!--[if lte IE 9]>\r\n");
      out.write("\t\t\t<link rel=\"stylesheet\" href=\"static/ace/css/ace-part2.css\" class=\"ace-main-stylesheet\" />\r\n");
      out.write("\t\t<![endif]-->\r\n");
      out.write("\t\t<!--[if lte IE 9]>\r\n");
      out.write("\t\t  <link rel=\"stylesheet\" href=\"static/ace/css/ace-ie.css\" />\r\n");
      out.write("\t\t<![endif]-->\r\n");
      out.write("\t\t<!-- inline styles related to this page -->\r\n");
      out.write("\t\t<!-- ace settings handler -->\r\n");
      out.write("\t\t<script src=\"static/ace/js/ace-extra.js\"></script>\r\n");
      out.write("\t\t<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->\r\n");
      out.write("\t\t<!--[if lte IE 8]>\r\n");
      out.write("\t\t<script src=\"static/ace/js/html5shiv.js\"></script>\r\n");
      out.write("\t\t<script src=\"static/ace/js/respond.js\"></script>\r\n");
      out.write("\t\t<![endif]-->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"static/ace/css/datepicker.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"static/global/css/mycomponents.css\">\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"static/global/plugins/jstree/dist/themes/default/style.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"static/global/css/plugins.css\">\r\n");
      out.write("\r\n");
      out.write("<head></head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"page-header\">\r\n");
      out.write("\t\t<ul class=\"navi\">\r\n");
      out.write("\t\t\t<li><a>系统管理</a><span> ≫ </span></li>\r\n");
      out.write("\t\t\t<li><a target=\"mainFrame\" href=\"sys/listCustomer\">用户管理</a></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"page-content\" style=\"padding: 10px;\">\r\n");
      out.write("\t\t<div class=\"container-fluid\">\r\n");
      out.write("\t\t\t<table style=\"margin-bottom:10px;\">\r\n");
      out.write("\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"ipt-td\">用户名</td>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"ipt-td\"><input class=\"form-control\" id=\"C_LGID\" type=\"text\"></td>\r\n");
      out.write("\t\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"ipt-td\"><button onclick=\"initCtrl();\" class=\"btn btn-primary\">重置</button></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"ipt-td\">手机号</td>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"ipt-td\"><input class=\"form-control\" id=\"C_TEL\" type=\"text\"></td>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"ipt-td\">姓名</td>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"ipt-td\"><input class=\"form-control\" id=\"C_NAME\" type=\"text\"></td>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"ipt-td\"><button onclick=\"search();\" class=\"btn btn-primary\">查询</button></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<form action=\"sys/listCustomer\" method=\"post\" name=\"sysCustomerForm\" id=\"sysCustomerForm\">\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<table id=\"simple-table\" class=\"table table-hover\" style=\"margin-top: 5px;\">\r\n");
      out.write("\t\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"center\" style=\"width: 80px;\">序号</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"center\" style=\"width: 150px;\">用户名</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"center\" style=\"width: 100px;\">姓名</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"center\" style=\"width: 100px;\">手机号</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"center\" style=\"width: 100px;\">邮箱</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"center\" style=\"width: 100px;\">操作</th>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t");
      //  c:choose
      org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
      boolean _jspx_th_c_005fchoose_005f0_reused = false;
      try {
        _jspx_th_c_005fchoose_005f0.setPageContext(_jspx_page_context);
        _jspx_th_c_005fchoose_005f0.setParent(null);
        int _jspx_eval_c_005fchoose_005f0 = _jspx_th_c_005fchoose_005f0.doStartTag();
        if (_jspx_eval_c_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
            out.write("\t\t\t\t\t\t\t");
            //  c:when
            org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
            boolean _jspx_th_c_005fwhen_005f0_reused = false;
            try {
              _jspx_th_c_005fwhen_005f0.setPageContext(_jspx_page_context);
              _jspx_th_c_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
              // /WEB-INF/jsp/system/sys/customer_list.jsp(74,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_c_005fwhen_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty customerList}", boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null)).booleanValue());
              int _jspx_eval_c_005fwhen_005f0 = _jspx_th_c_005fwhen_005f0.doStartTag();
              if (_jspx_eval_c_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                do {
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t\t\t");

						            int numStds = 0;								
						            pageContext.setAttribute("numStds", numStds);
						        
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t\t\t");
                  //  c:forEach
                  org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
                  boolean _jspx_th_c_005fforEach_005f0_reused = false;
                  try {
                    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
                    _jspx_th_c_005fforEach_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f0);
                    // /WEB-INF/jsp/system/sys/customer_list.jsp(79,8) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
                    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/system/sys/customer_list.jsp(79,8) '${customerList}'",_jsp_getExpressionFactory().createValueExpression(_jspx_page_context.getELContext(),"${customerList}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
                    // /WEB-INF/jsp/system/sys/customer_list.jsp(79,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                    _jspx_th_c_005fforEach_005f0.setVar("customer");
                    // /WEB-INF/jsp/system/sys/customer_list.jsp(79,8) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                    _jspx_th_c_005fforEach_005f0.setVarStatus("vs");
                    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
                    try {
                      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
                      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        do {
                          out.write("\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t");

							            numStds++;								
							            pageContext.setAttribute("numStds", numStds);
							        
                          out.write("\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t<tr tr_id=\"");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("\"><td class=\"center\">");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(page.currentPage -1) * page.showCount + numStds}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("</td>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t<td class=\"center\">");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.lg_id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("</td>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t<td class=\"center\">");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("</td>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t<td class=\"center\">");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.tel}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("</td>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t<td class=\"center\">");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.email}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("</td>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t<td class=\"center\">\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"btn-group\">\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t\t\t<a class=\"btn btn-minier btn-white btn_m_edit\"\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tonclick=\"viewCustomer('");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write('\'');
                          out.write(',');
                          out.write('\'');
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write('\'');
                          out.write(',');
                          out.write('\'');
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.lg_id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write('\'');
                          out.write(',');
                          out.write('\'');
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.tel}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write('\'');
                          out.write(',');
                          out.write('\'');
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.email}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("', '");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.address}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("');\">查看</a>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t\t\t<a class=\"btn btn-minier btn-white btn_m_delete\"\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tonclick=\"delCustomer('");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write('\'');
                          out.write(',');
                          out.write('\'');
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write('\'');
                          out.write(',');
                          out.write('\'');
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${customer.lg_id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("');\">删除</a>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t</td>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
                          out.write("\t\t\t\t\t\t\t\t");
                          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
                          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                            break;
                        } while (true);
                      }
                      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        return;
                      }
                    } catch (java.lang.Throwable _jspx_exception) {
                      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
                        out = _jspx_page_context.popBody();
                      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
                    } finally {
                      _jspx_th_c_005fforEach_005f0.doFinally();
                    }
                    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
                    _jspx_th_c_005fforEach_005f0_reused = true;
                  } finally {
                    org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f0_reused);
                  }
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_c_005fwhen_005f0.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
              }
              if (_jspx_th_c_005fwhen_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                return;
              }
              _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
              _jspx_th_c_005fwhen_005f0_reused = true;
            } finally {
              org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fwhen_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fwhen_005f0_reused);
            }
            out.write("\r\n");
            out.write("\t\t\t\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fchoose_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
        _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
        _jspx_th_c_005fchoose_005f0_reused = true;
      } finally {
        org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fchoose_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fchoose_005f0_reused);
      }
      out.write("\r\n");
      out.write("\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t<div class=\"position-relative\">\r\n");
      out.write("\t\t\t\t\t<table style=\"width: 100%;\">\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td style=\"vertical-align: top;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"pagination\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tstyle=\"float: left; padding-top: 0px; margin-top: 0px;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.pageStr}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t\t<form action=\"\" style=\"display: none;\" method=\"post\" name=\"customerEditForm\" id=\"customerEditForm\">\r\n");
      out.write("\t\t\t\t<input type=\"text\" id=\"NAME\" name=\"NAME\" value=\"\" />\r\n");
      out.write("\t\t\t\t<input type=\"text\" id=\"LG_ID\" name=\"LG_ID\" value=\"\" />\r\n");
      out.write("\t\t\t\t<input type=\"text\" id=\"TEL\" name=\"TEL\" value=\"\" />\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- Modal Start-->\r\n");
      out.write("\t<div class=\"modal fade\" id=\"viewModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"viewModalLabel\">\t\t\t\t\t\t\r\n");
      out.write("  \t\t<div class=\"modal-dialog\" role=\"document\" style=\"width:500px\">\r\n");
      out.write("    \t\t<div class=\"modal-content\">\r\n");
      out.write("    \t\t\t<div class=\"modal-header\">\r\n");
      out.write("    \t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\r\n");
      out.write("        \t\t\t\t<span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("       \t\t\t\t</button>\r\n");
      out.write("    \t\t\t\t<h4 id=\"viewTitle\" class=\"modal-title\">查看用户</h4>\r\n");
      out.write("   \t\t\t\t</div>\r\n");
      out.write("   \t\t\t\t<div class=\"modal-body\">\r\n");
      out.write("   \t\t\t\t\t<div class=\"bootbox-body\">\r\n");
      out.write("   \t\t\t\t\t\t<form class=\"bootbox-form form-horizontal\">\r\n");
      out.write("   \t\t\t\t\t\t\t<input id=\"MD_ID\" style=\"display:none;\" type=\"text\">\r\n");
      out.write("   \t\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("   \t\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">用户名</label>\r\n");
      out.write("   \t\t\t\t\t\t\t\t<div class=\"col-sm-10 ipt-container\">\r\n");
      out.write("   \t\t\t\t\t\t\t\t\t<label id=\"MD_LGID\"></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">姓名</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-10 ipt-container\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label id=\"MD_NAME\"></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">手机号</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-10 ipt-container\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label id=\"MD_TEL\"></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">地址</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-10 ipt-container\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label id=\"MD_ADDRESS\"></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">邮箱</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-10 ipt-container\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label id=\"MD_EMAIL\"></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\">确认</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("    \t</div>\r\n");
      out.write("  \t</div>\r\n");
      out.write("\t<!-- Modal End -->\r\n");
      out.write("\r\n");
      out.write("\t<a href=\"#\" id=\"btn-scroll-up\"\r\n");
      out.write("\t\tclass=\"btn-scroll-up btn btn-sm btn-inverse\"> <i\r\n");
      out.write("\t\tclass=\"ace-icon fa fa-angle-double-up icon-only bigger-110\"></i>\r\n");
      out.write("\t</a>\r\n");
      out.write("\r\n");
      out.write("\t<div></div>\r\n");
      out.write("\t<!-- /.main-container -->\r\n");
      out.write("\r\n");
      out.write("\t<!-- basic scripts -->\r\n");
      out.write("\t");
      out.write('	');
      out.write('	');

			String pathf = request.getContextPath();
			String basePathf = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ pathf + "/";
		
      out.write("\r\n");
      out.write("\t\t<!--[if !IE]> -->\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\twindow.jQuery || document.write(\"<script src='");
      out.print(basePathf);
      out.write("static/ace/js/jquery.js'>\"+\"<\"+\"/script>\");\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t<!-- <![endif]-->\r\n");
      out.write("\t\t<!--[if IE]>\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t window.jQuery || document.write(\"<script src='");
      out.print(basePathf);
      out.write("static/ace/js/jquery1x.js'>\"+\"<\"+\"/script>\");\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t<![endif]-->\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tif('ontouchstart' in document.documentElement) document.write(\"<script src='");
      out.print(basePathf);
      out.write("static/ace/js/jquery.mobile.custom.js'>\"+\"<\"+\"/script>\");\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t<script src=\"static/ace/js/bootstrap.js\"></script>");
      out.write("\r\n");
      out.write("\t<script src=\"static/ace/js/bootbox.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t<script src=\"static/ace/js/ace/ace.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t<script src=\"static/ace/js/date-time/bootstrap-datepicker.js\"\r\n");
      out.write("\t\ttype=\"text/javascript\"></script>\r\n");
      out.write("\t<script src=\"static/ace/js/chosen.jquery.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"static/js/jquery.tips.js\"></script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("$(top.hangge());\r\n");
      out.write("\r\n");
      out.write("function initCtrl(){\r\n");
      out.write("\t$(\"#C_NAME\").val(\"\");\r\n");
      out.write("\t$(\"#C_LGID\").val(\"\");\r\n");
      out.write("\t$(\"#C_TEL\").val(\"\");\r\n");
      out.write("\tsearch();\r\n");
      out.write("}\r\n");
      out.write("function search(){\r\n");
      out.write("\t$(\"#NAME\").val($(\"#C_NAME\").val());\r\n");
      out.write("\t$(\"#LG_ID\").val($(\"#C_LGID\").val());\r\n");
      out.write("\t$(\"#TEL\").val($(\"#C_TEL\").val());\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#customerEditForm\").attr(\"action\", \"sys/listCustomer\");\r\n");
      out.write("\t$(\"#customerEditForm\").submit();\r\n");
      out.write("}\r\n");
      out.write("function delCustomer(_id, _name, _lg){\r\n");
      out.write("\tvar options = {\r\n");
      out.write("\t\t\tmessage: \"<div class='item-row'><div class='item-label'>登录名 :</div>\" + \r\n");
      out.write("\t\t\t\t\t \"<div class='item-value'>\" + _lg + \"</div></div>\" +\r\n");
      out.write("\t\t\t\t\t \"<div class='item-row'><div class='item-label'>姓名 :</div>\" + \r\n");
      out.write("\t\t\t\t\t \"<div class='item-value'>\" + _name + \"</div></div>\" +\r\n");
      out.write("\t\t\t\t\t \"<div class='item-row'><div class='item-info'>您确定删除吗?</div></div>\",\r\n");
      out.write("\t\t\ttitle: \"删除用户\"\r\n");
      out.write("\t}\r\n");
      out.write("\tbootbox.confirm(options, function(result) {\r\n");
      out.write("\t\tif(result) {\r\n");
      out.write("\t\t\ttop.jzts();\r\n");
      out.write("\t\t\tvar url = \"");
      out.print(basePath);
      out.write("sys/deleteCustomer?ID=\" + _id;\r\n");
      out.write("\t\t\t$.get(url,function(data){\r\n");
      out.write("\t\t\t\tif(data.result == \"success\")\r\n");
      out.write("\t\t\t\t\tnextPage('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.currentPage}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("');\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("function viewCustomer(_id, _name, _lg, _tel, _email, _address){\r\n");
      out.write("\t\r\n");
      out.write("\t$('#MD_LGID').text(_lg);\r\n");
      out.write("\t$('#MD_NAME').text(_name);\r\n");
      out.write("\t$('#MD_TEL').text(_tel);\r\n");
      out.write("\t$('#MD_EMAIL').text(_email);\r\n");
      out.write("\t$('#MD_ADDRESS').text(_address);\r\n");
      out.write("\t$('#viewModal').modal(\"show\");\r\n");
      out.write("\t\r\n");
      out.write("}\r\n");
      out.write("$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});\r\n");
      out.write("\r\n");
      out.write("jQuery(function() {\r\n");
      out.write("\t$(\"#C_NAME\").val('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pd.NAME}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("');\r\n");
      out.write("\t$(\"#C_LGID\").val('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pd.LG_ID}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("');\r\n");
      out.write("\t$(\"#C_TEL\").val('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pd.TEL}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("');\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
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
