Student Grade Tracker

Overview:
The Student Grade Tracker is a Java-based program that allows teachers to input student grades and automatically compute useful statistics, such as the average, highest, and lowest scores. The program uses an ArrayList to store the student grades and provides a simple, interactive console interface for user input.

Features:
•	Add Grades: Teachers can input grades for each student, which are stored in a dynamic ArrayList.
•	Compute Statistics:
o	Average Grade: Automatically calculates the average grade of all students.
o	Highest Grade: Determines the highest grade from the list of students.
o	Lowest Grade: Identifies the lowest grade among the entered student grades.

Technologies Used:
•	Java: Core programming language used to implement the logic.
•	ArrayList: For dynamic storage and management of student grades.
•	Scanner: To accept user input for the number of students and their respective grades.
•	Collections: Utility class used to calculate the highest and lowest grades.

How to Run the Program:

1.	Ensure that you have Java installed on your system.
2.	Compile the Java program using the following command:
    javac Main.java
3.	Run the compiled program:
    java Main
4.	The program will prompt you to enter the number of students, followed by each student's grade.
5.	Once all grades are entered, the program will display the average, highest, and lowest grades.

Code Structure
•	Main Class:
o	The main method handles user interaction, accepting grades and displaying the calculated statistics.
•	Helper Methods:
o	calculateAverage: Computes the average of all grades.
o	calculateHighest: Finds the maximum grade.
o	calculateLowest: Finds the minimum grade.

Example Output:

    Enter the number of students: 3
    Enter grade for student 1: 85
    Enter grade for student 2: 90
    Enter grade for student 3: 78
    Average grade: 84.33
    Highest grade: 90
    Lowest grade: 78

Future Enhancements:
•	Implement a user-friendly interface using Java Swing or JavaFX for a more interactive experience.
•	Add functionality to save and load grades from a file for persistence.
•	Ability to handle input errors and ensure grades are within valid ranges.
•	Option to export the grades and statistics to a file for record-keeping.
•	Add more statistical analysis, such as grade distribution and median calculation.