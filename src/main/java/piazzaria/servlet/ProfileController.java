package piazzaria.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import piazzaria.dao.GestionCommande;
import piazzaria.entity.Commande;
import piazzaria.entity.Pizza;
import piazzaria.entity.PizzaCommande;
import piazzaria.entity.User;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProfileController", value = "/profile")
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userLogin") == null) {
        	String messages = "Connectez-vous pour continuer!";
        	request.setAttribute("messages", messages);
        	request.getRequestDispatcher("WEB-INF/auth/login.jsp").forward(request, response);
        } else {
            User user = (User) session.getAttribute("userLogin");
            GestionCommande gestionCommande = new GestionCommande();

            List<Commande> commandes = GestionCommande.getCommandeByUserId(user.getId());
            List<Commande> historiques = GestionCommande.getHistoriqueByUserId(user.getId());

            request.setAttribute("commandesEnAttentes", commandes);
            request.setAttribute("historiques", historiques);
            request.setAttribute("gestionCommande", gestionCommande);
            request.getRequestDispatcher("WEB-INF/pages/profile.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
