package pizzaria.beanform;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import piazzaria.dao.GestionUser;
import piazzaria.entity.User;

public class RegisterForm {


	private String nom;
    private String email;
    private String prenom;
    private String phone;
    private String password;
    private boolean emailExiste;

	public boolean register(HttpServletRequest req) throws IOException, ServletException {
    	this.nom = req.getParameter("nom");
    	this.prenom = req.getParameter("prenom");
    	this.phone = req.getParameter("phone");
    	this.email = req.getParameter("email");
    	this.password = req.getParameter("password");
    	if (validate()) {
    		try {
    			if (GestionUser.checkEmail(email)) {
    				this.emailExiste = true;
    			}else {
    				User user = new User(this.nom, this.prenom, this.email, this.phone, this.password);
                    GestionUser.insertNewUser(user);
    				this.emailExiste = false;
    				System.out.println("C'est bon");
    			}
			} catch (Exception e) {

				System.out.println("C'est pas bon "+e.getMessage());
			}
			return true;
		}
    	return false;
    }
    
    public boolean isEmailExiste() {
		return emailExiste;
	}

	public void setEmailExiste(boolean emailExiste) {
		this.emailExiste = emailExiste;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

    private Map<String, String> errors = new HashMap<>();

    public Map<String, String> getErrors() {
        return errors;
    }
    
    public boolean validate() {
        boolean isValid = true;

        if (nom == null || nom.length() < 3) {
            errors.put("nom", "Le nom doit contenir au moins 3 caractères.");
            isValid = false;
        }

        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.put("email", "L'e-mail est incorrect.");
            isValid = false;
        }

        if (prenom == null || prenom.length() < 3) {
            errors.put("prenom", "Le prenom doit contenir au moins 3 caractères.");
            isValid = false;
        }
        
        if (password == null || password.length() < 5) {
            errors.put("password", "Le password doit contenir au moins 5 caractères.");
            isValid = false;
        }

        return isValid;
    }
}
