package com.example.dailybible3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter  extends ArrayAdapter<ListViewDataModel> implements View.OnClickListener {

    private ArrayList<ListViewDataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView subtitle;
        TextView subtitle_text;
    }

    public ListViewAdapter(ArrayList<ListViewDataModel> data, Context context) {
        super(context, R.layout.list_view_items, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        ListViewDataModel dataModel = (ListViewDataModel) object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ListViewDataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_view_items, parent, false);
            viewHolder.subtitle = (TextView) convertView.findViewById(R.id.text);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.subtitle.setText(dataModel.getNote());

        // Return the completed view to render on screen
        return convertView;
    }

}
