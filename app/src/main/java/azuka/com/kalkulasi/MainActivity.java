package azuka.com.kalkulasi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView selection;
    String[] items = {"1","2" ,"3", "4", "5", "6", "7", "8", "9", "10"};
    Button lanjut;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> jumlah = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

        jumlah.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(jumlah);

        lanjut = (Button) findViewById(R.id.lanjut);
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jumlah_matkul = spinner.getSelectedItem().toString();
                Intent i = new Intent(MainActivity.this, Perhitungan.class);
                i.putExtra("jlh", jumlah_matkul);
                startActivity(i);
            }
        });
    }

}
