package pizzaria.beanform;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class PizzaForm {
	private int id;
    private String nom;
    private String categorie;
    private Float prix;
    private String photo;
    private String description;
    private Map<String, String> errors = new HashMap<>();
    private int qte_disponible;
    private static final String UPLOAD_REPERTOIRE = "images";
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQte_disponible() {
		return qte_disponible;
	}

	public void setQte_disponible(int qte_disponible) {
		this.qte_disponible = qte_disponible;
	}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
    
    public boolean pizzaAjoutForm(HttpServletRequest req) throws IOException, ServletException {
    	
    	this.nom = req.getParameter("nom");
    	this.categorie = req.getParameter("categorie");
        

        try {
            this.prix = Float.parseFloat(req.getParameter("prix"));
            this.qte_disponible = Integer.parseInt(req.getParameter("qte_disponible"));
        } catch (NumberFormatException e) {
            this.prix = null;
            this.qte_disponible = 0;
        }

        this.description = req.getParameter("description");

        Part filePart = req.getPart("photo");
        if (filePart != null) {
            String fName = filePart.getSubmittedFileName();
            this.photo = fName;
        }

        if (validate()) {
            String fileName = UUID.randomUUID().toString() + "." + getFileExtension(this.photo);
            String savePath = req.getServletContext().getRealPath("/images") + File.separator + fileName;
            this.photo = fileName;
            filePart.write(savePath);
            return true;
        }
        return false;
    	
    }
    
    public boolean pizzaModifierForm(HttpServletRequest req) throws IOException, ServletException {
    	if(req.getParameter("idPizza") != null && !req.getParameter("idPizza").isEmpty())
    	{
    		this.nom = req.getParameter("nom");
        	this.categorie = req.getParameter("categorie");
            

            try {
            	this.id = Integer.parseInt(req.getParameter("idPizza"));
                this.prix = Float.parseFloat(req.getParameter("prix"));
                this.qte_disponible = Integer.parseInt(req.getParameter("qte_disponible"));
            } catch (NumberFormatException e) {
                this.prix = null;
                this.qte_disponible = 0;
            }

            this.description = req.getParameter("description");

            Part filePart = req.getPart("photo");
            if (filePart != null) {
                String fName = filePart.getSubmittedFileName();
                this.photo = fName;
            }

            if (validate()) {
                String fileName = UUID.randomUUID().toString() + "." + getFileExtension(this.photo);
                String savePath = req.getServletContext().getRealPath("/") + UPLOAD_REPERTOIRE + File.separator + fileName;
                this.photo = fileName;
                filePart.write(savePath);
                return true;
            }
    	}

        return false;
    	
    }

    public boolean validate() {
        boolean isValid = true;

        if (nom == null || nom.length() < 3) {
            errors.put("nom", "Le nom doit contenir au moins 3 caractères.");
            isValid = false;
        }

        if (categorie == null || categorie.length() < 3) {
            errors.put("categorie", "La catégorie doit contenir au moins 3 caractères.");
            isValid = false;
        }

        if (prix == null || prix <= 0) {
            errors.put("prix", "Le prix doit être supérieur à zéro.");
            isValid = false;
        }
        if (qte_disponible <= 0) {
            errors.put("qte_disponible", "La quantité doit être supérieur à zéro.");
            isValid = false;
        }

        if (description == null || description.length() < 10) {
            errors.put("description", "La description doit contenir au moins 10 caractères.");
            isValid = false;
        }

        if (photo == null || !isSupportedImageType(photo)) {
            errors.put("photo", "Le format de l'image n'est pas pris en charge.");
            isValid = false;
        }

        return isValid;
    }

    private boolean isSupportedImageType(String fileName) {
        String[] supportedTypes = { "jpg", "jpeg", "png" };
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        for (String type : supportedTypes) {
            if (type.equals(fileExtension)) {
                return true;
            }
        }
        return false;
    }
    
    private String getFileExtension(String fileName) {
        if (fileName != null) {
            int index = fileName.lastIndexOf('.');
            if (index > 0 && index < fileName.length() - 1) {
                return fileName.substring(index + 1);
            }
        }
        return null;
    }
}
