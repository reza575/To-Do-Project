package com.moeiny.reza.todo.detail;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moeiny.reza.todo.R;
import com.moeiny.reza.todo.data.MyDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class DetailActivity extends AppCompatActivity {

    MyDatabase myDatabase;
    Integer id;
    EditText edtTitle;
    EditText edtDate;
    EditText edtTime;
    EditText edtDescription;
    Button btnSaveChanges;
    String mark;
    FragmentTransaction fragmentTransaction;

    String itemTitle,itemDescription,itemDate,itemTime, itemMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle=getIntent().getExtras();
        id=bundle.getInt("items_Id");
        itemTitle=bundle.getString("items_Title");
        itemDescription=bundle.getString("items_Description");
        itemDate=bundle.getString("items_Date");
        itemTime=bundle.getString("items_Time");
        itemMark =bundle.getString("items_Mark");

        setView();
    }

    private void setView() {
        myDatabase=new MyDatabase(DetailActivity.this);
        btnSaveChanges= findViewById(R.id.btn_detailActivity_save);
        edtTitle= findViewById(R.id.edt_detailActivity_title);
        edtDescription= findViewById(R.id.edt_detailActivity_desription);
        edtDate= findViewById(R.id.edt_detailActivity_date);
        edtTime= findViewById(R.id.edt_detailActivity_time);
        edtTitle.setText(itemTitle);
        edtDescription.setText(itemDescription);
        edtDate.setText(itemDate);
        edtTime.setText(itemTime);

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((edtTitle.getText().toString()=="")|| (edtTitle.getText().toString()==null)){
                    Toast.makeText(DetailActivity.this,"Filling title is nessecery",Toast.LENGTH_SHORT).show();
                } else
                {
                    ContentValues values;
                    values=new ContentValues();
                    values.put("title",edtTitle.getText().toString());
                    values.put("description",edtDescription.getText().toString());
                    values.put("date",edtDate.getText().toString());
                    values.put("time",edtTime.getText().toString());
                    values.put("mark", itemMark);
                    myDatabase.updateRow(id,values);
                }
                finish();
            }
        });
    }
}
