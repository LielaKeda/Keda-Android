package com.example.androidhive;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//import com.androidhive.xmlparsing.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Statistika extends Activity {
	public static Activity activity;
	
	// All static variables
	
// XML node keys
	static final String KEY_SONG = "entry"; // parent node
	static final String KEY_BILDES = "bildes";
	static final String KEY_ALBUMI = "albumi";
	static final String KEY_BALSIS = "balsis";
	static final String KEY_SKAITS = "skatits";
	
	ListView list;
	StatAdapter adapter;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		activity = this;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		

		ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
		//nolasa, par kuru bildi balsots
		
		String URL = "http://lielakeda.lv/battle/xml.php?lapa=stat";
		
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
			map.put(KEY_BILDES, parser.getValue(e, KEY_BILDES));
			map.put(KEY_ALBUMI, parser.getValue(e, KEY_ALBUMI));
			map.put(KEY_BALSIS, parser.getValue(e, KEY_BALSIS));
			map.put(KEY_SKAITS, parser.getValue(e, KEY_SKAITS));

			// adding HashList to ArrayList
			songsList.add(map);
		}
		

		list=(ListView)findViewById(R.id.list);
		
		// Getting adapter by passing xml data ArrayList
        adapter=new StatAdapter(this, songsList);        
        list.setAdapter(adapter);
        

        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//dabūjam nobalsotās bildes id un otras bildes id
			}
		});		
	}	
}