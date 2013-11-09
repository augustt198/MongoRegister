package im.prox.MongoRegister;

import org.apache.commons.validator.routines.EmailValidator;
import org.bukkit.Bukkit;

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
	
	public static void addPlayer(RegisterPlugin plugin, String username){
		String usernamefield = plugin.getConfig().getString("usernamefield");
		BasicDBObject player = new BasicDBObject(usernamefield, username);
		plugin.getCollection().insert(player);
		
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
	
	public static boolean validateEmail(String email){
		if(!email.contains(".") || !email.contains("@")){
			return false;
		}
		if(checkTLD(email) && EmailValidator.getInstance().isValid(email)){
			return true;
		}
		return false;
	}
	
	public static boolean checkTLD(String email){
		String address = email+" ";
		for(int i = 0; i < TLDs.length; i++){
			if(address.contains("."+TLDs[i]+" ")){
				return true;
			}
		}
		return false;
	}
	
	public static String TLDs[] = {
		"aero", "asia", "biz", "com", "coop", "info", "int", "jobs", "mobi", "museum", "name",
		"net", "org", "post", "pro", "tel", "travel", "xxx", "edu", "gov", "mil", "ac", "ad",
		"ae", "af", "ag", "ai", "al", "am", "an", "ao", "aq", "ar", "as", "at", "au", "aw",
		"ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bm", "bn", "bo",
		"br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cat", "cc", "cd", "cf", "cg", "ch",
		"ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cx", "cy", "cz", "dd", "de", "dj",
		"dk", "dm", "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "eu", "eus", "fi",
		"fj", "fk", "fm", "fo", "fr", "ga", "gal", "gb", "ge", "gf", "gg", "gh", "gi", "gl",
		"gm", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht",
		"hu", "id", "ie", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp",
		"ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc",
		"li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mg", "mh",
		"mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx",
		"my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "nr", "nu", "nz", "om",
		"pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py",
		"qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si",
		"sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "su", "sv", "sx", "sy", "sz",
		"tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tp", "tr", "tt",
		"tv", "tw", "tz", "ua", "ug", "uk", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi",
		"vn", "vu", "wf", "ws", "ye", "yt", "yu", "za", "zm", "zw"
	};
	
}
