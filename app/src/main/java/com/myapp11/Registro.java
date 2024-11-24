package com.myapp11;

import android.content.Intent; // Asegúrate de importar esta clase
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.myapp11.model.User;

public class Registro extends AppCompatActivity {

    private EditText inputUserId, inputUserName, inputUserLastName, inputUserEmail, inputUserPhone, inputUserPassword;
    private Button btnRegistrar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        // Inicializar Firebase
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        // Referenciar vistas
        inputUserId = findViewById(R.id.input_user_id);
        inputUserName = findViewById(R.id.input_user_name);
        inputUserLastName = findViewById(R.id.input_user_lastname);
        inputUserEmail = findViewById(R.id.input_user_email);
        inputUserPhone = findViewById(R.id.input_user_phone);
        inputUserPassword = findViewById(R.id.input_user_password);
        btnRegistrar = findViewById(R.id.btn_Registrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = inputUserEmail.getText().toString().trim();
                final String password = inputUserPassword.getText().toString().trim();

                // Validar campos
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Registro.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear usuario en Firebase Authentication
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Registro.this, task -> {
                            if (task.isSuccessful()) {
                                // Obtener UID del usuario
                                String userId = mAuth.getCurrentUser().getUid();

                                // Crear objeto usuario
                                User user = new User(
                                        inputUserId.getText().toString(),
                                        inputUserName.getText().toString(),
                                        inputUserLastName.getText().toString(),
                                        email,
                                        inputUserPhone.getText().toString()
                                );

                                // Guardar información adicional en Realtime Database
                                mDatabase.getReference("Users").child(userId).setValue(user)
                                        .addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                Toast.makeText(Registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                                // Redirigir a la actividad Dashboard
                                                Intent intent = new Intent(Registro.this, DasBoardt.class);
                                                startActivity(intent);
                                                // Finalizar la actividad actual para evitar que el usuario pueda volver atrás
                                                finish();
                                            } else {
                                                Toast.makeText(Registro.this, "Error al registrar en la base de datos", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            } else {
                                // Mostrar mensaje de error detallado
                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(Registro.this, "Error de autenticación: " + errorMessage, Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}
