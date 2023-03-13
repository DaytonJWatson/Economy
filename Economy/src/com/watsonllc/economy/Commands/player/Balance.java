package com.watsonllc.economy.Commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.watsonllc.economy.Config.Messages;
import com.watsonllc.economy.Controllers.AccountControl;

public class Balance implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;
		AccountControl account = new AccountControl(player);
		
		if(cmd.getName().equalsIgnoreCase("balance") && player.hasPermission("econ.balance")) {
			if(args.length == 0) {
				player.sendMessage(Messages.getMessage("balance") + account.balance());
			}
		}
		return false;
	}

}
