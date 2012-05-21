-module(control_abstraction).
-export([for/3]).

for(MAX, MAX, F) -> [F(MAX)];
for(I, MAX, F)   -> [F(I) | for(I+1, MAX, F)].
