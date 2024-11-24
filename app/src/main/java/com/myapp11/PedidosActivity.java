package com.myapp11;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import com.myapp11.model.Pedido;

import java.util.ArrayList;

public class PedidosActivity extends AppCompatActivity {

    private ListView listViewPedidos;
    private ArrayList<String> listaPedidos;
    private ArrayAdapter<String> adapter;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference pedidosRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pedidos);

        listViewPedidos = findViewById(R.id.listViewPedidos);
        listaPedidos = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPedidos);
        listViewPedidos.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        pedidosRef = mDatabase.getReference("Pedidos");

        cargarPedidos();
    }

    private void cargarPedidos() {
        String userId = mAuth.getCurrentUser().getUid();

        pedidosRef.orderByChild("userId").equalTo(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                listaPedidos.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Pedido pedido = dataSnapshot.getValue(Pedido.class);
                    String infoPedido = "Producto: " + pedido.producto + "\nPrecio: $" + pedido.precio;
                    listaPedidos.add(infoPedido);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Manejar error
            }
        });
    }
}
