package com.mao.bill;
import java.util.*;
import java.io.*;
import android.os.*;

public class ObjectIO
{
	

	private final static File file = new File(Environment.getExternalStorageDirectory(), "Android/data/com.mao.bill/.out");

	//序列化
	public static void outObject(List<Bank> banks){

		try
		{
			mkDir();
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(banks);
			oos.flush();
			oos.close();
		}
		catch (IOException e)
		{
			MyLog.d("Out IOException");
		}

	}

	//反序列化
	public static List<Bank> inObject(){

		List<Bank> banks = new ArrayList<Bank>();

		try
		{
			FileInputStream fis = new FileInputStream(file);MyLog.d(banks.toString());
			ObjectInputStream ois = new ObjectInputStream(fis);
			banks = (List<Bank>) ois.readObject();
		}
		catch (ClassNotFoundException e)
		{
			MyLog.d("Class Not Found");
		}
		catch (IOException e)
		{
			MyLog.d(" In IOException");
		}

		return banks;
	}
	
	//创建文件
	public static void mkDir()
	{
		try 
		{
			File dir = new File(Environment.getExternalStorageDirectory(), "Android/data/com.mao.bill/");
			if(!dir.exists()){   
				dir.mkdir();  
			}
		}catch(Exception e)
		{}
	}
	
}
