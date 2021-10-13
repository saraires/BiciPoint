package com.example.bicipoint;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private List<Note> allNotes;
    private DatabaseHelper databaseHelper;

    public CustomAdapter(Context context, List<Note> allNotes) {
        this.context = context;
        this.allNotes = allNotes;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_layout,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.title.setText(allNotes.get(position).getTitle());
        holder.date.setText(allNotes.get(position).getDate());

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Select Action");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        customDialog(position);

                    }
                });


                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int status = databaseHelper.deleteData(allNotes.get(position).getId());
                        if (status == 1){
                            allNotes.remove(allNotes.get(position));
                            notifyDataSetChanged();
                        }else {

                        }

                    }
                });

                builder.show();




                return true;
            }
        });



    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,date;
        LinearLayout layout;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title  = itemView.findViewById(R.id.custom_title);
            date   = itemView.findViewById(R.id.custom_date);
            layout = itemView.findViewById(R.id.layout);
        }

    }


    private void customDialog(final int position) {

        androidx.appcompat.app.AlertDialog.Builder builder  = new androidx.appcompat.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog,null);
        builder.setView(view);


        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();

        final EditText title       = view.findViewById(R.id.title);
        final EditText description = view.findViewById(R.id.description);
        title.setText(allNotes.get(position).getTitle());
        description.setText(allNotes.get(position).getDescription());

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


                long status = databaseHelper.updateData(new Note(allNotes.get(position).getId(),titleValue,descriptionValue,date));
                if (status == 1){
                    alertDialog.dismiss();
                    allNotes.clear();
                    allNotes.addAll(databaseHelper.getAllNotes());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
                }else {
                    alertDialog.dismiss();
                    Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();

                }




            }
        });






        alertDialog.show();





    }

}
