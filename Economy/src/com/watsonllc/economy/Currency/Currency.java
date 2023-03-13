package com.watsonllc.economy.Currency;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.watsonllc.economy.Config.Config;
import com.watsonllc.economy.Controllers.AccountControl;
import com.watsonllc.economy.Utilities.Utils;

public class Currency {
	Material material;
	String name;
	char currency;
	double worth;

	public Currency() {
		this.material = Material.valueOf(Config.getString("currency.material"));
		this.name = Config.getString("currency.name");
		this.worth = Config.getDouble("currency.worth");
		this.currency = Config.getChar("currency.char");
	}
	
	public void giveCurrency(Player player, double amount) {
		player.getInventory().addItem(createCurrency(amount));
	}

	public ItemStack createCurrency(double amount) {
		// How much dollar is worth & its char
		double netWorth = amount*worth;
		char CHAR = currency;
		// currency name
		String currencyName = name;
		// lore text, duh.
		String loreText = Utils.chat("&f" + CHAR + netWorth);
		// localized name
		String localizedName = "currency=" + netWorth;
		
		ItemStack physical = new ItemStack(material);
		ItemMeta meta = physical.getItemMeta();
		List<String> lore = new ArrayList<String>();
		
		lore.add(loreText);
		meta.setDisplayName(currencyName);
		meta.setLore(lore);
		meta.setLocalizedName(localizedName);
		physical.setItemMeta(meta);
		return physical;
	}
	
	public static void cashOutCurrency(Player player) {
		// Get the item in the players hand.
		ItemStack hand = player.getInventory().getItemInMainHand();
		// Get the players account
		AccountControl account = new AccountControl(player);
		
		// If they're not holding currency, cancel.
		if(!isHoldingCurrency(hand)) return;
		
		// Get the amount the player is
		// depositing into their account
		double deposited;
		deposited = getCurrencyWorth(hand);
		
		hand.setAmount(0); // clear the item
		account.deposit(deposited);
	}

	public static double getCurrencyWorth(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		double worth = Double.parseDouble(meta.getLocalizedName().replace("physicalCurrency=", ""));
		return worth;
	}
	
	public static boolean isHoldingCurrency(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		if (meta == null) return false;
		if (meta.getLocalizedName().contains("physicalCurrency=")) return true;
		return false;
	}
}
