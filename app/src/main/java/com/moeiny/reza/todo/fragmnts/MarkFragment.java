package com.moeiny.reza.todo.fragmnts;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class MarkFragment extends Fragment {
    //Var's
    MyDatabase myDatabase;
    List<ToDoItems> listItems;
    ItemsAdapter adapter;
    View myView;

    //Widget
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_mark, container, false);

        setView();  // The view is set from database in regard of Query result

        getMarkItems(myView.getContext());
        return myView;
    }

    public void getMarkItems(Context context) {
        myDatabase = new MyDatabase(context);
        Cursor cursor = myDatabase.getSomeData();  //search the data in Sqlite Table where the record are marked
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

            setDataOnRecycler();  // The Recycler is set from database in regard of Query result
            cursor.close();
        }
    }

    public void setDataOnRecycler() {
        adapter = new ItemsAdapter(myView.getContext(), listItems);
        recyclerView.setAdapter(adapter);
    }

    public void setView() {
        myDatabase = new MyDatabase(this.getContext());
        listItems = new ArrayList<ToDoItems>();
        recyclerView = myView.findViewById(R.id.rv_fragmentMark_markitems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
    }
}

