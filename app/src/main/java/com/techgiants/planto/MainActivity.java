package com.techgiants.planto;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
// Setup BottomNavigationView
        setupBottomNavigationView();
    }
    private void setupBottomNavigationView() {
        navigation = findViewById(R.id.bottomNavigation);
        navigation.setItemIconTintList(null);
        navigation.setOnItemSelectedListener(this::onNavigationItemSelected);
        navigation.setSelectedItemId(R.id.exploration);
    }

    private boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null;
        int id = menuItem.getItemId();
        if (id == R.id.register) {
            frag(new AboutYourPlantMain(),false);
        }
        else if (id == R.id.exploration) {
            frag(new AboutPlants(), false);
        }
        else{
            // remainder section
            frag(new RemainderSection(), false);
        }
        return true;
    }
    public void frag(Fragment fragment, boolean flag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (flag) {
            fragmentTransaction.add(R.id.containeer, fragment);
        } else {
            fragmentTransaction.replace(R.id.containeer, fragment);
        }
        fragmentTransaction.commit();

    }

}