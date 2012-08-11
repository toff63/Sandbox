-module(echo_otp_srv).
-behaviour(gen_server).
%% API
-export([start/0, stop/0, echo/1, hello/1]).
%% gen_server callbacks
-export([init/1, handle_call/3, handle_cast/2,
         handle_info/2, terminate/2, code_change/3]).

-record(state, {}).

%% API
start() -> gen_server:start_link({local, ?MODULE}, ?MODULE, [], []).
stop() -> gen_server:call(?MODULE, stop).

echo(Message) -> gen_server:call(?MODULE, {echo, Message}).
hello(Name) -> gen_server:call(?MODULE, {hello, Name}).

%% gen_server callbacks

%% Initializes the server
init([]) -> {ok, #state{}}.

%% Handling call messages in a remote call procedure manner (rpc)
handle_call({echo, Message}, _From, State) ->
    Reply = string:concat("Received: ", Message),
    {reply, Reply, State};
handle_call({hello, Name}, _From, State) ->
    Reply = string:concat("Hello ", Name),
    {reply, Reply, State}.

%% Handling cast messages:  call with no return value
handle_cast(_Msg, State) -> {noreply, State}.

%% Handling all non call/cast messages
handle_info(_Info, State) -> {noreply, State}.

%% called when gen_server stop. It should remove any state
terminate(_Reason, _State) ->  ok.

%% Convert process state when code is changed
code_change(_OldVsn, State, _Extra) -> {ok, State}.
