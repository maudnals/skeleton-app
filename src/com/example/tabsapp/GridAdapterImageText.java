package com.example.tabsapp;

import java.util.Random;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapterImageText extends BaseAdapter {

	private Context mContext;
	private final String[] web;
	private final int[] imageId;

	public GridAdapterImageText(Context c, String[] web, int[] imageId) {
		mContext = c;
		this.imageId = imageId;
		this.web = web;
	}

	@Override
	public int getCount() {
		return web.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View gridItemView;
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(position == 1){
			// needed to inflate (nt auto like ImageView)
			if (convertView == null) {
				gridItemView = new View(mContext);				
				gridItemView = inflater.inflate(R.layout.add_grid_item, null);
			} else {
				gridItemView = (View) convertView;
			}
			return gridItemView;				
		}else{
			
			// needed to inflate (nt auto like ImageView)
			if (convertView == null) {
				gridItemView = new View(mContext);
				
				gridItemView = inflater.inflate(R.layout.grid_item, null);
				TextView textView = (TextView) gridItemView.findViewById(R.id.grid_text);
				ImageView imageView = (ImageView)gridItemView.findViewById(R.id.grid_image);	
				textView.setText(web[position]);
				imageView.setImageResource(imageId[position]);	
				
				Random rnd = new Random(); 
				int color = Color.argb(200, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));   
				gridItemView.setBackgroundColor(color);
				gridItemView.invalidate();			
//				gridItemView.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
			} else {
				gridItemView = (View) convertView;
			}
			return gridItemView;			
		}		
	}
}
