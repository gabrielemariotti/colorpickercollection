## Color Picker from Stock Calendar

You can find original source code in :
[Stock Calendar Color Picker](https://android.googlesource.com/platform/frameworks/opt/colorpicker/)

![StockPicker](https://github.com/gabrielemariotti/colorpickercollection/raw/master/ColorPicker/images/stock.png)


### Usage

You can find an example of `ColorPickerDialog` in `MainActivity`:
It uses original code.

``` java

  ColorPickerDialog colorcalendar = ColorPickerDialog.newInstance(
              R.string.color_picker_default_title, 
              mColor,
              mSelectedColorCal0,
              5,
              Utils.isTablet(this)? ColorPickerDialog.SIZE_LARGE : ColorPickerDialog.SIZE_SMALL);
              
  //Implement listener to get selected color value
  colorcalendar.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener(){

				@Override
				public void onColorSelected(int color) {
					mSelectedColorCal0=color;
				}
				
	});
	
  colorcalendar.show(getFragmentManager(),"cal");
```

---

You can find an example of custom `ColorPickerPreference` in `calendarstock.SettingsPickerFragment`.
``` java
 Intent intent = new Intent(this, SettingsPickerActivity.class);
 startActivity(intent);
 
 public class SettingsPickerActivity extends PreferenceActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	       	getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SettingsPickerFragment()).commit();
	}
 }
 
 public class SettingsPickerFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Add  preferences.
        	addPreferencesFromResource(R.xml.pref_calendarcolor);
	}
}

```

``` xml
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto" >

    <PreferenceCategory android:title="@string/pref_config" >
        <it.gmariotti.android.example.colorpicker.calendarstock.ColorPickerPreference
            android:defaultValue="@android:color/white"
            android:key="calendar_colorkey"
            android:negativeButtonText="@null"
            android:positiveButtonText="@null"
            android:summary="Preference summary"
            android:title="Preference Title"
            app:cal_itemLayout="@layout/calendar_grid_item_color"
            app:cal_numColumns="5" />
    </PreferenceCategory>

</PreferenceScreen>
```
![StockPicker](https://github.com/gabrielemariotti/colorpickercollection/raw/master/ColorPicker/images/stock_prefs.png)

---

You can find `Utils.ColorUtils` some useful methods to manage colors.

To create an array of color you can use:
``` java
  Utils.ColorUtils.colorChoice(Context context)
```

It uses this array in dash_colors.xml.
You can easily change this list.
``` xml
 <string-array name="default_color_choice_values" translatable="false">
        <item>#33b5e5</item>
        <item>#aa66cc</item>
        <item>#99cc00</item>
        <item>#ffbb33</item>
        <item>#ff4444</item>
        <item>#0099cc</item>
        <item>#9933cc</item>
        <item>#669900</item>
        <item>#ff8800</item>
        <item>#cc0000</item>
        <item>#ffffff</item>
        <item>#eeeeee</item>
        <item>#cccccc</item>
        <item>#888888</item>
    </string-array>
```

### Files
src/calendarstock/*<br/>
res/drawable/calendar_*.xml<br/>
res/drawable-hpdi/ic_colorpicker_swatch_selected.png<br/>
res/drawable-mpdi/ic_colorpicker_swatch_selected.png<br/>
res/drawable-xpdi/ic_colorpicker_swatch_selected.png<br/>
res/layout/calendar_*.xml<br/>
res/values/calendar_*.xml<br/>

Credits
-------

Author: Gabriele Mariotti (gabri.mariotti@gmail.com)

License
-------

    Copyright 2013 Gabriele Mariotti

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
