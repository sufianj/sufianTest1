bzg@altern.org

List the files ending with "txt" in the current directory
search recursively in all files the string "new" and limit the result to five answers
print directly the n-last lines of a text file
create and access a directory in one command line

create a directory  and 3 differents sub directory in one command line. Moreover, the shell is supposed to return a message for every new directory. The directory will be accessible only for reading

Count the number of jpgs  in the directory and subs-directories :
~$ find . -name "*.jpg" | wc -l


search for the line:which contains a CAPITAL letter  followed by four lowercase letters.
>> check the grep documentation (something about the case of words / case sensitive)
>> size of the string (limited by 5 letters)
~$ grep "[A-Z][a-z]\{4\}" file.txt

How do you find all files in the current directory which content have been modified less than 24 hours ago?
find . -mtime -1

How do you find all files in the current directory that are newer than file x.txt ?

Set of checks to perform on a system?


- users should not be able to write files on other users home
- files should not have a change (ctime) in the future
- every week, send the list of new executable files to the sys admin

gedit : 1
vi : 2
emacs : 3
notepad++: 8
textmate : 
eclispe : 7
netbeans : 18
nano : 2
STS : 

man
--help
info
apropos

ls
mkdir
touch
less
rm
cd
pwd
echo
find
grep
xargs

chmod (chown chgrp adduser addgroup)

files permissions 1 2 4

>
>>
<
|

(find ... | xargs ...)

globbing

regular expressions


