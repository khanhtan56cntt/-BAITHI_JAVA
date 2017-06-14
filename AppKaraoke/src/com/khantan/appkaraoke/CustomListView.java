package com.khantan.appkaraoke;

import java.util.List;

import com.khantan.appkaraoke.R;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListView extends ArrayAdapter<Song> {

	Context context;
	int resource;
	List<Song> objects;
	
	public CustomListView(Context context, int resource, List<Song> objects) {
		super(context, resource, objects);
		
		this.context = context;
		this.resource = resource;
		this.objects = objects;
	}
	
	private class ViewHolder{
		TextView tvId, tvTenBaiHat,tvLoiBaiHat;
	}
	
	private int LayViTriBatDau(String chuoi,String chuoitimkiem){
		int vitri = 0;
		vitri = chuoi.indexOf(chuoitimkiem);
		
		return vitri;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
		    holder = new ViewHolder();
			convertView = View.inflate(context, resource, null);
			holder.tvId = (TextView) convertView.findViewById(R.id.tvID);
			holder.tvTenBaiHat = (TextView) convertView.findViewById(R.id.tvTenBaiHat);
			holder.tvLoiBaiHat = (TextView) convertView.findViewById(R.id.tvLoi);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		Song item = objects.get(position);
		
		holder.tvId.setText(item.getId().toString());
		holder.tvLoiBaiHat.setText(item.getLyric().toString());
		
		if(MainActivity.chuoiTimKiemKiem != null){
			SpannableString textSpan = new SpannableString(item.getSongname2());
			
			int vitribatdau = LayViTriBatDau(item.getSongname2(), MainActivity.chuoiTimKiemKiem);
			int vitriketthuc = MainActivity.chuoiTimKiemKiem.length();
			textSpan.setSpan(new BackgroundColorSpan(Color.RED), vitribatdau, vitribatdau + vitriketthuc, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
			holder.tvTenBaiHat.setText(textSpan);
		}else{
			holder.tvTenBaiHat.setText(item.getSongname());
		}
		
		
		
//		View view = View.inflate(context, resource, null);
//		TextView tvId = (TextView) view.findViewById(R.id.tvID);
//		TextView tvTenBaiHat = (TextView) view.findViewById(R.id.tvTenBaiHat);
//		TextView tvLoiBaiHat = (TextView) view.findViewById(R.id.tvLoi);
//		
//		Song item = objects.get(position);
//		tvId.setText(item.getId().toString());
//		tvTenBaiHat.setText(item.getSongname().toString());
//		tvLoiBaiHat.setText(item.getLyric().toString());
		
		return convertView;
	}

}
