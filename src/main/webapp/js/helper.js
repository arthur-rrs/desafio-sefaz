(function($) {
	$.fn.serializeFormJSON = function() {

		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};
})(jQuery);

function Helper() {
	return {
		'phonesToString': function(phones) {
			return phones.reduce( (str, phone) => {
				str += phone.type + ' - ' + '(' + phone.ddd + ') ' + phone.number + '<br>';
				return str;
			}, ''); 
		},
		'clearFields': function ($el) {	
			$el.find('.form-control').each((index, element) => {
				$(element).val('');
			});
		},
		'queryStringPageCurrent': function() {
			let urlSearchParams = new URLSearchParams(window.location.search);
			return urlSearchParams;
		},
		'validateForm': function(validators) {
			
		} 
	}
};
