package com.watsonllc.economy.Events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.watsonllc.economy.Config.Config;
import com.watsonllc.economy.Controllers.AccountControl;
import com.watsonllc.economy.Currency.Currency;

public class PlayerJoin implements Listener {
	
	boolean createOnJoin;
	boolean starterFunds;
	double starterFundsAmount;
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		AccountControl account = new AccountControl(player);
		Currency currency = new Currency();
		
		createOnJoin = Config.getBoolean("createAccountOnJoin");
		starterFunds = Config.getBoolean("starterFunds.enabled");
		starterFundsAmount = Config.getDouble("starterFunds.amount");
		
		// Create an account if a player doesn't have an account
		// AND if 'createAccountOnJoin' is enabled.
		if((createOnJoin) && (account.getAccount() == null)) account.openAccount();
		
		// Gives the player starter funds if enabled
		// AND player has never played before
		if(!player.hasPlayedBefore() && (starterFunds)) currency.giveCurrency(player, starterFundsAmount);
	}
}