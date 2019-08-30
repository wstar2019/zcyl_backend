<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<c:if test="${!page.onePage}||1==1">--%>
    <nav>
        <ul class="pagination">
            <li>
                <a href="${basePath}/${page.pageUrl}&currentPage=1" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach  begin="${page.startPage}" end="${page.totalPage}" var="i">
                <li <c:if test="${page.currentPage==i}">class="active"</c:if>> <a href="${basePath}/${page.pageUrl}&currentPage=${i}">${i}</a></li>
            </c:forEach>
            <li>
                <a href="${basePath}/${page.pageUrl}&currentPage=${page.totalPage}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <li>
                <a href="#" aria-label="Next">
                    共${page.totalPage}页
                </a>
            </li>
        </ul>
    </nav>
<%--</c:if>--%>