<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Book List</title>
</head>
<body>

<div id="global">
    <h1>Book List</h1>
    <table>
        <tr>
            <th>Category</th>
            <th>Title</th>
            <th>ISBN</th>
            <th>Author</th>
        </tr>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.category.name}</td>
                <td>${book.title}</td>
                <td>${book.isbn}</td>
                <td>${book.author}</td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <table>
        <tr style="background:#ababff">
            <th>Category</th>
            <th>Title</th>
            <th>ISBN</th>
            <th>Author</th>
        </tr>
        <c:forEach items="${books}" var="book"
                   varStatus="status">
            <c:if test="${status.count%2 == 0}">
                <tr style="background:#eeeeff">
            </c:if>
            <c:if test="${status.count%2 != 0}">
                <tr style="background:#00deff">
            </c:if>
            <td>${book.category.name}</td>
            <td>${book.title}</td>
            <td>${book.isbn}</td>
            <td>${book.author}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
