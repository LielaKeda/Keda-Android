package com.example.androidhive;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    public int augstums;
    public int platums;
    
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d, int Dwidth, int Dheight) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
        augstums = Dheight;
        platums = Dwidth;
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
            vi = inflater.inflate(R.layout.list_row, null);

        TextView balsots = (TextView)vi.findViewById(R.id.pirma); // vienaBilde
        TextView nebalsots = (TextView)vi.findViewById(R.id.otra); // otraBilde
        TextView albums = (TextView)vi.findViewById(R.id.albums); // Albums
        TextView reitings = (TextView)vi.findViewById(R.id.reitings); // Albums
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image

        thumb_image.getLayoutParams().height = (int) (augstums/2.2);
        
        
        
        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);
        
        // Setting all values in listview
        balsots.setText(song.get(CustomizedListView.KEY_ID));
        nebalsots.setText(song.get(CustomizedListView.KEY_TITLE));
        albums.setText(song.get(CustomizedListView.KEY_ALBUM));
        reitings.setText("Reitings: "+song.get(CustomizedListView.KEY_ARTIST)+"("+song.get(CustomizedListView.KEY_DURATION)+")");
        imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
        return vi;
    }
}