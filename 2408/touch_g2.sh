#!/bin/bash

if [ `ls -l new.txt | wc -l` -eq 0 ]; then
  touch new.txt    # if no create an empty file
fi

touch new.txt    # if no create an empty file
ls -l new.txt    # to show the file time record
touch new.txt    # to update the access and modification time to the current time
stat new.txt
touch -t 201304171415 new.txt # to set a certain time instead of the current time
ls -l new.txt    # to show the time record
touch a.txt  # create a new file a.txt
touch -r a.txt new.txt # change the last time modification of new.txt with the a.txt file's, same as the command --file
ls -l new.txt
touch -d '2013-04-17 15:11:11' new.txt # to set the date time, you must choose a posterior date, same as the command --date
ls -l new.txt
touch -c new1.txt # if the file does not exist, no create
touch -a new.txt # change only the access time
stat new.txt
touch -m new.txt # change only the modification time
stat new.txt
touch -a -t 201304171450 new.txt # set a certain time instead of the current time as the access time
stat new.txt

if ls|grep a.txt; then
 touch a-1.txt
else
 touch t.txt
fi

if [ -e a.txt ];then
  echo "File a.txt exists"
fi


for((i = 1 ; i <= 10 ; i++))
do 
 touch testfile_$i.txt
 echo "$i "
done
#  touch a.txt;
#  echo "File a.txt created"

 
