package it.gmariotti.android.example.colorpicker.dashclockpicker;

import it.gmariotti.android.example.colorpicker.R;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 *
 */
public class SettingsActivity extends PreferenceActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// In the simplified UI, fragments are not used at all and we instead
        // use the older PreferenceActivity APIs.

        // Add 'general' preferences.
        addPreferencesFromResource(R.xml.pref_dashcolor);
	}


}
