package im.prox.MongoRegister;

import java.net.UnknownHostException;

import im.prox.MongoRegister.Log;

import org.bukkit.plugin.java.JavaPlugin;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


public class RegisterPlugin extends JavaPlugin{
	
	public static RegisterPlugin plugin;
	
	public MongoClient mongo;
	
	public void onEnable(){
		getCommand("register").setExecutor(new RegisterCommand(this));
		
		String address = plugin.getConfig().getString("database.address");
		int port = plugin.getConfig().getInt("database.port");
		Log.info("Connecting to database at "+address+":"+port);
		try {
			mongo = new MongoClient(address, port);
		} catch (UnknownHostException e) {
			Log.severe("Unable to connect to database!");
			e.printStackTrace();
		}
	}
	
	public void onDisable(){
		
	}
	
	public DBCollection getCollection(){
		String dbName = plugin.getConfig().getString("database.db");
		String collectionName = plugin.getConfig().getString("database.collection");
		return mongo.getDB(dbName).getCollection(collectionName);
	}
	
}
