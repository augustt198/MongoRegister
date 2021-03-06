package im.prox.MongoRegister;

import java.net.UnknownHostException;

import im.prox.MongoRegister.Log;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


public class RegisterPlugin extends JavaPlugin{
	
	public static RegisterPlugin plugin;
	
	public MongoClient mongo;
	
	public void onEnable(){
		plugin = this;
		String addPlayer = plugin.getConfig().getString("addplayer");
		if(addPlayer == "true"){
			PluginManager pm = getServer().getPluginManager();
			pm.registerEvents(new RegisterListener(this), this);
			Log.info("Adding players on login enabled!");
		}
		
		getCommand("register").setExecutor(new RegisterCommand(this));
		
		String address = plugin.getConfig().getString("dbaddress");
		
		int port = plugin.getConfig().getInt("dbport");
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
		String dbName = plugin.getConfig().getString("dbname");
		String collectionName = plugin.getConfig().getString("collectionname");
		return mongo.getDB(dbName).getCollection(collectionName);
	}
	
}
