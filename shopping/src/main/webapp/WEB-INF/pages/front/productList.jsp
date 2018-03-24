<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include/taglib.jsp" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>网上商城</title>
    <link href="${ctxJsAndCss}/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${ctxJsAndCss}/css/product.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="container header">
        <%@ include file="/WEB-INF/pages/front/main/menu.jsp"%>
    </div>

    <div class="container productList">
        <!-- 左侧边栏的一二级类目列表 -->
        <div class="span6">
            <div class="hotProductCategory">
                <c:forEach var="c" items="${sessionScope.category1List}">
                    <dl>
                        <!-- 一级类目 -->
                        <dt>
                            <a href="${ctx}/findProductListByCategory1Id/${c.category1Id}">
                                ${c.category1Name}
                            </a>
                        </dt>
                        <!-- 当前一级类目下的二级类目 -->
                        <c:forEach var="cs" items="${c.category2List}">
                            <dd>
                                <a href="${ctx}/findProductListByCategory2Id/${cs.category2Id}">
                                    ${cs.category2Name}
                                </a>
                            </dd>
                        </c:forEach>
                    </dl>
                </c:forEach>
            </div>
        </div>

        <!-- 右侧边栏的所有二级类目 -->
        <div class="span18 last">
            <!-- 各项商品 -->
            <form id="productForm" action="">
                <div id="result" class="result table clearfix">
                    <ul>
                        <c:forEach var="p" items="${productList}">
                            <li>
                                <a href="${ctx}/getProductDetail/${p.productId}">
                                    <img src="${p.imgSrc}" width="170" height="170" style="display: inline-block;" />
                                    <span style='color: green'>${p.productName}</span>
                                    <span class="price"> 商城价： ￥${p.salePrice}</span>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <!-- 分页 -->
                <div class="pagination">
                    <!-- 当前是第几页/共多少页 -->
                    <span>第 <c:out value="${page}" />/${count}页</span>
                    <!-- 首页 -->
                    <span>
                        <a class="firstPage" href="${ctx}/findByCid/${cid}/1"></a>
                    </span>
                    <!-- 上一页 -->
                    <c:if test="${page != 1}">
                        <span>
                            <a class="previousPage" href="${ctx}/findByCid/${cid}/${page-1}"></a>
                        </span>
                    </c:if>
                    <!-- 具体的页数 -->
                    <c:forEach var="i" begin="1" end="${count }">
                        <span>
                           <!-- 如果是当前页则不能够点击 -->
                           <c:if test="${i == page }">
                                <span class="currentPage">${page }</span>
                           </c:if>
                           <c:if test="${i != page }">
                             <a href="${ctx}/findByCid/${cid}/${i}">
                                ${i}
                             </a>
                           </c:if>
                        </span>
                    </c:forEach>

                    <!-- 下一页 -->
                    <c:if test="${page != 1 }">
                        <span>
                            <a class="nextPage" href="${ctx}/findByCid/${cid}/${page+1}"></a>
                        </span>
                    </c:if>

                    <!-- 尾页 -->
                    <a class="lastPage" href="${ctx}/findByCid/${cid}/${count}"></a>
                </div>
            </form>
        </div>
    </div>
    <div class="container footer"></div>
</body>
</html>