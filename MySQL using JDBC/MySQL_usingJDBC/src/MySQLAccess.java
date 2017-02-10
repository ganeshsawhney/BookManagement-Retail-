import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner; 

public class MySQLAccess {
        
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  final private String host = "127.0.0.1";
  final private String user = "root";
  final private String passwd = "";
  
  public void readDataBase() throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://" + host + "/feedback?"
              + "user=" + user + "&password=" + passwd );

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      
      preparedStatement = connect
      .prepareStatement("CREATE DATABASE IF NOT EXISTS eclipse_MySQL_usingJDBC; ");
      preparedStatement.executeUpdate();
      System.out.println("DB Created");
      
      preparedStatement = connect
      .prepareStatement("CREATE table IF NOT EXISTS eclipse_MySQL_usingJDBC.books ( id INT NOT NULL AUTO_INCREMENT , name VARCHAR (50), author VARCHAR (50) , primary key(id) ); ");
      preparedStatement.executeUpdate();
      System.out.println("Table Created");
      
	  preparedStatement = connect
	          .prepareStatement("SELECT id,name,author from eclipse_MySQL_usingJDBC.books");
	  resultSet = preparedStatement.executeQuery();
      System.out.println("Current Data in Table: ");
	  writeResultSet(resultSet);
      System.out.println();
      
      System.out.print("Enter name of book to be inserted: ");
      Scanner scan = new Scanner(System.in);
      String ui_name = scan.next();
      System.out.print("\nEnter author of book to be inserted: ");
      String ui_author = scan.next();
      System.out.println();

      
	  preparedStatement = connect
	          .prepareStatement("insert into eclipse_MySQL_usingJDBC.books values (default, ?, ?)");
	  preparedStatement.setString(1, ui_name);
	  preparedStatement.setString(2, ui_author);
	  preparedStatement.executeUpdate();
      System.out.println("Successful write to DB");
      
	  preparedStatement = connect
	          .prepareStatement("SELECT id,name,author from eclipse_MySQL_usingJDBC.books");
	  resultSet = preparedStatement.executeQuery();
      System.out.println("Updated Data in Table: ");
	  writeResultSet(resultSet);
      System.out.println();
      
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }
 
  }


  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
	  int counter=1;
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      System.out.println("Data "+counter+": ");
      String id = resultSet.getString("id");
      String name = resultSet.getString("name");
      String author = resultSet.getString("author");
      System.out.println("\tID: " + id);
      System.out.println("\tName: " + name);
      System.out.println("\tAuthor: " + author);
      System.out.println();
      counter++;
    }

  }
  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

}