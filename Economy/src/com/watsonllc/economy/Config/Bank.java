package com.watsonllc.economy.Config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.watsonllc.economy.Econ;

public class Bank {
	public static File bankFile = new File(Econ.instance.getDataFolder(), "bank.yml");
	public static YamlConfiguration bank = YamlConfiguration.loadConfiguration(bankFile);

	public static void create() {
		if (!bankFile.exists()) {
			Econ.warning("Creating 'bank.yml'...");
			addDefaults();
			save(false);
		} else
			Econ.warning("Loading 'bank.yml'...");
	}

	public static void save(boolean announce) {
		try {
			if (announce)
				Econ.warning("Saving 'bank.yml'...");
			bank.save(bankFile);
		} catch (IOException e) {
			Econ.warning("Failed to save 'bank.yml'!");
		}
	}
	
	public static void addDefaults() {
		double defaultBalance = Config.getDouble("defaultBankBalance");
		double defaultTaxRate = Config.getDouble("defaultTaxRate");
		
		bank.set("Bank.balance", defaultBalance);
		bank.set("Bank.tax", defaultTaxRate);
	}

	public static void set(String path, Object object) {
		bank.set(path, object);
		save(false);
	}

	public static String getString(String path) {
		return bank.getString(path);
	}

	public static double getDouble(String path) {
		return bank.getDouble(path);
	}
	
	public static char getChar(String path) {
		return bank.getString(path).charAt(0);
	}
	
	public static boolean getBoolean(String path) {
		return bank.getBoolean(path);
	}
}
