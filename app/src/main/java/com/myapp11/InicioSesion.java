package com.myapp11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class InicioSesion extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnIniciarSesion;
    private FirebaseAuth mAuth;
    private Button btnVolverInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio_sesion);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Referenciar vistas
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        btnIniciarSesion = findViewById(R.id.btn_Iniciar_sesion);
        btnVolverInicio = findViewById(R.id.btn_Volver_inicio);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                // Validar campos
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(InicioSesion.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Iniciar sesión con Firebase Authentication
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(InicioSesion.this, task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(InicioSesion.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                                // Redirigir al dashboard
                                startActivity(new Intent(InicioSesion.this, DasBoardt.class));
                                finish();
                            } else {
                                Toast.makeText(InicioSesion.this, "Error en la autenticación", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btnVolverInicio.setOnClickListener(v -> {
            // Regresar a la pantalla anterior
            finish();
        });
    }
}
