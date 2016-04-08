package appdevtask1.bhargav.updatedtictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class About_activity extends AppCompatActivity {

    private Gameclass game;
    private Button buttons[];
    private Button btn;

    private TextView info;
    private TextView humancount;
    private TextView tiescount;
    private TextView androidcount;

    private int humancounter=0;
    private int tiescounter=0;
    private int androidcounter=0;

    private boolean humanfirst=true;
    private boolean gameover=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        buttons= new Button[Gameclass.get_B_Size()];
        buttons[0]=(Button)findViewById(R.id.one);
        buttons[1]=(Button)findViewById(R.id.two);
        buttons[2]=(Button)findViewById(R.id.three);
        buttons[3]=(Button)findViewById(R.id.four);
        buttons[4]=(Button)findViewById(R.id.five);
        buttons[5]=(Button)findViewById(R.id.six);
        buttons[6]=(Button)findViewById(R.id.seven);
        buttons[7]=(Button)findViewById(R.id.eight);
        buttons[8]=(Button)findViewById(R.id.nine);

        btn=(Button)findViewById(R.id.newgamebutton);

        setActivityBackgroundColor(-1);
        btn.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                // TODO Auto-generated method stub
                humanfirst=true;
                gameover=false;
                game =new Gameclass();
                startNewgame();
            }
        });
        info=(TextView)findViewById(R.id.information);
        humancount=(TextView)findViewById(R.id.humancount);
        tiescount=(TextView)findViewById(R.id.tiescount);
        androidcount=(TextView)findViewById(R.id.androidcount);

        humancount.setText(Integer.toString(humancounter));
        tiescount.setText(Integer.toString(tiescounter));
        androidcount.setText(Integer.toString(androidcounter));

        game =new Gameclass();
        startNewgame();
    }
    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
    private void startNewgame()
    {
        game.clearBoard();

        for(int i=0;i < buttons.length;i++)
        {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setOnClickListener(new ButtonClickListener(i));
        }

        if(humanfirst)
        {
            info.setText(R.string.first_human);
            final MediaPlayer mp = MediaPlayer.create(About_activity.this, R.raw.playx);
            mp.start();
            humanfirst=false;
        }

        else
        {
            info.setText(R.string.turn_computer);
            int move= game.computerMove();
            setMove(game.ANDROID_PLAYER, move);
            final MediaPlayer mp = MediaPlayer.create(About_activity.this, R.raw.zoop);
            mp.start();
            humanfirst=true;
        }

    }

    private class ButtonClickListener implements View.OnClickListener
    {
        int location;
        public ButtonClickListener(int location) {
            // TODO Auto-generated constructor stub

            this.location=location;
        }

        public void onClick(View view)
        {
            if(!gameover)
            {
                if(buttons[location].isEnabled()) {
                    setMove(game.HUMAN_PLAYER, location);
                    final MediaPlayer mp = MediaPlayer.create(About_activity.this, R.raw.playx);
                    mp.start();
                }
                    int winner = game.checkforwinner();

                    if(winner == 0)
                    {
                        info.setText(R.string.turn_computer);
                        int move= game.computerMove();
                        setMove(game.ANDROID_PLAYER, move);
                        final MediaPlayer mp = MediaPlayer.create(About_activity.this, R.raw.zoop);
                        mp.start();
                        winner=game.checkforwinner();
                    }

                    if(winner==0)
                    {
                        info.setText(R.string.turn_human);
                    }
                    else if(winner == 1)
                    {
                        info.setText(R.string.result_tie);
                        tiescounter++;
                        tiescount.setText(Integer.toString(tiescounter));
                        gameover=true;
                    }

                    else if(winner == 2)
                    {
                        info.setText(R.string.result_humanwins);
                        humancounter++;
                        humancount.setText(Integer.toString(humancounter));
                        gameover=true;
                    }
                    else
                    {
                        info.setText(R.string.result_computerwins);
                        androidcounter++;
                        androidcount.setText(Integer.toString(androidcounter));
                        gameover=true;
                    }
                }
            }
        }

    private void setMove(char player,int location)
    {
        game.setMove(player, location);
        buttons[location].setEnabled(false);
        buttons[location].setText(String.valueOf(player));
        if(player == game.HUMAN_PLAYER)
        {
            buttons[location].setTextColor(Color.GREEN);
        }
        else
            buttons[location].setTextColor(Color.RED);
    }


}




