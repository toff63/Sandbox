-module(record_fun).
-export([clear_status/1]).

-record(todo, {who="me", status="reminder", text=""}).

clear_status(#todo{status=S, who=W} = R) ->
	%% used pattern matching to put status in S and who in W
	R#todo{status=finished}.
