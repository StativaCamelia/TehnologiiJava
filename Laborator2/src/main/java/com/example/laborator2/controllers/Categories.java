package com.example.laborator2.controllers;

import com.example.laborator2.bean.Category;
import com.example.laborator2.service.CategoryService;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * Controller for the category resource
 */
@WebServlet(name = "category", value = "/category")
public class Categories extends HttpServlet {

    private CategoryService categoryService;

    public void init() {

        categoryService = CategoryService.getCategoryService();
    }

    /**
     *  Method that handles the get request for the category resource
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        List<Category> categoryList = categoryService.getCategories();
        request.setAttribute("listCategory", categoryList);

        String cookieCategory = request.getParameter("category");

        request.setAttribute("cookieAttribute", cookieCategory);
        request.setAttribute("listCategory", categoryList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("input.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}