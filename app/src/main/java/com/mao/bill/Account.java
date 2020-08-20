package com.mao.bill;
import java.util.*;
import java.io.*;

public class Account implements Serializable,Comparable
{
	private String number;
	private List<Row> rows;

	public Account(String number, List<Row> rows)
	{
		this.number = number;
		this.rows = rows;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getNumber()
	{
		return number;
	}

	public void setRows(List<Row> rows)
	{
		this.rows = rows;
	}

	public List<Row> getRows()
	{
		return rows;
	}

	@Override
	public boolean equals(Object obj)
	{
		// TODO: Implement this method
		return this.number.equals(((Account)obj).number);
	}

	@Override
	public int compareTo(Object p1)
	{
		// TODO: Implement this method
		return this.number.compareTo(((Account)p1).number);
	}

}
