package ru.diabalda.geocomrade.adapters;

import java.util.List;

import ru.diabalda.geocamrade.logic.Desire;
import ru.diabalda.geocomrade.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class DesiresAdapter extends ArrayAdapter<Desire>{
 
 public DesiresAdapter(Context context, List<Desire> list) {
  super(context, -1, list);
  
  mInflater = LayoutInflater.from(context);
 }

 @Override
 public View getView(int position, View convertView, ViewGroup parent) {
  ViewHolder holder;
  
  if (convertView == null) {
            convertView = mInflater.inflate(R.layout.desire_listview_item, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.desire_listview_item_textview);
            holder.checkbox = (CheckBox) convertView.findViewById(R.id.desire_listview_item_checkbox);
            
            convertView.setTag(holder);
        } else { 
            holder = (ViewHolder) convertView.getTag();
        }
  
  final Desire currentDesire = getItem(position); 
  
  holder.name.setText(currentDesire.getName());
  holder.checkbox.setChecked(currentDesire.isActive());
  
  holder.checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
   
   
   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    currentDesire.setActive(isChecked);
   }
  });
  
  return convertView;
 }

 class ViewHolder{
  TextView name;
  CheckBox checkbox;
 }
 
 private LayoutInflater mInflater;
}
