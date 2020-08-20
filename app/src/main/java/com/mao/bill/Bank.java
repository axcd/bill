package com.mao.bill;
import java.util.*;
import java.io.*;

public class Bank implements Serializable,Comparable
{
	private String name;
	private List<Account> accounts;

	public Bank(String name, List<Account> accounts)
	{
		this.name = name;
		this.accounts = accounts;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setAccounts(List<Account> accounts)
	{
		this.accounts = accounts;
	}

	public List<Account> getAccounts()
	{
		return accounts;
	}

	@Override
	public boolean equals(Object obj)
	{
		// TODO: Implement this method
		return this.name.equals(((Bank)obj).name);
	}

	@Override
	public int compareTo(Object p1)
	{
		// TODO: Implement this method
		return this.name.compareTo(((Bank)p1).name);
	}

}
