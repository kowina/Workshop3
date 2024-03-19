package pl.coderslab.users;

import pl.coderslab.user.User;
import pl.coderslab.userdao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserEdit extends HttpServlet {
    int idToEdit;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        idToEdit = Integer.parseInt(req.getParameter("id"));
        UserDao userDao = new UserDao();
        User user = userDao.read(idToEdit);
        req.setAttribute("user", user);
        req.getServletContext().getRequestDispatcher("/user/edit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       UserDao userDao = new UserDao();
       User userToUpdate = new User();
       userToUpdate.setUserName(req.getParameter("userName"));
       userToUpdate.setEmail(req.getParameter("email"));
       userToUpdate.setPassword(req.getParameter("password"));
       userToUpdate.setId(idToEdit);
       userDao.update(userToUpdate);
       resp.sendRedirect(req.getContextPath() + "/user/list");

    }
}
