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
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 
 * Only for Menu
 * 
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 *
 */
public class MainActivity extends ListActivity {

	//---------------------------------------------------------------
	//Only for Menu
	private NsMenuAdapter mAdapter;
	
	private String[] menuItems;
	private static final int MENU_DASH_0=0;
	private static final int MENU_DASH_1=1;
	private static final int MENU_DASH_2=2;
	private static final int MENU_CALENDAR_0=100;
	//---------------------------------------------------------------
	
	//Selected color
	private int mSelectedColorDash0= 0;
	private int mSelectedColorDash1= 0;
	private int mSelectedColorCal0= 0;
	
	//---------------------------------------------------------------
	
	@Deprecated
	public static final String[] options = { "Scenario 1: Color picker from Dashclock",
			"Scenario 2: ColorPreference from DashClock",
			"Scenario 3: Color picker from Stock Calendar", };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setListAdapter(new ArrayAdapter<String>(this,
		//		android.R.layout.simple_list_item_1, options));
		_initMenu();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		NsMenuItemModel item = mAdapter.getItem(position);
		
		if (item==null) return;
		Intent intent = null;
		int[] mColor= Utils.colorChoice(this);
		
		switch (item._id) {
		default:
		case MENU_DASH_0:
			intent = new Intent(this, SettingsActivity.class);
			break;
		case MENU_DASH_1:
			ColorPickerDialogDash colordashfragment = ColorPickerDialogDash.newInstance(R.string.color_picker_default_title,mColor,mSelectedColorDash1,5);
			
			//Implement listener to get color value
			colordashfragment.setOnColorSelectedListener(new ColorPickerDialogDash.OnColorSelectedListener(){

				@Override
				public void onColorSelected(int color) {
					mSelectedColorDash1=color;	
				}
				
			});
			colordashfragment.show(getFragmentManager(), "dash");
			break;
		
		case MENU_CALENDAR_0:
			ColorPickerDialog colorcalendar = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
																			mColor, mSelectedColorCal0, 5, Utils.isTablet(this)? ColorPickerDialog.SIZE_LARGE : ColorPickerDialog.SIZE_SMALL);
			
			//Implement listener to get color value
			colorcalendar.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener(){

				@Override
				public void onColorSelected(int color) {
					mSelectedColorCal0=color;
					
				}
				
			});
			colorcalendar.show(getFragmentManager(),"cal");
			break;
			
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

		// Add Header
		mAdapter.addHeader(R.string.ns_menu_main_header_dash);
		// Add Dashclock items
		NsMenuItemModel mItem1 = new NsMenuItemModel(R.string.ns_menu_main_row_dash_original, -1, MENU_DASH_0);
		NsMenuItemModel mItem2 = new NsMenuItemModel(R.string.ns_menu_main_row_dash_dialog, -1, MENU_DASH_1);
		mAdapter.addItem(mItem1);
		mAdapter.addItem(mItem2);
		
		// Add Header
		mAdapter.addHeader(R.string.ns_menu_main_header_calendar);
		NsMenuItemModel mItem1c = new NsMenuItemModel(R.string.ns_menu_main_row_calendar_original, -1, MENU_CALENDAR_0);
		mAdapter.addItem(mItem1c);
		
		setListAdapter(mAdapter);
		getListView().setDividerHeight(0);
	}

	

}
