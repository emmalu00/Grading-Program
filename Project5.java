/**
* Project5.java
* Emma Lucas
* This program reads in text files which contain student information. Each line of the 
* file contains the first name, last name, ID#, and exam scores of each student, separated
* by commas. The last score is the final exam score. The program displays the student's name, 
* ID#, exam percentage, and letter grade, followed by a summary of letter grades in the class
* and an average exam percentage. The program outputs the students basic info to an output file.
*/

import java.util.*;
import java.io.*;

public class Project5 
{
    public static void main (String[] args) throws IOException
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Filename of the file containing student data: ");
        File inFile = new File(s.nextLine());
        while (!(inFile.exists()))
        {
            System.out.print("Invalid filename. Please re-enter: ");
            inFile = new File(s.nextLine());
        }
        Scanner f = new Scanner(inFile);
        final int COURSE_EXAM = 50; // 50 for test1 file, 100 for test 2 file
        final int NUM_COURSE_EXAMS = 3; // 3 for test1 file, 2 for test 2 file
        final int MAX_STUDENTS = 50;
        final int FINAL_EXAM = 100;
        final int POINTS_POSSIBLE = (COURSE_EXAM * NUM_COURSE_EXAMS) + FINAL_EXAM;
        String[][] info = new String [MAX_STUDENTS][4 + NUM_COURSE_EXAMS];

        System.out.println();
        System.out.println("***Class Results***");
        int p = 0;
        double totExamScores = 0;
        while (f.hasNextLine() && p < MAX_STUDENTS)
        {
            String line = f.nextLine();
            StringTokenizer st = new StringTokenizer(line, ",");
            int numTokens = st.countTokens();
                for (int i = 0; i < numTokens; ++i)
                {
                    info[p][i] = st.nextToken();
                }
            p++;
        }

        int numA = 0;
        int numB = 0;
        int numC = 0;
        int numD = 0;
        int numF = 0;
        int totalExams = 0;
        for (int n = 0; n < p; ++n)
        {
            boolean courseValid = true;
            boolean finalValid = true;
            // checking if course exam scores are valid
            for (int i = 3; i < info[n].length - 2; ++i)
            {
                if (Integer.parseInt(info[n][i]) > COURSE_EXAM || Integer.parseInt(info[n][i]) < 0) 
                {
                    courseValid = false;
                }
            } 
            // checkig if final exam scores are valid
            if (Integer.parseInt(info[n][info[n].length - 1]) > FINAL_EXAM || Integer.parseInt(info[n][info[n].length - 1]) < 0)
            {
                finalValid = false;
            }            
            int exams = 0;
            for (int i = 3; i < info[n].length; ++i)
            {
                exams += Integer.parseInt(info[n][i]);
            }
            double examP = (exams / (double)POINTS_POSSIBLE) * 100;
            char letterGrade = ' ';
            if (courseValid && finalValid)
            {
                if (examP >= 90 && examP <= 100)
                {
                    letterGrade = 'A';
                    numA++;
                }
                else if (examP >= 80 && examP < 90)
                {
                    letterGrade = 'B';
                    numB++;
                }
                else if (examP >= 70 && examP < 80)
                {
                    letterGrade = 'C';
                    numC++;
                }
                else if (examP >= 60 && examP < 70)
                {
                    letterGrade = 'D';
                    numD++;
                }
                else if (examP < 60 && examP >= 0)
                {
                    letterGrade = 'F';
                    numF++;
                }
            }

            if (courseValid && finalValid)
            {
                System.out.println(info[n][1].toUpperCase() + ", " + info[n][0].toUpperCase() + "\n" + info[n][2]);
                System.out.printf("Exam Percentage: %.1f%%\n", examP);
                System.out.println("Final Grade: " + letterGrade);
                totExamScores += examP;
                totalExams++;
            }
            else if (!courseValid || !finalValid)
            {
                if (!courseValid)
                {
                    System.out.println("***Course Exam socre(s) invalid for " + info[n][0] + " " + info[n][1]);
                }
                if (!finalValid)
                {
                    System.out.println("***Final Exam socre invalid for " + info[n][0] + " " + info[n][1]);
                }
            }
            System.out.print("Press enter to display next student...");
            String enter = s.nextLine();
            System.out.println();
        }

        System.out.println();
        System.out.println("***Class Summary***\nTotal number of scores: " + totalExams);
        System.out.println("\tTotal number of A's: " + numA);
        System.out.println("\tTotal number of B's: " + numB);
        System.out.println("\tTotal number of C's: " + numC);
        System.out.println("\tTotal number of D's: " + numD);
        System.out.println("\tTotal number of F's: " + numF);
        double A = (numA / (double)totalExams) * 100;
        double B = (numB / (double)totalExams) * 100;
        double C = (numC / (double)totalExams) * 100;
        double D = (numD / (double)totalExams) * 100;
        double F = (numF / (double)totalExams) * 100;
        System.out.println();
        System.out.printf("Individual grade percentages: \n\tA: %.1f%%\n\tB: %.1f%%\n\tC: %.1f%%\n\tD: %.1f%%\n\tF: %.1f%%\n", A, B, C, D, F);
        System.out.println();
        double averageScore = (totExamScores / totalExams);
        System.out.printf("Overall Average Score = %.1f%%\n", averageScore);

        System.out.println();
        boolean keepGoing = true;
        while (keepGoing)
        {
            System.out.print("Enter filename for output: ");
            File outFile = new File(s.nextLine());
            if (outFile.exists())
            {
                FileWriter fw = new FileWriter(outFile);
                System.out.print("Do you wish to append to the existing file? (y/n): ");
                char append = s.nextLine().toLowerCase().charAt(0);
                if (append == 'y')
                {
                    System.out.println("Appending to file...");
                    for (int i = 0; i < p; i++)
                    {   
                        fw.append(info[i][0] + " " + info[i][1] + ", WID#: " + info[i][2] + "\n");
                    }
                    keepGoing = false;
                }
                else
                {
                    System.out.println("Information was not appended to file.");
                    System.out.print("Do you want to create a new file? (y/n): ");
                    char newFile = s.nextLine().toLowerCase().charAt(0);    
                    if (newFile == 'y')
                    {
                        keepGoing = true;
                    }
                }
                fw.close();
            }
            else
            {
                FileWriter fw = new FileWriter(outFile);
                System.out.println("Creating new file...");
                for (int i = 0; i < p; i++)
                {   
                    fw.append(info[i][0] + " " + info[i][1] + ", WID#: " + info[i][2] + "\n");
                }
                keepGoing = false;
                fw.close();
            }
        }
    }
}