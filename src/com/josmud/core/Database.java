/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josmud.core;

import com.josmud.Game;
import com.josmud.util.StringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Steven
 */
public class Database {
	private Connection connection = null;

	public boolean connect() 
			throws ClassNotFoundException, SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch ( ClassNotFoundException e ) {
			throw e;
		}
		
		try {
			String username = Game.config.getProperty("database.username");
			String password = Game.config.getProperty("database.password");
			String dbName   = Game.config.getProperty("database.name");
			String dbHost   = Game.config.getProperty("database.host", "localhost");

			connection = DriverManager.getConnection(
					"jdbc:mysql://" + dbHost + "/" + dbName
					+ "?user=" + username + "&password=" + password);
		} catch ( SQLException e ) {
			throw e;
		}

		return true;
	}

	public Results selectQuery(String sql) {
		return selectQuery(sql, new Object[] {});
	}

	public Results selectQuery(String sql, Object[] params) {
		Results results = null;

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			for ( int i = 0; i < params.length; i++ ) {
				stmt.setObject(i + 1, params[i]);
			}
			ResultSet rs = stmt.executeQuery();
			results = new Results(this, stmt, rs);
		} catch ( SQLException e ) {
			return null;
		}

		return results;
	}

	public int modifyQuery(String sql) {
		return modifyQuery(sql, new Object[] {});
	}

	public int modifyQuery(String sql, Object[] params) {
		int res = -1;

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			for ( int i = 0; i < params.length; i++ ) {
				stmt.setObject(i + 1, params[i]);
			}
			res = stmt.executeUpdate();
			closeStmt(stmt);
		} catch ( SQLException e ) {
			return -1;
		}

		return res;
	}

	/**
	 * Not *entirely* sure if we can rely on the keys and values being in the
	 * same order, but it may be fine as long as the map isn't modified
	 * between the two operations.
	 *
	 * @param table Name of the table to insert data into.
	 * @param fields Map of column name to column value.
	 * @return The return value of the internal executeUpdate call.
	 */
	public int insert(String table, Map<String, Object> fields) {
		StringBuilder sql = new StringBuilder(64);
		sql.append("INSERT INTO `");
		sql.append(table);
		sql.append("` (`");

		// construct the list of field names...
		String[] keys = fields.keySet().toArray(new String[0]);
		sql.append(StringUtils.implode("`, `", keys));
		sql.append("`) VALUES (");

		// construct the list of values...
		Object[] values = fields.values().toArray();
		for ( int i = 0; i < values.length; i++ ) {
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		System.out.println(sql);

		int returnValue = modifyQuery(sql.toString(), values);
		return returnValue;
	}

	public boolean doStuff() throws Exception {
		Integer[] newsIdList = { 1, 3, 6 };
		Results res = selectQuery(
				"SELECT * FROM news WHERE news_id IN (?, ?, ?)",
				newsIdList);
		ResultSet rs = res.rs;

		while ( rs.next() ) {
			int newsId = rs.getInt("news_id");
			int newsAuthor = rs.getInt("news_author");
			String newsTitle = rs.getString("news_title");
			String newsBody = rs.getString("news_body");
			Date newsDate = rs.getDate("news_time");

			System.out.println("ID: " + newsId);
			System.out.println("AuthorID: " + newsAuthor);
			System.out.println("Title: " + newsTitle);
			System.out.println("Body: " + newsBody);
			System.out.println("Date: " + newsDate);
		}

		res.close();

		// insert a record?
		/*HashMap<String,Object> cols = new HashMap<String,Object>();
		cols.put("news_author", 2);
		cols.put("news_title", "Title Added via Java");
		cols.put("news_body", "This is the body of a new article. Cool!");
		cols.put("news_time", new java.sql.Date(System.currentTimeMillis()));
		insert("news", cols);*/

		// alternatively.
		/*Object[] insertParams = new Object[] {
			3,
			"Another Title",
			"Hey look another body.",
			new java.sql.Date(System.currentTimeMillis())
		};
		modifyQuery("INSERT INTO news (news_author, news_title, news_body, news_time)"
				+ " VALUES (?, ?, ?, ?)", insertParams);*/

		return true;
	}

	protected boolean closeStmt(Statement stmt) {
		try {
			if ( stmt == null || stmt.isClosed() ) {
				return false;
			}
			stmt.close();
		} catch ( SQLException e ) {
			return false;
		}
		return true;
	}

	public boolean closeRs(ResultSet rs) {
		try {
			if ( rs == null || rs.isClosed() ) {
				return false;
			}
			rs.close();
		} catch ( SQLException e ) {
			return false;
		}
		return true;
	}

	public void close() throws Exception {
		try {
			if ( connection != null ) {
				connection.close();
			}
		} catch ( SQLException e ) {
			throw e;
		}
	}

	public class Results {
		public Database db;
		public Statement stmt;
		public ResultSet rs;

		public Results(Database db, Statement stmt, ResultSet rs) {
			this.rs = rs;
			this.stmt = stmt;
			this.db = db;
		}

		public void close() {
			db.closeRs(rs);
			db.closeStmt(stmt);
		}
	}
}
