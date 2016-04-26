package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	public static Connection dbCon;
	public Statement dbStmt;
	public ResultSet dbRst;

    public  static Connection setDBConnection() throws  SQLException{
    	
    	try{
    	
    		Class.forName("com.mysql.jdbc.Driver");
    		dbCon=DriverManager.getConnection("jdbc:mysql://localhost:3306/jersey","root","");
    		
    	} catch(ClassNotFoundException e)
    	{
    		System.out.println(e);
    	}
    	
    	
		return dbCon;
    	
    }


    public ResultSet getResultSet(String sqlQuery,Connection conn) throws SQLException{
    	
    	String query=sqlQuery;
    	dbCon=conn;
    	try{
    		
    		dbStmt=dbCon.createStatement();
    		dbRst=dbStmt.executeQuery(query);
    	}catch(SQLException e)
    	{
    		
    	}
    	
		return dbRst;
    	
    }


	public int updateResult(String sqlQuery, Connection conn) throws SQLException{

    	String query=sqlQuery;
    	dbCon=conn;
        int flag=0;
    	try{
    		
    		dbStmt=dbCon.createStatement();
    		flag=dbStmt.executeUpdate(query);
    		System.out.println(flag);
    		
    		
    	}catch(SQLException e)
    	{
    		
    	}
    	
		return flag;
	}
    
}
