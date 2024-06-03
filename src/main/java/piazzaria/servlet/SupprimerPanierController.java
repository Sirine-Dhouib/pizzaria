package piazzaria.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import piazzaria.entity.Panier;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SupprimerPanierController", value = "/supprimer-panier")
public class SupprimerPanierController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Panier> cartList = (ArrayList<Panier>) session.getAttribute("listPanier");

        if(request.getParameter("panier")!=null){
            session.removeAttribute("listPanier");
            response.sendRedirect(request.getContextPath()+"/panier");
        }
        else {
            int index= Integer.parseInt(request.getParameter("index"));
            if(cartList!=null) {
                cartList.remove(index);
                response.sendRedirect(request.getContextPath()+"/panier");
            }
        }
    }

}