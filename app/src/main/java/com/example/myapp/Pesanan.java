package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Pesanan extends AppCompatActivity {

    EditText nPemesan, aPemesan;
    Button btnPesan;
    TextView ttlHarga;
    String hargaTotal = "harga";
    String totalHargaJava;

    DatabaseReference databaseReference;
    Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);

        nPemesan = findViewById(R.id.namaPemesan);
        aPemesan = findViewById(R.id.alamatPemesan);
        ttlHarga = findViewById(R.id.totalHarga);
        btnPesan = findViewById(R.id.pesan);

        Bundle bundle = getIntent().getExtras();
        totalHargaJava = bundle.getString(hargaTotal);
        ttlHarga.setText(totalHargaJava);

        order = new Order();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Data Pembeli");

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order.setNama(nPemesan.getText().toString());
                order.setAlamat(aPemesan.getText().toString());

                databaseReference.push().setValue(order);

                Intent intent = new Intent(Pesanan.this, landing.class);
                intent.putExtra("hargaPesan", ttlHarga.getText().toString());
                startActivity(intent);
            }
        });
    }
}