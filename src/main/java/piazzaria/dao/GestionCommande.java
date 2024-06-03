package piazzaria.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import piazzaria.connection.DBConnection;
import piazzaria.entity.Commande;
import piazzaria.entity.Pizza;
import piazzaria.entity.PizzaCommande;

public class GestionCommande {
	
    private static final String queryCommandeEnAttenteyUserId = "select * from commandes where user_id=? and status=0";
    private static final String queryHistoriqueByUserId = "select * from commandes where user_id=? and status=1";

    private static final String queryInsert = "insert into commandes(num_commande, user_id, total, date_commande, adresse, phone) values (?,?,?,?,?,?)";

    private static final String queryInsertOrderProduct = "insert into pizza_commandes(num_commande, pizza_id, quantite) values (?, ?, ?)";
    
    private static final String queryDisplayPizzaCommandesByNumCommande = "select * from pizza_commandes where num_commande = ?";
    private static final String queryPizzaById = "select * from pizzas where id = ?";
    
    private static final String queryListeCommandesEnAttente = "select * from commandes where status=0";
    private static final String queryHistoriques = "select * from commandes where status=1";
    private static final String queryCommandeByOrderNumber = "select * from commandes where num_commande = ?";
    private static final String queryUpdateCommandeStatus = "update commandes set status=?, date_validation=? where num_commande=?";


    public static List<Commande> getCommandeByUserId(int userId){
        List<Commande> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryCommandeEnAttenteyUserId)){
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String num_commande = resultSet.getString("num_commande");
                float total = resultSet.getFloat("total");
                String date = resultSet.getString("date_commande");
                String adresse = resultSet.getString("adresse");
                String phone = resultSet.getString("phone");
                Commande commande = new Commande();
                commande.setNum_commande(num_commande);
                commande.setTotal(total);
                commande.setDate_commande(date);
                commande.setAdresse(adresse);
                commande.setPhone(phone);
                result.add(commande);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnection.getInstance().close();
        }
        return result;
    }
    
    public static List<Commande> getHistoriqueByUserId(int userId){
        List<Commande> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryHistoriqueByUserId)){
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String num_commande = resultSet.getString("num_commande");
                float total = resultSet.getFloat("total");
                String date = resultSet.getString("date_commande");
                String dateV = resultSet.getString("date_validation");
                String adresse = resultSet.getString("adresse");
                String phone = resultSet.getString("phone");
                Commande commande = new Commande();
                commande.setNum_commande(num_commande);
                commande.setTotal(total);
                commande.setDate_validation(dateV);
                commande.setDate_commande(date);
                commande.setAdresse(adresse);
                commande.setPhone(phone);
                result.add(commande);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnection.getInstance().close();
        }
        return result;
    }

    public static void insertNewCommande(Commande commande){

        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryInsert)){
            preparedStatement.setString(1, commande.getNum_commande());
            preparedStatement.setInt(2, commande.getUser_id());
            preparedStatement.setFloat(3, commande.getTotal());
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = dateFormat.format(date);
            preparedStatement.setString(4, dateStr);
            preparedStatement.setString(5, commande.getAdresse());
            preparedStatement.setString(6, commande.getPhone());
            preparedStatement.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnection.getInstance().close();
        }
    }

    public static void insertNewPizzaCommandee(String numCommande, int pizza_id, int quantite){

        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryInsertOrderProduct)){
            preparedStatement.setString(1, numCommande);
            preparedStatement.setInt(2, pizza_id);
            preparedStatement.setInt(3, quantite);
            preparedStatement.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnection.getInstance().close();
        }
    }
    public static Pizza getPizzaById(int id) {
        Pizza pizza = new Pizza();
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryPizzaById)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String categorie = resultSet.getString("categorie");
                float prix = resultSet.getFloat("prix");
                String photo = resultSet.getString("photo");
                int qte_disponible = resultSet.getInt("qte_disponible");
                String description = resultSet.getString("description");
                
                pizza.setId(id);
                pizza.setNom(nom);
                pizza.setCategorie(categorie);
                pizza.setPrix(prix);
                pizza.setPhoto(photo);
                pizza.setQte_disponible(qte_disponible);
                pizza.setDescription(description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close();
        }
        return pizza;
    }
    
   
    public static Map<Integer, PizzaCommande> getCommandesByOrderNumber(String numCommande) {
        Map<Integer, PizzaCommande> result = new HashMap<>();
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryDisplayPizzaCommandesByNumCommande)) {
            preparedStatement.setString(1, numCommande);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int pizzaId = resultSet.getInt("pizza_id");
                int quantity = resultSet.getInt("quantite");

                PizzaCommande pizzaCommande = new PizzaCommande();
                pizzaCommande.setQuantite(quantity);
                pizzaCommande.setNumCommande(numCommande);
                pizzaCommande.setPizzaId(pizzaId);

                result.put(pizzaId, pizzaCommande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close();
        }
        return result;
    }
    
    public static List<Commande> getAllCommandesEnAttentes() {
        List<Commande> commandesEnAttente = new ArrayList<>();

        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryListeCommandesEnAttente)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String num_commande = resultSet.getString("num_commande");
                float total = resultSet.getFloat("total");
                String date = resultSet.getString("date_commande");
                String adresse = resultSet.getString("adresse");
                String phone = resultSet.getString("phone");
                Commande commande = new Commande();
                commande.setId(id);
                commande.setNum_commande(num_commande);
                commande.setTotal(total);
                commande.setDate_commande(date);
                commande.setAdresse(adresse);
                commande.setPhone(phone);
                commandesEnAttente.add(commande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close();
        }

        return commandesEnAttente;
    }

    public static List<Commande> getHistoriques() {
        List<Commande> historiques = new ArrayList<>();

        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryHistoriques)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String num_commande = resultSet.getString("num_commande");
                float total = resultSet.getFloat("total");
                String date = resultSet.getString("date_commande");
                String dateV = resultSet.getString("date_validation");
                String adresse = resultSet.getString("adresse");
                String phone = resultSet.getString("phone");
                Commande commande = new Commande();
                commande.setId(id);
                commande.setNum_commande(num_commande);
                commande.setTotal(total);
                commande.setDate_validation(dateV);
                commande.setDate_commande(date);
                commande.setAdresse(adresse);
                commande.setPhone(phone);
                historiques.add(commande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close();
        }

        return historiques;
    }
    
    public static Commande getCommandeByOrderNumber(String numCommande) {
        Commande commande = null;

        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryCommandeByOrderNumber)) {
            preparedStatement.setString(1, numCommande);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                float total = resultSet.getFloat("total");
                String date = resultSet.getString("date_commande");
                String adresse = resultSet.getString("adresse");
                String phone = resultSet.getString("phone");
                int userId = resultSet.getInt("user_id");

                commande = new Commande();
                commande.setNum_commande(numCommande);
                commande.setTotal(total);
                commande.setDate_commande(date);
                commande.setAdresse(adresse);
                commande.setPhone(phone);
                commande.setUser_id(userId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close();
        }

        return commande;
    }
    


    public static void validerCommande(String numCommande) {
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryUpdateCommandeStatus)) {
            preparedStatement.setInt(1, 1);
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = dateFormat.format(date);
            preparedStatement.setString(2, dateStr);
            preparedStatement.setString(3, numCommande);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close();
        }
    }

    public static void annulerCommande(String numCommande) {
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryUpdateCommandeStatus)) {
            preparedStatement.setInt(1, 2);            
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = dateFormat.format(date);
            preparedStatement.setString(2, dateStr);
            preparedStatement.setString(3, numCommande);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close();
        }
    }

}

