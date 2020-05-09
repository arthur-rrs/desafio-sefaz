<!DOCTYPE html>
<html>

<head>
<title>Desafio SEFAZ</title>
<script type="text/javascript" src="vendor/jquery/jquery-3.5.1.min.js"></script>
<link rel="stylesheet"
	href="vendor/bootstrap-4.4.1-dist/css/bootstrap.min.css">
</head>

<body>
	<div class="container">
		<div class="row mt-5">
			<div class="col-md-8 offset-md-2 col-12 p-2">
				<a class="btn btn-primary mb-3" href="add-user.jsp">Novo Usuário</a>
				<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th>#</th>
							<th>Nome</th>
							<th>E-Mail</th>
							<th>Senha</th>
							<th>Telefones</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody id="table-body">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script src="js/helper.js"></script>
	<script src="js/services.js"></script>
	<script src="js/index.js"></script>
</body>
</html>