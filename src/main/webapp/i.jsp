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
            <span class="logo"><h1>РАБОЧИЙ ГРАФИК на ${requestScope.month} ${requestScope.year}</h1></span><span><h4 class="name">${requestScope.name}</h4></span>
            <div class="head">
                <form >
                    <input type="hidden" name="id" value="${requestScope.idParametr}">
                    <select name="year">
                        <c:forEach var="y" items="${requestScope.years}">
                            <option value="${y.toString()}"
                                    <c:if test="${y.equals(requestScope.year)}">selected</c:if> >${y.toString()}
                            </option>
                        </c:forEach>
                    </select>
                    <nav id="month">
                        <button type="submit" id="1" name="month" value="ЯНВАРЬ">ЯНВАРЬ</button>
                        <button type="submit" id="2" name="month" value="ФЕВРАЛЬ">ФЕВРАЛЬ</button>
                        <button type="submit" id="3" name="month" value="МАРТ">МАРТ</button>
                        <button type="submit" id="4" name="month" value="АПРЕЛЬ">АПРЕЛЬ</button>
                        <button type="submit" id="5" name="month" value="МАЙ">МАЙ</button>
                        <button type="submit" id="6" name="month" value="ИЮНЬ">ИЮНЬ</button>
                        <button type="submit" id="7" name="month" value="ИЮЛЬ">ИЮЛЬ</button>
                        <button type="submit" id="8" name="month" value="АВГУСГ">АВГУСГ</button>
                        <button type="submit" id="9" name="month" value="СЕНТЯБРЬ">СЕНТЯБРЬ</button>
                        <button type="submit" id="10" name="month" value="ОКТЯБРЬ">ОКТЯБРЬ</button>
                        <button type="submit" id="11" name="month"value="НОЯБРЬ">НОЯБРЬ</button>
                        <button type="submit" id="12" name="month" value="ДЕКАБРЬ">ДЕКАБРЬ</button>
                    </nav>
                </form>
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
                                <td <c:if test="${requestScope.name.equals(mainTable.getEmployees().get(i).getFullName())}">class="user" </c:if>>
                                        ${mainTable.getEmployees().get(i).getFullName()}
                                </td>
                                <c:forEach var="designation" items="${mainTable.getEmployees().get(i).getWorkSchedule()}">
                                    <td <c:if test="${requestScope.name.equals(mainTable.getEmployees().get(i).getFullName())}">class="user" </c:if>
                                        <c:if test="${designation.toString().equals(\"\")}">class="dayoff" </c:if>
                                        <c:if test="${designation.toString().equals(\"У\")}">class="morning" </c:if>
                                        <c:if test="${designation.toString().equals(\"В\")}">class="evening" </c:if>
                                        <c:if test="${designation.toString().equals(\"ОТ\")}">class="vacation" </c:if> >
                                            ${designation.toString()}
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </c:if>
                </c:if>
                <c:if test="${requestScope.mainTable eq null}">
                    <h3>График еще не сформирован. Укажите год и месяц!</h3>
                </c:if>
            </table>
        </main>

        <a href="/hello">Ссылка1</a>
        <a href="/id">Ссылка2</a>
    </body>
</html>