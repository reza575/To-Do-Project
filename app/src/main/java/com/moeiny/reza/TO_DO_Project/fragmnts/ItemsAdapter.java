package com.moeiny.reza.TO_DO_Project.fragmnts;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.moeiny.reza.TO_DO_Project.R;
import com.moeiny.reza.TO_DO_Project.data.MyDatabase;
import com.moeiny.reza.TO_DO_Project.data.ToDoItems;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    List<ToDoItems> itemsList;
    Context context;
    MyDatabase myDatabase;
    public ItemsAdapter(Context context, List<ToDoItems> itemsList){
        this.itemsList = itemsList;
        this.context=context;
        myDatabase=new MyDatabase(context);
    }

   public class ItemsViewHolder extends RecyclerView.ViewHolder {
        CardView parent;
       TextView txtTitle;
       TextView txtDescription;
       TextView txtDate;
       TextView txtTime;
       CheckBox chkMark;
       ImageView imgDeleteItems;
       ImageView imgEditItems;
       ImageView imgToDo;

       public ItemsViewHolder(@NonNull View itemView) {
           super(itemView);
           txtTitle=(TextView)itemView.findViewById(R.id.txt_todorow_title);
    //       txtDescription=(TextView)itemView.findViewById(R.id.txt_todorow_D);
           txtDate=(TextView)itemView.findViewById(R.id.txt_todorow_date);
           txtTime=(TextView)itemView.findViewById(R.id.txt_todorow_time);
           chkMark=(CheckBox) itemView.findViewById(R.id.chk_todorow_mark);
           imgDeleteItems=(ImageView)itemView.findViewById(R.id.img_todorow_delete);
           imgEditItems=(ImageView)itemView.findViewById(R.id.img_todorow_edit);
           imgToDo=(ImageView)itemView.findViewById(R.id.img_todorow_Icon);
           parent=(CardView)itemView.findViewById(R.id.card_todorow_parent);
       }
   }
    @NonNull
    @Override

    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_row,parent,false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemsViewHolder holder, int position) {
        final String description;
        final Integer id;

        final ToDoItems items=itemsList.get(position);
        holder.txtTitle.setText(items.getTitle().toString());
        holder.txtDate.setText(items.getDate().toString());
        holder.txtTime.setText(items.getTime().toString());
        description=items.getDescription();
        id=items.getId();
        if (items.getMark().equals("1")) {
            holder.chkMark.setChecked(true);
        }else{
            holder.chkMark.setChecked(false);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(context, DetailActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("items_Title",items.getTitle());
//                intent.putExtra("items_Date",items.getDate());
//                intent.putExtra("items_Time",items.getTime());
//                intent.putExtra("items_Description",items.getDescription());
//                context.startActivity(intent);

            }
        });

        holder.chkMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.chkMark.isChecked()){
                    ContentValues values;
                    values=new ContentValues();
                    values.put("mark","1");
                    myDatabase.updateRow(id,values);
                }else{
                    ContentValues values;
                    values=new ContentValues();
                    values.put("mark","0");
                    myDatabase.updateRow(id,values);
                }
              //  long id=myDatabase.addInfo(holder.txtTitle.getText().toString(),description,holder.txtDate.getText().toString(),holder.txtTime.getText().toString(),"1");
           //     Toast.makeText(context,id+"",Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgDeleteItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     myDatabase.deleteRow(id);

                }
        });

        holder.imgEditItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("id",items.getId());
//                bundle.putString("title",items.getTitle());
//                bundle.putString("description",items.getDescription());
//                bundle.putString("date",items.getDate());
//                bundle.putString("time",items.getTime());
//                bundle.putString("mark",items.getMark());
//
//                InsertFragment insertFragment = new InsertFragment();
//                insertFragment.setArguments(bundle);
//
//                FragmentManager fragmentManager = getsuppFragmentManager();
//
//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.content, insertFragment)
//                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

}