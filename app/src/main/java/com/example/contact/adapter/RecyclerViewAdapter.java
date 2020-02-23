package com.example.contact.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.contact.AwalActivity;
import com.example.contact.R;
import com.example.contact.models.contact2;

import java.util.List;

/**
 * Created by Aws on 11/03/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<contact2> mData ;


    public RecyclerViewAdapter(Context mContext, List<contact2> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
//        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.cardview_row_item,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(mContext, AwalActivity.class);
                i.putExtra("nomor",mData.get(viewHolder.getAdapterPosition()).getPhone());

                mContext.startActivity(i);

            }
        });




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tvName.setText(mData.get(position).getName());
        holder.tvNomor.setText(mData.get(position).getPhone());

        // Load Image from the internet and set it into Imageview using Glide

//        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.img_thumbnail);



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName ;
        TextView tvNomor;
        LinearLayout view_container;



        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tvName = itemView.findViewById(R.id.tvName);
            tvNomor= itemView.findViewById(R.id.tvNomor);

        }
    }

}
