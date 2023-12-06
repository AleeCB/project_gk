package com.example.gr.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gr.model.Alarma;
import com.example.gr.R;

import java.util.List;

public class AlarmasAdapter extends RecyclerView.Adapter<AlarmasAdapter.AlarmaViewHolder> {

    private List<Alarma> listaAlarmas;
    private View.OnClickListener eliminarClickListener;

    public AlarmasAdapter(List<Alarma> listaAlarmas, View.OnClickListener eliminarClickListener) {
        this.listaAlarmas = listaAlarmas;
        this.eliminarClickListener = eliminarClickListener;
    }

    @NonNull
    @Override
    public AlarmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardview_alarm, parent, false);
        return new AlarmaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmaViewHolder holder, int position) {
        Alarma alarma = listaAlarmas.get(position);
        holder.textViewNombre.setText(alarma.getNombre());
        holder.textViewFechaHora.setText(alarma.getFechaHoraString());
        holder.btnEliminar.setTag(position);

        // Configurar el click listener para el botón de eliminar
        holder.btnEliminar.setOnClickListener(eliminarClickListener);
    }

    @Override
    public int getItemCount() {
        return listaAlarmas.size();
    }

    public class AlarmaViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNombre;
        public TextView textViewFechaHora;
        public Button btnEliminar;

        public AlarmaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewFechaHora = itemView.findViewById(R.id.textViewFechaHora);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
