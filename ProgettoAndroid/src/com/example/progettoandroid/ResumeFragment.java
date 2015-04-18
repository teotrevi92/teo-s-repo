package com.example.progettoandroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResumeFragment extends Fragment{
	
	long timePassato;
	//Intent intent;
	int mNum;

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_resume, container, false);
		
		//riprendo i dati
		//intent = this.getActivity().getIntent();
		//timePassato = (intent.getLongExtra("timer", 0));
		timePassato = getArguments().getLong("timer");

		//ora stampo il resoconto
		String testo ="The end " + timePassato; //bisogna trasformarlo qui in min e sec
		TextView reso = (TextView) view.findViewById(R.id.resoconto);
		reso.setText(testo);


		return view;
	}	
	

}