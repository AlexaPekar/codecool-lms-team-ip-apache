package com.codecool.lms.servlet;

import com.codecool.lms.dao.DatabaseUserDao;
import com.codecool.lms.model.GitHub;
import com.codecool.lms.model.User;
import com.codecool.lms.service.UserServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/github")
public class GitHubServlet extends AbstractServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            UserServiceDaoImpl userServiceDao = new UserServiceDaoImpl(userDao);
            String avatar = req.getParameter("avatar");
            String html = req.getParameter("html");
            int repos = Integer.parseInt(req.getParameter("repos"));
            int gists = Integer.parseInt(req.getParameter("gists"));
            int followers = Integer.parseInt(req.getParameter("followers"));
            int following = Integer.parseInt(req.getParameter("following"));
            String company = req.getParameter("company");
            String blog = req.getParameter("blog");
            String location = req.getParameter("location");
            String created = req.getParameter("created");

            String[] htmls = req.getParameterValues("repo-html");
            String[] names = req.getParameterValues("repo-name");
            String[] stars = req.getParameterValues("repo-star");
            String[] watchers = req.getParameterValues("repo-watcher");
            String[] forks = req.getParameterValues("repo-forms");
            User user = (User) req.getSession().getAttribute("currentUser");

            user = userServiceDao.connectUserWithGithub(user, avatar, html, repos, gists, followers, following, company, blog, location, created, htmls, names, stars, watchers, forks);
            req.getSession().setAttribute("currentUser", user);
            resp.sendRedirect("profile");
        } catch (SQLException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            UserServiceDaoImpl userServiceDao = new UserServiceDaoImpl(userDao);
            User user = (User) req.getSession().getAttribute("currentUser");
            GitHub gitHub = userDao.findGithubByUserName(user.getId());
            user = userServiceDao.disconnectUserFromGithub(user, gitHub);
            req.getSession().setAttribute("currentUser", user);
            resp.sendRedirect("profile");
        } catch (SQLException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}