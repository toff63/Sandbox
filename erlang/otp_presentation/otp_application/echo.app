%% This is the application resource file for the 'base' application.
{application, echo,
[{description, "A dummy echo server with a simple slang detection :p"},
 {vsn, "0.0.1"},
 {modules, [echo_app, echo_supervisor, echo_server, slang_event_handler]},
 {registered, [echo_server, echo_super]},
 {applications, [kernel, stdlib]},
 {mod, {echo_app, []}},
 {start_phases, []}
]}.
