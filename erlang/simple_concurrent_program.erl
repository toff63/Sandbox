-module(simple_concurrent_program).
-compile(export_all).

start() -> spawn(fun() -> loop([]) end).

rpc(Pid, Request) ->
	Pid ! {self(), Request},
	receive
		{Pid, Response} -> Response
	end.

loop(X) ->
	receive
		Any ->
			io:format("Receive:~p~n", [Any]),
			loop(X)
	end.
