package com.mao.bill;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;

public class SetActivity extends Activity
{

	private ObjectIO<Usr> oio = new ObjectIO<Usr>();
	private String fname = "/.usr";
	private Usr usr = oio.inObject(fname);
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set);
		
		LinearLayout setOldLine = (LinearLayout)findViewById(R.id.setOldLine);
		Button cancel = (Button)findViewById(R.id.cancel);
		if(null==usr){
			setOldLine.setVisibility(8);
			cancel.setVisibility(8);
		}
	}

	//取消事件
	public void cancel(View view){
		finish();
	}
	
	//修改密码
	public void set(View view){
		
		EditText old = (EditText)findViewById(R.id.old);
		EditText new1 = (EditText)findViewById(R.id.new1);
		EditText new2 = (EditText)findViewById(R.id.new2);

		if(null!=usr){
			
			if(old.getText().toString().length()==0){
				Toast.makeText(this,"请输入原密码",Toast.LENGTH_LONG).show();
			}
			else if(!usr.getPassword().equals(old.getText().toString())){
				Toast.makeText(this,"原密码错误",Toast.LENGTH_LONG).show();
			}else if(new1.getText().length()==0){
				Toast.makeText(this,"请输入新密码",Toast.LENGTH_LONG).show();
			}else if(new2.getText().length()==0){
				Toast.makeText(this,"请再次输入新密码",Toast.LENGTH_LONG).show();
			}else if(!new1.getText().toString().equals(new2.getText().toString())){
				Toast.makeText(this,"两次输入不一致",Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(this,"修改成功",Toast.LENGTH_LONG).show();

				usr.setPassword(new1.getText().toString());
				oio.outObject(usr,fname);

				Intent intent = new Intent();
				intent.setClass(this, MainActivity.class); 
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
				
			}
		}else{
			usr = new Usr("", "123456");
			
			if(new1.getText().length()==0){
				Toast.makeText(this,"请输入新密码",Toast.LENGTH_LONG).show();
			}else if(new2.getText().length()==0){
				Toast.makeText(this,"请再次输入新密码",Toast.LENGTH_LONG).show();
			}else if(!new1.getText().toString().equals(new2.getText().toString())){
				Toast.makeText(this,"两次输入不一致",Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(this,"修改成功",Toast.LENGTH_LONG).show();

				usr.setPassword(new1.getText().toString());
				oio.outObject(usr,fname);

				Intent intent = new Intent();
				intent.setClass(this, MainActivity.class); 
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
				finish();
			}
		}
	}
	
}
