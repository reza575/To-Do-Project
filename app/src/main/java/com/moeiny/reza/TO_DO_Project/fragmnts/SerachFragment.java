package com.moeiny.reza.TO_DO_Project.fragmnts;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.moeiny.reza.TO_DO_Project.R;
import com.moeiny.reza.TO_DO_Project.data.MyDatabase;
import com.moeiny.reza.TO_DO_Project.data.ToDoItems;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SerachFragment extends Fragment {
    MyDatabase myDatabase;
    List<ToDoItems> listItems;
    ItemsAdapter adapter;
    View myView;
    RecyclerView recyclerView;
    EditText edtSearch;
    TextView txtNotFound;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.fragment_search,container,false);
        setView();
        return myView;
    }

    public void getSearchItems(Context context,String search) {
        listItems.clear();
        if (search.length()>0){
        Cursor cursor=myDatabase.getSearchData(search);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id=cursor.getInt(0);
                String title=cursor.getString(1);
                String description=cursor.getString(2);
                String date=cursor.getString(3);
                String time=cursor.getString(4);
                String mark=cursor.getString(5);
                ToDoItems item=new ToDoItems();
                item.setId(id);
                item.setTitle(title);
                item.setDescription(description);
                item.setDate(date);
                item.setTime(time);
                item.setMark(mark);
                listItems.add(item);

            } while (cursor.moveToNext());
            cursor.close();}

        }

        if(!listItems.isEmpty()){
            txtNotFound.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            ItemsAdapter itemsAdapter;
            itemsAdapter=new ItemsAdapter(myView.getContext(),listItems);
            recyclerView.setAdapter(itemsAdapter);
            itemsAdapter.notifyDataSetChanged();
        }
         else {
            txtNotFound.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }


    public void setView(){
        txtNotFound=myView.findViewById(R.id.txt_fragmentSearch_notFound);
        myDatabase=new MyDatabase(myView.getContext());
        listItems=new ArrayList<ToDoItems>();
        recyclerView = myView.findViewById(R.id.rv_fragmentSearch_searchedItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        edtSearch=(EditText)myView.findViewById(R.id.edt_fragmentSearch_search);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                    getSearchItems(myView.getContext(), s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                // do stuff
            }
        });
    }

}
