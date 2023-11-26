package com.example.gr;

import android.content.Intent;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapterInicio extends FirebaseRecyclerAdapter<MainModelinicio, MainAdapterInicio.MyViewHolder> {

    public MainAdapterInicio(@NonNull FirebaseRecyclerOptions<MainModelinicio> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull MainModelinicio model) {
        holder.nombre.setText(model.getNombre());



        // Glide para cargar la imagen desde la base de datos en lugar de la URL
        Glide.with(holder.img.getContext())
                .load(model.getImagen())// URL de la imagen desde la base de datos
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);



        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_inicioFragment_to_suculentasFragment
                );
            }
        })

        ;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new MyViewHolder(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView nombre;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img =  (CircleImageView) itemView.findViewById(R.id.img1);
            nombre =  (TextView) itemView.findViewById(R.id.nombretext);

        }
    }
}
