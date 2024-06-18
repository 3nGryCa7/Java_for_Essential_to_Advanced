package com.project.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class MysqlApplication {

	public static String _driver = "com.mysql.cj.jdbc.Driver";
	public static String _url = "jdbc:mysql://localhost:8306/dbtest?" +
			"useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

	public static void main(String[] args) {
		Connection conn = null;

		try {
			Class.forName(_driver);
			conn = DriverManager.getConnection(_url, "root", "toor");
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO tabletest(column2) VALUES ('abc')";
			System.out.println("Execute " + sql);
			int result = stmt.executeUpdate(sql);
			System.out.println("result = " + result);
			sql = "SELECT * FROM tabletest";
			System.out.println("Execute " + sql);
			ResultSet res = stmt.executeQuery(sql);

			while (res.next()) {
				int column1 = res.getInt("column1");
				String column2 = res.getString("column2");
				System.out.println(column1 + ":" + column2);
			}
			res.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				System.out.println("Connection is closed.");
				try {
					conn.close();
				} catch (Exception ignored) {}
			}
		}
	}

}
