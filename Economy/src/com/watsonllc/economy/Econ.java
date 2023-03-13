package com.watsonllc.economy;

import org.bukkit.plugin.java.JavaPlugin;

import com.watsonllc.economy.Commands.Commands;
import com.watsonllc.economy.Config.Config;
import com.watsonllc.economy.Events.Events;

public class Econ extends JavaPlugin {

	public static Econ instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
		Commands.setup();
		Config.setup();
		Events.setup();
	}	
	
	public static void warning(String string) {
		Econ.instance.getLogger().warning(string);
	}
}
