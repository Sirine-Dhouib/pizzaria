package piazzaria.servlet.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import piazzaria.dao.GestionPizza;
import piazzaria.entity.Pizza;
import piazzaria.entity.User;
import pizzaria.beanform.PizzaForm;
import jakarta.servlet.annotation.*;

import jakarta.servlet.annotation.MultipartConfig;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "AdminHomeController", value = "/admin-home")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final String VALUE_AJOUT = "ajout";
    private static final String VALUE_EDIT = "edit";
    private static final String VALUE_DELETE = "supprimer";
    private static final String VALUE_ANNULER = "annuler";
    
    
    
    private String nomActionPizza = "";
    

 
    boolean actionPizza = false;
    boolean deletePizza = false;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	

    	
    	
        HttpSession session = request.getSession();
        if (session.getAttribute("adminLogin") == null) {
        	String messages = "Connectez-vous en tant qu'admin pour continuer !";
        	request.setAttribute("messages", messages);
            request.getRequestDispatcher("WEB-INF/auth/login.jsp").forward(request, response);
        } else {
        	
        	String trueAction = request.getParameter("true");
        	if(VALUE_AJOUT.equals(trueAction)) {
    			request.setAttribute("pizzaAjoute", true);
        	}
        	
        	if(VALUE_EDIT.equals(trueAction)) {
    			request.setAttribute("pizzaModifie", true);
        	}
        	
        	String action = request.getParameter("action");
        	
        	PizzaForm pizzaForm = new PizzaForm();
        	
        	if (request.getParameter("id") != null) {
        		
        		try {
        	    	int id = Integer.parseInt(request.getParameter("id"));
    	            Pizza pizza = (Pizza) GestionPizza.getPizzaById(id);
    	            
    	            if (pizza.getId() == 0) {
    	                response.sendRedirect(request.getContextPath() + "/admin-home");
    	                return;
    	            }
                		
    	            pizzaForm.setNom(pizza.getNom());
    	            pizzaForm.setCategorie(pizza.getCategorie());
    	            pizzaForm.setQte_disponible(pizza.getQte_disponible());
    	            pizzaForm.setDescription(pizza.getDescription());
    	            pizzaForm.setPrix(pizza.getPrix());
    	            pizzaForm.setId(pizza.getId());
    	           
        	    } catch (NumberFormatException e) {
        	    	request.getRequestDispatcher("WEB-INF/pages/admin/home.jsp").forward(request, response);
        	    }
	        	if (VALUE_EDIT.equals(action)) {
	        		
	        		 actionPizza = true;
	    	            
	    	            request.setAttribute("actionPizza", actionPizza);
	    	            request.setAttribute("form", pizzaForm);
	    	            nomActionPizza = VALUE_EDIT;
	    	            request.setAttribute("nomActionPizza", nomActionPizza);

	    	            request.getRequestDispatcher("WEB-INF/pages/admin/home.jsp").forward(request, response);
	    	            return;
	        	    
	        	} else if (VALUE_DELETE.equals(action)) {
	                deletePizza = true;
	                request.setAttribute("deletePizza", deletePizza);
	                request.setAttribute("pizza", pizzaForm);
	                nomActionPizza = VALUE_DELETE;

	                request.setAttribute("nomActionPizza", nomActionPizza);
	                request.getRequestDispatcher("WEB-INF/pages/admin/home.jsp").forward(request, response);
	                return;
	        	}
	        	
        	} 
        	else if (VALUE_AJOUT.equals(action)) {
        		
    			actionPizza = true;
    			nomActionPizza = VALUE_AJOUT;

                request.setAttribute("nomActionPizza", nomActionPizza);
    			
        	}else if (VALUE_ANNULER.equals(action)) {
        		actionPizza = false;
        	}
        	
    		User user = (User) session.getAttribute("adminLogin");
    		List<Pizza> pizzaList = GestionPizza.getAllPizzas();
            request.setAttribute("pizzaList", pizzaList);
            request.setAttribute("user", user);
            request.setAttribute("pageTitle", "Admin Home");
            request.setAttribute("actionPizza", actionPizza);
            actionPizza = false;
            request.getRequestDispatcher("WEB-INF/pages/admin/home.jsp").forward(request, response);
    		  
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	
    	String action = request.getParameter("action");
        PizzaForm pizzaForm = new PizzaForm();
        
        if (VALUE_AJOUT.equals(action)) {
        	
        	boolean ajout = pizzaForm.pizzaAjoutForm(request);
            if (ajout) {
                
                try {
    				Pizza pizza = new Pizza(pizzaForm.getNom(), pizzaForm.getCategorie(), pizzaForm.getPrix(), pizzaForm.getPhoto(), pizzaForm.getDescription(),pizzaForm.getQte_disponible());
    				GestionPizza.insertNewPizza(pizza);
    				request.setAttribute("pizzaAjoute", true);
    				actionPizza = false;
    				String redirectURL = "/admin-home?true="+VALUE_AJOUT;
    				response.sendRedirect(request.getContextPath()+redirectURL);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
                
            } else {
            	
            	actionPizza = true;
            	request.setAttribute("form", pizzaForm);
            	request.setAttribute("actionPizza", actionPizza);
            	nomActionPizza = VALUE_AJOUT;
                request.setAttribute("nomActionPizza", nomActionPizza);
                request.setAttribute("errors", pizzaForm.getErrors());
                request.getRequestDispatcher("WEB-INF/pages/admin/home.jsp").forward(request, response);
            }
        } else if (VALUE_EDIT.equals(action)) {
        	
        	boolean modif = pizzaForm.pizzaModifierForm(request);
            if (modif) {
                
                try {
    				Pizza pizza = new Pizza(pizzaForm.getId(), pizzaForm.getNom(), pizzaForm.getCategorie(), pizzaForm.getPrix(), pizzaForm.getPhoto(), pizzaForm.getDescription(),pizzaForm.getQte_disponible());
    				GestionPizza.editPizza(pizza);

    				actionPizza = false;
    				String redirectURL = "/admin-home?true="+VALUE_EDIT;
    				response.sendRedirect(request.getContextPath()+redirectURL);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
                
            } else {
            	
            	actionPizza = true;
            	request.setAttribute("form", pizzaForm);
            	request.setAttribute("actionPizza", actionPizza);
            	nomActionPizza = VALUE_EDIT;
                request.setAttribute("nomActionPizza", nomActionPizza);
                request.setAttribute("errors", pizzaForm.getErrors());
                request.getRequestDispatcher("WEB-INF/pages/admin/home.jsp").forward(request, response);
            }
        } else if (VALUE_DELETE.equals(action)) {
        	try {
            	int id = Integer.parseInt(request.getParameter("idPizza"));
            	GestionPizza.deletePizzaById(id);
            	String redirectURL = "/admin-home?true="+VALUE_DELETE;
				response.sendRedirect(request.getContextPath()+redirectURL);
            } catch (NumberFormatException e) {
				e.printStackTrace();
            }
        }

    }


}