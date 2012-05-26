%% problem
filter(P,[H | T]) -> filter1(P(H), H, T, P);
filter(P,[]) -> [].

filter1(true, H, T, P)  -> [H, filter(P, T)];
filter1(false, H, T, P) -> filter(P, T).

%% it sucks!! you have to create a second function to implement filter

%% using case
filter_case(P, [H | T]) ->
	case P(H) of
		true 	->	[H, filter_case(P,T)];
		false ->	filter_case(P,T)
	end;
filter_case(P, []) -> [].

my_max(X, Y) ->
	if X > Y -> X;
		X =< Y -> Y
	end.

