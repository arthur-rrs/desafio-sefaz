<!DOCTYPE html>
<html>
<%
	String error = "";
	if (null != request.getAttribute("error")) {
		error = (String) request.getAttribute("error");	
	} 
%>
<head>
<title>Desafio SEFAZ</title>
<script type="text/javascript" src="vendor/jquery/jquery-3.5.1.min.js"></script>
<link rel="stylesheet"
	href="vendor/bootstrap-4.4.1-dist/css/bootstrap.min.css">
<script type="text/javascript" src="vendor/jquery/jquery-3.5.1.min.js"></script>
</head>

<body>
	<div class="container">
		<div class="row mt-5">
			<div class="col-md-4 offset-md-4 col-12 p-3 border rounded">
				<p class="h5 text-center">Login</p>
				<form id="form-login" method="post" action="./login">
					<div class="form-group">
						<label for="email">Email</label> <input type="email" name="email"
							class="form-control">
					</div>
					<div class="form-group">
						<label for="password">Senha</label> <input type="password"
							name="password" class="form-control">
					</div>
					<button type="submit" class="btn btn-primary">Entrar</button>
					<p class="text-danger text-center pt-2">
						<%=error%>
					</p>
				</form>
				<p>
					Ou crie um novo usuário clicando <a href="add-user.jsp">aqui</a>
				</p>
			</div>

		</div>
	</div>
</body>
</html>