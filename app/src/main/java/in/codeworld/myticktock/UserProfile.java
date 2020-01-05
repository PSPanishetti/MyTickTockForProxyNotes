package in.codeworld.myticktock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.Objects;

public class UserProfile extends AppCompatActivity {

    private SharedPreferenceManager sharedPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferenceManager=new SharedPreferenceManager(getApplicationContext());
        if(sharedPreferenceManager.getTheme()==0)
        {
            setContentView(R.layout.activity_user_profile);
        }else {
            setContentView(R.layout.user_profile_light_mode);
        }

        setTitle(Constants.USER_PROFILE);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void GoBack(View view) {
        onBackPressed();
    }
}
