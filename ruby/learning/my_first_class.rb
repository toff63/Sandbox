class Person
	def initialize(name="Bob")
		@name = name
	end

	def name
		@name
	end

	def name=(name)
		@name = name
	end
end

person = Person.new
puts "#{person.name}"
person.name = "Christophe"
puts "#{person.name}"

class Book
	attr_accessor :author, :title
	def initialize(title, author)
		@author = author
		@title = title
	end

	def to_s
		"Book: Title: " + @title  + "\nAuthor: " + @author
	end
end

puts "#{Book.new("Programming ruby", "Dave Thomas")}"
