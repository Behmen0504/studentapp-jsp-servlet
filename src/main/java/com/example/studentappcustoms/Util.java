package com.example.studentappcustoms;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static String alertMessage(int control) {
        String alert = null;
        if (control > 0) {
            alert = "<div>Operation is successfully completed<span style=\"cursor: pointer; color: white;background: #009688;\n" +
                    "    padding: 0px 4px;\n" +
                    "    display: inline-block;\n" +
                    "    margin-left: 10px;\" class=\"closebtn\" onclick = \"this.parentElement.style.display='none';\" > &times;</span></div>";
        } else {
            alert = "<div>Operation is failed<span style=\"cursor: pointer;color: white;background: red;padding: 0px 4px;\n" +
                    "    display: inline-block;\n" +
                    "    margin-left: 10px;\" class=\"closebtn\" onclick = \"this.parentElement.style.display='none';\" > &times;</span></div>";
        }
        return alert;
    }

    public static void sendDataAndForward(HttpServletRequest request,
                                          HttpServletResponse response,
                                          String dataName,
                                          String requestName,
                                          Object data) throws ServletException, IOException {
        request.setAttribute(dataName, data);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(requestName);
        requestDispatcher.forward(request, response);
    }
    public static void sendDataAndForward2(HttpServletRequest request,
                                           HttpServletResponse response,
                                           String dataName,
                                           String requestName, Object...data) throws ServletException, IOException {
        request.setAttribute(dataName, data);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(requestName);
        requestDispatcher.forward(request, response);
    }
}
