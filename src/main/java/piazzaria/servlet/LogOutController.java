package piazzaria.servlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import piazzaria.entity.User;

import java.io.IOException;

@WebServlet(name = "LogOutController", value = "/logout")
public class LogOutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("user") != null) {
        	User user = (User) session.getAttribute("userLogin");
            session.removeAttribute("userLogin");
            response.sendRedirect(request.getContextPath()+"/home");
            session.removeAttribute("listPanier");
        } else if(request.getParameter("admin") != null) {
            session.removeAttribute("adminLogin");
            response.sendRedirect(request.getContextPath()+"/home");
        } else {

            response.sendRedirect(request.getContextPath()+"/home");
        }
        
        
    }

}