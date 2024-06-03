package pizzaria.beanform;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import piazzaria.dao.GestionUser;
import piazzaria.entity.User;

public class LoginForm {


    private String email;
    private String password;
    User user;
    boolean bloque;

	public boolean isBloque() {
		return bloque;
	}

	public void setBloque(boolean bloque) {
		this.bloque = bloque;
	}

	public boolean login(HttpServletRequest req) throws IOException, ServletException {
		HttpSession session = req.getSession();
        this.email = req.getParameter("email");
        this.password = req.getParameter("password");
        if(validate()) {
        	try {
        		this.user  = GestionUser.getUserLogin(this.email, this.password);
            	if(user == null) {
            		session.setAttribute("loginError", true);
            		return false;
            	}
            	if(user.getStatus() == 1) {
            		this.bloque = true;
            	}
			} catch (Exception e) {
				return false;
			}
        	
        	return true;
        }
    	return false;
    }
    
    private Map<String, String> errors = new HashMap<>();
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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


    public Map<String, String> getErrors() {
        return errors;
    }
    
    public boolean validate() {
        boolean isValid = true;
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.put("email", "L'e-mail est incorrect.");
            isValid = false;
        }
        
        if (password == null|| password.length() < 5) {
            errors.put("password", "Le password avec minimum 5 caracteres est obligatoire");
            isValid = false;
        }

        return isValid;
    }
}
