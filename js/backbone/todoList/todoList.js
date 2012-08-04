(function(){

	var Item = Backbone.Model.extend({
		defaults: {
			part1: 'hello',
			part2: 'world'
		}
	});

	var List = Backbone.Collection.extend({
		model: Item
	});

	var ItemView = Backbone.View.extend({
		tagName: 'li', // name of orphan root tag  in this this.el
		initialize: function(){
			_.bindAll(this, 'render');
		},
		render: function(){
			$(this.el).html('<span>' + this.model.get('part1') + ' ' + this.model.get('part2') + '</span>');
			return this; // this is just for chainable calls like .render().el
		}
	
	});

	var ListView = Backbone.View.extend({
		el: $('body'), // attach this.el to an existing element

		events: {
			'click button#add': 'addItem'
		},

		initialize: function () {
			_.bindAll(this, 'render', 'addItem', 'appendItem'); // fixes loss of context for 'this' within methods

			this.collection = new List();
			this.collection.bind('add', this.appendItem); // collection event binder

			this.counter = 0;
			this.render(); // so it renders itself :)
		},

		render: function(){
			var self = this;
			$(this.el).append('<button id="add">Add list item</button>');
			$(this.el).append("<ul></ul>");
			_(this.collection.models).each(function(item) {
				self.appendItem(item);
			}, this);
		},

	  addItem: function(){
			this.counter++;
			var item = new Item();
			item.set({
				part2: item.get('part2') + ' ' + this.counter // modify the default object
			});
			this.collection.add(item); // add item to collection: view is updated via event 'add'
		},

		appendItem: function(item){
			var itemView = new ItemView({model: item});
			$('ul', this.el).append(itemView.render().el);
		}
	});

var ListView = new ListView();
}());
