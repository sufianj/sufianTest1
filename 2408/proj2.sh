#!/bin/bash 
inputfile=$1
if [ $# -ne 1 ];then
echo "ERROR:Number of Argument is $#!It needs 1 argument"
exit 1
fi
page_lang="lang="
page_title="<title>" 
page_key1='class="mw-changeslist-date"'
page_key2="class='history-user'"
page_key3='class="history-size"'
awk -F'[<>]' '{for(i=1;i<=NF;i++){
if($i~/'$page_lang'/) 
{printf("%s\n",$(i))}
}}' $inputfile>temp1.txt
awk -F'["]' '{for(i=1;i<=NF;i++){
if($i~/'$page_lang'/) 
{printf("%s\n",$(i+1))}
}}' $inputfile>temp1.txt
lang=`sort temp1.txt|uniq|grep "^[a-zA-Z]"`
title=`grep "<title>" $inputfile|cut -d '<' -f 3|cut -d '>' -f 2`
awk -F'[<>]' '{for(i=1;i<=NF;i++){
if($i~/'$page_key1'/) 
{printf("%s\n",$(i+1))}
}}' $inputfile>date.txt
awk -F'[<>]' '{for(i=1;i<=NF;i++){
if($i~/'$page_key2'/) 
{printf("%s\n",$(i+3))}
}}' $inputfile>user.txt
awk -F'[<>]' '{for(i=1;i<=NF;i++){
if($i~/'$page_key3'/) 
{printf("%s\n",$(i+1))}
}}' $inputfile>size.txt
num_modification=`wc -l date.txt|tr -s " "|cut -d " " -f 2`
echo $num_modification
latest=`sed -n 1p date.txt`
oldest=`sed -n "$num_modification"p date.txt`
echo "Title:$title">result.txt
echo "Language:$lang">>result.txt
echo "Nomber of modification:$num_modification">>result.txt
echo "Latest modification time:$latest">>result.txt
echo "Oldest modification time:$oldest">>result.txt
awk 'BEGIN{print "------------------------------------------------------"}'>>result.txt
awk 'BEGIN{print "Modification Time\t\tUser\t\tSize"}'>>result.txt
awk -F'[<>]' '{for(i=1;i<=NF;i++){
if($i~/'$page_key1'/)
{printf("%s\t\t",$(i+1))}
if($i~/'$page_key2'/) 
{printf("%s\t",$(i+3))}
if($i~/'$page_key3'/)
{printf("%s\n",$(i+1))}
}}' $inputfile>>result.txt
