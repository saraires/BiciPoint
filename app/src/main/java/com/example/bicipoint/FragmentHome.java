package com.example.bicipoint;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentHome extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton addButton;
    DatabaseHelper databaseHelper;
    CustomAdapter adapter;
    List<Note> dataList;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.activity_main, null);
        //setContentView(R.layout.activity_main);

        recyclerView           = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addButton              = view.findViewById(R.id.addButton);
        databaseHelper         = new DatabaseHelper(getContext());


        loadData();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customDialog();

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        return view;
    }

    private void customDialog() {

        AlertDialog.Builder builder  = new AlertDialog.Builder(getContext());
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
                    Toast.makeText(getContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                }else {
                    alertDialog.dismiss();
                    Toast.makeText(getContext(), "Failed to Insert", Toast.LENGTH_SHORT).show();

                }
            }
        });

        alertDialog.show();
    }

    private void loadData() {

        dataList  = new ArrayList<>();
        dataList = databaseHelper.getAllNotes();

        if (dataList.size() > 0){
            adapter = new CustomAdapter(getContext(),dataList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        }
    }
}