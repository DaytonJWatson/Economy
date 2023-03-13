package com.watsonllc.economy.Commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.watsonllc.economy.Config.Messages;
import com.watsonllc.economy.Currency.Currency;

public class Create implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;
		Currency coin = new Currency();
		double amount = Double.parseDouble(args[0]);
		
		if(cmd.getName().equalsIgnoreCase("createcurrency")) {
			if(args.length == 1) {
				player.getInventory().addItem(coin.createCurrency(amount));
			} else {
				player.sendMessage(Messages.getMessage("invalid-args"));
			}
			
		}
		return false;
	}

}
