package id.ac.binus.reviewfinals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private FrameLayout main_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // display bottom navbar
        BottomNavigationView nav = findViewById(R.id.bottom_navbar);
        nav.setOnItemSelectedListener(this);
        nav.getMenu().getItem(0).setChecked(true);

        // declare fragment
        StudentFragment fragment = new StudentFragment();

        // set frame
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();

    }

    // for menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.mnStudent:
                StudentFragment studentFragment = new StudentFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, studentFragment).commit();
                break;

            case R.id.mnCourse:
                CourseFragment courseFragment = new CourseFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, courseFragment).commit();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_menu, menu);
        return true;
    }
}