package azuka.com.kalkulasi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Perhitungan extends AppCompatActivity {

    LinearLayout ll, row;
    EditText sks, nilai, nama_matkul;
    Button b_hitung;
    TextView info;
    double hitung;
    double angka;
    String huruf = "";
    DecimalFormat df;
    int jumlah_matkul, jlh_sks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitungan);

        ll = (LinearLayout) findViewById(R.id.ll);
        Intent i = getIntent();
        jumlah_matkul = Integer.parseInt(i.getStringExtra("jlh"));

        for (int j=1; j<=jumlah_matkul; j++){
            // Add Info
            row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            info = new TextView(this);
            info.setPadding(5,5,5,5);
            info.setTextSize(23);
            info.setText("Matkul "+j);

            LinearLayout.LayoutParams nama_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            nama_param.setMargins(15,0,0,0);

            nama_matkul = new EditText(this);
            nama_matkul.setPadding(15,5,5,5);
            nama_matkul.setHint("Masukkan nama matkul "+j);
            nama_matkul.setTextSize(20);
            row.addView(info);
            row.addView(nama_matkul, nama_param);


            // Add SKS
            sks = new EditText(this);
            sks.setId(j);
            sks.setRawInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER);
            sks.setHint("Masukkan jumlah sks matkul "+j);

            // Add Nilai
            nilai = new EditText(this);
            nilai.setId(j+100);
            nilai.setHint("a/ab/b/bc/c/d/e");


            //hitung += Double.parseDouble(sks.getText().toString()) * angka ;

            LinearLayout.LayoutParams nilai_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            nilai_param.setMargins(0,0,0,80);


            // Add the views to LinearLayout
            ll.addView(row);
            ll.addView(sks);
            ll.addView(nilai, nilai_param);

        }


        b_hitung = (Button) findViewById(R.id.hitung);
        b_hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitung = 0;
                jlh_sks = 0;
                for (int j=1; j<= jumlah_matkul; j++){
                    EditText sks_k = findViewById(j);
                    EditText nilai_k = findViewById(j+100);
                    huruf = nilai_k.getText().toString();

                    if(sks_k.getText().toString().isEmpty()){
                        sks_k.setError("Masukkan SKS");
                        sks_k.requestFocus();
                        return;
                    } else if (nilai_k.getText().toString().isEmpty()){
                        nilai_k.setError("Masukkan nilai anda");
                        nilai_k.requestFocus();
                        return;
                    }

                    switch (huruf){
                        case "a" :case "A" :case "4":
                            angka = 4;
                            break;

                        case "ab" :case "AB":case "Ab":case "aB":case "3.5":
                            angka = 3.5;
                            break;

                        case "b":case "B" :case "3":
                            angka = 3.0;
                            break;

                        case "bc":case "BC":case "Bc":case "bC":case "2.5":
                            angka = 2.5;
                            break;

                        case "c":case "C" :case "2":
                            angka = 2.0;
                            break;

                        case "d":case "D" :case "1":
                            angka = 1.0;
                            break;

                        default:
                            angka = 0;
                            break;
                    }
                    jlh_sks += Integer.parseInt(sks_k.getText().toString());
                    hitung += Double.parseDouble(sks_k.getText().toString()) * angka ;
                }
                df = new DecimalFormat("###.##");
                AlertDialog.Builder hasil_dialog = new AlertDialog.Builder(Perhitungan.this);
                hasil_dialog.setTitle("Hasil IPK Semester")
                            .setMessage("IPK anda di semester ini adalah " + df.format(hitung/jlh_sks))
                            //.setIcon(R.drawable.tick)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // code here
                                }
                            });

                InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(nilai.getWindowToken(),0);
                hasil_dialog.show();
            }
        });
    }
}
