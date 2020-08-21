package com.mao.bill;
import java.io.*;

public class Usr implements Serializable
{
	private String name;
	private String password;

	public Usr(String name, String password)
	{
		this.name = name;
		this.password = password;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword()
	{
		return password;
	}
}
