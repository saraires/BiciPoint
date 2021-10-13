package com.example.bicipoint;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;
    DatabaseHelper databaseHelper;
    CustomAdapter adapter;
    List<Note> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView           = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        addButton              = findViewById(R.id.addButton);
        databaseHelper         = new DatabaseHelper(MainActivity.this);


        loadData();



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customDialog();

            }
        });


    }

    private void loadData() {

        dataList  = new ArrayList<>();
        dataList = databaseHelper.getAllNotes();

        if (dataList.size() > 0){
            adapter = new CustomAdapter(MainActivity.this,dataList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }

    }


    private void customDialog() {

        AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.custom_dialog,null);
        builder.setView(view);


        final AlertDialog alertDialog = builder.create();

        final EditText title       = view.findViewById(R.id.title);
        final EditText description = view.findViewById(R.id.description);

        Button saveButton    = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titleValue       = title.getText().toString();
                String descriptionValue = description.getText().toString();

                if (titleValue.isEmpty()){
                    title.setError("Enter Title");
                    return;
                }else if (descriptionValue.isEmpty()){
                    description.setError("Enter description");
                    return;
                }

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("MM-dd");
                String date = format.format(calendar.getTimeInMillis());



                long status = databaseHelper.insertData(new Note(titleValue,descriptionValue,date));
                if (status != -1){
                    alertDialog.dismiss();
                    loadData();
                    Toast.makeText(MainActivity.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                }else {
                    alertDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Failed to Insert", Toast.LENGTH_SHORT).show();

                }




            }
        });






        alertDialog.show();





    }
}