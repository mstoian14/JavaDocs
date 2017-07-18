package ro.teamnet.zth.web;

import ro.teamnet.zth.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Boolean.valueOf;
import static java.util.stream.Collectors.toList;

/**
 * TODO Write javadoc
 */
@MultipartConfig
public class ImportFileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  1: Obtain the username from the request instance
        String userName = request.getParameter("user");


        // Obtain the File object from the request instance
        Part file = request.getPart("uploadFile");

        // read the lines from CSV file and print the values
        //  2: Replace T with Person
        List<Person> personsFromFile = readLines(file);

        // Set the response type
        response.setContentType("text/html");

        //  6: Print a nice message to the response so the user will be notified of the result
        // TIP: The final text printed on the response should be something like this: "Hello <username>! You successfully imported 4 people. "
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<html>");
        printWriter.println("<body>");
        printWriter.println("<h1> Hello: " + userName + "! You successfully imported 4 people.</h1>");
        printWriter.println("</body>");
        printWriter.println("</html>");


    }

    /**
     * TODO write javadoc
     * @param file
     * @return
     */
    private List<Person> readLines(Part file) {
        List<Person> persons = new ArrayList<>();

        //  3: Replace with try-with-resources
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            Stream<String> stream = bufferedReader.lines();
            ) {
            persons = stream.map(s -> s.split(","))
                    .map(pers -> (new Person(pers[0], pers[1], new Long(pers[2]), valueOf(pers[3]))))
                    .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  4: Iterate through the lines of the reader using java streams.
        // TIP: Use map to get the current line
        // TIP: Use split() method for each line (check API documentation)
        // TIP: For Long and Boolean fields you should use valueOf() method
        // TIP: Use Collectors to return a List

        // after implementing the list, let's print it. It will print nicely if you have overridden the toString() method ;)
        persons.forEach(System.out :: println);

//        5: Sort the persons list by their age field
        // TIP: use lambda expression (only one line of code is needed to complete this step)
        persons.sort((Person p1, Person p2) -> p1.getAge().compareTo(p2.getAge()));
        // let's print again to check if it's sorted
        persons.forEach(System.out :: println);

        return persons;
    }

    private class T {
    }
}
