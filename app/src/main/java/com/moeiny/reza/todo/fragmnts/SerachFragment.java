package com.moeiny.reza.todo.fragmnts;

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

import com.moeiny.reza.todo.R;
import com.moeiny.reza.todo.data.MyDatabase;
import com.moeiny.reza.todo.data.ToDoItems;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SerachFragment extends Fragment {
    //Var's
    MyDatabase myDatabase;
    List<ToDoItems> listItems;
    ItemsAdapter adapter;
    View myView;

    //Widget's
    RecyclerView recyclerView;
    EditText edtSearch;
    TextView txtNotFound;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_search, container, false);

        setView();        //  The view is set from database in regard of Query result

        return myView;
    }

    public void getSearchItems(Context context, String search) {
        listItems.clear();
        if (search.length() > 0) {
            Cursor cursor = myDatabase.getSearchData(search);  //Search on in sqlite table and return the result of Query
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String description = cursor.getString(2);
                    String date = cursor.getString(3);
                    String time = cursor.getString(4);
                    String mark = cursor.getString(5);
                    ToDoItems item = new ToDoItems();
                    item.setId(id);
                    item.setTitle(title);
                    item.setDescription(description);
                    item.setDate(date);
                    item.setTime(time);
                    item.setMark(mark);
                    listItems.add(item);

                } while (cursor.moveToNext());
                cursor.close();
            }

        }

        if (!listItems.isEmpty()) {
            txtNotFound.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            ItemsAdapter itemsAdapter;
            itemsAdapter = new ItemsAdapter(myView.getContext(), listItems);
            recyclerView.setAdapter(itemsAdapter);
            itemsAdapter.notifyDataSetChanged();
        } else {
            txtNotFound.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }


    public void setView() {
        txtNotFound = myView.findViewById(R.id.txt_fragmentSearch_notFound);
        edtSearch = myView.findViewById(R.id.edt_fragmentSearch_search);
        recyclerView = myView.findViewById(R.id.rv_fragmentSearch_searchedItem);

        myDatabase = new MyDatabase(myView.getContext());
        listItems = new ArrayList<ToDoItems>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));


        //Managing the content of EditSearch afte each changes
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

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
