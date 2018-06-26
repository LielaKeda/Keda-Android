package com.example.androidhive;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		activity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		//ierakstam par kuru bildi nobalsots atmiņā
		SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(getString(R.string.vienaBilde), "tukšums1");
		editor.putString(getString(R.string.otraBilde), "tukšums2");
		editor.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void openBattle(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, CustomizedListView.class);
        startActivity(intent);
    }
    public void openTopPics(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, Top_pics.class);
        startActivity(intent);
    }
    public void openTopAlbums(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, TopAlbums.class);
        startActivity(intent);
    }
    public void openAboutApp(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, ParLietotni.class);
        startActivity(intent);
    }
    public void openStats(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, Statistika.class);
        startActivity(intent);
    }
}
