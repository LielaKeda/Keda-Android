package com.keda;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TopAlbums extends Activity {
	public static Activity activity;
	
	// All static variables
	
// XML node keys
	static final String KEY_SONG = "entry"; // parent node
	static final String KEY_ID = "URL";
	static final String KEY_ARTIST = "reitings";
	static final String KEY_DURATION = "balsis";
	static final String KEY_THUMB_URL = "src";
	static final String KEY_ALBUM = "albums";
	static final String HEIGHT_MAX = "600";
	
	ListView list;
	CopyOfLazyAdapter adapter;

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		activity = this;
		//dabū ekrāna izmērus
		Display display = getWindowManager().getDefaultDisplay(); 
		int Dwidth = display.getWidth();  // deprecated
		int Dheight = display.getHeight();  // deprecated
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		

		ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
		//nolasa, par kuru bildi balsots
		
		String URL = "http://lielakeda.lv/battle/xml.php?lapa=albumi";
		
		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL); // getting XML from URL
		Document doc = parser.getDomElement(xml); // getting DOM element
		
		NodeList nl = doc.getElementsByTagName(KEY_SONG);
		// looping through all song nodes <song>
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_ID, parser.getValue(e, KEY_ID));
			map.put(KEY_ARTIST, parser.getValue(e, KEY_ARTIST));
			map.put(KEY_DURATION, parser.getValue(e, KEY_DURATION));
			map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));
			map.put(KEY_ALBUM, parser.getValue(e, KEY_ALBUM));
			map.put(HEIGHT_MAX, parser.getValue(e, HEIGHT_MAX));

			// adding HashList to ArrayList
			songsList.add(map);
		}
		

		list=(ListView)findViewById(R.id.list);
		
		// Getting adapter by passing xml data ArrayList
        adapter=new CopyOfLazyAdapter(this, songsList, Dwidth, Dheight);        
        list.setAdapter(adapter);
        

        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//dabūjam nobalsotās bildes id un otras bildes id
				String bid = ((TextView) view.findViewById(R.id.pirma)).getText().toString();

				Display display = getWindowManager().getDefaultDisplay(); 
				int Dheight = display.getHeight();  // deprecated
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), SinglePic.class);
				in.putExtra(KEY_THUMB_URL, bid);
				in.putExtra(HEIGHT_MAX, Integer.toString(Dheight));
				
				startActivity(in);
			}
		});		
	}	
}