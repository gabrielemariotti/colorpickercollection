## Color Picker from Dashclock 

You can find source code in:
[Dashclock Color Picker source](https://code.google.com/p/dashclock/source/browse/main/src/main/java/com/google/android/apps/dashclock/configuration/ColorPreference.java)

![DashPicker](https://github.com/gabrielemariotti/colorpickercollection/raw/master/ColorPicker/images/dash_prefs.png)

### Usage

You can find an example of original `ColorPreference` in `dashclockpicker.SettingsDashFragment`.

``` java
 Intent intent = new Intent(this, SettingsActivity.class);
 startActivity(intent);
 
 
 public class SettingsActivity extends PreferenceActivity {

 	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SettingsDashFragment()).commit();
   }
 }
 
 public class SettingsDashFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref_dashcolor);
	}
 }
```

``` xml

<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res/it.gmariotti.android.example.colorpicker" >

    <PreferenceCategory android:title="@string/pref_config" >
        
         <it.gmariotti.android.example.colorpicker.dashclockpicker.ColorPreference
            android:key="dash_colorkey"
            android:title="Preference Title"
            android:summary="Preference summary"
            android:defaultValue="@android:color/white"
            android:negativeButtonText="@null"
            android:positiveButtonText="@null"
            app:numColumns="5"
            app:itemLayout="@layout/dash_grid_item_color"
         />
        
    </PreferenceCategory>

</PreferenceScreen>
```

---

You can find an example of Custom **ColorDialogPicker** in `MainActivity`:
``` java

  ColorPickerDialogDash colordashfragment = ColorPickerDialogDash.newInstance(
              R.string.color_picker_default_title,
              mColor,
              mSelectedColorDash1,
              5);
  
  //Implement listener to get color value
  colordashfragment.setOnColorSelectedListener(new ColorPickerDialogDash.OnColorSelectedListener(){

				@Override
				public void onColorSelected(int color) {
					mSelectedColorDash1=color;	
				}
				
	});        
	
  colordashfragment.show(getFragmentManager(), "dash");

```

![DashPicker](https://github.com/gabrielemariotti/colorpickercollection/raw/master/ColorPicker/images/dashDialog.png)

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
src/dashclockpicker/*<br/>
res/layout/dash_*.xml<br/>
res/values/dash_*.xml<br/>
res/xml/pref_dash*.xml<br/>

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
    

