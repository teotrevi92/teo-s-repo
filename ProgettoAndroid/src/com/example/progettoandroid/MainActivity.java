package com.example.progettoandroid;

import android.R.color;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.ActionBarDrawerToggle;

public class MainActivity extends ActionBarActivity implements OnItemClickListener {
	
	private DrawerLayout drawerLayout;
	private ListView listView;
	//private String[] menu_drawer;
	private ActionBarDrawerToggle drawerListner;
	private MyAdapter myAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//menu_drawer = getResources().getStringArray(R.array.menu_drawer);
		drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
		myAdapter = new MyAdapter(this);
		listView=(ListView) findViewById(R.id.drawerList);
		listView.setAdapter(myAdapter);
		//listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,menu_drawer));
		listView.setOnItemClickListener(this); 
		drawerListner = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.menu, R.string.drawer_open, R.string.drawer_close){
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
	//	getSupportActionBar().setHomeButtonEnabled(true); // rendo selezionabile la scritta "ProgettoAndroid"
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
		// definisco tutte le operazioni che andrï¿½ a fare nel tasto menï¿½
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
			//	Toast.makeText(this, menu_drawer[position] + " ï¿½ stato premuto", Toast.LENGTH_LONG).show();
		
	}
	
	
		
}

//creo la mia classe adapter: dovrò sovrascrivere tutti i suoi metodi; il MyAdapter si occupa della raccolta dei dati del nostro array di stringhe
class MyAdapter extends BaseAdapter{

	private Context context;
	String[] menu_drawer;
	int[] valori = {R.drawable.home, R.drawable.profile, R.drawable.settings, R.drawable.sessions, R.drawable.now_session, R.drawable.info}; // array contenente le immagini
	public MyAdapter(Context context) {
		//inizializzo array
		this.context=context;
		menu_drawer= context.getResources().getStringArray(R.array.menu_drawer);
	}
	@Override
	// ritorna il numero di elemente contenuti nell'array 
	public int getCount() {
		// TODO Auto-generated method stub
		return menu_drawer.length;
	}

	// restituisce una stringa contenente la posizione dell'oggetto (in posizione 0 abbiamo home...)
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return menu_drawer[position]; 
	}

	@Override
	// metodo che non verrà mai utilizzato
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position; 
	}

	@Override
	// questo è il metodo più importante: tramite posizione setta ogni riga mettendo immagine e scritta associata
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = null;
		if(convertView==null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // l'inflating instanzia un oggetto da una risorsa xml
			row = inflater.inflate(R.layout.custom_layout, parent,false);
		}
		else
		{
			row = convertView;
		}
		
		TextView titleTextView = (TextView) row.findViewById(R.id.textView1); // collego la TextView di custom_layout
		ImageView titleImageView = (ImageView) row.findViewById(R.id.imageView1); // collego la ImageView di custom_layout
		titleTextView.setText(menu_drawer[position]); // definisco il testo da visualizzare
		titleImageView.setImageResource(valori[position]); //definisco le immagini da visualizzare
		
		
		return row;
	}
	
}
