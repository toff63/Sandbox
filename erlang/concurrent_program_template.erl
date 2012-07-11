-module(concurrent_program_template).
-compile(export_all).

start()->
	spawn(concurrent_program_template, loop, []).

rpc(Pid, Request) ->
	Pid ! {self(), Request},
	receive
		{Pid, Response} ->
			Response
	end.

loop() ->
	receive
		Any ->
			io:format("Receive: ~p~n", [Any]),
			loop()
	end.
