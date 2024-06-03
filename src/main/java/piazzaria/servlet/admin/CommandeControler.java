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
import piazzaria.dao.GestionPizza;
import piazzaria.dao.GestionUser;
import piazzaria.entity.Commande;
import piazzaria.entity.Pizza;
import piazzaria.entity.PizzaCommande;
import piazzaria.entity.User;

@WebServlet(name = "CommandeController", value = "/commandes")
public class CommandeControler extends HttpServlet {
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
	        	
	        	String action = request.getParameter("action");
	        	if (request.getParameter("mumeroCommande") != null) {
	        		
        	    	String mumeroCommande = request.getParameter("mumeroCommande");
        	    	GestionCommande gestionCommande = new GestionCommande();
    	            Commande commande = (Commande) GestionCommande.getCommandeByOrderNumber(mumeroCommande);
    	            
    	            if (commande.getNum_commande() == null) {
    	                response.sendRedirect(request.getContextPath() + "/commandes");
    	                return;
    	            }
    	            
    	            User client = GestionUser.getUserById(commande.getUser_id());
    	            Map<Integer, PizzaCommande> pizzaCommandes = GestionCommande.getCommandesByOrderNumber(mumeroCommande);
    	            
		        	if (VIEW.equals(action)) {
		        		
		        		modalCommande = true;
		    	            
	    	            request.setAttribute("commande", commande);
	    	            request.setAttribute("modalCommande", modalCommande);
	    	            request.setAttribute("client", client);
	    	            request.setAttribute("pizzaCommandes", pizzaCommandes);
	    	            request.setAttribute("gestionCommande", gestionCommande);
	    	            request.getRequestDispatcher("WEB-INF/pages/admin/commandes.jsp").forward(request, response);
	    	            return;
		        	    
		        	}
		        	
	        	} else if("quitter".equals(action)) {
	        		modalCommande = false;
	        	}
	        	modalCommande = false;
	        	List<Commande> commandeEnAttentes = GestionCommande.getAllCommandesEnAttentes();
	            request.setAttribute("commandeEnAttentes", commandeEnAttentes);
	            
	        	List<Commande> historiques = GestionCommande.getHistoriques();
	            request.setAttribute("historiques", historiques);

	            request.setAttribute("modalCommande", modalCommande);
	            request.getRequestDispatcher("WEB-INF/pages/admin/commandes.jsp").forward(request, response);
	        }
	    }


	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");
	        System.out.println(action);
	        if ("valider".equals(action)) {
	            String numeroCommande = request.getParameter("numeroCommande");
	            GestionCommande.validerCommande(numeroCommande);
	        } else if ("annuler".equals(action)) {
	            String numeroCommande = request.getParameter("numeroCommande");
	            GestionCommande.annulerCommande(numeroCommande);
	        }
	        
	        response.sendRedirect(request.getContextPath() + "/commandes");
	    }

}
