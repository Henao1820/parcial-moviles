package com.myapp11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.myapp11.model.Pedido;

public class CartaActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private Button buyButton1, buyButton2, buyButton3, buyButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.carta);

        // Inicializar Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        // Referenciar botones
        buyButton1 = findViewById(R.id.buyButton1);
        buyButton2 = findViewById(R.id.buyButton2);
        buyButton3 = findViewById(R.id.buyButton3);
        buyButton4 = findViewById(R.id.buyButton4);

        buyButton1.setOnClickListener(view -> realizarCompra("Exotic", 25000));
        buyButton2.setOnClickListener(view -> realizarCompra("Apanada", 30000));
        buyButton3.setOnClickListener(view -> realizarCompra("Bacon Burger", 32000));
        buyButton4.setOnClickListener(view -> realizarCompra("Veggie Burger", 18000));
    }

    private void realizarCompra(String producto, int precio) {
        String userId = mAuth.getCurrentUser().getUid();

        // Crear objeto pedido
        Pedido pedido = new Pedido(userId, producto, precio);

        // Guardar pedido en Realtime Database
        mDatabase.getReference("Pedidos").push().setValue(pedido)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(CartaActivity.this, "Compra realizada", Toast.LENGTH_SHORT).show();
                        // Redirigir al apartado de pedidos
                        startActivity(new Intent(CartaActivity.this, PedidosActivity.class));
                    } else {
                        Toast.makeText(CartaActivity.this, "Error al realizar la compra", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
