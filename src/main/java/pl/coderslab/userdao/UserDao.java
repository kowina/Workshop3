package pl.coderslab.userdao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.user.User;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    public static final String RED = "\033[0;31m";
    public static final String RESET = "\033[0m";
    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String RETURN_GENERATED_KEY_QUERY = "SELECT id FROM users ORDER BY id DESC LIMIT 1 ";
    private static final String READ_USER_BY_ID_QUERY ="SELECT * FROM users WHERE id = ?;";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?;";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?;";
    private static final String FIND_ALL_QUERY = "SELECT id FROM users ORDER BY id;";
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public User create(User user){
        try (Connection connection = DbUtil.getConnection()){
            try (PreparedStatement prSt = connection.prepareStatement(CREATE_USER_QUERY);) {
                prSt.setString(1, user.getUserName());
                prSt.setString(2, user.getEmail());
                prSt.setString(3, hashPassword(user.getPassword()));
                prSt.executeUpdate();
                Statement st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(RETURN_GENERATED_KEY_QUERY);
                    if (resultSet.next()){
                        user.setId(resultSet.getInt("id"));
                    }
            }catch (SQLIntegrityConstraintViolationException e){
                System.out.println(RED + "Users email can't be duplicated!" + RESET);
                return null;
            }
            return user;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public User read(int userId){
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement prSt = connection.prepareStatement(READ_USER_BY_ID_QUERY);
            prSt.setInt(1, userId);
            ResultSet rs = prSt.executeQuery();
            User user = new User();
            while (rs.next()){
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }return user;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public void update(User user){
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement prSt = connection.prepareStatement(UPDATE_USER_QUERY);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getEmail());
            prSt.setString(3, hashPassword(user.getPassword()));
            prSt.setInt(4, user.getId());
            prSt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void delete(int userId){
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement prSt = connection.prepareStatement(DELETE_USER_BY_ID);
            prSt.setInt(1, userId);
            prSt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public User[] findAll(){
        try (Connection connection = DbUtil.getConnection()){
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(FIND_ALL_QUERY);
            User[] users = new User[0];
            UserDao userDao = new UserDao();
            while (rs.next()) {
            users = userDao.addToArray(userDao.read(rs.getInt("id")), users);
            }
            return users;
        }catch (SQLException e){
            e.printStackTrace();
        }return null;
    }
    public User[] addToArray(User u, User[] users){
        User[] tempUsers = Arrays.copyOf(users, users.length +1);
        tempUsers[tempUsers.length - 1] = u;
        return tempUsers;
    }
    public void readArray(User[] users){
        for (User u : users){
            System.out.println(u);
        }
    }

}
