package piazzaria.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import piazzaria.connection.DBConnection;
import piazzaria.entity.Pizza;
import piazzaria.entity.User;

public class GestionPizza {

	private static final String queryDisplay = "select * from pizzas where status = 0";
    private static final String queryInsert = "insert into pizzas(nom, categorie, prix, photo, description, qte_disponible) values (?,?,?,?,?,?)";

    private static final String queryById = "select * from pizzas where id = ?";

    private static final String queryUpdate = "update pizzas set nom = ?, categorie = ?, prix = ?, photo=? , description=?, qte_disponible=? where id = ?";

    public static List<Pizza> getAllPizzas() {
        List<Pizza> pizzaList = new ArrayList<>();
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryDisplay)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String categorie = resultSet.getString("categorie");
                float prix = resultSet.getFloat("prix");
                String photo = resultSet.getString("photo");
                int qte_disponible = resultSet.getInt("qte_disponible");
                String description = resultSet.getString("description");

                Pizza pizza = new Pizza(id, nom, categorie, prix, photo, description, qte_disponible);
                pizzaList.add(pizza);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close();
        }
        return pizzaList;
    }
    
    public static Pizza getPizzaById(int id) {
        Pizza pizza = new Pizza();
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryById)) {
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


    public static void insertNewPizza(Pizza pizza){

        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryInsert)){
            preparedStatement.setString(1, pizza.getNom());
            preparedStatement.setString(2, pizza.getCategorie());
            preparedStatement.setFloat(3, pizza.getPrix());
            preparedStatement.setString(4, pizza.getPhoto());
            preparedStatement.setString(5, pizza.getDescription());
            preparedStatement.setInt(6, pizza.getQte_disponible());
            preparedStatement.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnection.getInstance().close();
        }
    }
    
    public static void editPizza(Pizza pizza) {
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryUpdate)) {
            preparedStatement.setString(1, pizza.getNom());
            preparedStatement.setString(2, pizza.getCategorie());
            preparedStatement.setFloat(3, pizza.getPrix());
            preparedStatement.setString(4, pizza.getPhoto());
            preparedStatement.setString(5, pizza.getDescription());
            preparedStatement.setInt(6, pizza.getQte_disponible());
            preparedStatement.setInt(7, pizza.getId()); 
            preparedStatement.executeUpdate();
            int truee = preparedStatement.executeUpdate();
            if(truee == 1) {
            	System.out.println("image modifie avec succes"+ pizza.getId() + " Value "+ truee);
            }else {
            	System.out.println("image non modifie"+ pizza.getId()+ " Value "+ truee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close();
        }
    }
    
    public static void deletePizzaById(int id) {
        String queryDelete = "update pizzas set status = 1 where id = ?";
        
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryDelete)) {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 1) {
                System.out.println("Pizza supprimée avec succès, ID : " + id);
            } else {
                System.out.println("La pizza n'a pas été supprimée, ID : " + id);
            }
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close();
        }
    }


//
//    public static void updateCustomer(int id, String fName, String lName, String email, String phone){
//
//        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryUpdate)){
//            preparedStatement.setString(1, fName);
//            preparedStatement.setString(2, lName);
//            preparedStatement.setString(3, email);
//            preparedStatement.setString(4, phone);
//            preparedStatement.setInt(5, id);
//            preparedStatement.executeUpdate();
//
//        }catch(SQLException e){
//            throw new RuntimeException(e);
//        }finally {
//            DBConnection.getInstance().close();
//        }
//    }
//
//    public static User getUserLogin(String email, String password){
//        User user = null;
//        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryCheckLogin)){
//            preparedStatement.setString(1, email);
//            preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String nom = resultSet.getString("nom");
//                String prenom = resultSet.getString("prenom");
//                String phone = resultSet.getString("phone");
//                int status = resultSet.getInt("status");
//                String profile = resultSet.getString("profile");
//                
//                user = new User(id, nom, prenom, profile, email, phone, status);
//            }
//        }catch(SQLException e){
//            throw new RuntimeException(e);
//        }finally {
//            DBConnection.getInstance().close();
//        }
//        return user;
//    }
//
//    public static Boolean checkEmail(String email){
//        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(querySearchEmail)){
//            preparedStatement.setString(1, email);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return true;
//            }
//        }catch(SQLException e){
//            throw new RuntimeException(e);
//        }finally {
//            DBConnection.getInstance().close();
//        }
//        return false;
//    }
}
