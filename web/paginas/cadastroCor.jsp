<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="${pageContext.request.contextPath}/CadastroCorServlet">
            <div>    
            <label for="id da Cor">Id da Cor:</label>
            <input type="text" name="idCor"/>
            </div>
            <div>
                <label for="nome da cor">Nome da Cor:</label>
            <input type="text" name="nomeCor"/>
            <input type="submit" name="ok"/>
            </div>
        </form>
    </body>
</html>
