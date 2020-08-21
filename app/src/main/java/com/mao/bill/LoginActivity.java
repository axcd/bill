package com.mao.bill;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import java.util.*;

public class LoginActivity extends Activity
{

	private ObjectIO<Usr> oio = new ObjectIO<Usr>();
	private String fname = "/.usr";
	public Usr usr = null;

	@Override
    protected void onCreate(Bundle savedInstanceState)
    { 
		super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
		GridLayout gl = (GridLayout)findViewById(R.id.loginGridLayout1);
		final EditText et = (EditText)findViewById(R.id.loginEditText1);
		final StringBuffer secret = new StringBuffer("");

		//oio.outObject(new Usr("a","123456"),fname);
		//反序列化
		usr = oio.inObject(fname);

		if (null == usr)
		{
			Intent intent = new Intent();
			intent.setClass(this, SetActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
			finish();
		}
		else
		{

			for (int i=0;i < 12;i++)
			{
				final TextView tv = (TextView)gl.getChildAt(i);
				tv.setOnClickListener(new View.OnClickListener(){
						public void onClick(View v)
						{
							if (Character.isDigit(tv.getText().charAt(0)) && et.getText().length() < 8)
							{
								secret.append(tv.getText());
								et.setText(et.getText().append("●"));
								if (secret.toString().equals(usr.getPassword()))
								{
									Intent intent = new Intent();
									intent.setClass(LoginActivity.this, MainActivity.class); 
									intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
									startActivity(intent);
									finish();
								}
							}
							else if (tv.getText().equals("C") && secret.length() > 0)
							{
								et.setText(et.getText().subSequence(0, et.getText().length() - 1));
								secret.deleteCharAt(secret.length() - 1);
							}
						}
					});
			}
		}
    }

	//点两次退出
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
		{
            if ((System.currentTimeMillis() - exitTime) > 2000)
			{
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }
			else
			{
                finish();
				System.exit(0);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
