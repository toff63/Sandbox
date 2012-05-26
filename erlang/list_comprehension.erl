-module(list_comprehension).
-export([quicksort/1, pythag/1, word_permutation/1]).

quicksort([]) -> [];
quicksort([PIVOT | T]) -> 
	quicksort([X || X <- T, X < PIVOT]) 
	++ [PIVOT] ++ 
	quicksort([X || X <- T, X >= PIVOT]).

pythag(N) -> 
	[{A,B,C} || 
		A <- lists:seq(1,N),
		B <- lists:seq(1,N),
		C <- lists:seq(1,N),
		A+B+C =< N,
		A*A + B*B =:= C*C
	].

word_permutation([]) -> [[]];
word_permutation(L) -> [[H | T] || H <- L, T <- word_permutation(L--[H])].
