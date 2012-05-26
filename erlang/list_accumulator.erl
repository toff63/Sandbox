-module(list_accumulator).
-export([list_odd_and_even_acc/1]).

list_odd_and_even_acc(L) -> list_odd_and_even_acc(L, [], []).
list_odd_and_even_acc([H | T], Odds, Evens) ->
	case H rem 2 of
		1 -> list_odd_and_even_acc(T, [H | Odds], Evens);
		0 -> list_odd_and_even_acc(T, Odds, [H | Evens])
	end;
list_odd_and_even_acc([], Odds, Evens) -> {lists:reverse(Odds), lists:reverse(Evens)}.
