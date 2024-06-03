package pizzaria.beanform;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import piazzaria.dao.GestionCommande;
import piazzaria.entity.Commande;
import piazzaria.entity.Panier;
import piazzaria.entity.User;

public class CommandeForm {


    private String adresse;
    private String phone;
    private Float total;
    private String numCommande;
    
    private Map<String, String> errors = new HashMap<>();
    
    public boolean passerCommande(HttpServletRequest req) throws IOException, ServletException {
    	HttpSession session = req.getSession();
    	ArrayList<Panier> listPanier = (ArrayList<Panier>) session.getAttribute("listPanier");
        User user = (User) session.getAttribute("userLogin");
        this.adresse = req.getParameter("adresse");
        this.phone = req.getParameter("phone");
        try{
            this.total = Float.parseFloat(req.getParameter("total"));
            this.numCommande = "commande-"+ user.getNom()+"-"+UUID.randomUUID().toString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        if (validate()) {
	        try{
	            Commande commande = new Commande(this.getNumCommande(), user.getId(), this.getTotal(), this.getAdresse(), this.getPhone());
	            GestionCommande.insertNewCommande(commande);
	            for (Panier itemInPanier : listPanier) {
	                GestionCommande.insertNewPizzaCommandee(this.getNumCommande(), itemInPanier.getId(), itemInPanier.getQuantite());
	            }

	            session.removeAttribute("listPanier");
	        }catch(Exception ex){return false;}

            return true;
        }
		return false;
    	
    }
    
	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public String getNumCommande() {
		return numCommande;
	}

	public void setNumCommande(String numCommande) {
		this.numCommande = numCommande;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}


    public Map<String, String> getErrors() {
        return errors;
    }
    
    public boolean validate() {
        boolean isValid = true;
        if (adresse == null|| adresse.length() < 1) {
            errors.put("adresse", "L'adresse  est obligatoire");
            isValid = false;
        }
        
        if (phone == null|| phone.length() < 5) {
            errors.put("phone", "Le telephone avec minimum 5 caracteres est obligatoire");
            isValid = false;
        }

        return isValid;
    }
}
