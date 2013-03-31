#!/bin/sh

FILES=$(ls | sed '/.*html/d' | sed '/.*woff/d' | sed '/.*sh/d' | xargs)

for file in $FILES
do
	convert $file -resize 800x600 $file
done
