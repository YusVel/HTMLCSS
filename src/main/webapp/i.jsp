<%@page import="Table.MainTable" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>


<!DOCTYPE html>
<html >
    <head lang="ru">
        <meta charset="UTF-8">
        <meta name="vieport" content="width=device-width, initial-scale=1.0">
        <title></title>
        <style> 
            <%@include file="css/style.css"%>
        </style>
    </head>
    <body> 
        <header class="left_offset">
            <span class="logo"><h1>РАБОЧИЙ ГРАФИК на ${requestScope.month} ${requestScope.year}</h1></span>
            <div class="head">
                <nav class="year">
                    <span>2026</span>
                </nav>
                 <nav class="month">
                    <span>ЯНВАРЬ</span>
                    <span>ФЕВРАЛЬ</span>
                    <span>МАРТ</span>
                    <span>АПРЕЛЬ</span>
                    <span>МАЙ</span>
                    <span>ИЮНЬ</span>
                    <span>ИЮЛЬ</span>
                    <span>АВГУСГ</span>
                    <span>СЕНТЯБРЬ</span>
                    <span>ОКТЯБРЬ</span>
                    <span>НОЯБРЬ</span>
                    <span>ДЕКАБРЬ</span>
            </nav>
            </div>
        </header>
        <main>
            <table id="table">
                <c:if test="${requestScope.mainTable ne null}">
                    <c:if test="${requestScope.mainTable.getEmployees().size()!=0}">
                        <tr>
                            <th> ФИО </th>
                            <c:forEach var="i" begin="0" end="${requestScope.mainTable.getEmployees().get(1).getWorkSchedule().size()-1}">
                                <th>${i+1}</th>
                            </c:forEach>
                        </tr>
                        <c:forEach var="i" begin="0" end="${requestScope.mainTable.getEmployees().size()-1}" >
                            <tr>
                                <TD>${mainTable.getEmployees().get(i).getFullName()}</TD>
                                <c:forEach var="designation" items="${mainTable.getEmployees().get(i).getWorkSchedule()}">
                                    <td>
                                            ${designation.toString()}
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </c:if>
                </c:if>
            </table>
        </main>

        <a href="/hello">Ссылка1</a>
        <a href="/id">Ссылка2</a>
    </body>
</html>