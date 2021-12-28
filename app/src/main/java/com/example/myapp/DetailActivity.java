package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    ImageView imageViewFotoMakanan;
    TextView textViewNamaMakanan;
    TextView textViewInfoMakanan;
    TextView textViewHargaMakanan;

    Button btnKurang, btnTambah, ordering;
    TextView tampilanJumlah;

    int angka = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageViewFotoMakanan = findViewById(R.id.imageViewFotoMakanan);
        textViewNamaMakanan = findViewById(R.id.textViewNamaMakanan);
        textViewInfoMakanan = findViewById(R.id.textViewInfoMakanan);
        textViewHargaMakanan = findViewById(R.id.textViewHargaMakanan);

        btnKurang = findViewById(R.id.kurang);
        tampilanJumlah = findViewById(R.id.jumlah);
        btnTambah = findViewById(R.id.tambah);
        ordering = findViewById(R.id.pesanDetail);

        getIncomingExtra();
    }

    private void getIncomingExtra(){
        if(getIntent().hasExtra("foto_makanan") && getIntent().hasExtra("nama_makanan") && getIntent().hasExtra("info_makanan") && getIntent().hasExtra("harga_makanan")){

            String fotoMakanan = getIntent().getStringExtra("foto_makanan");
            String namaMakanan = getIntent().getStringExtra("nama_makanan");
            String infoMakanan = getIntent().getStringExtra("info_makanan");
            String hargaMakanan = getIntent().getStringExtra("harga_makanan");

            setDataActivity(fotoMakanan, namaMakanan, infoMakanan, hargaMakanan);

        }
    }

    private void setDataActivity(String fotoMakanan, String namaMakanan, String infoMakanan, String hargaMakanan){

        Glide.with(this).asBitmap().load(fotoMakanan).into(imageViewFotoMakanan);

        textViewNamaMakanan.setText(namaMakanan);
        textViewInfoMakanan.setText(infoMakanan);
        textViewHargaMakanan.setText(hargaMakanan);

        int hargaTetap = Integer.parseInt(hargaMakanan);
        final int[] harga = {Integer.parseInt(hargaMakanan)};

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angka++;
                if(angka > 1) {
                    String angkaStr = String.valueOf(angka);
                    tampilanJumlah.setText(angkaStr);
                    harga[0] = harga[0] + hargaTetap;
                    textViewHargaMakanan.setText(String.valueOf(harga[0]));
                }else{
                    textViewHargaMakanan.setText(hargaMakanan);
                }
            }
        });

        btnKurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(angka <= 1){
                    tampilanJumlah.setText("1");
                    textViewHargaMakanan.setText(hargaMakanan);
                }else{
                    angka--;
                    String angkaStr = String.valueOf(angka);
                    tampilanJumlah.setText(angkaStr);
                    harga[0] = harga[0] - hargaTetap;
                    textViewHargaMakanan.setText(String.valueOf(harga[0]));
                }
            }
        });

        ordering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, Pesanan.class);
                intent.putExtra("harga", String.valueOf(harga[0]));

                startActivity(intent);
            }
        });

    }


}