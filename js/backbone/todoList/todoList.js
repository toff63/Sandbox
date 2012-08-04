(function(){
	var ListView = Backbone.View.extend({
		el: $('body'), // attach this.el to an existing element

		initialize: function () {
			_.bindAll(this, 'render'); // fixes loss of context for 'this' within methods
			this.render(); // so it renders itself :)
		},

		render: function(){
			$(this.el).append("<ul><li>hello world</li></ul>");
		}
	});

var ListView = new ListView();
}());
