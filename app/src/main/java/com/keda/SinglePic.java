package com.keda;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("unused")
public class SinglePic  extends Activity {
	
	// XML node keys
	static final String KEY_THUMB_URL = "src";
	static final String HEIGHT_MAX = "600";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pic);

        // getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        String bid = in.getStringExtra(KEY_THUMB_URL);
        int augst = Integer.parseInt(in.getStringExtra(HEIGHT_MAX));
        
        // Displaying all values on the screen
        ImageView lblBals = (ImageView) findViewById(R.id.bals_label);
        lblBals.getLayoutParams().height = augst-30;
        

        
        URL url = null;
		try {
			url = new URL(bid);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Bitmap bmp = null;
		try {
			bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        lblBals.setImageBitmap(bmp);

		

        //Drawable image =ImageOperations(this,src);
        //lblSrc.setImageDrawable(image);
        
    }
	
	//bildei
	public Object fetch(String address) throws MalformedURLException,
    IOException {
        URL url = new URL(address);
        Object content = url.getContent();
        return content;
    }  
    private Drawable ImageOperations(Context ctx, String url) {
        try {
            InputStream is = (InputStream) this.fetch(url);
            Drawable d = Drawable.createFromStream(is, "src");
            return d;
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
