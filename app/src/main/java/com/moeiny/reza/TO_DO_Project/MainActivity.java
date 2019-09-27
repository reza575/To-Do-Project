package com.moeiny.reza.TO_DO_Project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.moeiny.reza.TO_DO_Project.fragmnts.HomeFragment;
import com.moeiny.reza.TO_DO_Project.fragmnts.InsertFragment;
import com.moeiny.reza.TO_DO_Project.fragmnts.MarkFragment;
import com.moeiny.reza.TO_DO_Project.fragmnts.SerachFragment;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

public class MainActivity extends AppCompatActivity {
    BottomNavigation bottomNavigation;
    FragmentTransaction fragmentTransaction;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        setupVews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rel_main_fragmentContainer,new HomeFragment());
        fragmentTransaction.commit();
    }

    private void setupVews() {
        bottomNavigation=(BottomNavigation)findViewById(R.id.bottom_navigation);
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rel_main_fragmentContainer,new HomeFragment());
        fragmentTransaction.commit();


        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int i) {
                switch (i){
                    case R.id.tab_home:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.rel_main_fragmentContainer,new HomeFragment());
                        fragmentTransaction.commit();
                        break;

                    case R.id.tab_insert:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.rel_main_fragmentContainer,new InsertFragment());
                        fragmentTransaction.commit();
                        break;

                    case R.id.tab_marked:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.rel_main_fragmentContainer,new MarkFragment());
                        fragmentTransaction.commit();
                        break;

                    case R.id.tab_search:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.rel_main_fragmentContainer,new SerachFragment());
                        fragmentTransaction.commit();
                        break;
                }
            }



        });
    }

    @Override
    public void onBackPressed() {
        if(bottomNavigation.getSelectedItem()==0){
            super.onBackPressed();
        } else
        {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rel_main_fragmentContainer,new HomeFragment());
            fragmentTransaction.commit();
        }
    }
}
