package com.example.progettoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class HomeFragment extends Fragment{

	private ImageButton play;
	private FragmentTransaction fragmentTransaction;
	private FragmentManager fragmentManager;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_home, container, false);
		
		//prendo i pulsanti con i loro id
		play = (ImageButton)view.findViewById(R.id.play);


		//azione del pulsante play
		play.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				fragmentManager = getFragmentManager();
				fragmentTransaction = fragmentManager.beginTransaction();
				SessionFragment ls_fragment = new SessionFragment();
				fragmentTransaction.replace(R.id.frag_show_activity, ls_fragment);
				//passaggio di paremetri
				Bundle args=new Bundle();
				args.putString("play", "play");
				ls_fragment.setArguments(args);
				//mi serve per metterlo nello stack per il pulsante indietro
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();



				// definisco l'intenzione di aprire l'Activity Session_Activity 
				//Intent invio = new Intent(getActivity().getApplication(), SessionFragment.class);
				//invio.putExtra("play", "play"); //controllo per verifica nell'altra activity
				//startActivity(invio);

				/*SessionFragment ls_fragment = new SessionFragment();
				fragmentTransaction.replace(R.id.frag_show_activity, ls_fragment);
				fragmentTransaction.commit();
				//ricreo l'oggetto per nuova futura Transaction
				fragmentTransaction = fragmentManager.beginTransaction();
				//mDrawer.closeDrawer(mDrawerList);*/

				//Toast.makeText(MainActivity.this, "Sessiona giÃ  attiva", Toast.LENGTH_SHORT).show();


			}
		});
		

		return view;
	}
	
	

}