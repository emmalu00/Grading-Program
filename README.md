# Grading-Program
This program takes in student information from a text file and uses it to calculate grades. Each line of the text files used for this program follow the following format:
>  _student first name, student last name, student ID#, midterm exam1 score, midterm exam2 score, ... ,final exam score_

### First, the program validates that the file exists and reads in the information.
The user is prompted to enter the name of a text file that will be read by the program. If the file entered by the user is not found in the directory, the program will loop until the user enters a text file that is found in the directory. Once a valid filename is entered, the program reads the student's information into a 2D array.
### Next, the program displays relevant information about each student.
The program takes all the information found in the text file to display the following for each student:
1. Last Name, First Name
1. Student ID#
1. Grade percentage based off the midterm and exam scores for that student
1. Letter grade given based off that student's percentage

If a student has an invalid score on one or more of their exams, then an error is displayed, and that student's information is not shown.

### Next, a class summary is displayed.
Once all students information has been read and displayed, the program displays a class summary, which includes:
1. The total number scores
2. The distribution of letter grades among the students
3. The overall average grade percentage among the students

Students with invalid exam scores are not considered in the class summary.
### Finally, the user is asked to enter an output file.
Information on each student is written to the output file, each on a newline, in the following format:
> _student first and last name, student ID#_

The program prompts the user to enter an output file to write to. If the user enters a file that does not exist, the program creates a new file and writes information to that file. If the user enters a file that does exist, the program asks the user if they would like to append of the existing file. If the user says yes, the information will be appended to the existing file. If the user does not wish to append to the existing file, the user is given the option to create a new file.
***
_Proj5_Test1.txt_ contins information for 3 midterm exams with a maximum score of 50 points each, and one final exam that has a maximum score of 100. _Proj5_Test2.txt_ contains information for 2 midterm exams with a maximum score of 100 points each, and one final exam that has a maximum score of 100. Because the number and maximum scores of of the midterm exams differ between both test files, some code must be modified within the Project5 class in order to properly test each text file. The number of midterm exams and the maximum score for a midterm are held as constants in the Project5 class, denoted by NUM_COURSE_EXAMS, and COURSE_EXAM respectively. When running the program with _Proj5_Test1.txt_, NUM_COURSE_EXAMS should be set to 3, and COURSE_EXAM should be set to 50. When running the program with _Proj5_Test2.txt_, NUM_COURSE_EXAMS should be set to 2, and COURSE_EXAM should be set to 100.
