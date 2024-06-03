package piazzaria.entity;

public class Commande {

	private int id;
	private String num_commande;
	private int user_id;
	private Float total;
	private String date_commande;
	private String date_validation;
	private String adresse;
	private String phone;
	
	
	public Commande(String num_commande, int user_id, Float total, String adresse, String phone) {
		super();
		this.num_commande = num_commande;
		this.user_id = user_id;
		this.total = total;
		this.adresse = adresse;
		this.phone = phone;
	}
	public Commande() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNum_commande() {
		return num_commande;
	}
	public void setNum_commande(String num_commande) {
		this.num_commande = num_commande;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public String getDate_commande() {
		return date_commande;
	}
	public void setDate_commande(String date_commande) {
		this.date_commande = date_commande;
	}
	public String getDate_validation() {
		return date_validation;
	}
	public void setDate_validation(String date_validation) {
		this.date_validation = date_validation;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
