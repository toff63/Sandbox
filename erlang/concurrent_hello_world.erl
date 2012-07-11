-module(concurrent_hello_world).
-export([c_hello_world/0]).

c_hello_world() ->
	receive
		{english} ->
			io:format("Hello World~n"),
			c_hello_world();
		{french} ->
			io:format("Bonjour le monde~n"),
			c_hello_world();
		{portuguese} ->
			io:format("Ola mundo~n"),
			c_hello_world()
end.
