package com.mao.bill;
import android.app.*;
import android.os.*;
import android.widget.*;
import java.util.*;
import android.view.*;
import android.content.*;

public class InsertActivity extends Activity
{

	private EditText bank;
	private EditText account;
	private EditText money;
	private EditText start;
	private EditText end;
	private EditText rate;
	private EditText year;
	private Button insert;
	private List<Bank> banks;
	private ObjectIO<List<Bank>> oio = new ObjectIO<List<Bank>>();
	private String fname = "/.bank";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert);
		
		bank = (EditText)findViewById(R.id.bank);
		account = (EditText)findViewById(R.id.account);
		money = (EditText)findViewById(R.id.money);
		start = (EditText)findViewById(R.id.start);
		end = (EditText)findViewById(R.id.end);
		rate = (EditText)findViewById(R.id.rate);
		year = (EditText)findViewById(R.id.year);
		insert = (Button)findViewById(R.id.insert);
	}
	
	//加入第一个银行数据
	public Bank getBank(String sbank,String saccount,String smoney,String sstart, String send,String srate,String syear ){

		List<Row> rows = new ArrayList<Row>();
		List<Account> accounts = new ArrayList<Account>();	
		Row row = new Row(smoney, sstart, send, srate, syear);
		rows.add(row);
		Account account = new Account(saccount, rows);
		accounts.add(account);
		Bank bank = new Bank(sbank, accounts);

		return bank;
	}
	
	//增加一个存单实现
	public void add(Bank bank){

		int x = find(this.banks,bank);
		if(x==-1){
			this.banks.add(bank);
			Collections.sort(this.banks); 
		}else{
			List<Account> accounts = this.banks.get(x).getAccounts();
			Account account = bank.getAccounts().get(0);
			int y = find(accounts, account);

			if(y==-1){
				accounts.add(account);
				Collections.sort(accounts);
			}else{
				List<Row> rows = this.banks.get(x).getAccounts().get(y).getRows();
				Row row = bank.getAccounts().get(0).getRows().get(0);
				rows.add(row);
				Collections.sort(rows);
			}	
		}		
	}
	
	//查找元素在列表中位置
	public <E> int find(List<E> list, E e)
	{
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals(e)){
				return i;
			}
		}
		return -1;
	}
	
	//插入数据
	public void insert(View view){
		
		String str = "";
		
		if(year.getText().length()<1){
			str = "年限不能为空";
		}
		
		if(rate.getText().length()<1){
			str = "利率不能为空";
		}
		
		if(end.getText().length()<1){
			str = "结束日期不能为空";
		}
		
		if(start.getText().length()<1){
			str = "开始日期不能为空";
		}
		
		if(money.getText().length()<1){
			str = "数目不能为空";
		}
		
		if(account.getText().length()<1){
			str = "账号不能为空";
		}
		
		if(bank.getText().length()<1){
			str = "银行不能为空";
		}
		
		if(!str.equals("")){
			Toast.makeText(this, str, Toast.LENGTH_LONG).show();
		}else{
			Bank addbank = getBank(bank.getText().toString(), account.getText().toString(), money.getText().toString(),
								   start.getText().toString(), end.getText().toString(), rate.getText().toString(),
								   year.getText().toString());

			//反序列化
			this.banks=oio.inObject(fname);
			//增加列表
			add(addbank);
			//序列化
			oio.outObject(banks,fname);	

			Intent intent = new Intent();
			intent.setClass(this, MainActivity.class); 
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
			finish();
		}
	}
	
	//取消事件
	public void cancel(View view){
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class); 
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		startActivity(intent);
		finish();
	}
/*
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK) 
        return true;//不执行父类点击事件
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }
*/
	//点两次退出
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
