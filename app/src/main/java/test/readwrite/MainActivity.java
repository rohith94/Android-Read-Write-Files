package test.readwrite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnRead, btnSave;
    EditText txtInput;
    TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtContent = (TextView) findViewById(R.id.txtContent);
        txtInput = (EditText) findViewById(R.id.txtInput);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtContent.setText(FileHelper.readExternalStorage());
            }
        });

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FileHelper.writeExternalStorage(getApplicationContext(), txtInput.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Saved to file", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error save file!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
