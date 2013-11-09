package im.prox.MongoRegister;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class RegisterCommand implements CommandExecutor {

	private final RegisterPlugin plugin;
	
	public RegisterCommand(RegisterPlugin plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED + "You must be a player to execute this command!");
			return false;
		}
		if(args.length > 1){
			sender.sendMessage(ChatColor.RED + "Too many arguments!");
			Usage(sender);
			return false;
		}
		if(args.length < 1){
			sender.sendMessage(ChatColor.RED + "Too few arguments!");
			Usage(sender);
			return false;
		}
		if(!RegisterUtils.validateEmail(args[0])){
			sender.sendMessage(ChatColor.RED + "Invalid email!");
			return false;
		}
		if(!RegisterUtils.playerExists(plugin, sender.getName())){
			sender.sendMessage(ChatColor.RED + "You don't exist in the database!");
			return false;
		}
		if(RegisterUtils.emailExists(plugin, sender.getName())){
			sender.sendMessage(ChatColor.RED + "You already have an email associated with your account!");
			return false;
		}
		
		RegisterUtils.updateEmail(plugin, sender.getName(), args[0]);
		sender.sendMessage(ChatColor.AQUA+"The email "+ChatColor.GREEN+args[0]+ChatColor.AQUA+" has been successfully linked to this account!");
		return false;
	}
	
	public void Usage(CommandSender player){
		player.sendMessage(ChatColor.AQUA+"Usage: "+ChatColor.GOLD+"/register "+ChatColor.GREEN+"[email]");
	}
	
}
