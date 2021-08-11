package com.mao.bill;
import java.util.*;
import java.io.*;
import android.os.*;

public class ObjectIO <T>
{
	

	private static File dir = Environment.getExternalStoragePublicDirectory("bill");

	//序列化
	public void outObject(T t,String fname){

		try
		{
			mkDir();
			FileOutputStream fos = new FileOutputStream(dir+fname);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(t);
			oos.flush();
			oos.close();
		}
		catch (IOException e)
		{
			//MyLog.d("Out IOException");
		}

	}

	//反序列化
	public T inObject(String fname){

		T t = null;
		
		try
		{
			FileInputStream fis = new FileInputStream(dir+fname);
			ObjectInputStream ois = new ObjectInputStream(fis);
			t = (T) ois.readObject();
		}
		catch (ClassNotFoundException e)
		{
			//MyLog.d("Class Not Found");
		}
		catch (IOException e)
		{
			//MyLog.d(" In IOException");
		}

		return t;
	}
	
	//创建文件
	public static void mkDir()
	{
		try 
		{
			if(!dir.exists()){   
				dir.mkdir();  
			}
		}catch(Exception e)
		{}
	}
	
}
