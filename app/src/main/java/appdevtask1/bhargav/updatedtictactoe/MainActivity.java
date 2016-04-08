package appdevtask1.bhargav.updatedtictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    Button b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b2= (Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
    }

    public void process(View v) {

        if (v.getId() == R.id.button2) {

            Intent i=new Intent(MainActivity.this ,About_activity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.button3) {
           // Toast.makeText(getApplicationContext(),"Wait to play multiplayer",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(MainActivity.this ,Multi_activity.class);
            startActivity(i);
        }
    }
}
