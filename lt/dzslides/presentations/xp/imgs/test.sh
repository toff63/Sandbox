#!/bin/sh

FILES=$(ls *.gif)

for file in $FILES
do
	FILE_NAME=$file.png
	echo $FILE_NAME
#	convert $file -resize 800x600 $file
done
