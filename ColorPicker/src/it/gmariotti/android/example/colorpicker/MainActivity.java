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
import it.gmariotti.android.example.colorpicker.dashclockpicker.ColorPickerDialogDash;
import it.gmariotti.android.example.colorpicker.dashclockpicker.SettingsActivity;
import it.gmariotti.android.example.colorpicker.internal.NsMenuAdapter;
import it.gmariotti.android.example.colorpicker.internal.NsMenuItemModel;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;

/**
 * This activity contains these examples:
 * 
 * DialogColorPicker by StockCalendar
 * ColorPreference by DashClock
 * DialogColorPickerDash extracted from Dashclock 
 * 
 * 
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 *
 */
public class MainActivity extends ListActivity{

	//---------------------------------------------------------------
	//Only for Menu
	private NsMenuAdapter mAdapter;
	
	private String[] menuItems;
	private static final int MENU_DASH_0=0;
	private static final int MENU_DASH_1=1;
	private static final int MENU_DASH_2=2;
	private static final int MENU_CALENDAR_0=100;
	//---------------------------------------------------------------
	
	//Selected colors
	private int mSelectedColorDash0= 0;
	private int mSelectedColorDash1= 0;
	private int mSelectedColorCal0= 0;
	
	//Keys for savedInstanceState
	private final static String KEY_BUNDLE_SCD0="KEY_BUNDLE_SCD0";
	private final static String KEY_BUNDLE_SCD1="KEY_BUNDLE_SCD1";
	private final static String KEY_BUNDLE_SCC0="KEY_BUNDLE_SCC0";
	
	//---------------------------------------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		// Get selected colors
		if (savedInstanceState!=null){
			mSelectedColorDash0=savedInstanceState.getInt(KEY_BUNDLE_SCD0);
			mSelectedColorDash1=savedInstanceState.getInt(KEY_BUNDLE_SCD1);
			mSelectedColorCal0=savedInstanceState.getInt(KEY_BUNDLE_SCC0);
		}
		
		//initialize menu
		_initMenu();
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		//Save selected color
		outState.putInt(KEY_BUNDLE_SCD0, mSelectedColorDash0);
		outState.putInt(KEY_BUNDLE_SCD1, mSelectedColorDash1);
		outState.putInt(KEY_BUNDLE_SCC0, mSelectedColorCal0);
	}
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		final NsMenuItemModel item = mAdapter.getItem(position);
		
		if (item==null) return;
		Intent intent = null;
		//Init colors to use in dialogs
		int[] mColor= Utils.ColorUtils.colorChoice(this);
		
		switch (item._id) {
		default:
		//-------------------------------------------------------------------------------------------------------------
		case MENU_DASH_0:
			//Original ColorPreference
			intent = new Intent(this, SettingsActivity.class);
			break;
		//-------------------------------------------------------------------------------------------------------------	
		case MENU_DASH_1:
			//Custom Dialog extracted from ColorPreference
			ColorPickerDialogDash colordashfragment = ColorPickerDialogDash.newInstance(R.string.color_picker_default_title,mColor,mSelectedColorDash1,5);
			
			//Implement listener to get selected color value
			colordashfragment.setOnColorSelectedListener(new ColorPickerDialogDash.OnColorSelectedListener(){

				@Override
				public void onColorSelected(int color) {
					mSelectedColorDash1=color;	
					item.colorSquare=color;
					mAdapter.notifyDataSetChanged();
				}
				
			});
			colordashfragment.show(getFragmentManager(), "dash");
			break;
		//-------------------------------------------------------------------------------------------------------------
		case MENU_CALENDAR_0:
			//Original Stock Calendar
			ColorPickerDialog colorcalendar = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
																			mColor, mSelectedColorCal0, 5, Utils.isTablet(this)? ColorPickerDialog.SIZE_LARGE : ColorPickerDialog.SIZE_SMALL);
			
			//Implement listener to get selected color value
			colorcalendar.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener(){

				@Override
				public void onColorSelected(int color) {
					mSelectedColorCal0=color;
					item.colorSquare=color;
					mAdapter.notifyDataSetChanged();
				}
				
			});
			colorcalendar.show(getFragmentManager(),"cal");
			break;
		//-------------------------------------------------------------------------------------------------------------	
		}

		if (intent != null)
			startActivity(intent);
	}
	
	
	/**
	 * Build Menu List
	 * 
	 */
	private void _initMenu() {
		mAdapter = new NsMenuAdapter(this);

		//Read preferences to get selected Color
		SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
		if (shared!=null){
			mSelectedColorDash0= shared.getInt("dash_colorkey", 0);
		}
		
		//-------------------------------------------------------------------------------------
		// Dashclock
		//-------------------------------------------------------------------------------------

		// Add Header
		mAdapter.addHeader(R.string.ns_menu_main_header_dash);
		// Add Dashclock items
		NsMenuItemModel mItem1 = new NsMenuItemModel(R.string.ns_menu_main_row_dash_original, mSelectedColorDash0, MENU_DASH_0);
		NsMenuItemModel mItem2 = new NsMenuItemModel(R.string.ns_menu_main_row_dash_dialog, mSelectedColorDash1, MENU_DASH_1);
		mAdapter.addItem(mItem1);
		mAdapter.addItem(mItem2);
		
		//-------------------------------------------------------------------------------------
		// Stock Calendar
		//-------------------------------------------------------------------------------------

		// Add Header
		mAdapter.addHeader(R.string.ns_menu_main_header_calendar);
		NsMenuItemModel mItem1c = new NsMenuItemModel(R.string.ns_menu_main_row_calendar_original, mSelectedColorCal0, MENU_CALENDAR_0);
		mAdapter.addItem(mItem1c);
		
		
		setListAdapter(mAdapter);
		getListView().setDividerHeight(0);
	}

}
