package im.prox.MongoRegister;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

public class RegisterUtils {

	public static boolean playerExists(RegisterPlugin plugin, String username){
		String usernamefield = plugin.getConfig().getString("database.usernamefield");
		
		BasicDBObject query = new BasicDBObject(usernamefield, username);
		DBCursor cursor = plugin.getCollection().find(query);
		
		if(cursor.hasNext()){
			return true;
		}
		return false;
	}
	
	public static boolean emailExists(RegisterPlugin plugin, String username){
		String usernamefield = plugin.getConfig().getString("database.usernamefield");
		String emailfield = plugin.getConfig().getString("database.emailfield");
		BasicDBObject query = new BasicDBObject(usernamefield, username);
		query.put(emailfield, null);
		
		DBCursor cursor = plugin.getCollection().find(query);
		
		if(cursor.hasNext()){
			return true;
		}
		return false;
	}
	
	public static void updateEmail(RegisterPlugin plugin, String username, String email){
		String usernamefield = plugin.getConfig().getString("database.usernamefield");
		BasicDBObject match = new BasicDBObject(usernamefield, username);
		BasicDBObject change = new BasicDBObject(plugin.getConfig().getString("database.emailfield"), email);
		plugin.getCollection().update(match, change);
	}
	
	
}
