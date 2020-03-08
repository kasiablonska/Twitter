<%--
  Created by IntelliJ IDEA.
  User: Kasia
  Date: 2020-03-07
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="include/meta.jsp"/>
    <title>Twitter - dodawanie arykulu</title>
</head>
<body>

<jsp:include page="include/header.jsp"/>

<main role="main">
    <form style="padding-top: 100px; width: 560px; margin: auto;"
          class="form-add-article" action="add-article" method="post">
    <label for="inputConcent" class="sr-only">Tresc</label>
    <textarea rows="10" id="inputConcent" name="content" class="form-control" placeholder="Tresc" required autofocus></textarea>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Wyslij</button>

    </form>
</main>


<jsp:include page="include/footer.jsp"/>


</body>
</html>

