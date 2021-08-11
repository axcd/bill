package com.mao.bill;
import java.io.*;

public class Row implements Serializable,Comparable
{
	private String moneys;
	private String start;
	private String end;
	private String rate;
	private String year;

	public Row(String moneys, String start, String end, String rate, String year)
	{
		this.moneys = moneys;
		this.start = start;
		this.end = end;
		this.rate = rate;
		this.year = year;
	}

	

	public void setMoneys(String moneys)
	{
		this.moneys = moneys;
	}

	public String getMoneys()
	{
		return moneys;
	}

	public void setStart(String start)
	{
		this.start = start;
	}

	public String getStart()
	{
		return start;
	}

	public void setEnd(String end)
	{
		this.end = end;
	}

	public String getEnd()
	{
		return end;
	}

	public void setRate(String rate)
	{
		this.rate = rate;
	}

	public String getRate()
	{
		return rate;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public String getYear()
	{
		return year;
	}

	@Override
	public int compareTo(Object p1)
	{
		// TODO: Implement this method
		Row row = (Row)p1;
		if(!this.end.equals(row.end)){
			return this.end.compareTo(row.end);
		}
		if(!this.start.equals(row.start)){
			return this.start.compareTo(row.start);
		}
		if(!this.moneys.equals(row.moneys)){
			return this.moneys.compareTo(row.moneys);
		}
		if(!this.rate.equals(row.rate)){
			return this.rate.compareTo(row.rate);
		}
		if(!this.year.equals(row.year)){
			return this.year.compareTo(row.year);
		}
		return 0;
	}

}
