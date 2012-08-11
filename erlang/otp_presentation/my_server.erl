-module(my_server).
-export([start/2, rpc/2, upgrade_code/2]).

start(Name, Mod) ->
    register(Name,
             spawn(fun() -> loop(Name, Mod, Mod:init()) end)).

upgrade_code(Name, NewMod) -> rpc(Name, {upgrade_code, NewMod}).


rpc(Name, Request) ->
    Name ! {self(), Request},
    receive
        {Name, Response} -> Response
    end.

loop(Name, Mod, OldState) ->
    receive 
        {From, {upgrade_code, NewMod}} ->
            From ! {Name, ack},
            loop(Name, NewMod, OldState);
        {From, Request} -> 
            {Response, NewState} = Mod:handle(Request, OldState),
            From ! {Name, Response},
            loop(Name, Mod, NewState)
    end.

