/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * You can find source here:
 * https://code.google.com/p/dashclock/source/browse/main/src/main/java/com/google/android/apps/dashclock/configuration/ColorPreference.java 
 */

package it.gmariotti.android.example.colorpicker.dashclockpicker;

import it.gmariotti.android.example.colorpicker.R;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ColorDialogFragmentSA extends DialogFragment {
	
    private ColorGridAdapter mAdapter;
    private GridView mColorGrid;
    private int[] mColorChoices = {};
    private int mValue = 0;
    private int mItemLayoutId = R.layout.dash_grid_item_color;
    private int mNumColumns = 5;

    public ColorDialogFragmentSA() {
    }

    public static ColorDialogFragmentSA newInstance() {
        return new ColorDialogFragmentSA();
    }

    public void setPreference() {
        tryBindLists();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        initValues();
        tryBindLists();
    }
    
    //Added
    private void initValues(){
    	
    	String[] color_array = getResources().getStringArray(R.array.default_color_choice_values);
    	
        if (color_array!=null && color_array.length>0) {
            mColorChoices = new int[color_array.length];
            for (int i = 0; i < color_array.length; i++) {
                mColorChoices[i] = Color.parseColor(color_array[i]);
            }
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View rootView = layoutInflater.inflate(R.layout.dash_dialog_colors, null);

        mColorGrid = (GridView) rootView.findViewById(R.id.color_grid);
        
        mColorGrid.setNumColumns(mNumColumns);
        
        mColorGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                    int position, long itemId) {
            			Toast.makeText(getActivity(), "Selected color="+mAdapter.getItem(position), Toast.LENGTH_LONG).show();
            		dismiss();
            }
        });

        tryBindLists();

        return new AlertDialog.Builder(getActivity())
                .setView(rootView)
                .create();
    }

    private void tryBindLists() {
        
        if (isAdded() && mAdapter == null) {
            mAdapter = new ColorGridAdapter();
        }

        if (mAdapter != null && mColorGrid != null) {
             mAdapter.setSelectedColor(mAdapter.getItem(2));  //USE This to select color
            mColorGrid.setAdapter(mAdapter);
        }
    }

    private class ColorGridAdapter extends BaseAdapter {
        private List<Integer> mChoices = new ArrayList<Integer>();
        private int mSelectedColor;

        private ColorGridAdapter() {
            for (int color : mColorChoices) {
                mChoices.add(color);
            }
        }

        @Override
        public int getCount() {
            return mChoices.size();
        }

        @Override
        public Integer getItem(int position) {
            return mChoices.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mChoices.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity())
                        .inflate(mItemLayoutId, container, false);
            }

            int color = getItem(position);
            setColorViewValue(convertView.findViewById(R.id.color_view), color);
            convertView.setBackgroundColor(color == mSelectedColor
                    ? 0x6633b5e5 : 0);

            return convertView;
        }

        public void setSelectedColor(int selectedColor) {
            mSelectedColor = selectedColor;
            notifyDataSetChanged();
        }
    }


	private static void setColorViewValue(View view, int color) {
	    if (view instanceof ImageView) {
	        ImageView imageView = (ImageView) view;
	        Resources res = imageView.getContext().getResources();
	
	        Drawable currentDrawable = imageView.getDrawable();
	        GradientDrawable colorChoiceDrawable;
	        if (currentDrawable != null && currentDrawable instanceof GradientDrawable) {
	            // Reuse drawable
	            colorChoiceDrawable = (GradientDrawable) currentDrawable;
	        } else {
	            colorChoiceDrawable = new GradientDrawable();
	            colorChoiceDrawable.setShape(GradientDrawable.OVAL);
	        }
	
	        // Set stroke to dark version of color
	        int darkenedColor = Color.rgb(
	                Color.red(color) * 192 / 256,
	                Color.green(color) * 192 / 256,
	                Color.blue(color) * 192 / 256);
	
	        colorChoiceDrawable.setColor(color);
	        colorChoiceDrawable.setStroke((int) TypedValue.applyDimension(
	                TypedValue.COMPLEX_UNIT_DIP, 1, res.getDisplayMetrics()), darkenedColor);
	        imageView.setImageDrawable(colorChoiceDrawable);
	
	    } else if (view instanceof TextView) {
	        ((TextView) view).setTextColor(color);
	    }
	}
}
