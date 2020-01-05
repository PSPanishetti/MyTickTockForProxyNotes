package in.codeworld.myticktock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class Settings extends AppCompatActivity {

    private TextView themeText;
    private SharedPreferenceManager sharedPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_settings);
        sharedPreferenceManager=new SharedPreferenceManager(getApplicationContext());
        setThemeTextAndLayout();
        setTitle(Constants.SETTINGS);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        setThemeTextAndLayout();
        super.onStart();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void ToggleTheme(View view) {
        sharedPreferenceManager=new SharedPreferenceManager(getApplicationContext());
        sharedPreferenceManager.toggleTheme();
        Intent intent=new Intent(Settings.this, Settings.class);
        startActivity(intent);
        finish();
    }



    public void setThemeTextAndLayout()
    {
        if(sharedPreferenceManager.getTheme()==0) {
        setContentView(R.layout.activity_settings_dark);
            themeText=findViewById(R.id.themeText);
            themeText.setText(Constants.DARK_MODE_TEXT);
    }else{

            setContentView(R.layout.activity_settings);
            themeText=findViewById(R.id.themeText);
            themeText.setText(Constants.LIGHT_MODE_TEXT);
    }

    }


    public void ReadAboutMe(View view) {
        Intent intent=new Intent(getApplicationContext(),AboutMe.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
