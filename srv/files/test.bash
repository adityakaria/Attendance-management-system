#!/bin/bash
filename=$1
if [ -f $filename ]; then
    head -2 $filename > swap.csv 
    tail +3 $filename | sort | uniq >> swap.csv
    rm $filename
    cat swap.csv > $filename
    
else
    echo "$filename does not exist"
fi