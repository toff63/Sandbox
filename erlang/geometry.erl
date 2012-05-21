-module(geometry).
-export([area/1]).

area({rectangle, Width, Height}) -> Width * Height;
area({circle, Radius}) 					 -> Radius * Radius * 3.14159;
area({square, Side})  					 -> Side * Side.
