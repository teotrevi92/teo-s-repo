package com.example.progettoandroid;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RiepilogoSessioniFragment extends Fragment {
	 
	private ArrayList<SingleRow> arrayList; 
	private ListView list;
	private BaseAdapter adapter;
	private SingleRow setItem;
	private Activity activity;
	 
	@Override
	public void onAttach(Activity a){
		super.onAttach(a);
		activity = a;
	}
	 
	 
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_riepilogo_sessioni, container, false);
		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		/*list        = (ListView) getView().findViewById(R.id.listView1);
		list.setAdapter(new VivzAdapter(getActivity(), arrayList));
		adapter =new VivzAdapter(activity, arrayList);
		list.setAdapter(adapter);*/
		arrayList = new ArrayList<SingleRow>();
		list = (ListView) getView().findViewById(R.id.listView1);
		adapter =new VivzAdapter(activity, arrayList);
		list.setAdapter(adapter);
		registerForContextMenu(list);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0: Toast.makeText(getActivity(), "Provo click", Toast.LENGTH_LONG).show();
					
					break;

				default: Toast.makeText(getActivity(), "Dettagli sessione", Toast.LENGTH_LONG).show();
					break;
				}
			}
		});
		
		
		
		
		
		/*list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0: Toast.makeText(getActivity(),"Provo longClick", Toast.LENGTH_LONG).show();
					
					return true;

				default: Toast.makeText(getActivity(), "Eventuale ContextMenu o Dialog", Toast.LENGTH_LONG).show();
					return true;
				}
			}
		});*/
		
		
	};
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.main_context_menu,menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.delete_id:
			Toast.makeText(getActivity(), "Da implementare la rimozione", Toast.LENGTH_SHORT).show();
//			arrayList.remove(info.position);
//			adapter.notifyDataSetChanged();
			return true;
		case R.id.mod_id:
			Toast.makeText(getActivity(), "Da implementare la modifica", Toast.LENGTH_SHORT).show();
//			arrayList.set(info.position,setItem);
//			adapter.notifyDataSetChanged();
			return true;
		case R.id.other_id:
			Toast.makeText(getActivity(), "Da implementare altre opzioni", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
		//return super.onContextItemSelected(item);
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
		String[] titles;
		Context context;
		ArrayList<SingleRow> list;
		VivzAdapter(Context c, ArrayList<SingleRow> arrList) {
			// TODO Auto-generated constructor stub
			list = arrList;
			context = c;
			
			Resources res = c.getResources();
			titles = res.getStringArray(R.array.titoli);
			String[] description = res.getStringArray(R.array.descrizioni);
			int[] images = {R.drawable.settings,R.drawable.settings, R.drawable.settings,R.drawable.settings,
							R.drawable.settings,R.drawable.settings, R.drawable.settings,R.drawable.settings,
							R.drawable.settings,R.drawable.settings, R.drawable.settings,R.drawable.settings,
							R.drawable.settings,R.drawable.settings, R.drawable.settings,R.drawable.settings};
			
			for(int i=0; i<16; i++) // per adesso metto valori a caso poi andremo a pescarceli dal db
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
