package com.example.gr.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gr.model.MainModelinicio;
import com.example.gr.R;
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



        holder.img.setTag(position);

        // Configurar el evento de clic en la imagen
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la posición del ítem desde la etiqueta
                int clickedPosition = (int) v.getTag();

                // Llamar a un método para manejar la navegación según la posición
                navigateToFragment(clickedPosition,holder);
            }
        });
    }
    private void navigateToFragment(int position,MyViewHolder holder) {
        switch (position) {
            case 0:
                // Navegar al primer fragmento
                Navigation.findNavController(holder.img).navigate(R.id.action_inicioFragment_to_suculentasFragment);
                break;
            case 1:
                // Navegar al segundo fragmento
                Navigation.findNavController(holder.img).navigate(R.id.action_inicioFragment_to_follageFragment);
                break;
            case 2:
                // Navegar al segundo fragmento
                Navigation.findNavController(holder.img).navigate(R.id.action_inicioFragment_to_floracionFragment);
                break;
            case 3:
                // Navegar al segundo fragmento
                Navigation.findNavController(holder.img).navigate(R.id.action_inicioFragment_to_vegetalesFragment);
                break;
            case 4:
                // Navegar al segundo fragmento
                Navigation.findNavController(holder.img).navigate(R.id.action_inicioFragment_to_grandesFragment);
                break;
            case 5:
                // Navegar al segundo fragmento
                Navigation.findNavController(holder.img).navigate(R.id.action_inicioFragment_to_colgantesFragment);
            default:
                // Manejar caso predeterminado o no hacer nada
                break;
        }
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