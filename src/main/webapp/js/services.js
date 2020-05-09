function UserService() {
	return {
		'save' : function(data) {
			return $.when($.ajax('./register', {
				data : data,
				contentType : 'application/json',
				accepts : 'application/json',
				method : 'POST'
			}));
		},
		'getAll' : function() {
			return $.when($.ajax('./user', {
				contentType : 'application/json',
				accepts : 'application/json',
				dataType : 'json'
			}));
		},
		'remove' : function(id) {
			return $.when($.ajax('./user?id=' + id, {
				contentType : 'application/json',
				accepts : 'application/json',
				dataType : 'json',
				method : 'DELETE'
			}));
		},
		'get' : function(id) {
			return $.when($.ajax('./user?id=' + id, {
				contentType : 'application/json',
				accepts : 'application/json',
				dataType : 'json'
			}));
		},
		'update' : function(data) {
			console.log(data);
			return $.when($.ajax('./user', {
				contentType : 'application/json',
				accepts : 'application/json',
				dataType : 'json',
				method : 'PUT',
				data : data
			}));
		}
	};
};
