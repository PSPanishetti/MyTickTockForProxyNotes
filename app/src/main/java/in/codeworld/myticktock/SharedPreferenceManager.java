package in.codeworld.myticktock;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private final SharedPreferences sharedPreferences;
    private Context context;


    public SharedPreferenceManager(Context context) {
        this.context = context;
         sharedPreferences = this.context.getSharedPreferences(Constants.SHARED_PREF_FILE, 0);
    }

    public void setTheme(int mode)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(Constants.THEME_ID,mode);
        editor.apply();
    }

    public  int getTheme()
    {
        return sharedPreferences.getInt(Constants.THEME_ID,0);
    }

    public void toggleTheme(){
        if(sharedPreferences.getInt(Constants.THEME_ID,0)==0)
        {
            setTheme(1);

        }else {
            setTheme(0);
        }

    }



}
