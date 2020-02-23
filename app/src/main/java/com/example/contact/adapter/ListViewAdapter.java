package com.example.contact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.contact.R;
import com.example.contact.models.contact2;

import java.util.List;



public class ListViewAdapter extends ArrayAdapter<contact2> {

    private List<contact2> rankList;

    private Context context;

    public ListViewAdapter(List<contact2> rankList, Context context) {
        super(context, R.layout.list_item, rankList);
        this.rankList = rankList;
        this.context = context;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        TextView textViewNama= listViewItem.findViewById(R.id.textViewName);
        TextView textViewNomor= listViewItem.findViewById(R.id.textViewNomor);


        contact2 playerItem = rankList.get(position);

        textViewNama.setText(playerItem.getName());
        textViewNomor.setText(playerItem.getPhone());

        return listViewItem;
    }
}