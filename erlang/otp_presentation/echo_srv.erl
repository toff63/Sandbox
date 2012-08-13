-module(echo_srv).
-export([init/0, onMessage/2]).

init() -> {}.

onMessage(Request, OldState) ->
    {io:format("Received: ~s~n", [Request]), OldState}.
