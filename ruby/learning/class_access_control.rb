class Myclass

	def method1  # default is public
	end

	protected

	def method2  # protected
	end

	def method3  # protected
	end


	public

	def method4  # public
	end


	private

	def method5  # private
	end
end


class Myclass2

	def method1  
	end

	def method2  
	end

	def method3  
	end


	def method4  
	end

	def method5  
	end

	public :method1, :method4
	protected :method2, :method3
	private :method5
end


class Book
	attr_reader :title  # accessor method 'title'
	protected :title    # only made accessible for book object, not to other objects
end
