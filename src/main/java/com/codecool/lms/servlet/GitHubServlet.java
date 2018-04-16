package com.codecool.lms.servlet;

import com.codecool.lms.model.GitHub;
import com.codecool.lms.model.Repository;
import com.codecool.lms.model.User;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/github")
public class GitHubServlet extends AbstractServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

        List<Repository> repositories = UserServiceImpl.getUserService().createRepositoryList(htmls, names, stars, watchers, forks);
        GitHub github = UserServiceImpl.getUserService().createGithub(avatar, html, repos, gists, followers, following, company, blog, location, created, repositories);

        User user = (User) req.getSession().getAttribute("currentUser");
        UserServiceImpl.getUserService().connectUserWithGithub(user, github);
        resp.sendRedirect("profile");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("currentUser");
        UserServiceImpl.getUserService().disconnectUserFromGithub(user);
        resp.sendRedirect("profile");
    }
}
