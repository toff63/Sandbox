-module(hello_world_srv).
-export([init/0, onMessage/2]).

init() -> {}.

onMessage(Request, OldState) ->
    {io:format("Hey ~s~n", [Request]), OldState}.
