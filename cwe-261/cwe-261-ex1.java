// read password from properties file and use it to connect to database

=======
Suggestion 2

// use try with resources to close connection automatically
// use prepared statement to prevent sql injection

import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;

public class JdbcSelectTest2 {
  public static void main(String[] args) {
    // read username, password and db url from properties file
    try (FileInputStream fis = new FileInputStream("db.properties")) {
      Properties prop = new Properties();
      prop.load(fis);
      String username = prop.getProperty("username");
      String password = prop.getProperty("password");
      String dburl = prop.getProperty("dburl");
      // connect to database
      try (Connection conn = DriverManager.getConnection(dburl, username, password)) {
        // create a statement
        Statement stmt = conn.createStatement();
        // execute a query
        ResultSet rs = stmt.executeQuery("select * from s1");
        // process the result set
        while (rs.next()) {
          System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

=======
Suggestion 3

// note: properties file is not checked in to git
// to run:
// javac DBConnect.java
// java -cp ".:mysql-connector-java-5.1.22-bin.jar" DBConnect
// output:
// Connected to database
// Disconnected from database
// 
// reference: http://www.vogella.com/articles/MySQLJava/article.html

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnect {
	public static void main(String[] args) {
		// read properties file
		Properties prop = new Properties();
		try {
			prop.load(DBConnect.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// connect to database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(prop.getProperty("dburl"), prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
			System.out.println("Connected to database");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close connection
			try {
				if (conn != null) {
					conn.close();
					System.out.println("Disconnected from database

=======
Suggestion 4

// https://stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database
import java.io.*;
import java.util.*;
import java.sql.*;

public class ConnectToMySql {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
            conn.close();
        } catch (Exception e) {

=======
Suggestion 5


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ReadPasswordFromPropertiesFileAndUseItToConnectToDatabase {
    public static void main(String[] args) throws IOException, SQLException {
        // 1. read properties file
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/db.properties");
        properties.load(fileInputStream);

        // 2. get values from properties file
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        // 3. connect to database
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employees");

        // 4. print results
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
        }

        // 5. close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}

=======
Suggestion 6

// 1. create a properties file with username and password
// 2. load the file using java
// 3. get the username and password from properties file
// 4. use it to connect to database

import java.sql.*;
import java.io.*;
import java.util.Properties;

public class JdbcProperties {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub

		Properties prop = new Properties();
		InputStream input = new FileInputStream("src/main/java/db.properties");
		prop.load(input);
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String sql = "select * from employees where salary > 10000";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getString("first_name") + "-" + rs.getString("last_name") + "-"
						+ rs.getString("salary") + "-" + rs.getString("department_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			st.close();
			rs.close();
		}

	}

}
