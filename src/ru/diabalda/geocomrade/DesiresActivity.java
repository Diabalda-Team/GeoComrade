package ru.diabalda.geocomrade;

import java.util.ArrayList;
import java.util.List;

import ru.diabalda.geocamrade.logic.Desire;
import ru.diabalda.geocomrade.adapters.DesiresAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class DesiresActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.desires);
		
		initViews();
	}
	
	@Override
	protected void onResume() {
		mAdapter.notifyDataSetChanged();
		super.onResume();
	}
	
	private void initViews(){
		mListView = (ListView) findViewById(R.id.desires_listview);
		GeoComradeApp app = (GeoComradeApp)getApplicationContext();
		
		
		/*
		mDesiresList = new ArrayList<Desire>();
		
		mDesiresList.add(new Desire("This is a desire", false));
		mDesiresList.add(new Desire("This is a desire", false));
		mDesiresList.add(new Desire("This is a desire", false));
		mDesiresList.add(new Desire("This is a desire", false));
		mDesiresList.add(new Desire("This is a desire", false));
		mDesiresList.add(new Desire("This is a desire", false));
		mDesiresList.add(new Desire("This is a desire", false));
		mDesiresList.add(new Desire("This is a desire", false));
		*/
		
		mAdapter = new DesiresAdapter(DesiresActivity.this, app.desireManager.getDesireList());
		mListView.setAdapter(mAdapter);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Toast.makeText(DesiresActivity.this, "Item clicked", Toast.LENGTH_SHORT).show();
			}
			
		});
		
		Button mAddButton = (Button) findViewById(R.id.desires_add_button);
		mAddButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), AddDesire.class));
			}
		});
	}

	private DesiresAdapter mAdapter;
	
	private ListView mListView;
	
}
