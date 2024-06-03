package piazzaria.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import piazzaria.dao.GestionCommande;
import piazzaria.entity.Commande;
import piazzaria.entity.Panier;
import piazzaria.entity.User;
import pizzaria.beanform.CommandeForm;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "CommanderController", value = "/commander")
public class CommanderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        CommandeForm form = new CommandeForm();
        boolean passerCommande = form.passerCommande(request);
        
        if (!passerCommande) {

	        request.setAttribute("errors", form.getErrors());
	        request.setAttribute("form", form);

	        request.getRequestDispatcher("WEB-INF/pages/panier.jsp").forward(request, response);
	    } else {
	    	HttpSession session = request.getSession();
	    	session.setAttribute("commandePasser", form.getNumCommande());
            response.sendRedirect(request.getContextPath()+"/profile");
	    }
        

    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/profile");
	}
}

