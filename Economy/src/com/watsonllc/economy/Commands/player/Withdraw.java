package com.watsonllc.economy.Commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.watsonllc.economy.Config.Messages;
import com.watsonllc.economy.Controllers.AccountControl;

public class Withdraw implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;
		AccountControl account = new AccountControl(player);
		double amount = Double.parseDouble(args[0]);
		
		if(cmd.getName().equalsIgnoreCase("withdraw") && player.hasPermission("econ.withdraw")) {
			if(args.length == 1) {
				account.withdraw(amount);
				player.sendMessage(Messages.getMessage("withdraw") + amount);
			} else {
				Messages.getMessage("invalid-args");
			}
		}
		return false;
	}
}