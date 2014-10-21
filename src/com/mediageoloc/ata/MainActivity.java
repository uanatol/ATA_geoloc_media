package com.mediageoloc.ata;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {

	private Button _buttonPhoto;
	private Button _buttonVideo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addButtonPhotoListener();
		addButtonVideoListener();
		
	}
	
	
	/*
	 * Ajout 
	 */
	private void addButtonPhotoListener(){
		_buttonPhoto = (Button) findViewById(R.id.buttonPhoto);
		_buttonPhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            }
        });
	}
	
	private void addButtonVideoListener(){
		_buttonVideo = (Button) findViewById(R.id.buttonVideo);
		_buttonVideo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            }
        });
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
