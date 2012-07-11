-module(client_server_hello_world).
-export([start/0, cs_hello_world/2]).

start() -> spawn(fun loop/0).

cs_hello_world(Pid, What) ->
	rpc(Pid, What).

rpc(Pid, Request) ->
	Pid ! {self(), Request},
	receive
		{Pid, Response} ->
			Response
	end.

loop() ->
	receive
		{From, {english}} ->
			From ! {self(), "Hello World"},
			loop();
		{From, {french}} ->
			From ! {self(),"Bonjour le monde"},
			loop();
		{From, {portuguese}} ->
			From !  {self(), "Ola mundo"},
			loop()
end.
