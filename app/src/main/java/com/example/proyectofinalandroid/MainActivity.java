package com.example.proyectofinalandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etCorreo, etContraseña, nombreApellido, edad, genero, telefono;
    private Button btnAceptar, btAceptar, btCancelar, botonsiguiente, botonaceptar, botonAceptar3, botonAceptar;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Establecer márgenes para evitar superposición con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        databaseHelper = new DatabaseHelper(this);

        etCorreo = findViewById(R.id.Epcorreo);
        etContraseña = findViewById(R.id.Epcontraseña);
        nombreApellido = findViewById(R.id.Epnombre_apellido);
        edad = findViewById(R.id.Epedad);
        genero = findViewById(R.id.Epgenero);
        telefono = findViewById(R.id.Eptelefono);

        btnAceptar = findViewById(R.id.Btaceptar);
        btAceptar = findViewById(R.id.BtAceptar);
        btCancelar = findViewById(R.id.Btcancelar);
        botonsiguiente = findViewById(R.id.btsiguiente1);
        botonaceptar = findViewById(R.id.btaceptar1);
        botonAceptar3 = findViewById(R.id.btaceptar3);
        botonAceptar = findViewById(R.id.Btaceptar6);

        btnAceptar.setOnClickListener(v -> {
            String correo = etCorreo.getText().toString().trim();
            String contraseña = etContraseña.getText().toString().trim();

            if (correo.isEmpty() || correo.contains(" ") || contraseña.isEmpty() || contraseña.contains(" ")) {
                Toast.makeText(MainActivity.this, "No se permiten espacios en blanco", Toast.LENGTH_SHORT).show();
            } else {
                boolean userExists = databaseHelper.checkUser(correo, contraseña);
                if (userExists) {
                    Toast.makeText(MainActivity.this, "Inicio de sesión aprobado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btAceptar.setOnClickListener(v -> {
            if (validateInputs()) {
                Toast.makeText(MainActivity.this, "Creación de usuario exitosa", Toast.LENGTH_SHORT).show();
                clearInputs();
            }
        });

        btCancelar.setOnClickListener(v -> {
            // Lógica de cancelación si es necesario
        });

        botonsiguiente.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, menu.class);
            startActivity(intent);
        });

        botonaceptar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, inicio_de_seccion.class);
            startActivity(intent);
        });

        botonAceptar3.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, creacion_usuario.class);
            startActivity(intent);
        });

        botonAceptar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, lista_de_datos.class);
            startActivity(intent);
        });

        Toast.makeText(this, "apk de contactos iniciada", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Bienvenido usuario", Toast.LENGTH_SHORT).show();

        // Agregar un usuario de ejemplo a la base de datos
        databaseHelper.addUser("test@example.com", "password123");
    }

    private boolean validateInputs() {
        if (TextUtils.isEmpty(nombreApellido.getText().toString().trim())) {
            nombreApellido.setError("Nombre y Apellido es requerido");
            return false;
        }
        if (TextUtils.isEmpty(edad.getText().toString().trim())) {
            edad.setError("Edad es requerida");
            return false;
        }
        if (TextUtils.isEmpty(genero.getText().toString().trim())) {
            genero.setError("Género es requerido");
            return false;
        }
        if (TextUtils.isEmpty(telefono.getText().toString().trim())) {
            telefono.setError("Teléfono es requerido");
            return false;
        }
        if (TextUtils.isEmpty(etCorreo.getText().toString().trim())) {
            etCorreo.setError("Correo electrónico es requerido");
            return false;
        }
        if (TextUtils.isEmpty(etContraseña.getText().toString().trim())) {
            etContraseña.setError("Contraseña es requerida");
            return false;
        }
        return true;
    }

    private void clearInputs() {
        nombreApellido.setText("");
        edad.setText("");
        genero.setText("");
        telefono.setText("");
        etCorreo.setText("");
        etContraseña.setText("");
    }
}