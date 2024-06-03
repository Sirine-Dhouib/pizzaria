package piazzaria.servlet;
import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import piazzaria.entity.Panier;
import jakarta.servlet.annotation.*;

@WebServlet(name = "QuantiteController", value = "/quantite")
public class QuantiteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ArrayList<Panier> listPanier = (ArrayList<Panier>) session.getAttribute("listPanier");
        int action = Integer.parseInt(request.getParameter("action"));
        int index = Integer.parseInt(request.getParameter("index"));
        if(action==1){
        	listPanier.get(index).setQuantite(listPanier.get(index).getQuantite()+1);
        }else{
        	listPanier.get(index).setQuantite(listPanier.get(index).getQuantite()-1);
            if(listPanier.get(index).getQuantite()==0){
            	listPanier.remove(index);
            }
        }
        request.getRequestDispatcher("WEB-INF/pages/panier.jsp").forward(request, response);
    }

}
