package com.watsonllc.economy.Config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.watsonllc.economy.Econ;
import com.watsonllc.economy.Utilities.Utils;

public class Messages {
	public static File messagesFile = new File(Econ.instance.getDataFolder(), "messages.yml");
	public static YamlConfiguration messages = YamlConfiguration.loadConfiguration(messagesFile);

	public static void create() {
		if (!messagesFile.exists()) {
			Econ.warning("Creating 'messages.yml'...");
			addDefaults();
			save(false);
		} else
			Econ.warning("Loading 'messages.yml'...");
	}

	public static void save(boolean announce) {
		try {
			if (announce)
				Econ.warning("Saving 'messages.yml'...");
			messages.save(messagesFile);
		} catch (IOException e) {
			Econ.warning("Failed to save 'messages.yml'!");
		}
	}
	
	public static void addDefaults() {
		messages.set("balance", "&cYour balance: ");
		messages.set("deposit", "&cYou deposited: ");
		messages.set("withdraw", "&cYou withdrew: ");
		messages.set("invalid-args", "&cInvalid Arguments!");
	}
	
	public static String getMessage(String message) {
		return Utils.chat(messages.getString(message));
	}
}
