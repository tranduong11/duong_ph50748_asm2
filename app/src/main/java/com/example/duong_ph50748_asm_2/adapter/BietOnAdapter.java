package com.example.duong_ph50748_asm_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duong_ph50748_asm_2.R;
import com.example.duong_ph50748_asm_2.models.BietOn;

import java.util.ArrayList;

public class BietOnAdapter extends RecyclerView.Adapter<BietOnAdapter.HealthyViewHolder> {

    private Context context;
    private ArrayList<BietOn> listBietOn;

    public BietOnAdapter(Context context, ArrayList<BietOn> listBietOn) {
        this.context = context;
        this.listBietOn = listBietOn;
    }

    @NonNull
    @Override
    public HealthyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_item_tinhthan,parent,false);
        return new HealthyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthyViewHolder holder, int position) {
        BietOn bietOn=listBietOn.get(position);
        holder.tv_bieton.setText(bietOn.getLoiBietOn());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                UsersDAO usersDAO=new UsersDAO(context);
//                boolean check=usersDAO.deleteDataBietOn(bietOn.getIdbieton());
//                if (check){
//                    listBietOn.set(position,bietOn);
//                    notifyItemRemoved(position);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (listBietOn!=null){
            return listBietOn.size();
        }
        return 0;
    }

    public class HealthyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_bieton;
        public HealthyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_bieton=itemView.findViewById(R.id.tv_bieton);
        }
    }
}
