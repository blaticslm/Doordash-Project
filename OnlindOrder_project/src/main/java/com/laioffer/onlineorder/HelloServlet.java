package com.laioffer.onlineorder;

import org.json.JSONObject;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.apache.commons.io.IOUtils;
import com.laioffer.onlineorder.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //response.setContentType("text/html");
        /*
        // Hello
        String customer = request.getParameter("customer");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello " + customer + "</h1>");
        out.println("</body></html>");

         */
        /*
        JSONObject customer = new JSONObject();
        customer.put("Email", "mchengli@umich.edu");
        customer.put("First Name", "Mingcheng");
        customer.put("Last Name", "Li");
        customer.put("Age", 23);
        response.getWriter().print(customer);

         */

//        response.setContentType("application/json");
//        ObjectMapper mapper = new ObjectMapper(); //用于转json变成string
//        Customer customer= new Customer();
//        customer.setEmail("mchengli@umich.edu");
//        customer.setPassword("12345");
//        customer.setFirstName("Mingcheng");
//        customer.setLastName("Li");
//        customer.setEnabled(true);
//
//        response.getWriter().print(mapper.writeValueAsString(customer)); //把一个class转换成Json
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        //read coustomer infomation
//        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
//        String email = jsonRequest.getString("Email");
//        String firstName = jsonRequest.getString("First Name");
//        String lastName = jsonRequest.getString("Last Name");
//        int age = jsonRequest.getInt("Age");
//
//        //print costomer info to IDE
//        System.out.println("Email: " + email);
//        System.out.println("First Name: " + firstName);
//        System.out.println("Last Name: " + lastName);
//        System.out.println("Age: " + age);
//
//        //Return xtatuc ok as response body to the client
//        response.setContentType("application/json");
//        JSONObject jsonResponse = new JSONObject();
//        jsonResponse.put("Status","ok");
//        response.getWriter().print(jsonResponse);
//    }

    public void destroy() {
    }
}