package com.myapp11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class DasBoardt extends AppCompatActivity {

    private Button buttonCarta, buttonPedidos, buttonSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_das_boardt);

        buttonCarta = findViewById(R.id.button7);
        buttonPedidos = findViewById(R.id.button8);
        buttonSalir = findViewById(R.id.button16);

        buttonCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DasBoardt.this, CartaActivity.class);
                startActivity(intent);
            }
        });

        buttonPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DasBoardt.this, PedidosActivity.class);
                startActivity(intent);
            }
        });

        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cerrar sesión y regresar al inicio
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(DasBoardt.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // No hacer nada o mostrar un mensaje si lo prefieres
        // Por ejemplo, puedes mostrar un mensaje de confirmación si el usuario quiere salir de la aplicación
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
