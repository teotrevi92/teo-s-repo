package com.example.progettoandroid;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBarActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.ActionBarDrawerToggle;

public class MainActivity extends ActionBarActivity implements OnItemClickListener {
	
	private DrawerLayout drawerLayout;
	private ListView listView;
	private String[] prova;
	private ActionBarDrawerToggle drawerListner;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		prova = getResources().getStringArray(R.array.prova);
		drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
		listView=(ListView) findViewById(R.id.drawerList);
		listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,prova));
		listView.setOnItemClickListener(this); 
		drawerListner = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.drawer_icon, R.string.drawer_open, R.string.drawer_close){
			@Override
			// definisco cosa deve succedere quando apro il drawer: in questo caso mi segnala tramite Toast che l'ho aperto
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "Drawer aperto", Toast.LENGTH_LONG).show();
			}
			
			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "Drawer chiuso", Toast.LENGTH_LONG).show();
			}
			
		};
		drawerLayout.setDrawerListener(drawerListner);
		getSupportActionBar().setHomeButtonEnabled(true); // rendo selezionabile la scritta "ProgettoAndroid"
		getSupportActionBar().setDisplayHomeAsUpEnabled(true); // ora compare la freccia <
		
		
	}
	
	// informa il sistema che ci possono essere dei cambiamenti come ad es la rotazione dello schermo
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		drawerListner.onConfigurationChanged(newConfig);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		drawerListner.syncState(); //appare la classica icona del drawer
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

	@Override
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		// definisco tutte le operazioni che andrò a fare nel tasto menù
		if(drawerListner.onOptionsItemSelected(item))
		{
			return true;
		}
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		// Cosa Voglio vedere quando premo sui vari tasti?
				Toast.makeText(this, prova[position] + " è stato premuto", Toast.LENGTH_LONG).show();
		
	}
}

