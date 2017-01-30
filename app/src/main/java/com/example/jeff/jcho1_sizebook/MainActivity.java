package com.example.jeff.jcho1_sizebook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String FILENAME = "file.sav";
    public final static String EXTRA_MESSAGE = "com.example.jeff.jcho1_sizebook.MESSAGE";

    private EditText bodyText;
    private ListView oldRecordsList;
    private ArrayList<Record> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bodyText = (EditText) findViewById(R.id.name); //view
        oldRecordsList = (ListView) findViewById(R.id.oldRecordsList); //view
    }

    public void save(View v) {
        Intent intent = new Intent(this, new_entry.class);
        EditText editText = (EditText) findViewById(R.id.name);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        ArrayAdapter<Record> adapter = new ArrayAdapter<Record>(this, R.layout.list_view, records);
        oldRecordsList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Taken from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 2015-09-22
            Type listType = new TypeToken<ArrayList<Record>>(){}.getType();
            records = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            records = new ArrayList<Record>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

