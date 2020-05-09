$(document).ready(function() {
	$('.ddd').mask('00');
	$('.number').mask('000000000');
	$('#form-user').submit(AddUserView().sendFormUser);
	$('#btn-add-phone').click(AddUserView().addPhone);
	let urlSearchParams = new URLSearchParams(window.location.search);
	if (AddUserView().isAlterForm() ) {
		let id = Helper().queryStringPageCurrent().get('id');
		UserService()
			.get(id)
			.then(AddUserView().fillForm)
			.then(AddUserView().changeTextButton)
			.then(AddUserView().changeEvent);
	}
}); 

function AddUserView() {

	return {
		'changeEvent': function() {
			$('#form-user').off('submit');
			$('#form-user').submit(AddUserView().sendFormAlterUser);
		},
		'changeTextButton': function() {
			$('#btn-submit').text('Alterar');
		}, 
		'isAlterForm': function() {
			return Helper().queryStringPageCurrent().has('id');
		},
		'fillForm': function(response) {
			$('[name="id"').val(response.id);
			$('[name="email"]').val(response.email)
			$('[name="name"]').val(response.name);
			$('[name="password"]').val(response.password);
			response
				.phones
				.forEach((phone, index) => {
					if (!index) {
						$('[name="phone_ddd"]').val(phone.ddd);
						$('[name="phone_number"]').val(phone.number);
						$('[name="phone_type"]').val(phone.type);
					} else {
						AddUserView()
						.createInputPhone(phone.ddd, phone.number, phone.type);
					}
				});
		},
		'removePhoneInput': function (event) {
			$(this).parent().parent().remove();
		},
		'createInputPhone': function(ddd, number, type) {
			const $form = $('#form-div-phones');
			$form.append($('<div class="form-group row form-group-new"><label  class="col-sm-2 col-form-label">Telefone</label>'
							+ '<div class="col-sm-2">'
							+ '<input name="phone_ddd" value="' + ddd + '" type="text" class="form-control ddd" id="ddd">'
							+ '</div>'
							+ '<div class="col-sm-3">'
							+ '	<input name="phone_number" value="' + number + '" type="text" class="form-control number" id="number">'
							+ '</div>'
							+ '<div class="col-sm-3">'
							+ '	<select name="phone_type" value="' + type +'" class="form-control type">'
							+ '		<option value="Celular">Celular</option> '
							+ '		<option value="Whatsapp">Whatsapp</option> '
							+ '		<option value="Comercial">Comercial</option> '
							+ '	</select>' + 
							'</div>'
							+ '<div class="col-sm-2">'
							+ '<button class="btn btn-danger btn-remove" type="button">Remover</button>'
							+'</div>'
							+'</div>'));
			$form.find('.type option[value=' + type + ']').attr('selected','selected');
			$form.find('.ddd').mask('00');
			$form.find('.number').mask('000000000');
			$('.btn-remove').off('click');
			$('.btn-remove').click(AddUserView().removePhoneInput);
		},
		'addPhone' : function(event) {
			AddUserView().createInputPhone('', '', '');
			
		},
		'sendFormAlterUser' : function(event) {
			event.preventDefault();
			const $form = $(this);
			const data = $form.serializeFormJSON();
			const phones = [];
			$('[name="phone_ddd"]').each(function(index, element) {
				phones.push({
					'ddd': $(element).val() || 0
				});
			});
			$('[name="phone_number"]').each(function(index, element) {
				phones[index]['number'] = $(element).val();
			});
			$('[name="phone_type"]').each(function(index, element) {
				phones[index]['type'] = $(element).val();
			});
			data['phones'] = phones;
			const body = JSON.stringify(data); 
			UserService()
				.update(body) 
				.then(response => {
					window.history.back();
				}).catch((error) => {
					$('#message-send-form').text('');
					const data = error.responseJSON;
					const status = error.status;
					if (400 == status) {
						const $listError = $('#list-error');
						$listError
							.empty();
						data.forEach((error) => {
							$listError
							.append('<li class="text-danger">' + error + '</li>');
						}); 
					}
				});
		}, 
		'sendFormUser' : function(event) {
			event.preventDefault();
			const $form = $(this);
			const data = $form.serializeFormJSON();
			delete data['id'];
			const phones = [];
			
			$('[name="phone_ddd"]').each(function(index, element) {
				phones.push({
					'ddd': $(element).val() || 0
				});
			});
			$('[name="phone_number"]').each(function(index, element) {
				phones[index]['number'] = $(element).val();
			});
			$('[name="phone_type"]').each(function(index, element) {
				phones[index]['type'] = $(element).val();
				
			});
			
			data['phones'] = phones;
			const body = JSON.stringify(data); 
			UserService()
				.save(body) 
				.then(response => {
					$('.form-group-new').each((index, element) => {
						$(element).remove();
					});
					Helper().clearFields($form);
					$('#list-error').empty();
					$('#message-send-form')
						.removeClass('text-danger')
						.text('O Usuário foi salvo com sucesso!')
						.addClass('text-success');
				}).catch((error) => {
					$('#message-send-form').text('');
					const data = error.responseJSON;
					const status = error.status;
					if (400 == status) {
						const $listError = $('#list-error');
						$listError
							.empty();
						data.forEach((error) => {
							$listError
							.append('<li class="text-danger">' + error + '</li>');
						}); 
					}
				});
		}
	}
}