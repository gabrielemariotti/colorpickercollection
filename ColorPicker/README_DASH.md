## Color Picker from Dashclock 

You can find source code in:
[Dashclock Color Picker source](https://code.google.com/p/dashclock/source/browse/main/src/main/java/com/google/android/apps/dashclock/configuration/ColorPreference.java)

![DashPicker](https://github.com/gabrielemariotti/colorpickercollection/raw/master/ColorPicker/images/dashDialog.png)

### Usage

You can find an example of original `PreferenceColor` in `dashclockpicker.dashclockSettingsActivity`.

``` java
 Intent intent = new Intent(this, SettingsActivity.class);
 startActivity(intent);
 
 
 public class SettingsActivity extends PreferenceActivity {

 	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
        // Add 'general' preferences.
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

![DashPicker](https://github.com/gabrielemariotti/colorpickercollection/raw/master/ColorPicker/images/dash_prefs.png)
