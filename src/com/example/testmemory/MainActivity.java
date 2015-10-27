package com.example.testmemory;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Bitmap bit1;
	private Bitmap bit2;
	private Bitmap bit3;
	private Bitmap bit4;
	private Bitmap bit5;
	private Bitmap bit6;
	private Bitmap bit7;
	private Bitmap bit8;
	private Bitmap bit9;
	private Bitmap bit10;
	
	private boolean hasClicked = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bit1 = BitmapFactory.decodeResource(getResources(), R.drawable.cc);
		bit2 = BitmapFactory.decodeResource(getResources(), R.drawable.cc);
		bit3 = BitmapFactory.decodeResource(getResources(), R.drawable.cc);
		bit4 = BitmapFactory.decodeResource(getResources(), R.drawable.cc);
		bit5 = BitmapFactory.decodeResource(getResources(), R.drawable.cc);
		bit6 = BitmapFactory.decodeResource(getResources(), R.drawable.cc);
		bit7 = BitmapFactory.decodeResource(getResources(), R.drawable.cc);
		bit8 = BitmapFactory.decodeResource(getResources(), R.drawable.cc);
		bit9 = BitmapFactory.decodeResource(getResources(), R.drawable.cc);
		bit10 = BitmapFactory.decodeResource(getResources(), R.drawable.cc);
		
		final LinearLayout main_ll = (LinearLayout)findViewById(R.id.main_ll);
		final LinearLayout main_ll2 = (LinearLayout)findViewById(R.id.main_ll2);
		final TextView text_test1 = (TextView)findViewById(R.id.text_test1);
		TextView text_test2 = (TextView)findViewById(R.id.text_test2);
		text_test2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!hasClicked){
					hasClicked = true;
					LayoutParams param = new LayoutParams(LayoutParams.MATCH_PARENT, 500);
					text_test1.setLayoutParams(param);
					main_ll.removeAllViews();
					final ScrollView view = new ScrollView(MainActivity.this);
					view.addView(main_ll2);
					main_ll.addView(view);
//				new Handler().post(new Runnable() {  
//				    @Override  
//				    public void run() {  
					view.fullScroll(ScrollView.FOCUS_DOWN);  
//				    }  
//				});
				}else{
					Toast.makeText(MainActivity.this, "已添加过", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		System.exit(0);
//		finish();
	}
	
	

}
