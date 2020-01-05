package in.codeworld.myticktock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GestureDetectorCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.material.snackbar.Snackbar;

import in.codeworld.myticktock.interfaces.SwipeActions;

public class MainActivity extends AppCompatActivity {


    private GestureDetectorCompat gestureDetectorCompat;
    private CoordinatorLayout coordinatorLayout;

    private int index = 0;
    private int[] rawIds;

    private VideoView videoView;

    private TextView videoName;
    private ImageView settings;
    SharedPreferenceManager sharedPreferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

        if (sharedPreferenceManager.getTheme() == 0) {
            setContentView(R.layout.activity_main_light);
        } else {
            setContentView(R.layout.activity_main);
        }

        InitViews();
        int rawId1 = getResources().getIdentifier("demo_one", "raw", getPackageName());
        int rawId2 = getResources().getIdentifier("demo_two", "raw", getPackageName());
        int rawId3 = getResources().getIdentifier("demo_three", "raw", getPackageName());
        int rawId4 = getResources().getIdentifier("demo_four", "raw", getPackageName());
        int rawId5 = getResources().getIdentifier("demo_five", "raw", getPackageName());
        int rawId6 = getResources().getIdentifier("demo_six", "raw", getPackageName());


        rawIds = new int[]{rawId4, rawId1, rawId5, rawId2, rawId3, rawId6};

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String path = "android.resource://" + getPackageName() + "/" + rawIds[0];
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();


        videoName.setText("Currently Playing Video " + index);

        final DetectSwipeGesture detectSwipeGesture = new DetectSwipeGesture(new SwipeActions() {
            @Override
            public void onSwipeLeft() {

                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Subscribed Successfully", Snackbar.LENGTH_LONG);
                snackbar.setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                snackbar.getView().setBackgroundColor(getColor(R.color.colorAccent));
                snackbar.show();

            }

            @Override
            public void onSwipeRight() {
                Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                Bundle bundleAnimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.left_to_right, R.anim.right_to_left).toBundle();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent, bundleAnimation);
            }

            @Override
            public void onSwipeUp() {

                if (index != 5) {
                    videoName.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.top_to_bottom));
                    videoView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_to_top));
                    index++;
                    String path = "android.resource://" + getPackageName() + "/" + rawIds[index];
                    videoView.setVideoURI(Uri.parse(path));
                    videoView.start();
                    videoName.setText("Currently Playing Video " + index);

                }

            }

            @Override
            public void onSwipeDown() {

                if (index != 0) {
                    videoName.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.top_to_bottom));
                    videoView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.top_to_bottom));
                    index--;
                    String path = "android.resource://" + getPackageName() + "/" + rawIds[index];
                    videoView.setVideoURI(Uri.parse(path));
                    videoView.start();
                    videoName.setText("Currently Playing Video " + index);

                }

            }


        });

        gestureDetectorCompat = new GestureDetectorCompat(getApplicationContext(), detectSwipeGesture);

    }

    private void InitViews() {

        videoView = findViewById(R.id.video_view);
        coordinatorLayout = findViewById(R.id.container);
        videoName = findViewById(R.id.currentVideoName);
        settings = findViewById(R.id.settings);


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (sharedPreferenceManager.getTheme() == 0) {
            setContentView(R.layout.activity_main_light);
        } else {
            setContentView(R.layout.activity_main);
        }

        InitViews();

        String path = "android.resource://" + getPackageName() + "/" + rawIds[index];
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();
        videoName.setText("Currently Playing Video " + index);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
