Given /I have entered (.*) into the calculator/ do |n|
	calculator = Calculator.new
	@res = calculator.push(n.to_i)
end

When /I press add/ do
end

Then /^the result should be (\d+)$/ do |expected|
	expected ==  @res 
end

