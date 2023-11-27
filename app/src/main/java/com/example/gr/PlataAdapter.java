package com.example.gr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.text.DecimalFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlataAdapter extends FirebaseRecyclerAdapter<PlantaModel, PlataAdapter.MyViewHolder> {

    public PlataAdapter(@NonNull FirebaseRecyclerOptions<PlantaModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull PlantaModel model) {

        DecimalFormat format = new DecimalFormat("0");
        holder.nombre.setText(model.getNombre());
        holder.loca.setText(model.getLocalizacion());
        holder.tipo.setText(model.getTipo());
        holder.maceta.setText( format.format(model.getMacetas()));


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pet_single, parent, false);
        return new MyViewHolder(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, loca, tipo, maceta;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre =  (TextView) itemView.findViewById(R.id.nombreP);
            loca =  (TextView) itemView.findViewById(R.id.loP);
            tipo =  (TextView) itemView.findViewById(R.id.tipoP);
            maceta =  (TextView) itemView.findViewById(R.id.maP);


        }
    }
}
