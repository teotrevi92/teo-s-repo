package com.example.progettoandroid;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	//dichiarazione varibili
	private ImageButton play;
	long timeWhenStopped = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//prendo i pulsanti con i loro id
		play = (ImageButton)findViewById(R.id.play);

		//azione del pulsante play
		play.setOnClickListener(new View.OnClickListener() {
			
			@Override
				public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// definisco l'intenzione di aprire l'Activity Session_Activity 
				Intent invio = new Intent(MainActivity.this, Session_Activity.class);
				invio.putExtra("play", "play"); //controllo per verifica nell'altra activity
				startActivity(invio);
				
				//Toast.makeText(MainActivity.this, "Sessiona già attiva", Toast.LENGTH_SHORT).show();
	
				
			}
		});
		
		
		
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
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	

	
	

}