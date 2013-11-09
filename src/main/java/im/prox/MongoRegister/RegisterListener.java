package im.prox.MongoRegister;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RegisterListener implements Listener {

	final RegisterPlugin plugin;
	
	public RegisterListener(RegisterPlugin plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void PlayerLogin(PlayerJoinEvent event){
		String username = event.getPlayer().getName();
		if(!RegisterUtils.playerExists(plugin, username)){
			RegisterUtils.addPlayer(plugin, username);
		}
	}
	
}
