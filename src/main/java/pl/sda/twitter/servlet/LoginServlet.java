package pl.sda.twitter.servlet;

import pl.sda.twitter.constans.SessionValues;
import pl.sda.twitter.exception.IncorrectLoginPasswordException;
import pl.sda.twitter.persistence.entities.TbUser;
import pl.sda.twitter.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    private final String user= SessionValues.USER.getValue();
    private final String messages=SessionValues.MESSAGES.getValue();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        final HttpSession session=req.getSession();
        try{
            final TbUser tbuser=userService.getUser(login, password);

            session.setAttribute("user", tbuser);
            resp.sendRedirect("index.jsp");
        }catch (IncorrectLoginPasswordException e){
            session.setAttribute(messages, Collections.singletonList("Niepoprawne logowanie sprobuj ponownie"));
            resp.sendRedirect("login.jsp");
        }



//        final PrintWriter writer =resp.getWriter();
//        final String userName="admin";
//        if (login.equals(userName)&& password.equals("password")){
//            final HttpSession session=req.getSession();
//            session.setAttribute("user", userName);
//            resp.sendRedirect(req.getContextPath()+"/");
//        }else {
//            writer.write("Niepoprawne dane logowania");
//        }
//    }
    }
}