package piazzaria.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import piazzaria.dao.GestionUser;
import piazzaria.entity.User;
import pizzaria.beanform.RegisterForm;
import jakarta.servlet.annotation.*;


import java.io.IOException;
import java.util.Map;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("WEB-INF/auth/register.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        
        RegisterForm form = new RegisterForm();
        boolean register = form.register(request);
        if (!register) {

	        request.setAttribute("errors", form.getErrors());
	        request.setAttribute("form", form);

	        request.getRequestDispatcher("WEB-INF/auth/register.jsp").forward(request, response);
	    } else {
            if (form.isEmailExiste()) {
                session.setAttribute("emailExist", true);
                request.setAttribute("form", form);
                request.getRequestDispatcher("WEB-INF/auth/register.jsp").forward(request, response);
            }else {
            	request.setAttribute("compteCree", true);
                request.getRequestDispatcher("WEB-INF/auth/login.jsp").forward(request, response);
            }
	    }
        
    }
}
