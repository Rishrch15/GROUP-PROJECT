package com.example.epermit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TransferFormActivity extends AppCompatActivity {

    EditText etEvent, etDate, etFrom, etThings;
    Button btnSubmit;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_form);

        db = new DatabaseHelper(this);

        etEvent = findViewById(R.id.etEvent);
        etDate = findViewById(R.id.etDate);
        etFrom = findViewById(R.id.etFrom);
        etThings = findViewById(R.id.etThingsDescription);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            boolean success = db.submitTransfer(
                    etEvent.getText().toString(),
                    etDate.getText().toString(),
                    etFrom.getText().toString(),
                    etThings.getText().toString()
            );
            Toast.makeText(this, success ? "Submitted" : "Failed", Toast.LENGTH_SHORT).show();
            if (success) finish();
        });
    }
}
