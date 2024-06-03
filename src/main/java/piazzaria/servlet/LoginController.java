package piazzaria.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import piazzaria.dao.GestionUser;
import piazzaria.entity.User;
import pizzaria.beanform.LoginForm;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String USER_PROFILE = "user";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Login");
        request.getRequestDispatcher("WEB-INF/auth/login.jsp").forward(request, response);
    }
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
        	HttpSession session = request.getSession();
            LoginForm form = new LoginForm();
            boolean login = form.login(request);
            
            if (!login) {

    	        request.setAttribute("errors", form.getErrors());  
    	        request.setAttribute("form", form);

    	        request.getRequestDispatcher("WEB-INF/auth/login.jsp").forward(request, response);
    	    } else {
    	    	if(form.isBloque()) {
    	    		request.setAttribute("bloque", true);
        	        request.getRequestDispatcher("WEB-INF/auth/login.jsp").forward(request, response);
        	        return;
    	    	}else if(form.getUser()!=null){
                	
                    if(USER_PROFILE.equals(form.getUser().getProfil())){
                        session.setAttribute("userLogin", form.getUser());
                        response.sendRedirect(request.getContextPath() + "/profile");
                    }else {
                    	session.setAttribute("adminLogin", form.getUser());
                    	response.sendRedirect(request.getContextPath() + "/admin-home");
                    }
                }
    	    	
    	    }
            
        }
}
