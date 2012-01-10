
function isFizz(i) {
	return i % 3 === 0;
}

function isBuzz(i) {
	return i % 5 === 0;
}

function FizzBuzz(i) {
		if( isFizz(i) && isBuzz(i)) return "FizzBuzz";
		if (isFizz(i)) return "Fizz";
		if (isBuzz(i)) return "Buzz";
		return i;
}

function fizzBuzz(from, to) {
	var i = from;
	var buf = from;
	for(i = from +1; i <= to; i++){
		buf += ', ' + FizzBuzz(i);
	}
	return buf;
}

print(fizzBuzz(1, 20));
