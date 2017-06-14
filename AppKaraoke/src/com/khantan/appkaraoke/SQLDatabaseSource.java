package com.khantan.appkaraoke;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLDatabaseSource {
	SQLiteDatabase db;
	DatabaseHelper helper;

	public SQLDatabaseSource(Context context) {
		helper = new DatabaseHelper(context);
		helper.createDatabase();
		db = helper.openDatabase();
	}

	public List<Song> LayDanhSachBaiHatTheoMa(String tenbaihat) {
		List<Song> list = new ArrayList<Song>();

		String[] column = { DatabaseHelper.SONG_ID, DatabaseHelper.SONG_NAME,
				DatabaseHelper.SONG_NAME2, DatabaseHelper.SONG_LYRIC };

		// Cursor c = db.query(DatabaseHelper.TABLE_SONG, column, null, null,
		// null, null, null);
		
		// select _id , song_name , song_name2 , song_lyric From song where song_name2 like '% tenbaihat %'
		String truyvan = "Select " + column[0] + " , " + column[1] + " , "
				+ column[2] + " , " + column[3] + " From "
				+ DatabaseHelper.TABLE_SONG + " Where "
				+ DatabaseHelper.SONG_NAME2 + " LIKE '%" + tenbaihat.toLowerCase() + "%'";
		Cursor c = db.rawQuery(truyvan, null);
		c.moveToFirst();
		while (!c.isAfterLast()) {
			Song item = new Song();
			item.setId(c.getString(0));
			item.setSongname(c.getString(1));
			item.setSongname2(c.getString(2));
			item.setLyric(c.getString(3));

			list.add(item);
			c.moveToNext();
		}
		return list;
	}

	public List<Song> LayDanhSachBaiHat() {
		List<Song> list = new ArrayList<Song>();

		String[] column = { DatabaseHelper.SONG_ID, DatabaseHelper.SONG_NAME,
				DatabaseHelper.SONG_NAME2, DatabaseHelper.SONG_LYRIC };

		Cursor c = db.query(DatabaseHelper.TABLE_SONG, column, null, null,
				null, null, null);
		c.moveToFirst();
		while (!c.isAfterLast()) {
			Song item = new Song();
			item.setId(c.getString(0));
			item.setSongname(c.getString(1));
			item.setSongname2(c.getString(2));
			item.setLyric(c.getString(3));

			list.add(item);
			c.moveToNext();
		}
		return list;
	}
}
