package com.watsonllc.economy.Config;

import com.watsonllc.economy.Econ;

public class Config {	
	public static void setup() {
		create();
		Bank.create();
		Messages.create();
	}
	
	public static void create() {
		Econ.instance.getConfig().options().copyDefaults(true);
		Econ.instance.saveDefaultConfig();
		Econ.warning("Loading 'config.yml'...");
		
	}
	
	public static void save(boolean announce) {
		if(announce = true) Econ.warning("Saving 'config.yml'...");
		Econ.instance.saveConfig();
	}
	
	public static void set(String path, Object object) {
		Econ.instance.getConfig().set(path, object);
		save(false);
	}
	
	public static String getString(String path) {
		return Econ.instance.getConfig().getString(path);
	}
	
	public static double getDouble(String path) {
		return Econ.instance.getConfig().getDouble(path);
	}
	
	public static boolean getBoolean(String path) {
		return Econ.instance.getConfig().getBoolean(path);
	}
	
	public static char getChar(String path) {
		return Econ.instance.getConfig().getString(path).charAt(0);
	}
}
