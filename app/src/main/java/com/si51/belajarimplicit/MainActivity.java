package com.si51.belajarimplicit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etUrl, etLokasi, etTeks;
    Button btnBukaWebsite, btnBukaLokasi, btnBagikanTeks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUrl = findViewById(R.id.et_url);
        etLokasi = findViewById(R.id.et_lokasi);
        etTeks = findViewById(R.id.et_teks);

        btnBukaWebsite = findViewById(R.id.btn_buka_website);
        btnBukaLokasi = findViewById(R.id.btn_buka_lokasi);
        btnBagikanTeks = findViewById(R.id.btn_bagikan_teks);

        btnBukaWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlWebsite = etUrl.getText().toString();
                Uri konvUrlWebsite = Uri.parse(urlWebsite);
                Intent bukaWebsite = new Intent(Intent.ACTION_VIEW, konvUrlWebsite);
                try {
                    startActivity(bukaWebsite);
                } catch (Exception e){
                    etUrl.setError("Ada Kesalahan URL Website");
                }
            }
        });

        btnBukaLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lokasi = etLokasi.getText().toString();
                Uri konvLokasi = Uri.parse("geo:0,0?q="+lokasi);
                Intent bukaLokasi = new Intent(Intent.ACTION_VIEW, konvLokasi);
                startActivity(bukaLokasi);
            }
        });

        btnBagikanTeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teks = etTeks.getText().toString();

                String mimeType = "text/plain";
                new ShareCompat
                        .IntentBuilder(MainActivity.this)
                        .setType(mimeType)
                        .setChooserTitle("Bagikan Teks Ini")
                        .setText(teks)
                        .startChooser();
            }
        });

    }
}