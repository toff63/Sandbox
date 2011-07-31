require "Fizz"
require "test/unit"

class TestFizz < Test::Unit::TestCase

	def test_1
		assert_equal(1, Fizz.new(1).fizz)
	end

	def test_0
		assert_equal(0, Fizz.new(0).fizz)
	end

	def test_3
		assert_equal("Fizz", Fizz.new(3).fizz)
	end

	def test_5
		assert_equal("Buzz", Fizz.new(5).fizz)
	end

	def test_6
		assert_equal("Fizz", Fizz.new(6).fizz)
	end

	def test_7
		assert_equal(7, Fizz.new(7).fizz)
	end

	def test_9
		assert_equal("Fizz", Fizz.new(9).fizz)
	end

	def test_10
		assert_equal("Buzz", Fizz.new(10).fizz)
	end

	def test_15
		assert_equal("FizzBuzz", Fizz.new(15).fizz)
	end

	def test_all_multiple_from_3
		(1..11).each do |i|
			unless i == 5 or i == 10
				assert_equal("Fizz", Fizz.new(3*i).fizz, (3*i).to_s + " doesn't return Fizz")
			end
		end
	end

	def test_all_multiple_from_5
		(1..11).each do |i|
			unless i == 3 or i == 6 or i == 9
				assert_equal("Buzz", Fizz.new(5*i).fizz, (5*i).to_s + " doesn't return Buzz")
			end
		end
	end

	def test_all_multiple_from_5_and_3
		(1..10).each do |i|
			assert_equal("FizzBuzz", Fizz.new(5*3*i).fizz, (5*3*i).to_s + "doesn't return FizzBuzz") 
		end
	end
end
