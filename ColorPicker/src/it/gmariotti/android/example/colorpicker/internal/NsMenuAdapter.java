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
package it.gmariotti.android.example.colorpicker.internal;


import it.gmariotti.android.example.colorpicker.R;
import it.gmariotti.android.example.colorpicker.Utils;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * MenuAdapter
 * 
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 *
 */
public class NsMenuAdapter extends ArrayAdapter<NsMenuItemModel> {

    protected static final int TYPE_ROW=0;
    protected static final int TYPE_HEADER=1;

    
	public NsMenuAdapter(Context context) {
		super(context, 0);
	}

	public void addHeader(int title) {
		add(new NsMenuItemModel(title, -1, true,-1));
	}

	public void addItem(int title, int icon,int id) {
		add(new NsMenuItemModel(title, icon, false,id));
	}

	public void addItem(NsMenuItemModel itemModel) {
		add(itemModel);
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		return getItem(position).isHeader ? TYPE_HEADER : TYPE_ROW;
	}

	@Override
	public boolean isEnabled(int position) {
		return !getItem(position).isHeader;
	}

	//-----------------------------------------------------------------------------------------------
	
	public static class ViewHolder {
		public final TextView textHolder;
		//public final ImageView imageHolder;
		public final CalendarColorSquare squareHolder;

		public ViewHolder(TextView text1, CalendarColorSquare squareHolder) {
			this.textHolder = text1;
			//this.imageHolder = image1;
			this.squareHolder=squareHolder;
		}
	}

	public static class ViewHolderHeader {
	
		public final TextView textHolder;
		
		public ViewHolderHeader(TextView text1) {
			this.textHolder = text1;
		}
	}
	
	//-----------------------------------------------------------------------------------------------
		
	
	public View getView(int position, View convertView, ViewGroup parent) {

		NsMenuItemModel item = getItem(position);
		View view = convertView;

		 int viewType = this.getItemViewType(position);
	        switch (viewType){
	            case TYPE_HEADER:
	            	ViewHolderHeader viewHolderHeader=null;
	            	
	            	if (view != null) {
	            		Object obj= view.getTag();
	            		if (obj instanceof ViewHolderHeader){
	            			viewHolderHeader = (ViewHolderHeader) obj;
	            		}else{
	            			viewHolderHeader = null;
	            		}
	            	}
	            	
	            	if (view == null || viewHolderHeader==null){
	        			int layout = R.layout.ns_menu_row_header;
	        			view = LayoutInflater.from(getContext()).inflate(layout, null);

	        			TextView text1 = (TextView) view.findViewById(R.id.menurow_title);
	        			viewHolderHeader= new ViewHolderHeader(text1);
	        			view.setTag(viewHolderHeader);
	        		}
	            	
	            	if(item != null && viewHolderHeader != null){
	            		if (viewHolderHeader.textHolder != null)
	        				viewHolderHeader.textHolder.setText(item.title);
	            	}
	            	
	            	break;
	            	
	            case TYPE_ROW:
	            	ViewHolder viewHolder=null;
	            	
	            	if (view != null) {
	            		Object obj= view.getTag();
	            		if (obj instanceof ViewHolder){
	            			viewHolder = (ViewHolder) obj;
	            		}else{
	            			viewHolder = null;
	            		}
	            	}
	            	
	            	if (view == null || viewHolder==null){
	            		int layout = R.layout.ns_menu_row;
	            		view = LayoutInflater.from(getContext()).inflate(layout, null);
	            		
	            		TextView text1 = (TextView) view.findViewById(R.id.menurow_title);
	            		//ImageView imageView = (ImageView) view.findViewById(R.id.menurow_icon);
	            		CalendarColorSquare square1 = (CalendarColorSquare) view.findViewById(R.id.menurow_square);
	            		viewHolder = new ViewHolder(text1, square1);
	            		view.setTag(viewHolder);
	            		
	            	}
	            	
	            	
	            	if(item != null && viewHolder != null){
	            		if (viewHolder.textHolder != null)
	            			viewHolder.textHolder.setText(item.title);
	            		
	            		if (viewHolder.squareHolder != null) {
	        				if (item.colorSquare != 0) {
	        					//viewHolder.squareHolder.setVisibility(View.VISIBLE);
	        					viewHolder.squareHolder.setBackgroundColor(item.colorSquare);
	        				} else {
	        					//viewHolder.imageHolder.setVisibility(View.GONE);
	        					viewHolder.squareHolder.setBackgroundColor(Utils.ColorUtils.parseWhiteColor());
	        				}
	        			}
	            	}	
	            	
	            	break;
	            default:
	                //Throw exception, unknown data type
	        }
	    
	    return view;		
	}

}
