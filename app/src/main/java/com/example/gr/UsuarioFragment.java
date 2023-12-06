package com.example.gr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsuarioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private AlertDialog alertDialog;

    private TextView correoTextView;
    private Button editarContrasenaButton;
    private String mParam1;
    private String mParam2;

    public UsuarioFragment() {
        // Required empty public constructor
    }


    public static UsuarioFragment newInstance(String param1, String param2) {
        UsuarioFragment fragment = new UsuarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuario, container, false);

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance();

        // Obtener referencia a los elementos de la interfaz de usuario en tu diseño de fragment
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView correoTextView = view.findViewById(R.id.correoTextView);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button editarContrasenaButton = view.findViewById(R.id.editarContrasenaButton);
        // Obtener referencia al botón de cerrar sesión en el diseño de fragment
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button cerrarSesionButton = view.findViewById(R.id.cerrarSesionButton);

        // Obtener el usuario actualmente autenticado
        currentUser = auth.getCurrentUser();

        // Verificar si el usuario está autenticado
        if (currentUser != null) {
            // Mostrar el correo electrónico en el TextView
            correoTextView.setText("Correo electrónico: " + currentUser.getEmail());
        }

        // Configurar el onClickListener para el botón de editar contraseña
        editarContrasenaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitarContrasenaParaMostrarVentanaEmergente();
            }
        });
        // Configurar el onClickListener para el botón de cerrar sesión
        cerrarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });

        return view;
    }

    private void solicitarContrasenaParaMostrarVentanaEmergente() {
        // Crear un AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());


        // Inflar el diseño personalizado para el contenido del diálogo
        View popupView = getLayoutInflater().inflate(R.layout.editar_contra_popup, null);

        // Configurar referencias a elementos en el diseño de la ventana emergente

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText contrasenaActualEditText = popupView.findViewById(R.id.contrasenaActualEditText);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText nuevaContrasenaEditText = popupView.findViewById(R.id.nuevaContrasenaEditText);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button guardarButton = popupView.findViewById(R.id.guardarButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button cancelarButton = popupView.findViewById(R.id.cancelarButton);

        // Configurar el diseño personalizado en el diálogo
        builder.setView(popupView);
        // Crear y mostrar el AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Configurar el onClickListener para el botón de guardar en la ventana emergente
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contrasenaIngresada = contrasenaActualEditText.getText().toString();
                // Verificar la contraseña ingresada antes de mostrar la ventana emergente
                verificarContrasenaActualYMostrarVentanaEmergente(contrasenaIngresada, nuevaContrasenaEditText.getText().toString());
                alertDialog.dismiss();
            }
        });

        // Configurar el onClickListener para el botón de cancelar en la ventana emergente
        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar la ventana emergente sin guardar cambios
                alertDialog.dismiss();
            }
        });


    }


    private void verificarContrasenaActualYMostrarVentanaEmergente(String contrasenaIngresada, String nuevaContrasena) {
        // Crear credencial con el correo electrónico actual y la contraseña ingresada
        AuthCredential credential = EmailAuthProvider.getCredential(currentUser.getEmail(), contrasenaIngresada);

        // Reautenticar al usuario con la contraseña ingresada
        currentUser.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Reautenticación exitosa, mostrar la ventana emergente
                            cambiarContrasena(nuevaContrasena);
                            // Asegúrate de que alertDialog no sea nulo antes de intentar dismiss
                            if (alertDialog != null) {
                                alertDialog.dismiss();
                            }
                        } else {
                            // Reautenticación fallida, mostrar un mensaje al usuario
                            Toast.makeText(getActivity(), "Contraseña incorrecta. Inténtelo de nuevo.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void cambiarContrasena(String nuevaContrasena) {
        // Cambiar la contraseña del usuario actual
        currentUser.updatePassword(nuevaContrasena)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Contraseña actualizada exitosamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Error al actualizar" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    // Método para cerrar sesión
    private void cerrarSesion() {
        FirebaseAuth.getInstance().signOut();
        // Puedes redirigir al usuario a la pantalla de inicio de sesión o a donde desees
        startActivity(new Intent(getActivity(), InicioActivity.class));
        getActivity().finish();
    }
}