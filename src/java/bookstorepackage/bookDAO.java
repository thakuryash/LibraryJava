/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstorepackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thaku
 */
public class bookDAO {
  private String jdbcURL, jdbcUsername, jdbcPassword;
  private Connection jdbcConnection;

    public bookDAO(String jdbcURL, String jdbcUsername, String jdbcPassword){
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
    
    protected void connect() throws SQLException{
        if(jdbcConnection == null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
            }catch(ClassNotFoundException ce){
                ce.printStackTrace();
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
    
    protected void disconnect() throws SQLException{
        if(jdbcConnection!=null || !jdbcConnection.isClosed())
            jdbcConnection.close();
    }
    
    public boolean insertBook(book b) throws SQLException{
        String sql = "Insert into book (title, author, price) values (?,?,?)";
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, b.getTitle());
        statement.setString(2, b.getAuthor());
        statement.setFloat(3, b.getPrice());
        
        boolean rowInserted = statement.executeUpdate()>0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public boolean updateBook(book b )throws SQLException
    {
        String sql = "Update book set title=?, author=?, price=? where id =?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, b.getTitle());
        statement.setString(2, b.getAuthor());
        statement.setFloat(3, b.getPrice());
        statement.setInt(4, b.getId());
        boolean rowupdated = statement.executeUpdate() >0;
        statement.close();
        disconnect();
        return rowupdated;
        
    }
    
     public boolean deleteBook(book b )throws SQLException
    {
        String sql = "Delete from book where id =?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, b.getId());
        
        boolean rowdeleted = statement.executeUpdate()>0;
        statement.close();
        disconnect();
        return rowdeleted;
        
    }
     
     public List<book> listAllBooks() throws SQLException
     {
         List<book> listbook = new ArrayList<>();
         
         String sql = "Select * from book";
         connect();
         
         Statement statement = jdbcConnection.createStatement();
         
         ResultSet result = statement.executeQuery(sql);
         
         while(result.next())
         {
             int id = result.getInt("id");
             String title = result.getString("title");
             String author = result.getString("author");
             float price = result.getFloat("price");
             
             book b = new book(id,title,author,price);
             listbook.add(b);
         }
         result.close();
         statement.close();
         disconnect();
         
         return listbook;
     }
     
     public book getBook(int id) throws SQLException
     {
         book b = null;
         String sql = "select * from book where id =?";
         
         connect();
         
         Statement statement = jdbcConnection.createStatement();
         
         ResultSet result = statement.executeQuery(sql);
         if(result.next())
         {
           String title  = result.getString("title");
           String author = result.getString("author");
           float price = result.getFloat("price");
           
           b = new book(id,title,author,price);
         }
         result.close();
         statement.close();
         disconnect();
         
         return b;
     }
}
