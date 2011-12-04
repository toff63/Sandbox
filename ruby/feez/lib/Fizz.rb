class Fizz

	def initialize(num)
		@n = num
	end

	def fizz
		isMultiple3 = true
		isMultiple5 = true
		isMultiple3 = false unless @n % 3 == 0
		isMultiple5 = false unless @n % 5 == 0
		return 0 if @n == 0
		return "FizzBuzz" if isMultiple3 and isMultiple5
		return "Fizz" if isMultiple3
		return "Buzz" if isMultiple5
		@n
	end
	
end
