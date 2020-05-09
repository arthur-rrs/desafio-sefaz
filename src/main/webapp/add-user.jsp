<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<div class="col-md-8 offset-md-2 col-12">
				<p class="h5">Usuários</p>
				<form id="form-user">
					<input type="hidden" name="id" value="null" />
					<div class="form-group row">
						<label for="name" class="col-sm-2 col-form-label">Nome</label>
						<div class="col-sm-10">
							<input name="name" type="text" class="form-control" id="name">
						</div>
					</div>
					<div class="form-group row">
						<label for="email" class="col-sm-2 col-form-label">Email</label>
						<div class="col-sm-10">
							<input name="email" type="email" class="form-control" id="email">
						</div>
					</div>
					<div class="form-group row">
						<label for="password" class="col-sm-2 col-form-label">Senha</label>
						<div class="col-sm-10">
							<input name="password" type="password" class="form-control"
								id="password">
						</div>
					</div>
					<div id="form-div-phones">
						<div class="form-group row">
							<label class="col-sm-2 col-form-label">Telefone</label>
							<div class="col-sm-2">
								<input name="phone_ddd" type="text"
									class="form-control ddd is-mask" id="ddd">
							</div>
							<div class="col-sm-3">
								<input name="phone_number" type="text"
									class="form-control number is-mask" id="number">
							</div>
							<div class="col-sm-3">
								<select name="phone_type" class="form-control">
									<option></option>
									<option value="Celular">Celular</option>
									<option value="Whatsapp">Whatsapp</option>
									<option value="Comercial">Comercial</option>
								</select>
							</div>
							<div class="col-sm-2">
								<button id="btn-add-phone" type="button" class="btn btn-primary">Incluir</button>
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary" id="btn-submit">Salvar</button>
				</form>
				<p id="message-send-form" class="text-center mt-3"></p>
				<ul id="list-error" class="mt-2">
				</ul>
			</div>
		</div>
	</div>
	<script src="vendor/jquery-mask/jquery.mask.min.js"></script>
	<script src="js/helper.js"></script>
	<script src="js/services.js"></script>
	<script src="js/add-user.js"></script>
</body>
</html>