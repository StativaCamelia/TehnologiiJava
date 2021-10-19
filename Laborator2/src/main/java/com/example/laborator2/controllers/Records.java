package com.example.laborator2.controllers;

import com.example.laborator2.bean.Category;
import com.example.laborator2.bean.Record;
import com.example.laborator2.service.RecordService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller for the record resource
 */
@WebServlet(name = "record", value = "/record")
public class Records extends HttpServlet {

    private RecordService recordService;

    public void init() {
        recordService = RecordService.getRecordServiceInstance();
    }

    /**
     * Method that handles the post calls for the record resource, if category is none then it sets the category with a value from the application context
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String key = request.getParameter("key");
        String value = request.getParameter("value");
        String category = request.getParameter("category");

        if(category.equals("none")){

            System.out.println("here");
            category = (String) getServletContext().getAttribute("category");
        }

        recordService.postRecord(value, key, new Category(category));

        List<Record> recordList = recordService.getRecord();
        request.setAttribute("recordList", recordList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
        dispatcher.forward(request, response);
    }

}
