# TehnologiiJava

## Homework 1

1. Start the server, and from any browser access: http://localhost:8080/lab1-1.0-SNAPSHOT/, there will be a form with the following data: key, value, sync and mock and the expected behavior will be the following: 
  * if mock is true than the servlet simply returns a confirmation message.
  * if mock is false, the servlet writes in a text file called repository a line containing the key, repeated value times, along with the timestamp of the request, and returns the content of the repository, as an HTML page containing all the lines that were created, ordered by key.
  * if sync is false, then the servlet will not use any synchronized method when writing in the file.

2.  The server can be invoked by running the ServerCaller(a Java desktop application), this application will call the server with different values for the sync parameter
3.  With the results from the previos point using the plot, Python project we can plot the times resulted by calling the server with different sync params and plot the result so we can see the difference in the execution time

## Homework 2

1.  Start the server, and from any browser access: http://localhost:8081/Laborator2_war/, there will be a form with the following data: key, value and category. The category is read from a JSON file. When the user clicks the submit button, the data associated with the record will be saved in another JSON file.
2.  We have a web filter that logs data about the request, the data can be seen in the console and a web filter that adds the test "filtered" at the beggining and at the end of the html response
3.  We can see that if we set none for the category then the actual value will be animal, a value that it's set as a context init parameter and it is read at the servlet startup
4.  We can see by inspecting the html file that after submit there will be a cookie with the category

## Homework 3

1.  Start the server, and from any browser access: http://localhost:8080/JavaServerFaces/, there will a form with the following buttons:
 * Create Exam -> will open a form where you can create a new exam
 * Create Student -> there you can add a new student
 * See Students -> you can see the students and the associated exam in a data table
 * See exams -> you can see all the exams in a data table
 * A dropdown where you can select the language

All of these are implemented usign the PrimeFaces framework
