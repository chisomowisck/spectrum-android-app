package com.ujjwalagrawal.spectrum.profile.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.sip.SipSession;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;



import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.ujjwalagrawal.spectrum.R;

import com.ujjwalagrawal.spectrum.events.event_details.view.EventDetailActivity;
import com.ujjwalagrawal.spectrum.helper.image_loaders.GlideImageLoader;
import com.ujjwalagrawal.spectrum.helper.image_loaders.ImageLoader;
import com.ujjwalagrawal.spectrum.home.HomeActivity;
import com.ujjwalagrawal.spectrum.profile.model.EventsList;
import com.ujjwalagrawal.spectrum.profile.presenter.RegisterListPresenter;
import com.ujjwalagrawal.spectrum.profile.presenter.RegisterListPresenterImpl;
import com.ujjwalagrawal.spectrum.profile.provider.RetrofitRegisterListProvider;

import java.util.ArrayList;
import java.util.List;
import android.view.View;

/**
 * Created by Shubham on 17-01-2018.
 */

public class RegisterAdapter extends RecyclerView.Adapter<RegisterAdapter.MyViewHolder> {

    private List<EventsList> data  = new ArrayList<>();
    Context context;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    private ProfileFragment profileFragment;

    public RegisterAdapter(Context context, ProfileFragment profileFragment) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        imageLoader = new GlideImageLoader(context);
        this.profileFragment=profileFragment;
    }

    void setData(List<EventsList> data) {
        this.data = data;
    }

    @Override
    public RegisterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
       View view = layoutInflater.inflate(R.layout.event_register_card,parent,false);
       return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        final EventsList eventsList = data.get(position);
        holder.cardView.setRadius(17);

        holder.checklist.setClickable(false);
        holder.checklist.setEnabled(false);
        holder.event_name.setText(eventsList.getName());

        if (eventsList.getParticipated() == 1) {
            holder.checklist.setChecked(true);
            holder.checklist.setText("Registered");
            holder.checklist.setTextColor(context.getResources().getColor(R.color.md_green_800));

        } else {
            holder.checklist.setChecked(false);
            holder.checklist.setText("Register");
            holder.checklist.setTextColor(context.getResources().getColor(R.color.md_red_300));


        }

        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("CHANGE","afsasdf");
                profileFragment.changeParticipatedStatus(eventsList.getParticipated(),eventsList.getId());

                if(eventsList.getParticipated()==1){
                    Snackbar snackbar = Snackbar
                            .make(view, "Unregistered from "+eventsList.getName()+" ", Snackbar.LENGTH_SHORT)
                            .setAction("INFO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(context, EventDetailActivity.class);
                                    intent.putExtra("event_id",eventsList.getId());
                                    ((HomeActivity)context).startActivity(intent);
                                }
                            });
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.RED);
                    snackbar.show();
                }else {
                    Snackbar snackbar = Snackbar
                            .make(view, "Registered to "+eventsList.getName()+" ", Snackbar.LENGTH_SHORT)
                            .setAction("INFO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(context, EventDetailActivity.class);
                                    intent.putExtra("event_id",eventsList.getId());
                                    ((HomeActivity)context).startActivity(intent);
                                }
                            });
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.GREEN);

                    snackbar.show();
                }

            }
            }
        );



//        Toast.makeText(context,data.size()+"",Toast.LENGTH_SHORT).show();
//
//        TextView textView  = cardView.findViewById(R.id.event_name);
//        textView.setText("hello");
//        Switch swi =cardView.findViewById(R.id.switch_register);
//        textView.setText(eventsList.getName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView event_name;
        public Switch checklist;
        public CardView cardView;

        public MyViewHolder(View itemView){
            super(itemView);
            event_name= itemView.findViewById(R.id.event_name);
            checklist= itemView.findViewById(R.id.switch_register);
            cardView =itemView.findViewById(R.id.event_details);
        }
    }

}
