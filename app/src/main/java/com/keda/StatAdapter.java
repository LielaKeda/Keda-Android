package com.keda;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StatAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public StatAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.activity_statistika, null);

        TextView balsots = (TextView)vi.findViewById(R.id.TextView00); // vienaBilde
        TextView nebalsots = (TextView)vi.findViewById(R.id.TextView01); // otraBilde
        TextView albums = (TextView)vi.findViewById(R.id.TextView02); // Albums
        TextView reitings = (TextView)vi.findViewById(R.id.TextView03); // Albums
        
        
        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);
        
        // Setting all values in listview
        balsots.setText("Kopā bilžu: "+song.get(Statistika.KEY_BILDES));
        nebalsots.setText("Kopā balsu: "+song.get(Statistika.KEY_BALSIS));
        albums.setText("Kopā albumu: "+song.get(Statistika.KEY_ALBUMI));
        reitings.setText("Kopā skatījumu: "+song.get(Statistika.KEY_SKAITS));
        return vi;
    }
}