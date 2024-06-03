package piazzaria.entity;

public class Pizza {

	private int id;
    private String nom;
    private String categorie;
    private Float prix;
    private String photo;
    private int qte_disponible;
    private String description;
    
    public Pizza() {
    	
    }
    
    public int getQte_disponible() {
		return qte_disponible;
	}

	public void setQte_disponible(int qte_disponible) {
		this.qte_disponible = qte_disponible;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Pizza(String nom, String categorie, Float prix, String photo, String description, int qte_disponible) {
		super();
		this.nom = nom;
		this.categorie = categorie;
		this.prix = prix;
		this.photo = photo;
		this.description = description;
		this.qte_disponible = qte_disponible;
	}

	public Pizza(int id, String nom, String categorie, Float prix, String photo, String description, int qte_disponible) {
		super();
		this.id = id;
		this.nom = nom;
		this.categorie = categorie;
		this.prix = prix;
		this.photo = photo;
		this.description = description;
		this.qte_disponible = qte_disponible;
	}
	
}
