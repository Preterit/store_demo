<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
            width: 100%;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }
    </style>
</head>

<body>

<!--
    描述：菜单栏
-->

<!--
    描述：导航条
-->
<jsp:include page="header.jsp"></jsp:include>


<div class="row" style="width:1210px;margin:0 auto;">
    <div class="col-md-12">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
        </ol>
    </div>
    <c:if test="${empty page.list}">

    <div class="col-md-12">
        <h1>暂无数据</h1>
    </div>

    </c:if>

    <c:if test="${not empty page.list}">
        <div class="col-md-12">
            <c:forEach items="${page.list}" var="p">
                <div class="col-md-2" style="padding: 0px;height: 250px;text-align: center;">
                    <a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${p.pid}">
                        <img src="${pageContext.request.contextPath}/${p.pimage}" width="170px" height="170px" style="display: inline-block;" />
                    </a>
                    <p style="width: 100%;height: 40px; "><a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${p.pid}" style='color:green;width: 100%;'>${p.pname}</a></p>
                    <p style="width: 100%;height: 16px; "><font color="#FF0000">商城价：&yen;${p.shop_price}</font></p>
                </div>
            </c:forEach>
        </div>
       <div style="width: 1210px; margin-top: 50px">
           <jsp:include page="pageFile.jsp"></jsp:include>
       </div>

    </c:if>

    <!--
           商品浏览记录:
     -->
    <div class="col-md-12" style="width:1210px;margin-top:50px; padding: 0 ;border: 1px solid #ddd;border-top: 2px solid #999;height: 246px;">

        <h4 style="width: 50%;float: left;">浏览记录</h4>
        <div style="width: 50%;float: right;text-align: right;"><a href="">more</a></div>
        <div style="clear: both;"></div>

        <div style="overflow: hidden;">

            <ul style="list-style: none;">
                <li style="width: 150px;height: 216px;float: left;margin: 0 8px 0 0;padding: 0 18px 15px;text-align: center;">
                    <img src="${pageContext.request.contextPath}/products/1/cs10001.jpg" width="130px" height="130px"/>
                </li>
            </ul>

        </div>
    </div>
    <div>
        <jsp:include page="footer.jsp"></jsp:include>
    </div>
</body>

</html>