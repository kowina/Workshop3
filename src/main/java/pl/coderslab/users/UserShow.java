package pl.coderslab.users;

import pl.coderslab.user.User;
import pl.coderslab.userdao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/show")
public class UserShow extends HttpServlet {
    int idToShow;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       idToShow = Integer.parseInt(req.getParameter("id"));
        UserDao userDao = new UserDao();
        User user = userDao.read(idToShow);
        req.setAttribute("user", user);
        req.getServletContext().getRequestDispatcher("/user/show.jsp").forward(req,resp);
    }
}
