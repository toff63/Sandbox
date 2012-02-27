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

book = Book.new("Programming ruby", "Dave Thomas")
puts "#{book}"

book.freeze # prevent modifications to the object => immutable
book.author = "Me" # should return an error telling you cannot modify 
									# a frozen object
