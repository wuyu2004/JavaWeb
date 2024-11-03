<%@ page import="top.soft.bookonline.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<ul>
    <li>
        <a href="${pageContext.request.contextPath}/index">首页</a>
    </li>
    <li>
        <a href="#">读书</a>
    </li>
    <li>
        <a href="#">音乐</a>
    </li>
    <li>
        <a href="#">小组</a>
    </li>
    <li>
        <a href="#">同城</a>
    </li>
    <li>
        <a href="#">FM</a>
    </li>
    <li>
        <a href="#">时间</a>
    </li>
    <li>
        <a href="#">更多</a>
    </li>
</ul>

<ul>
    <%--根据user是否为空，显示不同的内容--%>
    <%
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
    %>
    <%
        if (user!= null) {
    %>
    <li>
        <a href="${pageContext.request.contextPath}/user">
            <img src="${user.avatar}" alt="" class="avatar">
        </a>
    </li>
    <li>
        <a href="#" onclick="logout()">退出</a>
    </li>
    <%
    } else {
    %>
    <a href="${pageContext.request.contextPath}/login-page">去登录</a>
    <%
        }
    %>
</ul>

<script>
    function logout() {
        if (confirm('确定要退出吗？')) {
            window.location.href = '${pageContext.request.contextPath}/logout';
        }
    }
</script>