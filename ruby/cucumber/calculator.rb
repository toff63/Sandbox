class Calculator
	def push(n)
		@args ||= []
		@args << n
		sum = @args.inject(:+)
	end

end
