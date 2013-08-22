# Color Picker collection

This repository provides a simple collection of Color Picker.

* **Color Picker from Stock Calendar**
* **Color Picker from Dashclock**

![DashPicker](https://github.com/gabrielemariotti/colorpickercollection/raw/master/ColorPicker/images/allscreen.png) 

For more detailed information you read this [document:](https://github.com/gabrielemariotti/colorpickercollection/tree/master/ColorPicker/README.md)

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

You can also find an example of original `PreferenceColor` in `dashclockpicker.dashclockSettingsActivity`.

![DashPicker](https://github.com/gabrielemariotti/colorpickercollection/raw/master/ColorPicker/images/dash_prefs.png)

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
    

