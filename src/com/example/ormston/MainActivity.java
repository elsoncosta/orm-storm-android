package com.example.ormston;

import java.util.List;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.adapters.bookAdapter;
import com.example.database.TestDatabaseHelper;
import com.example.database.TestDbFactory;
import com.example.entity.Book;
import com.example.entity.dao.BookDao;
import com.turbomanage.storm.DatabaseHelper;

public class MainActivity extends Activity {
	Book book;
	BookDao dao;
	
	EditText txtTitle, txtEditin;
	bookAdapter adapter;
	List<Book> books;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtTitle = (EditText) findViewById(R.id.txtTitle);
        txtEditin = (EditText) findViewById(R.id.txtEdition);
        
        final ListView listView = (ListView) findViewById(R.id.listView1);
        
        Button btnSalvar = (Button) findViewById(R.id.button1);
        
        openDatabase();
        dao = new BookDao(this);        
        
        btnSalvar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				 book = new Book();
				 book.setEdition(txtEditin.getText().toString());
			     book.setTitle(txtTitle.getText().toString());
			     
			     dao.insert(book);
			     books = dao.listAll();
			     
			     adapter = new bookAdapter(MainActivity.this, books);
			     
			     listView.setAdapter(adapter);
			}
		});
        
        books = dao.listAll();
        
        if (books.size() > 0) 
        {
        	adapter = new bookAdapter(this, books);
		    listView.setAdapter(adapter);
		}
        
        listView.setOnItemClickListener(new OnItemClickListener() 
        {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
			{
				Book b = (Book) arg0.getItemAtPosition(arg2);
				
				dao.delete(b.getId());
				books.remove(arg2);
				
				adapter.notifyDataSetChanged();
			}
		});
    }
    
   
    private void openDatabase() {
        DatabaseHelper dbHelper = TestDbFactory.getDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // wipe database
        
        if (TestDatabaseHelper.DB_VERSION > db.getVersion()) 
        {
        	dbHelper.upgrade(db, TestDatabaseHelper.DB_VERSION, db.getVersion());
		}
}
   
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) 
//    {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    
}
