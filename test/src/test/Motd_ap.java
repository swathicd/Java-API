package test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
 
@Path("/motd_ap")
public class Motd_ap {
	
	DBConnection dbCon;
	Connection conn;
	ResultSet rslt;
	String motd;
 
	  @GET
	  @Produces("application/json")
	  public Response getmotd_ap() throws JSONException ,SQLException{
 
		
		JSONObject jsonObject = new JSONObject();
		String user="ap";
		String query="Select MOTD from jersey.motd where USER='"+user+"' ";
		try{
			dbCon=new DBConnection();
			conn=DBConnection.setDBConnection();
			rslt=dbCon.getResultSet(query, conn);
			
			if(rslt.next())
			{
				motd=rslt.getString(1);
			}
			else
				motd="Unsuccessfull";
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		jsonObject.put("Current MOTD Admin/Provisioner",motd ); 
		
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
		String query="Update jersey.motd set MOTD= '"+new_motd_ap+"' where USER='"+user+"' ";
	
 
		
		try{
			dbCon=new DBConnection();
			conn=DBConnection.setDBConnection();
			flag=dbCon.updateResult(query, conn);
			
			if(flag==1)
			{
				motd=new_motd_ap;
			}
			else
			{
				
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		jsonObject.put("New MOTD Admin/Provisioner",motd ); 
		
		String result = ""+ jsonObject;
		return Response.status(200).entity(result).build();
	  }
	  
 
}
