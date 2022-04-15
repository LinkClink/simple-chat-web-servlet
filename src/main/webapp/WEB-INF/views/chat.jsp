<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='/WEB-INF/views/css/main.css' %>
</style>
<html>
<head>
    <title>Simple chat</title>
</head>
<body>
<%@include file="logoutHeader.jsp" %>
<br>
<h2 class="error">${errormsg}</h2>
<div id="chat-container">
    <h1 style="display: inline-block" class="title">Username: </h1>
    <h2 class="username">${username}</h2>
    <table>
        <c:forEach var="message" items="${messages}">
            <tr>
                <td>
                    <c:out value="${message.dataTime}"/>
                </td>
                <td style="color: #8111f8">
                    <c:out value="${message.username}"/>
                </td>
                <td class="message">
                    <c:out value="${message.content}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form id="messageForm" name="messageForm" method="POST">
        <div class="input-message">
            <input type="text" name="message" autocomplete="off"
                   placeholder="Type message"/>
            <button type="submit" value="send" name="button">Send</button>
            <button type="submit" value="refresh" name="button">Refresh</button>
        </div>
    </form>
    <%
        response.setIntHeader("Refresh", 10); // page refresh after 10 second
    %>
</div>
</body>
</html>
