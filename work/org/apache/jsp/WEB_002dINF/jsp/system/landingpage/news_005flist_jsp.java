/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.29
 * Generated at: 2019-06-13 00:31:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.system.landingpage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class news_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(6);
    _jspx_dependants.put("jar:file:/G:/EclipsePhotonWorkArray/jee-photon/ZCYL/src/main/webapp/WEB-INF/lib/jstl-1.2.jar!/META-INF/fmt.tld", Long.valueOf(1153356282000L));
    _jspx_dependants.put("/WEB-INF/jsp/system/landingpage/../index/foot.jsp", Long.valueOf(1534542862000L));
    _jspx_dependants.put("/WEB-INF/jsp/system/landingpage/../index/top.jsp", Long.valueOf(1544232589518L));
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

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

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
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fchoose.release();
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
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
      out.write("<style type=\"text/css\">\r\n");
      out.write(".btn.btn-app.btn-xs {\r\n");
      out.write("\twidth: 64px;\r\n");
      out.write("\tfont-size: 12px;\r\n");
      out.write("\tborder-radius: 6px;\r\n");
      out.write("\tpadding-bottom: 2px;\r\n");
      out.write("\tpadding-top: 2px;\r\n");
      out.write("\tline-height: 1.45;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<head></head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"page-header\">\r\n");
      out.write("\t\t<ul class=\"navi\">\r\n");
      out.write("\t\t\t<li><a>首页管理</a><span> ≫ </span></li>\r\n");
      out.write("\t\t\t<li><a target=\"mainFrame\" href=\"fp/listDPRKNews\">朝鲜新闻</a></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"page-content\" style=\"padding: 10px;\">\r\n");
      out.write("\t\t<div class=\"container-fluid\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<form action=\"fp/listNews\" method=\"post\" name=\"newsForm\" id=\"newsForm\">\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<table id=\"simple-table\" class=\"table table-hover\" style=\"margin-top: 5px;\">\r\n");
      out.write("\t\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"center\" style=\"width: 10px;\"></th>\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"center\" style=\"width: 80px;\">序号</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"center\" style=\"width: 150px;\">标题</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"center\" style=\"width: 100px;\">发布者</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"center\" style=\"width: 100px;\">发布时间</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"center\" style=\"width: 100px;\">浏览数</th>\r\n");
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
              // /WEB-INF/jsp/system/landingpage/news_list.jsp(64,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_c_005fwhen_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty newsList}", boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null)).booleanValue());
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
                    // /WEB-INF/jsp/system/landingpage/news_list.jsp(69,8) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
                    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/system/landingpage/news_list.jsp(69,8) '${newsList}'",_jsp_getExpressionFactory().createValueExpression(_jspx_page_context.getELContext(),"${newsList}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
                    // /WEB-INF/jsp/system/landingpage/news_list.jsp(69,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                    _jspx_th_c_005fforEach_005f0.setVar("news");
                    // /WEB-INF/jsp/system/landingpage/news_list.jsp(69,8) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
                          out.write("\t\t\t\t\t\t\t\t\t<tr id=\"");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${news.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("\">\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t<td class=\"center art-check\">\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t\t");
                          if (_jspx_meth_c_005fif_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
                            return;
                          out.write("\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t\t");
                          if (_jspx_meth_c_005fif_005f1(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
                            return;
                          out.write("\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t\t\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t</td>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t<td class=\"center\">");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(page.currentPage -1) * page.showCount + numStds}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("</td>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t<td class=\"center art-name\">");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${news.sn_title}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("</td>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t<td class=\"center art-desc\">");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${news.sn_man}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("</td>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t<td class=\"center art-desc\">");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${news.sn_datetime}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("</td>\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t<td class=\"center art-desc\">");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${news.view_num}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("</td>\r\n");
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
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<a href=\"#\" id=\"btn-scroll-up\"\r\n");
      out.write("\t\tclass=\"btn-scroll-up btn btn-sm btn-inverse\"> <i\r\n");
      out.write("\t\tclass=\"ace-icon fa fa-angle-double-up icon-only bigger-110\"></i>\r\n");
      out.write("\t</a>\r\n");
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
      out.write("\t<script src=\"static/ace/js/date-time/bootstrap-datepicker.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t<script src=\"static/ace/js/chosen.jquery.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"static/js/jquery.tips.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("<script>\r\n");
      out.write("\t$('input[type=\"checkbox\"]').on('change', function(event){\r\n");
      out.write("\t  \tvar myId = $(this).attr('id');\r\n");
      out.write("\t  \tvar _state = \"\";\r\n");
      out.write("\t  \tvar _msg = \"\";\r\n");
      out.write("\t  \tif(event.target.checked){\r\n");
      out.write("\t\t\t_state = \"1\";\r\n");
      out.write("\t  \t\t_msg = \"你 显示 消息吗?\";\r\n");
      out.write("\t  \t}else{\r\n");
      out.write("\t\t\t_state = \"0\";\r\n");
      out.write("\t\t\t_msg = \"你 不显示 消息吗?\";\r\n");
      out.write("\t  \t} \r\n");
      out.write("\t  \tupdateState(myId, _state, _msg);\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\tfunction updateState(_id, _state, _msg){\r\n");
      out.write("\t\tvar options = {\r\n");
      out.write("\t\t\t\tmessage: \"<p><span class='item-info'>\" + _msg + \"</span></p>\",\r\n");
      out.write("\t\t\t\ttitle: \"选择消息通知\"\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tbootbox.confirm(options, function(result) {\r\n");
      out.write("\t\t\tif(result) {\r\n");
      out.write("\t\t\t\ttop.jzts();\r\n");
      out.write("\t\t\t\tvar url = \"");
      out.print(basePath);
      out.write("fp/update_news_vs?ID=\" + _id + \"&TRAVELER_FP_VIEW=\" + _state;\r\n");
      out.write("\t\t\t\t$.get(url,function(data){\r\n");
      out.write("\t\t\t\t\tif(data.result == \"success\")\r\n");
      out.write("\t\t\t\t\t\t$(top.hangge());\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
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

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    boolean _jspx_th_c_005fif_005f0_reused = false;
    try {
      _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
      // /WEB-INF/jsp/system/landingpage/news_list.jsp(76,11) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${news.traveler_fp_view == 1 }", boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null)).booleanValue());
      int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
      if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"checkbox\" id=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${news.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
          out.write("\" checked>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      _jspx_th_c_005fif_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fif_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fif_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    boolean _jspx_th_c_005fif_005f1_reused = false;
    try {
      _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
      // /WEB-INF/jsp/system/landingpage/news_list.jsp(79,11) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${news.traveler_fp_view == 0 }", boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null)).booleanValue());
      int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
      if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"checkbox\" id=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${news.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
          out.write("\">\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      _jspx_th_c_005fif_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fif_005f1, _jsp_getInstanceManager(), _jspx_th_c_005fif_005f1_reused);
    }
    return false;
  }
}
