(function(){


	var ListView = Backbone.View.extend({
		el: $('body'), // attach this.el to an existing element

		events: {
			'click button#add': 'addItem'
		},

		initialize: function () {
			_.bindAll(this, 'render'); // fixes loss of context for 'this' within methods

			this.counter = 0;
			this.render(); // so it renders itself :)
		},

		render: function(){
			$(this.el).append('<button id="add">Add list item</button>');
			$(this.el).append("<ul></ul>");
		},

	  addItem: function(){
			this.counter++;
			$('ul', this.el).append('<li>hell world ' + this.counter + '</li>');
		}
	});

var ListView = new ListView();
}());
