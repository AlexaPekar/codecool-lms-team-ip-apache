package com.codecool.lms.servlet;

import com.codecool.lms.service.PageServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/delete")
public class DeletePageServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageName = req.getParameter("page");
        PageServiceImpl.getPageService().removePage(pageName);
        resp.sendRedirect("home");
    }
}