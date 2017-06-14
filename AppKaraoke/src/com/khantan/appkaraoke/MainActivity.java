package com.khantan.appkaraoke;

import java.util.ArrayList;
import java.util.List;

import com.khantan.appkaraoke.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class MainActivity extends Activity implements OnQueryTextListener{

	SQLDatabaseSource db;
	List<Song> list;
	ListView lvHienThi;
	CustomListView adapter;
	public static String chuoiTimKiemKiem = "";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lvHienThi = (ListView) findViewById(R.id.lvHienThi);
        
        list = new ArrayList<Song>();
        db = new SQLDatabaseSource(this);
        list = db.LayDanhSachBaiHat();
        setAdapterListView(list);
        
    }

    private void setAdapterListView(List<Song> list){
    	 adapter = new CustomListView(this, R.layout.custom_layout_listview, list);
         lvHienThi.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


	@Override
	public boolean onQueryTextChange(String chuoi) {
		list = db.LayDanhSachBaiHatTheoMa(chuoi);
		setAdapterListView(list);
		adapter.notifyDataSetChanged();
		chuoiTimKiemKiem = chuoi;
		return true;
	}


	@Override
	public boolean onQueryTextSubmit(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}


}
