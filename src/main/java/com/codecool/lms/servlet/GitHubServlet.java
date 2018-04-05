package com.codecool.lms.servlet;

import com.codecool.lms.model.GitHub;
import com.codecool.lms.model.Repository;
import com.codecool.lms.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/github")
public class GitHubServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        List<Repository> repositories = new ArrayList<>();
        if (htmls != null) {
            for (int i = 0; i < htmls.length; i++) {
                repositories.add(new Repository(htmls[i], names[i], Integer.parseInt(stars[i]), Integer.parseInt(watchers[i]), forks[i]));
            }
        }
        GitHub github = new GitHub(avatar, html, repos, gists, followers, following, company, blog, location, created, repositories);

        ((User) req.getSession().getAttribute("currentUser")).setConnected(true);
        ((User) req.getSession().getAttribute("currentUser")).setGitHub(github);
        resp.sendRedirect("profile");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ((User) req.getSession().getAttribute("currentUser")).setConnected(false);
        ((User) req.getSession().getAttribute("currentUser")).setGitHub(null);
        resp.sendRedirect("profile");
    }
}
