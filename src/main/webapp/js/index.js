$(document).ready(function() {
	UserView().renderUsers();
}); 
function UserView() {

	return {
		'renderUsers': function() {
			const $tbody = $('#table-body');
			UserService()
			.getAll()
			.then(response => {
				UserView().renderTable(response, $tbody);
			});
		},
		'removeUser': function(event) {
			const $el = $(this).parent().parent();
			const id = $el.attr('data-id');
			UserService()
				.remove(id)
				.then(UserView().renderUsers);
		},
		'renderTable' : function(users, $el) {
			$('.btn-remove-user').off('click');
			$el.empty();
			for (i = 0; i < users.length; i++) {
				const alterUrl = './add-user.jsp?id=' + users[i].id;
				const $tr = '<tr data-id="' + users[i].id + '">'
						+ '<td>' + users[i].id + '</td>' 
						+ '<td>' + users[i].name + '</td>' 
						+ '<td>' + users[i].email + '</td>' 
						+ '<td>' + users[i].password + '</td>'
						+ '<td>' + Helper().phonesToString(users[i].phones) + '</td>' 
						+ '<td>'
						+   '<button class="btn-remove-user btn btn-danger">remover</button>'
						+ '</td>'
						+ '<td>'
						+   '<a class="btn btn-primary" href="' + alterUrl + '">alterar</a>'
						+ '</td>'
					+ '</tr>';
				$el.append($tr);
			}
			$('.btn-remove-user').click(UserView().removeUser);
		}
	}
}