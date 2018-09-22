package ggboy.idea.java.用户邀请人数排序缓存;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import mustry.common.utils.uuid.UuidUtil;

public class Database {
	private final Connection conn;
	private final static String url = "jdbc:mysql://47.104.5.254:3306/study";
	private final static String username = "root";
	private final static String password = "Zzq=123456";

	Database(String url, String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = (Connection) DriverManager.getConnection(url, username, password);
	}

	public Connection getConnection() {
		return this.conn;
	}

	public void close() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Database database = new Database(Database.url, Database.username, Database.password);
		Connection conn = database.getConnection();
		String sql = "insert into invite (user_name,invite_num) values(?,?)";
		PreparedStatement pstmt;
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setInt(2, 0);
		for (int i = 0;i<99999;i++) {
			pstmt.setString(1, UuidUtil.getUuid());
			pstmt.executeUpdate();
		}
		System.out.println("done");
	}
}

class User implements Comparable<User> {
	String name;
	int num;

	User(String name) {
		this.name = name;
		this.num = 0;
	}

	public void add() {
		num++;
	}

	@Override
	public int compareTo(User user) {
		return (this.num < user.num) ? -1 : ((this.num == user.num) ? 0 : 1);
	}
}