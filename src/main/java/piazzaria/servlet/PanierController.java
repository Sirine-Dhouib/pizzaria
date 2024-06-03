package piazzaria.servlet;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import piazzaria.dao.GestionPizza;
import piazzaria.entity.Panier;
import piazzaria.entity.Pizza;

@WebServlet(name = "PanierController", value = "/panier")
public class PanierController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    if(request.getParameter("id")!=null) {
	        int id = Integer.parseInt(request.getParameter("id"));
	        Pizza pizza = GestionPizza.getPizzaById(id);
	        Panier panier = new Panier();
	        panier.setId(id);
	        panier.setQuantite(1);
	        panier.setNom(pizza.getNom());
	        panier.setPrix(pizza.getPrix());
	        panier.setPhoto(pizza.getPhoto());
	        session.setAttribute("panier", panier);
	
	        ArrayList<Panier> listPanier = (ArrayList<Panier>) session.getAttribute("listPanier");
	        if (listPanier == null) {
	            ArrayList<Panier> panierList = new ArrayList<>();
	            panierList.add(panier);
	            session.setAttribute("listPanier", panierList);
	        } else {
	            boolean existe = false;
	            for (Panier itemInPanier : listPanier) {
	                if (itemInPanier.getId() == id) {
	                	existe = true;
	                    session.setAttribute("itemInPanier", true);
	                }
	            }
	            if (!existe) {
	            	listPanier.add(panier);
	            }
	            session.setAttribute("listPanier", listPanier);
	        }
	    }
	    request.getRequestDispatcher("WEB-INF/pages/panier.jsp").forward(request, response);
	}
}
