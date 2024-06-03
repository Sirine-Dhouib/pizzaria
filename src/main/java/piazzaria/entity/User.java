package piazzaria.entity;


public class User {
	
	public User() {}
	
	public User(int id, String nom, String prenom, String profil, String email, String phone, int status) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.profil = profil;
		this.email = email;
		this.phone = phone;
		this.status = status;
	}
	
	public User(String nom, String prenom, String email, String phone, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.phone = phone;
		this.password = password;
		
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getProfil() {
		return profil;
	}
	public void setProfil(String profil) {
		this.profil = profil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	private int id;
    private String nom;
    private String prenom;
    private String profil;
    private String email;
    private String phone;
    private int status;
    private String password;
}
