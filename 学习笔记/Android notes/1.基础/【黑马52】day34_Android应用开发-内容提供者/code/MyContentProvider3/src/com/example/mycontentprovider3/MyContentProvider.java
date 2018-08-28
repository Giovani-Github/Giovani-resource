package com.example.mycontentprovider3;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

	private MyOpenHelper oh;
	private SQLiteDatabase db;
	
	//创建uri匹配器。如果用户传入的uri没有一条跟我们定义的匹配规则匹配，匹配码就返回-1
	private static UriMatcher um = new UriMatcher(UriMatcher.NO_MATCH);
	
	//添加匹配规则
	static {
		/*
		 * authority: 内容提供者的主机名，与我们定义的内容提供者的主机名一致
		 * path：路径（表名）
		 * code：匹配码，如果用户传入的uri与这一条匹配，就返回这个匹配码
		 */
		// 用户传入的uri：content://com.example.mycontentprovider3/person，与这条匹配
		um.addURI("com.example.mycontentprovider3", "person", 1); 
		// 用户传入的uri：content://com.example.mycontentprovider3/person2，与这条匹配
		um.addURI("com.example.mycontentprovider3", "person2", 2);
		
		//路径path除了可以携带表名，也可以携带其他数据（“*”: 任何文本，“#:任何数字”）
		// 用户传入的uri：content://com.example.mycontentprovider3/person/abd，与这条匹配
		//um.addURI("com.example.mycontentprovider3", "person/*", 3);
		// 用户传入的uri：content://com.example.mycontentprovider3/person/123，与这条匹配
		um.addURI("com.example.mycontentprovider3", "person/#", 4);
	}
	

	//内容提供者创建时调用
	@Override
	public boolean onCreate() {
		oh = new MyOpenHelper(getContext());
		db = oh.getWritableDatabase();
		
		return false;
	}

	@Override
	public String getType(Uri uri) {
		if(um.match(uri) == 1) {
			//查询的是多条数据,要用：vnd.android.cursor.item开头
			return "vnd.android.cursor.item/person"; // 告诉调用者，查询到的是person表中的多条数据
		} else if(um.match(uri) == 4) {
			return "vnd.android.cursor.dir/person"; //告诉调用者，查询到的是person表中的单条数据
		}
		return null;
	}

	/*
	 * 增。用来往数据库里插入数据，这个方法是给其他应用调用的(non-Javadoc)
	 * uri：内容提供者的主机名，也就是地址，调用者通过这个uri，指定要调用那个内容提供者
	 * values：由其他应用传入，用于封装要插入的数据
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		//把用户传入的uri与匹配器进行匹配，会返回一个匹配码
		if(um.match(uri) == 1) {
			db.insert("person", null, values);
			System.out.println("增");
		} else if(um.match(uri) == 2) {
			db.insert("person2", null, values);
		} else {
			throw new IllegalAccessError("没有匹配的uri");
		}
		
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
		Cursor cursor = null;
		if(um.match(uri) == 1) {
			cursor = db.query("person", projection, selection, selectionArgs, null, null, sortOrder, null);
		} else if(um.match(uri) == 2) {
			cursor = db.query("person2", projection, selection, selectionArgs, null, null, sortOrder, null);
		} else if(um.match(uri) == 4) { //如果携带的是数字，就作为id查询
			//把uri末尾携带的数字取出来
			long id = ContentUris.parseId(uri);
			System.out.println(id);
			cursor = db.query("person", projection, "_id = ?", new String[] {id+""}, null, null, sortOrder, null);
		} else {
			throw new IllegalAccessError("没有匹配的uri");
		}
		//返回结果集
		return cursor;
	}
}
