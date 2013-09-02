/*******************************************************************************
 * Copyright 2013 Gabriele Mariotti
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package it.gmariotti.android.example.colorpicker;

import it.gmariotti.android.example.colorpicker.calendarstock.ColorPickerDialog;
import it.gmariotti.android.example.colorpicker.calendarstock.ColorPickerSwatch;
import it.gmariotti.android.example.colorpicker.calendarstock.SettingsPickerActivity;
import it.gmariotti.android.example.colorpicker.dashclockpicker.ColorPickerDialogDash;
import it.gmariotti.android.example.colorpicker.dashclockpicker.SettingsActivity;
import it.gmariotti.android.example.colorpicker.internal.NsMenuAdapter;
import it.gmariotti.android.example.colorpicker.internal.NsMenuItemModel;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

/**
 * This activity contains these examples:
 * 
 * DialogColorPicker by StockCalendar ColorPreference by DashClock
 * DialogColorPickerDash extracted from Dashclock ColorPickerPreference based on
 * StockCalendar
 * 
 * 
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 * 
 */
public class MainActivity extends ListActivity {

	// ---------------------------------------------------------------
	// Only for Menu
	private NsMenuAdapter mAdapter;

	private String[] menuItems;
	private static final int MENU_DASH_0 = 0;
	private static final int MENU_DASH_1 = 1;
	private static final int MENU_DASH_2 = 2;
	private static final int MENU_CALENDAR_0 = 100;
	private static final int MENU_CALENDAR_1 = 101;
	// ---------------------------------------------------------------

	// Selected colors
	private int mSelectedColorDash0 = 0;
	private int mSelectedColorDash1 = 0;
	private int mSelectedColorCal0 = 0;
	private int mSelectedColorCal1 = 0;

	// Keys for savedInstanceState
	private final static String KEY_BUNDLE_POSITION = "KEY_BUNDLE_POSITION";
	private final static String KEY_BUNDLE_SCD0 = "KEY_BUNDLE_SCD0";
	private final static String KEY_BUNDLE_SCD1 = "KEY_BUNDLE_SCD1";
	private final static String KEY_BUNDLE_SCC0 = "KEY_BUNDLE_SCC0";
	private final static String KEY_BUNDLE_SCC1 = "KEY_BUNDLE_SCC1";
	
	int mLastPosition;
	
	// ---------------------------------------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Get selected colors
		restoreSelectedColor(savedInstanceState);

		// initialize menu
		_initMenu();
		
		
		//Re-bind listeners
		if (savedInstanceState!=null){
			
			ColorPickerDialogDash colordash = (ColorPickerDialogDash)
					getFragmentManager().findFragmentByTag("dash");
	        if (colordash != null) {
	            // re-bind listener to fragment
	            colordash.setOnColorSelectedListener(colordashListner);
	        }
	        
			ColorPickerDialog colorcalendar = (ColorPickerDialog) 
					getFragmentManager().findFragmentByTag("cal");
	        if (colorcalendar != null) {
	            // re-bind listener to fragment
	            colorcalendar.setOnColorSelectedListener(colorcalendarListener );
	        }
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		_initMenu();
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
		// Get selected colors
		restoreSelectedColor(state);
	}
	
	private void restoreSelectedColor(Bundle savedInstanceState){
		// Get selected colors
		if (savedInstanceState != null) {
			mSelectedColorDash0 = savedInstanceState.getInt(KEY_BUNDLE_SCD0);
			mSelectedColorDash1 = savedInstanceState.getInt(KEY_BUNDLE_SCD1);
			mSelectedColorCal0 = savedInstanceState.getInt(KEY_BUNDLE_SCC0);
			mSelectedColorCal1 = savedInstanceState.getInt(KEY_BUNDLE_SCC1);
			mLastPosition = savedInstanceState.getInt(KEY_BUNDLE_POSITION);
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// Save selected color
		outState.putInt(KEY_BUNDLE_SCD0, mSelectedColorDash0);
		outState.putInt(KEY_BUNDLE_SCD1, mSelectedColorDash1);
		outState.putInt(KEY_BUNDLE_SCC0, mSelectedColorCal0);
		outState.putInt(KEY_BUNDLE_SCC1, mSelectedColorCal1);
		outState.putInt(KEY_BUNDLE_POSITION, mLastPosition);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		NsMenuItemModel item = mAdapter.getItem(position);
		mLastPosition = position;

		if (item == null)
			return;
		Intent intent = null;
		// Init colors to use in dialogs
		int[] mColor = Utils.ColorUtils.colorChoice(this);

		switch (item._id) {
		default:
			// -------------------------------------------------------------------------------------------------------------
		case MENU_DASH_0:
			// Original ColorPreference
			intent = new Intent(this, SettingsActivity.class);
			break;
		// -------------------------------------------------------------------------------------------------------------
		case MENU_DASH_1:
			// Custom Dialog extracted from ColorPreference
			ColorPickerDialogDash colordashfragment = ColorPickerDialogDash
					.newInstance(R.string.color_picker_default_title, mColor,
							mSelectedColorDash1, 5);

			// Implement listener to get selected color value
			colordashfragment.setOnColorSelectedListener(colordashListner);
			colordashfragment.show(getFragmentManager(), "dash");
			break;
		// -------------------------------------------------------------------------------------------------------------
		case MENU_CALENDAR_0:
			// Original Stock Calendar
			ColorPickerDialog colorcalendar = ColorPickerDialog.newInstance(
					R.string.color_picker_default_title, mColor,
					mSelectedColorCal0, 5,
					Utils.isTablet(this) ? ColorPickerDialog.SIZE_LARGE
							: ColorPickerDialog.SIZE_SMALL);
						
			colorcalendar.setOnColorSelectedListener(colorcalendarListener);
			colorcalendar.show(getFragmentManager(), "cal");
			break;
		// -------------------------------------------------------------------------------------------------------------
		case MENU_CALENDAR_1:
			// ColorPreference with StockCalendar
			intent = new Intent(this, SettingsPickerActivity.class);
			break;
		}

		if (intent != null)
			startActivity(intent);
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	// Listeners
	//-----------------------------------------------------------------------------------------------------------------
	
	ColorPickerDialogDash.OnColorSelectedListener colordashListner=new ColorPickerDialogDash.OnColorSelectedListener(){

		@Override
		public void onColorSelected(int color) {
			mSelectedColorDash1 = color;
			NsMenuItemModel item = mAdapter.getItem(mLastPosition);
			if (item!=null)
				item.colorSquare = color;
			mAdapter.notifyDataSetChanged();
		}

	};

	
	// Implement listener to get selected color value
	ColorPickerSwatch.OnColorSelectedListener colorcalendarListener = new ColorPickerSwatch.OnColorSelectedListener(){

		@Override
		public void onColorSelected(int color) {
			mSelectedColorCal0 = color;
			NsMenuItemModel item = mAdapter.getItem(mLastPosition);
			if (item!=null)
				item.colorSquare = color;
			mAdapter.notifyDataSetChanged();
		}
	};

				
	/**
	 * Build Menu List
	 * 
	 */
	private void _initMenu() {
		mAdapter = new NsMenuAdapter(this);

		// Read preferences to get selected Color
		SharedPreferences shared = PreferenceManager
				.getDefaultSharedPreferences(this);
		if (shared != null) {
			mSelectedColorDash0 = shared.getInt("dash_colorkey", 0);
			mSelectedColorCal1 = shared.getInt("calendar_colorkey", 0);
		}

		// -------------------------------------------------------------------------------------
		// Dashclock
		// -------------------------------------------------------------------------------------

		// Add Header
		mAdapter.addHeader(R.string.ns_menu_main_header_dash);
		// Add Dashclock items
		NsMenuItemModel mItem1 = new NsMenuItemModel(
				R.string.ns_menu_main_row_dash_original, mSelectedColorDash0,
				MENU_DASH_0);
		NsMenuItemModel mItem2 = new NsMenuItemModel(
				R.string.ns_menu_main_row_dash_dialog, mSelectedColorDash1,
				MENU_DASH_1);
		mAdapter.addItem(mItem1);
		mAdapter.addItem(mItem2);

		// -------------------------------------------------------------------------------------
		// Stock Calendar
		// -------------------------------------------------------------------------------------

		// Add Header
		mAdapter.addHeader(R.string.ns_menu_main_header_calendar);
		NsMenuItemModel mItem1c = new NsMenuItemModel(
				R.string.ns_menu_main_row_calendar_original,
				mSelectedColorCal0, MENU_CALENDAR_0);
		NsMenuItemModel mItem2c = new NsMenuItemModel(
				R.string.ns_menu_main_row_calendar_preference,
				mSelectedColorCal1, MENU_CALENDAR_1);
		mAdapter.addItem(mItem1c);
		mAdapter.addItem(mItem2c);

		setListAdapter(mAdapter);
		getListView().setDividerHeight(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		// About
		case R.id.menu_about:
			Utils.showAbout(this);
			return true;

		}

		// Handle your other action bar items...
		return super.onOptionsItemSelected(item);
	}

}
