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
	
	private Chronometer crono;
	private ImageButton pause;
	private ImageButton stop;
	private ImageButton play;
	long timeWhenStopped = 0;
	Intent int1;
	String recupero;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_session);

		crono = (Chronometer)findViewById(R.id.chronometer);
		pause = (ImageButton)findViewById(R.id.pause);
		play = (ImageButton)findViewById(R.id.play2);
		stop = (ImageButton)findViewById(R.id.stop);
		int1 =  getIntent();
		recupero = int1.getStringExtra("play");
		stop.setVisibility(View.INVISIBLE);
		play.setVisibility(View.INVISIBLE);
		pause.setVisibility(View.VISIBLE);
		
		if(recupero.equals("play")) {
			//riparto dove ero rimasto
			crono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
			crono.start();
		}
		recupero = "";
	
		
		pause.setOnClickListener(new View.OnClickListener() {
		@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			
			//memorizzo dove sono rimasto
			timeWhenStopped = crono.getBase() - SystemClock.elapsedRealtime();
			crono.stop();
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
			timeWhenStopped = 0;
			Toast.makeText(Session_Activity.this, "Hai interrotto la registrazione", Toast.LENGTH_SHORT).show();
		}
	});
	
	
	play.setOnClickListener(new View.OnClickListener() {
		@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			
			//riparto dove ero rimasto
			crono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
			crono.start();
			stop.setVisibility(View.INVISIBLE);
			play.setVisibility(View.INVISIBLE);
			pause.setVisibility(View.VISIBLE);
		}
	});
	
		
	
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
