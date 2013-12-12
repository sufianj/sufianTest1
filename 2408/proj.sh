#!/bin/bash 
page_key1='class="mw-changeslist-date"'
page_key2="class='history-user'"
page_key3='class="history-size"'
awk 'BEGIN{print "------------------------------------------"}'>temp_wiki.txt
awk 'BEGIN{print "Modification Time\t\tUser\t\tSize"}'>>temp_wiki.txt
awk -F'[<>]' '{for(i=1;i<=NF;i++){if($i~/'$page_key1'/)
{print $(i+1)}
if($i~/'$page_key2'/) 
{print $(i+3)}
if($i~/'$page_key3'/)
{print $(i+1)}
}}' wikihistory2.txt>>temp_wiki.txt
