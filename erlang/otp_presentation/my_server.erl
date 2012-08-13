-module(my_server).
-export([start/2, rpc/2, upgrade_code/2]).

start(Name, Mod) ->
    register(Name,
             spawn(fun() -> loop(Name, Mod, Mod:init()) end)).

rpc(Name, Request) ->
    Name ! {self(), Request},
    receive
        {Name, Response} -> Response
    end.

loop(Name, Mod, OldState) ->
    receive 
        {From, Request} -> 
            {Response, NewState} = Mod:onMessage(Request, OldState),
            From ! {Name, Response},
            loop(Name, Mod, NewState)
    end.

