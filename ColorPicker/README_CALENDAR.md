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
src/calendarstock/*
res/drawable/calendar_*.xml
res/drawable-hpdi/ic_colorpicker_swatch_selected.png
res/drawable-mpdi/ic_colorpicker_swatch_selected.png
res/drawable-xpdi/ic_colorpicker_swatch_selected.png
res/layout/calendar_*.xml
res/values/calendar_*.xml

