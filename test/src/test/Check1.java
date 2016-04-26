package test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
 
@Path("/check1")
public class Check1 {
	
	DBConnection dbCon;
	Connection conn;
	ResultSet rslt;
	String motd;
 
	  
	
	 @GET
	  @Produces("application/json")
	  public Response getmotd_ap() throws JSONException ,SQLException{

		 System.out.println("inside get");
		JSONObject jsonObject = new JSONObject();
		String user="ap";
		String query="Select MOTD from jersey.motd where USER='"+user+"' ";
		try{
			dbCon=new DBConnection();
			conn=DBConnection.setDBConnection();
			rslt=dbCon.getResultSet(query, conn);
			String motd;
			
			if(rslt.next())
			{
			
				String motd_address = rslt.getString(1);
				 System.out.println("address" +motd_address);
				if(!motd_address.isEmpty())			
				{
					
					
					BufferedReader reader = new BufferedReader(new FileReader (motd_address));
				    String         line = null;
				    StringBuilder  stringBuilder = new StringBuilder();
				    String         ls = System.getProperty("line.separator");

				    try {
				        while( ( line = reader.readLine() ) != null ) {
				            stringBuilder.append( line );
				            //stringBuilder.append( ls );
				            stringBuilder.append( "   " );
				           
				           
				        }

				        motd= stringBuilder.toString();
				        System.out.println("output" +motd);
				     //   motd.replace("\n", "").replace("\r", "");
				        jsonObject.put("Current MOTD AP",motd ); 
				        
				    } finally {
				        reader.close();
				    }
					
				}
				
			}
			else
			{
				motd="Unsuccessfull";
				jsonObject.put("Current MOTD AP",motd ); 
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		String result = ""+ jsonObject;
		return Response.status(200).entity(result).build();
	  } 
	 
	
	
	  @Path("{motd_ap}")
	  @GET
	  @Produces("application/json")
	  public Response convertFtoCfromInput(@PathParam("motd_ap") String motd_ap) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		String new_motd_ap = motd_ap;
		String user="ap";
		String motd="Unsuccess";
		int flag;
		FileWriter writer;
		BufferedWriter bufferedWriter = null;
		
		System.out.println(" input" +new_motd_ap);
		
		String query="Select MOTD from jersey.motd where USER='"+user+"' ";
		
		String[] line=new_motd_ap.split("   ");
		
		try{
			dbCon=new DBConnection();
			conn=DBConnection.setDBConnection();
			rslt=dbCon.getResultSet(query, conn);
			
			
			if(rslt.next())
			{
			
				String motd_address = rslt.getString(1);
				System.out.println(motd_address);
				if(!motd_address.isEmpty())			
				{
					
					try{
				 writer = new FileWriter(motd_address, false);
			     bufferedWriter = new BufferedWriter(writer);
			  
			     
						     for (String s: line) {           
						    	
						         System.out.println(s); 
						         bufferedWriter.write(s);
						         bufferedWriter.newLine();
						     }
			   motd=motd_ap;
			 jsonObject.put("New MOTD AP",motd ); 
			          
					}
					
			          
				        
				     finally {
				    	  bufferedWriter.close();
				    }
					
				}
				
			}
			else
			{
				motd="Unsuccessfull";
				jsonObject.put("Current MOTD AP",motd ); 
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	
		System.out.println("before");
		
		String result = ""+ jsonObject;
		return Response.status(200).entity(result).build();
	  }
	
	
	  
	  
	  
}

