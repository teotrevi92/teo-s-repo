package com.example.progettoandroid;


import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;

public class ListActivity2 extends ListActivity {
	
	
	private ArrayAdapter<MyObj> adapter;
	MyObj obj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(ListHelper.buildViewHolderAdapter(this,
				R.layout.list_item));
		// Show the Up button in the action bar.
		setupActionBar();
		adapter = (ViewHolderAdapter) getListView().getAdapter();
		registerForContextMenu(getListView());
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

	//	getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_context_menu,menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.delete_id:
		//	Toast.makeText(this, "Da implementare la rimozione", Toast.LENGTH_SHORT).show();
			MyObj o = adapter.getItem(info.position);
			adapter.remove(o);
			adapter.notifyDataSetChanged();
			return true;
		case R.id.mod_id:
			Toast.makeText(this, "Da implementare la modifica", Toast.LENGTH_SHORT).show();
//			arrayList.set(info.position,setItem);
//			adapter.notifyDataSetChanged();
			return true;
		case R.id.other_id:
			Toast.makeText(this, "Da implementare altre opzioni", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
		//return super.onContextItemSelected(item);
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
