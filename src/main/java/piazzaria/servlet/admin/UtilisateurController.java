package piazzaria.servlet.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import piazzaria.dao.GestionCommande;
import piazzaria.dao.GestionUser;
import piazzaria.entity.Commande;
import piazzaria.entity.PizzaCommande;
import piazzaria.entity.User;

@WebServlet(name = "UtilisateurController", value = "/utilisateurs")
public class UtilisateurController extends HttpServlet{
	private static final long serialVersionUID = 1L;

    private static final String VIEW = "view";
    
    boolean modalCommande = false;
    
    
    
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        if (session.getAttribute("adminLogin") == null) {
	        	String messages = "Connectez-vous en tant qu'admin pour continuer!";
	        	request.setAttribute("messages", messages);
	        	request.getRequestDispatcher("WEB-INF/auth/login.jsp").forward(request, response);
	        } else {
	        	
	        	List<User> utilisateurs = GestionUser.getAll();
	            request.setAttribute("utilisateurs", utilisateurs);
	            request.getRequestDispatcher("WEB-INF/pages/admin/utilisateurs.jsp").forward(request, response);
	        }
	    }


	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");
	        System.out.println(action);
	        if ("bloquer".equals(action)) {
	        	int id= Integer.parseInt(request.getParameter("idUser"));
	            GestionUser.blockUserById(id);
	        } else if ("debloquer".equals(action)) {
	        	int id= Integer.parseInt(request.getParameter("idUser"));
	            GestionUser.unblockUserById(id);
	        }
	        
	        response.sendRedirect(request.getContextPath() + "/utilisateurs");
	    }
}
