package com.codecool.lms.servlet;

import com.codecool.lms.service.PageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/delete")
public class DeletePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageName = req.getParameter("page");
        PageServiceImpl.getPageService().removePage(pageName);
        resp.sendRedirect("home");
    }
}