package com.example.p_mat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.p_mat.Models.NoticeHelper;
import com.example.p_mat.Models.TodoHelper;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class add_new_todo extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    ArrayList<String> members = new ArrayList<String>();



    String date_time = "";
    int mYear;
    int mMonth;
    int mDay;

    int mHour;
    int mMinute;
    String AEmail = "";
    String DeadlineDate = "";


    //EditText et_show_date_time = (EditText) findViewById(R.id.et_show_date_time);
    //Button btn_set_date_time = (Button) findViewById(R.id.btn_set_date_time);





    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        members = (ArrayList) b.get("member");


        //++++++++++++++Drop Down List+++++++++++++++++++++++++++
        autoCompleteTextView=findViewById(R.id.autoCompleteDodoItem);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, members);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteDodoItem);
        textView.setAdapter(adapter);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);
                AEmail = item;
            }
        });



        //++++++++++++++++++++++++++Date And Time ++++++++++++++++++++++++++++++++========

        EditText todoAddTitle = (EditText) findViewById(R.id.todoAddTitle);
        EditText todoAddDescription = (EditText) findViewById(R.id.todoAddDescription);
        Button todoAddSubmit = (Button) findViewById(R.id.todoAddSubmit);

        todoAddSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                DatabaseReference reference = rootNode.getReference("todo");

             //   Log.d("him","================================================"+DeadlineDate);
                //DeadlineDate has correct date use it
                String DeadlineTime = "";
                String temp = String.valueOf(mHour);
                if(temp.length() == 1){
                    temp = "0" + temp + "";
                }
                DeadlineTime += temp;
                DeadlineTime += ("" + mMinute);



//                Toast.makeText(getApplicationContext(), DeadlineDate, Toast.LENGTH_SHORT).show();
                String Title = todoAddTitle.getText().toString(); // Not more than 25 characters
                String Description = todoAddDescription.getText().toString(); // Not more than 60 characters
                Boolean Completed = false; // true or false
                String AssignedEmail = AEmail;
                    if(!Description.equals("") && !DeadlineDate.equals("") && !DeadlineTime.equals("") && !AssignedEmail.equals("")){
                        TodoHelper todoHelper = new TodoHelper(DeadlineDate, DeadlineTime, Title, Description, Completed, AssignedEmail);
                        String id = reference.push().getKey();
                        reference.child(id).setValue(todoHelper);
                        Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Something is Missing", Toast.LENGTH_SHORT).show();
                    }


            }
        });


    }
    public void setDate(View v){
        datePicker();
    }
    public  void setTime(View v){
        timePicker();
    }
    private void datePicker(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {

                        date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        TextInputLayout deadlineDate=(TextInputLayout) findViewById(R.id.deadlineDate);
                        Objects.requireNonNull(deadlineDate.getEditText()).setText(date_time);

                        DeadlineDate= ""; // DDMMYYYY
//                        ("" + dayOfMonth) + ("" + (monthOfYear+1)) + ("" + year);
                        String temp = String.valueOf(dayOfMonth);
                        if(temp.length() == 1){
                            temp = "0" + temp;
                        }
                        DeadlineDate += temp;
                        temp = String.valueOf(monthOfYear+1);
                        if(temp.length() == 1){
                            temp = "0" + temp;
                        }
                        DeadlineDate += temp;
                        DeadlineDate += ("" + year);
                        //*************Call Time Picker Here ********************
                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }

    private void timePicker(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {
                    TextInputLayout deadlineTime=(TextInputLayout) findViewById(R.id.deadlineTime);


                    mHour = hourOfDay;
                    mMinute = minute;
                    Objects.requireNonNull(deadlineTime.getEditText()).setText(hourOfDay + ":" + minute);

                      //Objects.requireNonNull(showDate.getEditText()).setText(date_time+" "+hourOfDay + ":" + minute);
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }




}