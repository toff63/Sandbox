-module(hello_world_srv).
-export([init/0, handle/2]).

init() -> {}.

handle(Request, OldState) ->
    {io:format("Hey ~s~n", [Request]), OldState}.
