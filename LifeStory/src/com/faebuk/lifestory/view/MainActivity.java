package com.faebuk.lifestory.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.faebuk.lifestory.R;

public class MainActivity extends Activity {

	private DrawerLayout menuDrawerLayout;
	private ListView lvMenu;
	private ActionBarDrawerToggle abdToggle;

	private CharSequence menuDrawerTitle;
	private CharSequence menuTitle;
	private String[] menuTitlesArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		menuTitle = menuDrawerTitle = getTitle();
		menuTitlesArray = getResources().getStringArray(R.array.planets_array);
		menuDrawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);
		lvMenu = (ListView) findViewById(R.id.lv_menu);

		// einen benutzerdefinierten Schatten setzen, der den Hauptinhalt
		// überlagert, wenn das Menu geöffnet ist
		menuDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// Einrichten der ListView des Drawers mit items und Click Listener
		 lvMenu.setAdapter(new ArrayAdapter<String>(this,
		 R.layout.menu_item_list, menuTitlesArray));

		lvMenu.setOnItemClickListener(new MenuItemClickListener());

		// ActionBar App-Icon aktivieren
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		abdToggle = new ActionBarDrawerToggle(this, /* host Activity */
		menuDrawerLayout, /* DrawerLayout objekt */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(menuTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(menuDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		menuDrawerLayout.setDrawerListener(abdToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// Aufgerufen, wenn wir invalidateOptionsMenu() ausführen
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = menuDrawerLayout.isDrawerOpen(lvMenu);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// ActionBar Home/UP Button sollte Das Menu öffnen/schliessen
		// ActionBarDrawToggle
		if (abdToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(getApplicationContext(), "K", Toast.LENGTH_LONG)
					.show();
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* Der click listner für die ListView im navigation drawer */
	private class MenuItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	public void selectItem(int position) {
		// update das main content indem er die fragmente replaced
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new PlanetFragment();
			Bundle args = new Bundle();
			args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
			fragment.setArguments(args);
			break;
		case 1:
			fragment = new SettingsFragment();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		}
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.fl_content, fragment)
				.commit();

		// update item und drawer
		// schließe dann das menu
		lvMenu.setItemChecked(position, true);
		setTitle(menuTitlesArray[position]);
		menuDrawerLayout.closeDrawer(lvMenu);
	}

	@Override
	public void setTitle(CharSequence title) {
		menuTitle = title;
		getActionBar().setTitle(menuTitle);
	}

	/**
	 * Wenn ActionBarDrawerToggle benutzt wird, dann zwischen onPostCreate() and
	 * onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		abdToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		abdToggle.onConfigurationChanged(newConfig);
	}
}
