package piazzaria.servlet;

import jakarta.servlet.*;

import jakarta.servlet.http.*;
import piazzaria.dao.GestionPizza;
import piazzaria.entity.Pizza;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "HomeController", value = "/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pizza> pizzaList = GestionPizza.getAllPizzas();
        request.setAttribute("pizzaList", pizzaList);
        request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request, response);
    }
}
