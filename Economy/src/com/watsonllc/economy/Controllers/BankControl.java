package com.watsonllc.economy.Controllers;


import org.bukkit.entity.Player;

import com.watsonllc.economy.Config.Bank;
import com.watsonllc.economy.Config.Config;

public class BankControl extends Controller {
	boolean constructed;
	char currency;
	double balance;
	double tax;
	
	// dataPath shortcuts
	private String balancePath;
	private String currencyPath;
	private String taxPath;
	
	public BankControl() {
		// Checks if the bank has values
		if(!constructed()) {
			Config.set("CONSTRUCTED", true);
			this.balance = defaultBalance();
			this.currency = defaultCurrency();
			this.tax = defaultTax();
		} else {
			this.constructed = constructed();
			this.balance = balance();
			this.currency = currency();
			this.tax = tax();
		}
		
		this.balancePath = "Bank.balance";
		this.currencyPath = "Bank.currency";
		this.taxPath = "Bank.tax";
	}
	
	public void transfer(Player recipient, double amount) {
		AccountControl toAccount = new AccountControl(recipient);
		toAccount.deposit(amount);
		withdraw(amount);
	}
	
	public void deposit(double amount) {
		Bank.set(balancePath, DEPOSIT(balance, amount));
	}
	
	public void withdraw(double amount) {
		Bank.set(balancePath, WITHDRAW(balance, amount));
	}
	
	// returns the banks values from bank.yml
	public double defaultBalance() {
		return Config.getDouble("defaultBankBalance");
	}
	
	public char defaultCurrency() {
		return Config.getChar("defaultCurrency");
	}
	
	public double defaultTax() {
		return Config.getDouble("defaultTax");
	}
	
	public double balance() {
		return Bank.getDouble(balancePath);
	}
	
	public char currency() {
		return Bank.getChar(currencyPath);
	}
	
	public double tax() {
		return Bank.getDouble(taxPath);
	}
	
	public boolean constructed() {
		return Config.getBoolean("CONSTRUCTED");
	}
}
