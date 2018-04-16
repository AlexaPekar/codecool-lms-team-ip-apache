package com.codecool.lms.servlet;

import com.codecool.lms.model.Page;
import com.codecool.lms.service.PageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/create")
public class CreateServlet extends AbstractServlet {


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String type = req.getParameter("type");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int maxPoint = Integer.parseInt(req.getParameter("maxScore"));

        Page page = PageServiceImpl.getPageService().createNewPage(title, content, type, maxPoint);
        PageServiceImpl.getPageService().addNewPage(page);

        resp.sendRedirect("home");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        req.setAttribute("current", req.getSession().getAttribute("currentUser"));
        if (type.equals("text")) {
            req.getRequestDispatcher("createTextInHome.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("createAssignmentInHome.jsp").forward(req, resp);
        }
    }
}
