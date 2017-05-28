package cphbusiness.test.sp.integration.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cphbusiness.test.sp.integration.entity.User;

public class MysqlService {

	private String url;
	private String username;
	private String password;
	private String driver;

	public MysqlService(String url, String username, String password, String driver) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.driver = driver;
	}

	public Connection getConnection() {
		try {
			Class.forName(this.driver);
			return DriverManager.getConnection(this.url, this.username, this.password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		try (Connection conn = getConnection()) {
			PreparedStatement ps = conn.prepareStatement(getAllUsersPS());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				users.add(
						new User(rs.getInt("id"), rs.getString("name"), rs.getString("job"), rs.getString("birthday")));
			}

		} catch (Exception e) {
			return null;
		}
		return users;

	}

	public int getDepthOneByUser(int id) {
		try (Connection conn = getConnection()) {
			PreparedStatement ps = conn.prepareStatement(getAllEndorsmentsByUserPS(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;

	}

	private String getAllUsersPS() {
		return "SELECT * FROM nodes";
	}

	private String getAllEndorsmentsByUserPS(int id) {
		return "SELECT count(DISTINCT target_node_id) FROM edges JOIN nodes ON nodes.id = source_node_id "
				+ "WHERE source_node_id =" + id;
	}

}
