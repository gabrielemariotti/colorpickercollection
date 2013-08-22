# Color Picker collection

This repository provides a simple collection of Color Picker.

* [x] Color Picker from Stock Calendar
* [x] Color Picker from Dashclock

---

## Color Picker from Stock Calendar

You can find source code in :
[Stock Calendar Color Picker](https://android.googlesource.com/platform/frameworks/opt/colorpicker/)

![StockPicker](https://github.com/gabrielemariotti/colorpickercollection/raw/master/ColorPicker/images/stock.png)


### Usage

You can find an example in `MainActivity`:
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

## Color Picker from Dashclock 

You can find source code in:
[Dashclock Color Picker source](https://code.google.com/p/dashclock/source/browse/main/src/main/java/com/google/android/apps/dashclock/configuration/ColorPreference.java)

![DashPicker](https://github.com/gabrielemariotti/colorpickercollection/raw/master/ColorPicker/images/dashDialog.png)

### Usage

You can find an example of original `PreferenceColor` in `dashclockpicker.dashclockSettingsActivity`.

You can find an example of Custom ColorDialogPicker in `MainActivity`:
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
