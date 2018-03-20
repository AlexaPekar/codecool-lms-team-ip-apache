package com.codecool.lms.servlet;

import com.codecool.lms.model.Page;
import com.codecool.lms.service.PageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/home")
public class CurriculumServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Page> pages = PageServiceImpl.getPageService().getPages();
        req.setAttribute("pages", pages);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}