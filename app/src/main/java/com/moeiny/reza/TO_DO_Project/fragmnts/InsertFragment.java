package com.moeiny.reza.TO_DO_Project.fragmnts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.moeiny.reza.TO_DO_Project.R;
import com.moeiny.reza.TO_DO_Project.data.MyDatabase;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InsertFragment extends Fragment {
    View myView;
    EditText edtTitle;
    EditText edtDescription;
    EditText edtDate;
    EditText edtTime;
    Button btnSave;
    MyDatabase myDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.fragment_insert,container,false);
        setView();
        return myView;
    }

    public void setView(){
        myDatabase=new MyDatabase(myView.getContext());
        edtTitle=myView.findViewById(R.id.edt_insertFragment_title);
        edtDescription=myView.findViewById(R.id.edt_insertFragment_desription);
        edtDate=myView.findViewById(R.id.edt_insertFragment_date);
        edtTime=myView.findViewById(R.id.edt_insertFragment_time);
        btnSave=myView.findViewById(R.id.btn_insertFragment_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((edtTitle.getText().toString()=="")|| (edtTitle.getText().toString()==null)){
                    Toast.makeText(myView.getContext(),"Filling title is nessecery",Toast.LENGTH_SHORT).show();
                } else
                {
                    long result;
                    result=myDatabase.addInfo(edtTitle.getText().toString(),edtDescription.getText().toString(),
                                              edtDate.getText().toString(),edtTime.getText().toString(),"0");
                    if(result>0){
                        Toast.makeText(myView.getContext(),"Record has been saved  "+result,Toast.LENGTH_SHORT).show();
                        edtTitle.setText("");
                        edtDescription.setText("");
                        edtDate.setText("");
                        edtTime.setText("");
                    }
                }
            }
        });
    }
}
