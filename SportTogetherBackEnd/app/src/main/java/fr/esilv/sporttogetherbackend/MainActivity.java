package fr.esilv.sporttogetherbackend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.Profile;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private TextView text1;
    private Button reloadButton;

    protected void Initialize()
    {
        text1 = (TextView)findViewById(R.id.text1);
        reloadButton = (Button)findViewById(R.id.reloadButton);

        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.getCurrentUser().logOut();
                Log.i("logging stat", "logged out!");
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    //determiner if user authenticate
    protected boolean checkIfAuthenticated()
    {
        return Profile.getCurrentProfile() != null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialize();
        Log.i("appLog","In Main");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(checkIfAuthenticated()) {
            Log.i("appLog", String.valueOf(ParseFacebookUtils.isLinked(ParseUser.getCurrentUser())));
            Log.i("appLog", Profile.getCurrentProfile().getName() + "," +
                    Profile.getCurrentProfile().getId());
            Log.i("appLog", ParseUser.getCurrentUser().getUsername() + "," +
                    ParseUser.getCurrentUser().getEmail());
            text1.setText("Bonjour, " + Profile.getCurrentProfile().getName() + "!");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
