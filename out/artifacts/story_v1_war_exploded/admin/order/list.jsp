<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        function showDetail(oid) {
            var $val = $("#but" + oid).val();

            if ($val == "订单详情") {
                // ajax 显示图片,名称,单价,数量
                <%--$("#div" + oid).append("<img width='60' height='65' src='${pageContext.request.contextPath}/products/1/c_0028.jpg'>&nbsp;xxxx&nbsp;998<br/>");--%>
                var url = "/store_demo/AdminOrderServlet"
                var obj = {"method":"findOrderByOidWithAjax","oid":oid}
                $.post(url,obj,function (data){
                    //利用jqery遍历服务器响应的数据
                    $.each(data,function (i, obj) {
                        var temp = "<img width='30' height='30' src='${pageContext.request.contextPath}/"+obj.product.pimage+"'>&nbsp;"+obj.product.pname+"&nbsp;"+obj.total+"<br/>";
                        $("#div" + oid).append(temp);
                    })
                },"json");
                $("#but" + oid).val("关闭");
            } else {
                $("#div" + oid).html("");
                $("#but" + oid).val("订单详情");
            }
        }
    </script>
</HEAD>
<body>
<br>
<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
    <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3">
                <strong>订单列表</strong>
            </TD>
        </tr>

        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all"
                       bordercolor="gray" border="1" id="DataGrid1"
                       style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr
                            style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

                        <td align="center" width="10%">
                            序号
                        </td>
                        <td align="center" width="10%">
                            订单编号
                        </td>
                        <td align="center" width="10%">
                            订单金额
                        </td>
                        <td align="center" width="10%">
                            收货人
                        </td>
                        <td align="center" width="10%">
                            订单状态
                        </td>
                        <td align="center" width="50%">
                            订单详情
                        </td>
                    </tr>
                    <c:if test="${empty page.list}">
                        <tr>
                            暂无订单
                        </tr>
                    </c:if>
                    <c:if test="${not empty page.list}">
                        <c:forEach items="${page.list}" var="order" varStatus="status">
                            <tr onmouseover="this.style.backgroundColor = 'white'"
                                onmouseout="this.style.backgroundColor = '#F5FAFE';">
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="10%">
                                    ${status.count}
                                </td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="20%">
                                   ${order.oid}
                                </td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="10%">
                                    ${order.total}
                                </td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="10%">
                                        ${order.name}
                                </td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="10%">
                                    <c:if test="${order.state==1}"><a href="#"> 未付款</a></c:if>
                                    <c:if test="${order.state==2}"><a href="#"> 发货</a></c:if>
                                    <c:if test="${order.state==3}"><a href="#"> 已发货</a></c:if>
                                    <c:if test="${order.state==4}"> 订单完成</c:if>
                                </td>
                                <td align="center" style="HEIGHT: 22px">
                                    <input type="button" value="订单详情" id="but${order.oid}" onclick="showDetail('${order.oid}')"/>
                                    <div id="div${order.oid}">

                                    </div>
                                </td>

                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </td>
        </tr>
        <tr align="center">
            <td colspan="7">

            </td>
        </tr>
        </TBODY>
    </table>
    <jsp:include page="/jsp/pageFile.jsp"></jsp:include>
</form>
</body>
</HTML>

