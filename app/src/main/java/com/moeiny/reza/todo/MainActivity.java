package com.moeiny.reza.todo;

import android.os.Bundle;

import com.moeiny.reza.todo.fragmnts.HomeFragment;
import com.moeiny.reza.todo.fragmnts.InsertFragment;
import com.moeiny.reza.todo.fragmnts.MarkFragment;
import com.moeiny.reza.todo.fragmnts.SerachFragment;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    //Widget's
    BottomNavigation bottomNavigation;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * The view is managed by Transaction management
         */
        setupVews();
    }

    //onResume adjust the Fragment when switch to MainActivity
    @Override
    protected void onResume() {
        super.onResume();
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rel_main_fragmentContainer,new HomeFragment());
        fragmentTransaction.commit();
    }

    private void setupVews() {
        bottomNavigation= findViewById(R.id.bottom_navigation);
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rel_main_fragmentContainer,new HomeFragment());
        fragmentTransaction.commit();

       //this method called when user clicked on the different Tabs of BottomNavofation
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

   //when User click on the backButton the first tab should be selected and showed by this method
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
