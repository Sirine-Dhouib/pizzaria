package piazzaria.entity;


public class PizzaCommande {
    private int id;
    private String numCommande; 
    private int pizzaId;
    private int quantite;

    public PizzaCommande() {
    }

    public PizzaCommande(String numCommande, int pizzaId, int quantite) {
        this.numCommande = numCommande;
        this.pizzaId = pizzaId;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}

