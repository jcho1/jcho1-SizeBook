package com.example.jeff.jcho1_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private EditText editText;
    private EditText neckText;
    private EditText bustText;
    private EditText chestText;
    private EditText waistText;
    private EditText hipText;
    private EditText inseamText;
    private EditText commentText;

    private Measurements measurements;
    private ArrayAdapter<Record> adapter;
    private ArrayList<Record> recordArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        Intent intent = getIntent();

        // set variable id
        editText = (EditText) findViewById(R.id.entry_name);
        neckText = (EditText) findViewById(R.id.neck);
        bustText = (EditText) findViewById(R.id.bust);
        chestText = (EditText) findViewById(R.id.chest);
        waistText = (EditText) findViewById(R.id.waist);
        hipText = (EditText) findViewById(R.id.hip);
        inseamText = (EditText) findViewById(R.id.inseam);
        commentText = (EditText) findViewById(R.id.comment);

        // Set name previously entered
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        editText.setText(message);
    }

    public void saveEntry(View view){
        setResult(RESULT_OK);

        float neck = 0;
        float bust = 0;
        float chest = 0;
        float waist = 0;
        float hip = 0;
        float inseam = 0;


        // save Name in records
        // TODO: ADD DATE
        String text = editText.getText().toString();
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
        measurements.setWaist(waist);
        measurements.setHip(hip);
        measurements.setInseam(inseam);
        record.setMeasurements(measurements);

        // Add comment
        String comment = commentText.getText().toString();
        record.setComment(comment);

        // Add record
        recordArrayList.add(record);
        adapter.notifyDataSetChanged();

        saveInFile();
    }

    public void clear(View v){
        neckText.setText("");
        bustText.setText("");
        chestText.setText("");
        waistText.setText("");
        hipText.setText("");
        inseamText.setText("");
        commentText.setText("");
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
