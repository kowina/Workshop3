package pl.coderslab.users;

import pl.coderslab.user.User;
import pl.coderslab.userdao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/add")
public class UserAdd extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/user/add.jsp")
                .forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String userName = req.getParameter("userName");
    String email = req.getParameter("email");
    String password = req.getParameter("password");
        User user = new User(userName, email, password);
        UserDao userDao = new UserDao();
        userDao.create(user);
        resp.sendRedirect(req.getContextPath() + "/user/list");
    }
}
