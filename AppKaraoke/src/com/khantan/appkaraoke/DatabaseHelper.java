package com.khantan.appkaraoke;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static final String DATASE_NAME = "SongDB";
	public static final String TABLE_SONG = "song";
	
	public static final String SONG_ID = "_id";
	public static final String SONG_NAME = "song_name";
	public static final String SONG_NAME2 = "song_name2";
	public static final String SONG_LYRIC = "song_lyric";
	public static final String SONG_LYRIC2 = "song_lyric2";
	public static final String SONG_ARTIST = "song_artist";
	public static final String SONG_ARTIST2 = "song_artist2";
	
	Context context;
	String duongDanDatabase = "";
	
	
	// data/data/com.checongbinh.appkaraokelist/database/SongDB;
	public DatabaseHelper(Context context) {
		super(context, DATASE_NAME, null, 1);
		this.context = context;
		duongDanDatabase = context.getFilesDir().getParent() + "/databases/" + DATASE_NAME;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public SQLiteDatabase openDatabase(){
		return SQLiteDatabase.openDatabase(duongDanDatabase, null, SQLiteDatabase.OPEN_READWRITE);
		
	}
	
	public void copyDatabase(){
		try {
			InputStream is =  context.getAssets().open(DATASE_NAME);
			OutputStream os = new FileOutputStream(duongDanDatabase);
			byte[] buffer = new byte[1024];
			int lenght = 0;
			while((lenght = is.read(buffer)) > 0){
				os.write(buffer, 0, lenght);
			}
			
			os.flush();
			os.close();
			is.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createDatabase(){
		boolean kt = KiemTraDB();
		if(kt){
			Log.d("KetNoi", "Máy đã có database");
		}else{
			Log.d("KetNoi", "Máy chưa có database tiến hành copy dữ liệu");
			this.getWritableDatabase();
			copyDatabase();
		}
	}
	
	
	public boolean KiemTraDB(){
		SQLiteDatabase kiemTraDB = null;
		try{
			kiemTraDB = SQLiteDatabase.openDatabase(duongDanDatabase, null, SQLiteDatabase.OPEN_READONLY);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(kiemTraDB !=null){
			kiemTraDB.close();
		}
		return kiemTraDB !=null ? true : false;
	}

}
