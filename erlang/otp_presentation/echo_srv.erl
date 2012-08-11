-module(echo_srv).
-export([init/0, handle/2]).

init() -> {}.

handle(Request, OldState) ->
    {io:format("Received: ~s~n", [Request]), OldState}.
