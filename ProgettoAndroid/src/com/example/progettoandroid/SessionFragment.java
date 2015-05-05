package com.example.progettoandroid;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.widget.ImageButton;

public class SessionFragment extends Fragment{

	//dichiarazione variabili
	private Chronometer crono;
	private ImageButton pause;
	private ImageButton stop;
	private ImageButton play;
	long timeStop = 0;
	//Intent int1;
	String recupero = ""; //mi serve per capire se e' stato premuto il pulsante nell'activity
	private FragmentTransaction fragmentTransaction;
	private FragmentManager fragmentManager;

	//metodi per queado sono in pausa e quando sono in Play
	private void inPause()
	{
		stop.setVisibility(View.VISIBLE);
		play.setVisibility(View.VISIBLE);
		pause.setVisibility(View.INVISIBLE);
	}
	private void inPlay()
	{
		stop.setVisibility(View.INVISIBLE);
		play.setVisibility(View.INVISIBLE);
		pause.setVisibility(View.VISIBLE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_session, container, false);

		//prendo i pulsanti con i loro id
		crono = (Chronometer)view.findViewById(R.id.chronometer);
		pause = (ImageButton)view.findViewById(R.id.pause);
		play = (ImageButton)view.findViewById(R.id.play2);
		stop = (ImageButton)view.findViewById(R.id.stop);
		//recupero valore da activity
		//int1 =  this.getActivity().getIntent();
		//recupero = int1.getStringExtra("play");
		recupero = getArguments().getString("play");
		Toast.makeText(getActivity(), recupero, Toast.LENGTH_LONG).show();

		//Ricerco i dati se salvati, quando giro lo schermo
		if (savedInstanceState != null)
		{
			Long crnValue = savedInstanceState.getLong("crnValue");
			String rnValue = savedInstanceState.getString("rnValue").toString();
			if (crnValue != null) 
			{
				//crono.setBase(SystemClock.elapsedRealtime()+crnValue);
				timeStop = crnValue;
				crono.setBase(SystemClock.elapsedRealtime() + timeStop);
				recupero = rnValue;
			} 
		}

		//se sono arrivato qui e c'era play continuo e faccio start
		if(recupero.equals("play")) {
			//riparto dove ero rimasto
			crono.setBase(SystemClock.elapsedRealtime() + timeStop);
			crono.start();
			inPlay(); //metodo creato prima per visualizzare i tasti giusti
		}
		else //altrimenti lascio in pausa e vaccio vedere i tasti del Pause
			inPause();//metodo creato prima per visualizzare i tasti giusti


		pause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				//memorizzo dove sono rimasto
				timeStop = crono.getBase() - SystemClock.elapsedRealtime();
				crono.stop();
				recupero = "pause"; //lo tengo presente per l'eventualita' che venga girato lo schermo
				//cambio stato dei pulsanti
				inPause(); //metodo creato prima per visualizzare i tasti giusti
			}
		});

		stop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				//memorizzo dove sono rimasto
				timeStop = crono.getBase() - SystemClock.elapsedRealtime();
				crono.stop();
				//salvo  il cronometro in stringa
				String strtempo = crono.getText().toString();
				

				fragmentManager = getFragmentManager();
				fragmentTransaction = fragmentManager.beginTransaction();
				ResumeFragment ls_fragment = new ResumeFragment();
				fragmentTransaction.replace(R.id.frag_show_activity, ls_fragment);
				//passaggio di paremetri
				Bundle args=new Bundle();
				args.putString("timer", strtempo);
				ls_fragment.setArguments(args);

				fragmentManager.popBackStack(); //viene tolto dallo stack questo fragment

				
				
				//mi serve per metterlo nello stack per il pulsante indietro
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();

				//chiudo l'activity
				//Intent inten = new Intent();
				//setResult(RESULT_OK, inten);
				//finish();

 
			}
		});


		play.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				//riparto dove ero rimasto
				crono.setBase(SystemClock.elapsedRealtime() + timeStop);
				crono.start();
				recupero ="play";	
				//cambio stato dei pulsanti
				inPlay(); //metodo creato prima per visualizzare i tasti giusti
			}
		});

		return view;

	}

	//salvo qui i dati per la rotazione
	public void onSaveInstanceState(Bundle savedInstanceState)
	{
		// NOTE: with the implementation of this method inherited from
		// Activity, some widgets save their state in the bundle by default.
		// Once the user interface contains AT LEAST one non-autosaving
		// element, you should provide a custom implementation of
		// the method

		long crn=0;
		if (recupero.equals("play")) //se e' attivo salvo l'ultimo cronometro
			crn = crono.getBase() - SystemClock.elapsedRealtime();
		else
			crn = timeStop;//altrimenti se e' in pausa o stop tengo l'ultimo salvato

		savedInstanceState.putLong("crnValue", crn);
		String rn = recupero;
		savedInstanceState.putString("rnValue", rn);


		super.onSaveInstanceState(savedInstanceState);

	}



}

