package pl.coderslab.users;

import pl.coderslab.user.User;
import pl.coderslab.userdao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/delete")
public class UserDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        int idToDelete = Integer.parseInt(req.getParameter("id"));
        UserDao userDao = new UserDao();
        userDao.delete(idToDelete);
        resp.sendRedirect(req.getContextPath() + "/user/list");
    }
}
