# Color Picker collection

This repository provides a simple collection of Color Picker.

---

## Color Picker from Stock Calendar

You can find source code in :
[Stock Calendar Color Picker](https://android.googlesource.com/platform/frameworks/opt/colorpicker/)

![StockPicker](https://github.com/gabrielemariotti/colorpickercollection/raw/master/ColorPicker/images/stock.png)


### Usage

You can find an example in `MainActivity`:
``` java

  ColorPickerDialog colorcalendar = ColorPickerDialog.newInstance(
              R.string.color_picker_default_title, 
              mColor, 0, 5,
              Utils.isTablet(this)? ColorPickerDialog.SIZE_LARGE : ColorPickerDialog.SIZE_SMALL);
  colorcalendar.show(getFragmentManager(),"cal");
```


---

## Color Picker from Dashclock 

You can find source code in:
[Dashclock Color Picker source](https://code.google.com/p/dashclock/source/browse/main/src/main/java/com/google/android/apps/dashclock/configuration/ColorPreference.java)

![DashPicker](https://github.com/gabrielemariotti/colorpickercollection/raw/master/ColorPicker/images/dashDialog.png)

### Usage

You can find an example in `MainActivity`:
``` java

  ColorDialogFragmentSA colordashfragment = ColorDialogFragmentSA.newInstance();
  colordashfragment.show(getFragmentManager(), "dash");

```

You can find also, an example of `PreferenceColor` in `dashclockpicker.dashclockSettingsActivity`

![DashPicker](https://github.com/gabrielemariotti/colorpickercollection/raw/master/ColorPicker/images/dash_prefs.png)
