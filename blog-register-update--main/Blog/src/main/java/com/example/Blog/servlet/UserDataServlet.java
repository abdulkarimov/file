package com.example.Blog.servlet;

import com.example.Blog.dao.DbManager;
import com.example.Blog.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@WebServlet("/userData")
public class UserDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.getRequestDispatcher("userData.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String fullName =request.getParameter("full_name");
        String pass = request.getParameter("password");

        String redirect = "/userData ? email = " + email +"&error=2";
        try {
            boolean update = DbManager.updateUser(fullName,pass,email);
            if(update)
                redirect = "/userData";
            else
                redirect = "/userData ? email ="  + email + "&error=2";

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        response.sendRedirect(redirect);

    }
}
