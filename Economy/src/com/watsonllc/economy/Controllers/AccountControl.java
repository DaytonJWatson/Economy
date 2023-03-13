package com.watsonllc.economy.Controllers;

import org.bukkit.entity.Player;

import com.watsonllc.economy.Config.Bank;
import com.watsonllc.economy.Config.Config;

public class AccountControl extends Controller {
	// variables
	Player player;
	String accountID;
	double balance;
	char currency;
	boolean taxExempt;
	boolean circulationCap;
	
	// dataPath shortcuts
	private String accountPath;
	private String playerPath;
	private String balancePath;
	private String currencyPath;
	private String taxExemptPath;
	
	public AccountControl(Player player) {
		if(player != null) {
			// get the players values
			this.player = player;
			this.balance = balance();
			this.currency = currency();
			this.taxExempt = taxExempt();
			this.circulationCap = circulationCap();
		} else {
			// get the default values
			this.player = player;
			this.balance = defaultBalance();
			this.currency = defaultCurrency();
			this.taxExempt = defaultTaxExempt();
			this.circulationCap = circulationCap();
		}
		
		if(this.player != null) {
			// creates the dataPath shortcuts
			this.accountPath = "Bank.accounts." + player.getUniqueId().toString();
			this.playerPath = accountPath + ".user" + player.getName();
			this.balancePath = accountPath + ".balance";
			this.currencyPath = accountPath + ".currency";
			this.taxExemptPath = accountPath + ".taxExempt";
		}
	}
	
	public String getAccount() {
		// gets the unique player ID
		return Bank.getString(accountPath);
	}
	
	public void openAccount() {
		// open a new account		
		Bank.set(balancePath, defaultBalance());
		Bank.set(currencyPath, defaultCurrency());
		Bank.set(taxExemptPath, defaultTaxExempt());
	}
	
	public void closeAccount() {
		// this wipes the account from bank.yml entirely
		Bank.set(playerPath, null);
	}
	
	public void transfer(Player recipient, double amount) {
		// transfer money from current account to specified account
		AccountControl toAccount = new AccountControl(recipient);
		toAccount.deposit(amount);
		withdraw(amount);
	}
	
	public void deposit(double amount) {
		// deposit money to bank
		if(circulationCap) payBank(amount);
				
		// deposit no where
		if(!circulationCap) Bank.set(balancePath, DEPOSIT(balance, amount));
	}
	
	public void withdraw(double amount) {
		// withdraw money from bank
		if(circulationCap) bankPay(amount);
		
		// create new currency
		if(!circulationCap) Bank.set(balancePath, WITHDRAW(balance, amount));
	}
	
	// pay the player from the bank
	public void bankPay(double amount) {
		BankControl bank = new BankControl();
		bank.withdraw(amount);
		deposit(amount);
	}
	
	// pay the bank from the player
	public void payBank(double amount) {
		BankControl bank = new BankControl();
		withdraw(amount);
		bank.deposit(amount);
	}
	
	// returns the players values from bank.yml
	public double defaultBalance() {
		return Config.getDouble("defaultAccountBalance");
	}
	
	public char defaultCurrency() {
		return Config.getChar("defaultCurrency");
	}
	
	public boolean defaultTaxExempt() {
		return Config.getBoolean("defaultTaxExempt");
	}
	
	public boolean circulationCap() {
		return Config.getBoolean("currencyCirculationCap");
	}
	
	public double balance() {
		return Bank.getDouble(balancePath);
	}
	
	public char currency() {
		return Bank.getChar(currencyPath);
	}
	
	public boolean taxExempt() {
		return Bank.getBoolean(taxExemptPath);
	}
	
}
