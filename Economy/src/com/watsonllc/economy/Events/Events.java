package com.watsonllc.economy.Events;

import org.bukkit.plugin.PluginManager;

import com.watsonllc.economy.Econ;
import com.watsonllc.economy.Events.player.PlayerJoin;

public class Events {
	public static void setup() {
		PluginManager pm = Econ.instance.getServer().getPluginManager();
		
		// register the events
		pm.registerEvents(new PlayerJoin(), Econ.instance);
	}
}
