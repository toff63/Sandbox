-module(echo_supervisor).

-behaviour(supervisor).

%% API
-export([start_link/0, start/0, start_in_shell_for_testing/0]).

%% Supervisor callbacks
-export([init/1]).

-define(CHILD(Id, Mod, Type, Args), {Id, {Mod, start_link, Args},
                                     permanent, 5000, Type, [Mod]}).
-define(MAX_RESTARTS, 3).
-define(TIME, 10).

%%%===================================================================
%%% API functions
%%%===================================================================

%%--------------------------------------------------------------------
%% @doc
%% Starts the supervisor
%%
%% @spec start_link() -> {ok, Pid} | ignore | {error, Error}
%% @end
%%--------------------------------------------------------------------
start_link() ->
    supervisor:start_link({local, ?MODULE}, ?MODULE, []).

start_in_shell_for_testing() ->
    {ok, Pid} = supervisor:start_link({local, ?MODULE}, ?MODULE, _Arg = []),
    unlink(Pid).

start() ->
    spawn(fun() ->
                supervisor:start_link({local, ?MODULE}, ?MODULE, _Arg = [])
          end).
%%%===================================================================
%%% Supervisor callbacks
%%%===================================================================

%%--------------------------------------------------------------------
%% @private
%% @doc
%% Whenever a supervisor is started using supervisor:start_link/[2,3],
%% this function is called by the new process to find out about
%% restart strategy, maximum restart frequency and child
%% specifications.
%%
%% @spec init(Args) -> {ok, {SupFlags, [ChildSpec]}} |
%%                     ignore |
%%                     {error, Reason}
%% @end
%%--------------------------------------------------------------------
init([]) ->
    %% first swap generic alarm handler to mine
    io:format("Starting supervisor.~n"),
    alarm_handler:delete_alarm_handler(alarm_handler),
    alarm_handler:add_alarm_handler(slang_event_handler),
    io:format("Alarm handler switched.~n"),
    io:format("Supervisor configuration:~n {ok, {{one_for_one, ~p, ~p}, [~p]}}~n", [?MAX_RESTARTS, ?TIME, ?CHILD(echo_server1, echo_server, worker, [])]),
    {ok, {{one_for_one, ?MAX_RESTARTS, ?TIME}, [?CHILD(echo_server1, echo_server, worker, [])]}}.

%%%===================================================================
%%% Internal functions
%%%===================================================================
