package com.example.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

	private MyOpenHelper oh;
	private SQLiteDatabase db;

	//内容提供者创建时调用
	@Override
	public boolean onCreate() {
		oh = new MyOpenHelper(getContext());
		db = oh.getWritableDatabase();
		
		return false;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	/*
	 * 增。用来往数据库里插入数据，这个方法是给其他应用调用的(non-Javadoc)
	 * uri：内容提供者的主机名，也就是地址，调用者通过这个uri，指定要调用那个内容提供者
	 * values：由其他应用传入，用于封装要插入的数据
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		db.insert("person", null, values);
		
		//可以把插入后返回的主键，拼接在uri后面。这里就不拼接
		return uri;
	}

	/*
	 * 删。用来删除数据库的数据，这个方法是给其他应用调用的
	 * uri：内容提供者的主机名，也就是地址，调用者通过这个uri，指定要调用那个内容提供者
	 * selection：删除的where条件，由其他应用传入
	 * selectionArgs：填充where条件站位符的，由其他应用传入
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int i = db.delete("person", selection, selectionArgs);
		//返回删除后的影响条数
		return i;
	}

	/*
	 * 改。用来修改数据库的数据，这个方法是给其他应用调用的
	 * uri：内容提供者的主机名，也就是地址，调用者通过这个uri，指定要调用那个内容提供者
	 * values：修改后的新数据，封装在ContentValues，由其他应用传入
	 * selection：修改的where条件，由其他应用传入
	 * selectionArgs：填充where条件占位符的，由其他应用传入
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		int i = db.update("person", values, selection, selectionArgs);
		//返回修改后的影响条数
		return i;
	}

	/*
	 * 查。用来查询数据库中的数据。这个方法是给其他应用调用的
	 * uri：uri：内容提供者的主机名，也就是地址，调用者通过这个uri，指定要调用那个内容提供者。由其他应用传入
	 * projection：查询的字段，如果是null就查所有字段，由其他应用传入
	 * selection：where条件，由其他应用传入
	 * selectionArgs：填充where条件占位符的，由其他应用传入
	 * sortOrder：排序条件，由其他应用传入
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Cursor cursor = db.query("person", projection, selection, selectionArgs, null, null, sortOrder, null);
		//返回结果集
		return cursor;
	}
}
