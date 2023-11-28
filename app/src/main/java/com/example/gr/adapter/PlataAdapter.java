package com.example.gr.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gr.R;
import com.example.gr.model.PlantaModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.HashMap;
import java.util.Map;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.ViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlataAdapter extends FirebaseRecyclerAdapter<PlantaModel, PlataAdapter.MyViewHolder> {

    public PlataAdapter(@NonNull FirebaseRecyclerOptions<PlantaModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull PlantaModel model) {


        holder.nombre.setText(model.getNombre());
        holder.loca.setText(model.getLocalizacion());
        holder.maceta.setText(model.getMacetas());
        holder.tipo.setText(model.getTipo());


        // Cargar la imagen desde Firebase Storage usando Glide
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        // Construir la URL completa de la imagen


        Glide.with(holder.imageUrl.getContext())
                .load(model.getImageUrl)// URL de la imagen desde la base de datos
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imageUrl);


        // botones de editar y eliminar
        // botones de editar y eliminar
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // lo siguiente hace que se muestre el update_popup al presionar el botón editar
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imageUrl.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true, 1200)
                        .create();

                // esto es solo para ver que realmente funciona dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.txtNombre);
                EditText loca = view.findViewById(R.id.txtLoca);
                EditText tipo = view.findViewById(R.id.txtTipo);
                EditText macetas = view.findViewById(R.id.txtMacetas);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                // setear los valores obtenidos de la base de datos a los campos
                name.setText(model.getNombre());
                loca.setText(model.getLocalizacion());
                tipo.setText(model.getTipo());
                macetas.setText(model.getMacetas());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("nombre", name.getText().toString());
                        map.put("localizacion", loca.getText().toString());
                        map.put("tipo", tipo.getText().toString());
                        map.put("macetas", macetas.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("miPlanta")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.nombre.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.nombre.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.nombre.getContext());
                builder.setTitle("¿Está seguro?");
                builder.setMessage("Los datos eliminados no se pueden deshacer.");

                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("miPlanta")
                                .child(getRef(position).getKey()).removeValue();
                        //Toast.makeText(holder.name.getContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.nombre.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pet_single, parent, false);
        return new MyViewHolder(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, loca, tipo, maceta;
        CircleImageView imageUrl;

        Button btnEdit, btnDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre =  (TextView) itemView.findViewById(R.id.nombreP);
            loca =  (TextView) itemView.findViewById(R.id.loP);
            tipo =  (TextView) itemView.findViewById(R.id.tipoP);
            maceta =  (TextView) itemView.findViewById(R.id.maP);
            imageUrl =  (CircleImageView) itemView.findViewById(R.id.img1);
            // botones para editar y borrar
            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);

        }
    }
}
