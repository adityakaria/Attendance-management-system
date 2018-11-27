#!/bin/bash
echo -n "filename: "
read filename
if [ -f $filename.csv ]; then
    `head -2 $filename.csv` > swap1.csv
    `tail +3 $filename.csv | sort -u`> swap2.csv
    rm $filename.csv
    touch $filename.csv
    cat swap.csv > $filename.csv
    cat swap2.csv > $filename.csv
else
    echo "$filename does not exist"
fi