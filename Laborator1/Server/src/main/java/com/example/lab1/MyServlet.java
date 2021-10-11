package com.example.lab1;

import com.example.lab1.helper.FileHelper;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "myServlet", value = "/myServlet")
public class MyServlet extends HttpServlet {

    String htmlTemplate;
    private static final Logger logger = Logger.getLogger ("MyServlet");

    public void init() {

        htmlTemplate = "<html>" +
                "<head>" +
                "<title>Mock is true</title>" +
                "</head>" +
                "<body>" +
                "<p> %s </p>" +
                "</body>" +
                "</html>";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<String> webAgent = Arrays.asList("Mozilla", "Opera", "Chrome", "Safari");
        logServerData(request);
        if(webAgent.contains(request.getHeader("User-Agent"))) {

            getHtmlResponse(request, response);
        }
        else{

            getTextResponse(request, response);

        }
    }

    private void getTextResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        String key = request.getParameter("key");
        Integer value = Integer.parseInt(request.getParameter("value"));
        Boolean mock = request.getParameter("mock") != null;
        Boolean sync = request.getParameter("sync") != null;

        String responseContent = mockCasesText(mock, key, value, sync);
        PrintWriter out = response.getWriter();
        out.println(responseContent);
        out.close();

    }

    private String mockCasesText(Boolean mock, String key, Integer value, Boolean sync) {
        String response;
        if (mock) {

            response = createConfirmationPageText();
        } else {

            response = getRepositoryHtmlText(key, value, sync);
        }

        return response;
    }

    private void getHtmlResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String key = request.getParameter("key");
        Integer value = Integer.parseInt(request.getParameter("value"));
        Boolean mock = request.getParameter("mock") != null;
        Boolean sync = request.getParameter("sync") != null;

        String responseContent = mockCasesHtml(mock, key, value, sync);
        PrintWriter out = response.getWriter();
        out.println(responseContent);
        out.close();
    }

    private void logServerData(HttpServletRequest request){

        logger.info(request.getMethod());
        logger.info(request.getRemoteAddr());
        logger.info(request.getHeader("User-Agent"));
        logger.info(request.getHeader("Accept-Language"));
        for(Map.Entry<String, String[]> entry: request.getParameterMap().entrySet()){
            logger.info(String.format("%s = %s",entry.getKey(), Arrays.toString(entry.getValue())));
        }

    }

    private String mockCasesHtml(Boolean mock, String key, Integer value, Boolean sync) {

        String response;
        if (mock) {

            response = createConfirmationPageHtml();
        } else {

            response = getRepositoryHtml(key, value, sync);
        }

        return response;
    }

    private String getRepositoryHtml(String key, Integer value, Boolean sync) {
        if(sync) {
            new FileHelper().writeToFileSynchronized(key, value);
        }
        else{
            new FileHelper().writeToFileDesynchronized(key, value);
        }

        String content = FileHelper.readFromFile();
        return String.format(htmlTemplate, content);
    }

    private String createConfirmationPageHtml() {

        String content = "Mock is true";
        return String.format(htmlTemplate, content);

    }

    private String getRepositoryHtmlText(String key, Integer value, Boolean sync) {
        if(sync) {
            new FileHelper().writeToFileSynchronized(key, value);
        }
        else{
            new FileHelper().writeToFileDesynchronized(key, value);
        }

        return FileHelper.readFromFile();
    }

    private String createConfirmationPageText() {

        return "Mock is true";

    }

    public void destroy() {
    }
}