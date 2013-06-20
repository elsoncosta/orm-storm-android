package com.example.adapters;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.entity.Book;
import com.example.ormston.R;

public class bookAdapter extends BaseAdapter 
{
	Activity activity;
	List<Book> List;
	LayoutInflater inflater;
	
	public bookAdapter(Activity activity, List<Book> List)
	{
		this.activity = activity;
		this.List = List;
		this.inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return List.size();
	}

	@Override
	public Object getItem(int position) {
		return List.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		Book book = List.get(position);
		
		View view = convertView;
		
		ViewHolder v = null;
		
		if (view == null) 
		{
			view = inflater.inflate(R.layout.rown_book, null);		
			v = new ViewHolder();
			v.textId = (TextView) view.findViewById(R.id.textId);
			v.textEditons = (TextView) view.findViewById(R.id.textEditons);
			v.textIsbn = (TextView) view.findViewById(R.id.textIsbn);
			v.textNunPags = (TextView) view.findViewById(R.id.textNunPags);
			v.textTitle = (TextView) view.findViewById(R.id.textTitle);
			
			view.setTag(v);
		}
		else
		{
			 v = (ViewHolder) view.getTag();
		}
		
		v.textEditons.setText(book.getEdition());
		v.textId.setText(Long.toString(book.getId()));
		v.textIsbn.setText(book.getIsbn());
		v.textNunPags.setText(Integer.toString(book.getNun_pages()));
		v.textTitle.setText(book.getTitle());	
		
		return view;
	}
	
	static class ViewHolder
	{
		TextView textId, textTitle, textEditons, textNunPags, textIsbn;
	}

}
