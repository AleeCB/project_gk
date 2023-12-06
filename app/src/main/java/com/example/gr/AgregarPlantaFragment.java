package com.example.gr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.HashMap;
import java.util.Map;

public class AgregarPlantaFragment extends DialogFragment {

    Button btn_add, btn_add_image;
    EditText nombre, loc, tipo, cMacetas;
    ImageView imageView;
    private DatabaseReference mDatabase;
    private StorageReference mStorageRef;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inicializar Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("miPlanta");
        mStorageRef = FirebaseStorage.getInstance().getReference("imagenes_planta");
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_agregar_planta, container, false);

        nombre = v.findViewById(R.id.nombre);
        loc = v.findViewById(R.id.localizacion);
        tipo = v.findViewById(R.id.tipo);
        cMacetas = v.findViewById(R.id.cuanMacentas);
        imageView = v.findViewById(R.id.imageView);
        btn_add = v.findViewById(R.id.btn_add);
        btn_add_image = v.findViewById(R.id.btn_add_image);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreP = nombre.getText().toString().trim();
                String localiP = loc.getText().toString().trim();
                String tipoP = tipo.getText().toString().trim();
                String cMacetasP = cMacetas.getText().toString().trim();

                if (nombreP.isEmpty() || localiP.isEmpty() || tipoP.isEmpty() || cMacetasP.isEmpty() || mImageUri == null) {
                    Toast.makeText(getContext(), "Ingresar todos los datos y seleccionar una imagen", Toast.LENGTH_SHORT).show();
                } else {
                    try {

                        uploadImageAndPostPet(nombreP, localiP, tipoP, cMacetasP);
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Error al ingresar el número de macetas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
                btn_add_image.setVisibility(View.GONE); // Ocultar el botón después de hacer clic
            }
        });


        return v;
    }

    private void uploadImageAndPostPet(final String nombreP, final String localiP, final String tipoP, final String cMacetasP) {
        // Crear una referencia única para la imagen
        final StorageReference imageRef = mStorageRef.child(System.currentTimeMillis() + ".jpg");

        // Sube la imagen al Storage
        imageRef.putFile(mImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // La imagen se ha subido
                        // obtener  la URL de la imagen
                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri downloadUri) {
                                // se almacena en "downloadUri" contiene la URL de la imagen
                                String imageUrl = downloadUri.toString();
                                postPetConImagen(nombreP, localiP, tipoP, cMacetasP, imageUrl);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // fallo al subir la imagen
                        Toast.makeText(getContext(), "Error al subir la imagen", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void postPetConImagen(String nombreP, String localiP, String tipoP, String cMacetasP, String imageUrl) {
        // Crea un mapa de datos con la URL de la imagen
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombreP);
        map.put("localizacion", localiP);
        map.put("tipo", tipoP);
        map.put("macetas", cMacetasP);
        map.put("imageUrl", imageUrl);

        // Agregar datos a Realtime Database
        mDatabase.push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                        getDialog().dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            imageView.setImageURI(mImageUri);
            btn_add_image.setVisibility(View.GONE); // Ocultar el botón después de seleccionar la imagen

        }
    }
}