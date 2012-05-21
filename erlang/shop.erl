-module(shop).
-export([cost/1, total/1, total_using_lists/1, total_comp_list/1]).
-import(lists, [map/2, sum/1]).

cost(oranges)		-> 5;
cost(newspaper) -> 8;
cost(apples) 		-> 2;
cost(pears) 		-> 9;
cost(milk) 			-> 7.

total([{What, N} | T]) -> cost(What) * N + total(T);
total([])              -> 0.

total_using_lists(L) -> sum(map(fun({What, N}) -> cost(What) * N end, L)).

total_comp_list(L) -> sum([cost(What) * N || {What, N} <- L]).
