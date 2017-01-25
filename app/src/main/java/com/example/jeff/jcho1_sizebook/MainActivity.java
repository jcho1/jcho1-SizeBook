package com.example.jeff.jcho1_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    public Button getSaveButton() {
        return saveButton;
    }

    private Button saveButton;

    public EditText getBodyText() {
        return bodyText;
    }

    private EditText bodyText;

    public ListView getOldRecordsList() {
        return oldRecordsList;
    }

    private ListView oldRecordsList;

    public ArrayList<Record> getRecords() {
        return records;
    }

    private ArrayList<Record> records = new ArrayList<Record>();
    private ArrayAdapter<Record> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bodyText = (EditText) findViewById(R.id.name); //view
        saveButton = (Button) findViewById(R.id.save);
        oldRecordsList = (ListView) findViewById(R.id.oldRecordsList); //view

    }

    public void save(View v) {
        String text = bodyText.getText().toString();

    }

    /*
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Record>(this, R.layout.list_item, records);
        oldRecordsList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Taken from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 2015-09-22
            Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType();
            records = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            records = new ArrayList<Record>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(records, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    */
}

