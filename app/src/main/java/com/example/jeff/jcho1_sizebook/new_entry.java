package com.example.jeff.jcho1_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class new_entry extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ArrayList<Record> recordArrayList;
    private EditText editText;
    private ArrayAdapter<Record> adapter;
    private Measurements measurements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        Intent intent = getIntent();

        // Set name previously entered
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        editText = (EditText) findViewById(R.id.entry_name);
        editText.setText(message);
    }

    private void Save(){
        setResult(RESULT_OK);
        String text = editText.getText().toString();
        EditText commentText = (EditText) findViewById(R.id.entry_name);
        String comment = commentText.getText().toString();

        EditText neckText = (EditText) findViewById(R.id.neck);
        float neck = 0;
        EditText bustText = (EditText) findViewById(R.id.bust);
        float bust = 0;
        EditText chestText = (EditText) findViewById(R.id.chest);
        float chest = 0;
        EditText waistText = (EditText) findViewById(R.id.waist);
        float waist = 0;
        EditText hipText = (EditText) findViewById(R.id.hip);
        float hip = 0;
        EditText inseamText = (EditText) findViewById(R.id.inseam);
        float inseam = 0;


        // save Name in records
        // TODO: ADD DATE
        Record record = new Record(text);

        // Get measurements
        try{
            neck = Float.valueOf(neckText.getText().toString());
            bust = Float.valueOf(bustText.getText().toString());
            chest = Float.valueOf(chestText.getText().toString());
            waist = Float.valueOf(waistText.getText().toString());
            hip = Float.valueOf(hipText.getText().toString());
            inseam = Float.valueOf(inseamText.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Add measurements to record
        measurements.setNeck(neck);
        measurements.setBust(bust);
        measurements.setChest(chest);
        measurements.setChest(waist);
        measurements.setHip(hip);
        measurements.setInseam(inseam);
        record.setMeasurements(measurements);

        // Add comment
        record.setComment(comment);

        // Add record
        recordArrayList.add(record);
        adapter.notifyDataSetChanged();

        saveInFile();
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(recordArrayList, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
