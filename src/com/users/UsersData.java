

import java.sql.*;

public class UsersData {
    Connection connection;
    public UsersData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JukeBox", "root", "Rakesh@2022");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public int validateUser(String userId, String userPassword) {
        int found = 0;
        try {
            String sql = "select * from UsersData";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String user = resultSet.getString(3);
                String userPass = resultSet.getString(4);
                if (user.compareTo(userId) == 0 && userPassword.compareTo(userPass) == 0) {
                    found = 1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return found;
    }
    public int userSignIn(String userName, long usersPhoneNumber, String userId, String userPassword) {
        int row = 0;
        int isExist = validateUser(userId, userPassword);
        if (isExist == 1) {
            System.out.println("Dear User You Have Been Registered Already in Our Portal Please Login");
        } else {
            String sql = "insert into UsersData values(?,?,?,?)";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, userName);
                statement.setLong(2, usersPhoneNumber);
                statement.setString(3, userId);
                statement.setString(4, userPassword);
                row = statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return row;
    }
    public int userLoginPage(String userEmailId, String userPassWord){
        int line = 0;
        try {
            String sql = "select * from UsersData";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                String userId = resultSet.getString(3);
                String userPassword = resultSet.getString(4);
                if (userId.equals(userEmailId) && userPassword.equals(userPassWord)) {
                    line = 1;
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return line;
    }
}



