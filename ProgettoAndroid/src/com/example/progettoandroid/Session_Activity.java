package com.example.progettoandroid;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.Toast;


public class Session_Activity extends ActionBarActivity {	
	
	//dichiarazione variabili
	private Chronometer crono;
	private ImageButton pause;
	private ImageButton stop;
	private ImageButton play;
	long timeStop = 0;
	Intent int1;
	String recupero = ""; //mi serve per capire se e' stato premuto il pulsante nell'activity
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_session);

		//prendo i pulsanti con i loro id
		crono = (Chronometer)findViewById(R.id.chronometer);
		pause = (ImageButton)findViewById(R.id.pause);
		play = (ImageButton)findViewById(R.id.play2);
		stop = (ImageButton)findViewById(R.id.stop);
		//recupero valore da activity
		int1 =  getIntent();
		recupero = int1.getStringExtra("play");
		//stato iniziale con rispettive visibilita'
		stop.setVisibility(View.INVISIBLE);
		play.setVisibility(View.INVISIBLE);
		pause.setVisibility(View.VISIBLE);
		
		//se sono arrivato qui dal play faccio partire
		if(recupero.equals("play")) {
			//riparto dove ero rimasto
			crono.setBase(SystemClock.elapsedRealtime() + timeStop);
			crono.start();
		}
		recupero = ""; //azzero la variabile
	
		
		if (savedInstanceState != null)
		 {
			 Long crnValue = savedInstanceState.getLong("crnValue");
			 if (crnValue != null) crono.setBase(SystemClock.elapsedRealtime()+crnValue);
			 		
		 } 
		
		
		
		pause.setOnClickListener(new View.OnClickListener() {
		@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			
			//memorizzo dove sono rimasto
			timeStop = crono.getBase() - SystemClock.elapsedRealtime();
			crono.stop();
			//cambio stato dei pulsanti
			stop.setVisibility(View.VISIBLE);
			play.setVisibility(View.VISIBLE);
			pause.setVisibility(View.INVISIBLE);
		}
	});
	
	stop.setOnClickListener(new View.OnClickListener() {
		@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			
			//azzero e fermo
			crono.setBase(SystemClock.elapsedRealtime());
			crono.stop();
			timeStop = 0;
			//rendo non cliccabile il pulsante play
			play.setClickable(false);
			//avviso interruzione registrazione
			Toast.makeText(Session_Activity.this, "Hai interrotto la registrazione", Toast.LENGTH_SHORT).show();
		}
	});
	
	
	play.setOnClickListener(new View.OnClickListener() {
		@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			
			//riparto dove ero rimasto
			crono.setBase(SystemClock.elapsedRealtime() + timeStop);
			crono.start();
			//cambio stato dei pulsanti
			stop.setVisibility(View.INVISIBLE);
			play.setVisibility(View.INVISIBLE);
			pause.setVisibility(View.VISIBLE);
		}
	});
	
		
	
	}
	
	//salvo qui i dati per la rotazione
		public void onSaveInstanceState(Bundle savedInstanceState)
		{
		// NOTE: with the implementation of this method inherited from
		// Activity, some widgets save their state in the bundle by default.
		// Once the user interface contains AT LEAST one non-autosaving
		// element, you should provide a custom implementation of
		// the method

			long crn = crono.getBase() - SystemClock.elapsedRealtime();
			savedInstanceState.putLong("crnValue", crn);

			super.onSaveInstanceState(savedInstanceState);
		}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.session_, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
