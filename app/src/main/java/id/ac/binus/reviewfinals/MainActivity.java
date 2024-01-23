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

        // TODO add initial fragment here (Student Fragment)
        add initial fragment here

    }

    // for menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.mnStudent:
                // TODO add logic to switch fragment to student fragment whenever menu is clicked here
                something...
                break;

            case R.id.mnCourse:
                // TODO add logic to switch fragment to course fragment whenever menu is clicked here
                something...
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