package com.example.progettoandroid;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class RiepilogoSessioniFragment extends Fragment {
	 
	 private ListView list;
	 
	 
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_riepilogo_sessioni, container, false);
		list        = (ListView) view.findViewById(R.id.listView1);
		list.setAdapter(new VivzAdapter(getActivity()));
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0: Toast.makeText(getActivity(), "Bisogna fare qualcosa", Toast.LENGTH_LONG).show();
					
					break;

				default: Toast.makeText(getActivity(), "Da implementare", Toast.LENGTH_LONG).show();
					break;
				}
			}
		});

		
		
		return view;
	}
	
	// questa classe mi serve per creare un un'inico arrayList invece che 3 array separati e poter così sfruttare i metodi di VivzAdapter
	class SingleRow
	{
		String title;
		String description;
		int image;
		SingleRow(String title, String description, int image)
		{
			this.title = title;
			this.description = description;
			this.image = image;
		}
	}

	class VivzAdapter extends BaseAdapter
	{
		
		Context context;
		ArrayList<SingleRow> list;
		VivzAdapter(Context c) {
			// TODO Auto-generated constructor stub
			context = c;
			list = new ArrayList<SingleRow>();
			Resources res = c.getResources();
			String[] titles = res.getStringArray(R.array.titoli);
			String[] description = res.getStringArray(R.array.descrizioni);
			int[] images = {R.drawable.settings,R.drawable.settings, R.drawable.settings,R.drawable.settings};
			
			for(int i=0; i<4; i++) // per adesso metto valori a caso poi andremo a pescarceli dal db
			{
				list.add(new SingleRow(titles[i],description[i],images[i]));
				
			}
			
			
			
			
		}
		// ritorna il numero di elementi all'interno dell'array 
		@Override
		//Se non avessi creato la classe SingleRow quale lunghezza dell'array avrei restituito (Title, Description o images?)
		public int getCount() {
			// TODO Auto-generated method stub
			
			return list.size();
			
		}

		// ritorna l'oggetto alla posizione arg0
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
		
			
			return list.get(position);
		
			
			
		}

		// ritorna l'id della cella alla quale l'array fa riferimento
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			
			return position;
			
		}

		// fa visualizzare l'xml 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// creo una copia del mio xml composto da due text view e l'immagine.. per questo usa getSystemService e non findViewById
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
			View row = inflater.inflate(R.layout.single_raw, parent, false);
			TextView title = (TextView) row.findViewById(R.id.textView1);
			TextView description = (TextView) row.findViewById(R.id.textView2);
			ImageView image = (ImageView) row.findViewById(R.id.imageView2);
			SingleRow temp = list.get(position); // posso gestire cella per cella
			title.setText(temp.title);
			description.setText(temp.description);
			image.setImageResource(temp.image);
			
			
			
			return row;
		}
	
	

}}
