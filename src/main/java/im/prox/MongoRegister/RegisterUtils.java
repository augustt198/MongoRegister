package im.prox.MongoRegister;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

public class RegisterUtils {

	public static boolean playerExists(RegisterPlugin plugin, String username){
		String usernamefield = plugin.getConfig().getString("usernamefield");
		
		BasicDBObject query = new BasicDBObject(usernamefield, username);
		DBCursor cursor = plugin.getCollection().find(query);
		
		if(cursor.hasNext()){
			return true;
		}
		return false;
	}
	
	public static boolean emailExists(RegisterPlugin plugin, String username){
		String usernamefield = plugin.getConfig().getString("usernamefield");
		String emailfield = plugin.getConfig().getString("emailfield");
		BasicDBObject query = new BasicDBObject(usernamefield, username);
		query.put(emailfield, null);
		
		DBCursor cursor = plugin.getCollection().find(query);
		
		if(cursor.hasNext()){
			return false;
		}
		return true;
	}
	
	public static void updateEmail(RegisterPlugin plugin, String username, String email){
		String usernamefield = plugin.getConfig().getString("usernamefield");
		String emailfield = plugin.getConfig().getString("emailfield");
		BasicDBObject match = new BasicDBObject(usernamefield, username);
		BasicDBObject update = new BasicDBObject("$set", new BasicDBObject(emailfield, email));
		plugin.getCollection().update(match, update);
	}
	
	
}
