package com.watsonllc.economy.Commands;

import com.watsonllc.economy.Econ;
import com.watsonllc.economy.Commands.admin.Create;
import com.watsonllc.economy.Commands.player.Balance;

public class Commands {
	public static void setup() {
		//admin
		Econ.instance.getCommand("createcoin").setExecutor(new Create());
		
		//player
		Econ.instance.getCommand("balance").setExecutor(new Balance());
		Econ.instance.getCommand("deposit").setExecutor(new Balance());
		Econ.instance.getCommand("withdraw").setExecutor(new Balance());
		
	}
}
