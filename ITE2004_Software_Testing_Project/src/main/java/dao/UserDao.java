package dao;

import model.*;

import java.sql.*;

public class UserDao {
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;

	public UserDao(Connection con) {
		this.con = con;
	}
	
	public User userLogin(String email, String password) {
		User user = null;
        try {
            query = "SELECT * FROM users WHERE email=? and password=?";
            /*The conventional method is not being here to prevent SQL Injection.*/
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if(rs.next()){
            	user = new User();
            	user.setId(rs.getInt("id"));
            	user.setName(rs.getString("name"));
            	user.setEmail(rs.getString("email"));
            	/*The password is not being returned due to the security reasons.*/
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return user;
    }
}
