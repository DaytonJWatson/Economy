package com.watsonllc.economy.Controllers;

import org.bukkit.entity.Player;

public class Controller {
	/** 
	 * Was going to use this for an account ID, rather use UUID.
	 * 
	private String createRandomString(int l) {
		Random ran = new Random();
		StringBuilder sb = new StringBuilder(l);
		String charsToUse = "0123456789abcdelmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		for(int i = 0; i < l; i++) {
			int index = (int) (ran.nextInt(charsToUse.length()));
			char randomChar = charsToUse.charAt(index);
			sb.append(randomChar);
		}
		
		return sb.toString();
	}*/

	protected String acoountPath(Player player) {
		return "Bank.accounts." + player.getUniqueId().toString();
	}
	
	/**
	protected String idPath(Player player) {
		return "Bank.accounts.ID";
	}
	
	For account ID if it gets reused.
	 
	protected String NEW_ID(int length) {
		return createRandomString(length);
	}*/
	
	// deposit math handler
	protected double DEPOSIT(double balance, double amount) {
		double newBalance;
		newBalance = balance+amount;
		return ROUND(newBalance);
	}
	
	// withdraw math handler
	protected double WITHDRAW(double balance, double amount) {
		double newBalance;
		newBalance = balance+amount;
		return ROUND(newBalance);
	}
	
	// round decimal math handler
	protected static double ROUND(double num) {
		return Math.round(num * 100.0) / 100.0;
	}
}
