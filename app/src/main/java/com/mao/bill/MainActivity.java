package com.mao.bill;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import java.util.zip.*;
import java.util.*;
import android.graphics.*;
import java.io.*;
import android.util.*;
import android.view.View.*;
import android.content.*;

public class MainActivity extends Activity 
{
	
	private ScrollView scrollView;
	private LinearLayout view;
	private List<Bank> banks = new ArrayList<Bank>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		
		//滚动条实例化
		scrollView = (ScrollView) getLayoutInflater().inflate(R.layout.scrollview, null);
		
		//滚动条里面View实例化
		view = (LinearLayout) getLayoutInflater().inflate(R.layout.main, null);
		
		//反序列化
		banks=ObjectIO.inObject();
  
        //增加BanksView
        addBanksView(banks);
		
		//序列化
		ObjectIO.outObject(banks);	
		
		//把view添加到滚动条
		scrollView.addView(view);
		
		//显示视图
		setContentView(scrollView);
    }
	
	//增加一个银行视图
	public void addBanksView(List<Bank> banks){
		for(int i=0;i<banks.size();i++){
			addBankViewS(banks.get(i),i);
		}
	}
	
	//增加所有银行列表视图
	public void addBankViewS(Bank bank, int x){
		addBankView(bank);
		addAccountsView(bank.getAccounts(), x);
	}
	
	//增加账号列表视图
	public void addAccountsView(List<Account> accounts,int x){
		for(int i=0;i<accounts.size();i++){
			addAccountView(accounts.get(i));
			addHeadView();
			addRowsView(accounts.get(i).getRows(),x,i);
		}
	}
	
	//增加存单列表视图
	public void addRowsView(List<Row> rows,int x, int y){
		for(int i=0;i<rows.size();i++){
			addRowView(rows.get(i),x,y,i);
		}
	}
	
	public void addBankView(Bank bank){
		//银行实例化
		LinearLayout bankView = (LinearLayout)getLayoutInflater().inflate(R.layout.bank, null);
		((TextView)bankView.getChildAt(0)).setText(bank.getName());
		view.addView(bankView);
	}
	
	public void addAccountView(Account account){
		//账号实例化
		LinearLayout accountView = (LinearLayout)getLayoutInflater().inflate(R.layout.account, null);
		((TextView)accountView.getChildAt(0)).setText(account.getNumber());
		view.addView(accountView);
	}
	
	public void addHeadView(){
		//标题实例化
		LinearLayout headView = (LinearLayout)getLayoutInflater().inflate(R.layout.head, null);
		view.addView(headView);
	}
	
	public void addRowView(Row row,final int x,final int y,final int z){
		//存单实例化
		LinearLayout rowView = (LinearLayout)getLayoutInflater().inflate(R.layout.row, null);
		
		((TextView)rowView.getChildAt(0)).setText(row.getMoneys());
		((TextView)rowView.getChildAt(1)).setText(row.getStart());
		((TextView)rowView.getChildAt(2)).setText(row.getEnd());
		((TextView)rowView.getChildAt(3)).setText(row.getRate());
		((TextView)rowView.getChildAt(4)).setText(row.getYear());
		
		((ImageView)rowView.getChildAt(5)).setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				
				List<Bank> banks = MainActivity.this.banks;
				List<Account> accounts = MainActivity.this.banks.get(x).getAccounts();
				List<Row> rows = MainActivity.this.banks.get(x).getAccounts().get(y).getRows();
				
				rows.remove(z);
				
				if(rows.size()==0)
					accounts.remove(y);
				if(accounts.size()==0)
					MainActivity.this.banks.remove(x);
	
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MainActivity.class); 
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
				finish();
				ObjectIO.outObject(banks);
			}
		});
	
		view.addView(rowView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		// TODO: Implement this method
		switch(item.getItemId())
		{
			
			case R.id.item:
				
	     		Intent intent = new Intent();
				intent.setClass(MainActivity.this, InsertActivity.class); 
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
				finish();
				return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
/*
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK) 
			return true;//不执行父类点击事件
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }
*/	
	private boolean isExit;

	/**
	 * 双击返回键退出
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isExit) {
				this.finish();

			} else {
				Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
				isExit = true;
				new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							isExit= false;
						}
					}, 2000);
			}
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
	
}
