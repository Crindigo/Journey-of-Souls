/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josmud.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Steven
 */
public class Database {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStmt = null;
	private ResultSet resultSet = null;

	public boolean connect() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/rpg2?user=root&password=test");
		} catch ( Exception e ) {
			throw e;
		}

		return true;
	}

	public boolean doStuff() throws Exception {
		statement = connection.createStatement();
		resultSet = statement.executeQuery("SELECT * FROM news");

		while ( resultSet.next() ) {
			int newsId = resultSet.getInt("news_id");
			int newsAuthor = resultSet.getInt("news_author");
			String newsTitle = resultSet.getString("news_title");
			String newsBody = resultSet.getString("news_body");
			Date newsDate = resultSet.getDate("news_time");

			System.out.println("ID: " + newsId);
			System.out.println("AuthorID: " + newsAuthor);
			System.out.println("Title: " + newsTitle);
			System.out.println("Body: " + newsBody);
			System.out.println("Date: " + newsDate);
		}

		return true;
	}

	public void close() throws Exception {
		try {
			if ( connection != null ) {
				connection.close();
			}
			if ( statement != null ) {
				statement.close();
			}
			if ( resultSet != null ) {
				resultSet.close();
			}
		} catch ( Exception e ) {
			throw e;
		}
	}
}
