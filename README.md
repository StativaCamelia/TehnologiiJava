# TehnologiiJava

## Homework 1

1. Start the server, and from any browser access: http://localhost:8080/lab1-1.0-SNAPSHOT/, there will be a form with the following data: key, value, sync and mock and the expected behavior will be the following: 
  * if mock is true than the servlet simply returns a confirmation message.
  * if mock is false, the servlet writes in a text file called repository a line containing the key, repeated value times, along with the timestamp of the request, and returns the content of the repository, as an HTML page containing all the lines that were created, ordered by key.
  * if sync is false, then the servlet will not use any synchronized method when writing in the file.

2.  The server can be invoked by running the ServerCaller(a Java desktop application), this application will call the server with different values for the sync parameter
3.  With the results from the previos point using the plot, Python project we can plot the times resulted by calling the server with different sync params and plot the result so we can see the difference in the execution time
