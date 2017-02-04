package com.example.jeff.jcho1_sizebook;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.Measure;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.example.jeff.jcho1_sizebook.MESSAGE";
    private static final String FILENAME = "file.sav";
    private EditText editText;
    private EditText dateText;
    private EditText neckText;
    private EditText bustText;
    private EditText chestText;
    private EditText waistText;
    private EditText hipText;
    private EditText inseamText;
    private EditText commentText;
    private ListView oldRecordsList;
    private ArrayList<Record> recordsList;
    private ArrayAdapter<Record> adapter;

    private boolean editMode; // change between creating new(0) and editing old entry(1)
    private int editPosition;  // Index of entry being edited

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        oldRecordsList = (ListView) findViewById(R.id.oldRecordsList); //listview
        editMode = false;
        Button button2 = (Button) findViewById(R.id.button2);

        button2.setVisibility(View.GONE);

        // listview onclick listener
        oldRecordsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id){
                // TODO: clickthing to work
                editPosition = position;

                // Get old data and put it into editText
                Measurements oldmeasurement = recordsList.get(position).getMeasurements();
                editText.setText(recordsList.get(position).getName());
                commentText.setText(recordsList.get(position).getComment());
                neckText.setText(""+oldmeasurement.getNeck());
                bustText.setText(""+oldmeasurement.getBust());
                chestText.setText(""+ oldmeasurement.getBust());
                waistText.setText(""+oldmeasurement.getWaist());
                hipText.setText(""+oldmeasurement.getHip());
                inseamText.setText(""+oldmeasurement.getInseam());

                Toast.makeText(getBaseContext(),"Old Entry " + position,Toast.LENGTH_SHORT).show();

                // Change to edit mode
                editMode = true;
                Button button1 = (Button) findViewById(R.id.button1);
                Button button2 = (Button) findViewById(R.id.button2);
                Button button3 = (Button) findViewById(R.id.button3);

                button1.setText("Back");
                button2.setVisibility(View.VISIBLE);
                button3.setText("Change/Edit");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();

        editText = (EditText) findViewById(R.id.entry_name);
        neckText = (EditText) findViewById(R.id.neck);
        bustText = (EditText) findViewById(R.id.bust);
        chestText = (EditText) findViewById(R.id.chest);
        waistText = (EditText) findViewById(R.id.waist);
        hipText = (EditText) findViewById(R.id.hip);
        inseamText = (EditText) findViewById(R.id.inseam);
        commentText = (EditText) findViewById(R.id.comment);
        adapter = new ArrayAdapter<Record>(this, R.layout.list_view, recordsList);
        oldRecordsList.setAdapter(adapter);

        changeListCount();
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public String dateToString(Date date){
        //to convert Date to String, use format method of SimpleDateFormat class
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        return dateFormat.format(date);
    }

    public void button3(View v){

        // Check to if Name entry is valid
        if(isEmpty(editText)) {
            Toast.makeText(getBaseContext(),"Name Cannot be left Empty" ,Toast.LENGTH_SHORT).show();
            return;
        }

        // create record and add it in the correct position in the ArrayList
        Record record = createRecord();
        if(editMode) { // Edit old entry
            recordsList.set(editPosition,record);
        }else{  // Save new Entry
            recordsList.add(record);
        }

        adapter.notifyDataSetChanged();
        saveInFile();
        changeListCount();
    }

    public void button1(View v){
        if(editMode) { // Exiting edit mode
            editMode = false;
            exitEditMode();
            clear();
        }else{
            clear();
        }
    }

    private void changeListCount(){
        TextView entry_num = (TextView) findViewById(R.id.entry_num);
        entry_num.setText("Number of Entries: " + recordsList.size());
    }

    private void exitEditMode(){
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        button1.setText("Clear");
        button2.setVisibility(View.GONE);
        button3.setText("Create New");
    }

    private void clear(){
        editText.setText("");
        commentText.setText("");
        neckText.setText("");
        bustText.setText("");
        chestText.setText("");
        waistText.setText("");
        hipText.setText("");
        inseamText.setText("");
    }

    public void delete(View v) {
        recordsList.remove(editPosition);
        adapter.notifyDataSetChanged();
        saveInFile();
        clear();
        editMode = false;
        exitEditMode();
        changeListCount();
    }

    private Record createRecord() {
        setResult(RESULT_OK);

        // set variable id
        Measurements measurements = new Measurements();

        float neck = 0;
        float bust = 0;
        float chest = 0;
        float waist = 0;
        float hip = 0;
        float inseam = 0;


        // save Name and date in records
        String text = editText.getText().toString();
        Record record = new Record(text);

        // Get measurements
        try{
            if(!isEmpty(neckText)) neck = Float.valueOf(neckText.getText().toString());
            if(!isEmpty(bustText)) bust = Float.valueOf(bustText.getText().toString());
            if(!isEmpty(chestText)) chest = Float.valueOf(chestText.getText().toString());
            if(!isEmpty(waistText)) waist = Float.valueOf(waistText.getText().toString());
            if(!isEmpty(hipText)) hip = Float.valueOf(hipText.getText().toString());
            if(!isEmpty(inseamText)) inseam = Float.valueOf(inseamText.getText().toString());
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

        return record;
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(recordsList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Taken from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 2015-09-22
            Type listType = new TypeToken<ArrayList<Record>>(){}.getType();
            recordsList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            recordsList = new ArrayList<Record>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

