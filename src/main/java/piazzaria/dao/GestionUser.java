package piazzaria.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import piazzaria.connection.DBConnection;
import piazzaria.entity.User;


public class GestionUser {
    private static final String queryDisplay = "select * from users where profile = ?";
    private static final String queryInsert = "insert into users(nom, prenom, email, phone, password) values (?,?,?,?,?)";
    private static final String queryCheckLogin = "select * from users where email = ? and password = ?";

    private static final String querySearchEmail = "select * from users where email = ?";    
    private static final String queryDeleteUserById = "delete from users where id = ?";
    private static final String queryGetUserById = "select * from users where id = ?";
    private static final String queryBlockUserById = "update users set status = 1 where id = ?";
    private static final String queryUnblockUserById = "update users set status = 0 where id = ?";




    public static List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryDisplay)) {
        	preparedStatement.setString(1, "user");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String profile = resultSet.getString("profile");
                int status = resultSet.getInt("status");

                User user = new User(id, nom, prenom, profile, email, phone, status);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close();
        }
        return userList;
    }

    public static void insertNewUser(User user){

        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryInsert)){
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();

           
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnection.getInstance().close();
        }
    }


    public static User getUserLogin(String email, String password){
        User user = null;
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryCheckLogin)){
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String phone = resultSet.getString("phone");
                int status = resultSet.getInt("status");
                String profile = resultSet.getString("profile");
                
                user = new User(id, nom, prenom, profile, email, phone, status);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnection.getInstance().close();
        }
        return user;
    }

    public static Boolean checkEmail(String email){
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(querySearchEmail)){
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnection.getInstance().close();
        }
        return false;
    }
    
    public static User getUserById(int userId) {
        User user = null;

        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryGetUserById)){
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String profile = resultSet.getString("profile");
                int status = resultSet.getInt("status");

                user = new User(id, nom, prenom, profile, email, phone, status);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnection.getInstance().close();
        }
        return user;
    }
    
    public static void deleteUserById(int userId) {
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryDeleteUserById)){
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnection.getInstance().close();
        }
    }
    
    public static void blockUserById(int userId) {
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryBlockUserById)){
            preparedStatement.setInt(1, userId);
            int blok = preparedStatement.executeUpdate();
            if (blok == 0) {
				System.out.println("non bloquer "+ userId);
			}else {
				System.out.println(" bloquer "+ userId);
			}
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnection.getInstance().close();
        }
    }
    
    public static void unblockUserById(int userId) {
        try (PreparedStatement preparedStatement = DBConnection.getInstance().preparedQuery(queryUnblockUserById)){
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnection.getInstance().close();
        }
    }
}
