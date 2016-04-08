package appdevtask1.bhargav.updatedtictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Multi_activity extends AppCompatActivity {

    private Gameclass game;
    private Button buttons[];
    private Button btn;

    private TextView info;
    private TextView player1count;
    private TextView tiescount;
    private TextView player2count;

    private int player1counter=0;
    private int tiescounter=0;
    private int player2counter=0;

    private boolean player1first=true;
    private boolean gameover=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

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
                player1first=true;
                gameover=false;
                game =new Gameclass();
                startNewgame();
            }
        });
        info=(TextView)findViewById(R.id.information);
        player1count=(TextView)findViewById(R.id.humancount);
        tiescount=(TextView)findViewById(R.id.tiescount);
        player2count=(TextView)findViewById(R.id.androidcount);

        player1count.setText(Integer.toString(player1counter));
        tiescount.setText(Integer.toString(tiescounter));
        player2count.setText(Integer.toString(player2counter));

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

        if(player1first)
        {
            info.setText(R.string.player1);
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.playx);
            mp.start();
            player1first=false;
        }

        else
        {
            info.setText(R.string.player2);
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.zoop);
            mp.start();
            //int move= game.computerMove();
            //setMove(game.ANDROID_PLAYER,move);
            player1first=true;
        }


    }

    private class ButtonClickListener implements View.OnClickListener
    {
        int location;
        int winner = 0;
        public ButtonClickListener(int location) {
            // TODO Auto-generated constructor stub

            this.location=location;
        }

        public void onClick(View view)
        {
            if(!gameover)
            {
                if(buttons[location].isEnabled())
                {

                    if(player1first == true) {
                        setMove(game.HUMAN_PLAYER, location);
                        final MediaPlayer mp = MediaPlayer.create(Multi_activity.this, R.raw.playx);
                        mp.start();
                        player1first=false;

                         winner = game.checkforwinner();
                    }
                    else if(player1first == false)
                    {
                        setMove(game.ANDROID_PLAYER, location);
                        final MediaPlayer mp = MediaPlayer.create(Multi_activity.this, R.raw.zoop);
                        mp.start();
                        player1first=true;

                        winner = game.checkforwinner();
                    }
                    if(winner == 0)
                    {
                        //info.setText(R.string.player1);
                        //int move= game.computerMove();
                        //setMove(game.ANDROID_PLAYER,move );
                        winner=game.checkforwinner();
                    }

                    if(winner==0)
                    {
                        if(player1first == true)
                           info.setText(R.string.player2);
                        else info.setText(R.string.player1);
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
                        info.setText(R.string.player2win);
                        player1counter++;
                        player1count.setText(Integer.toString(player1counter));
                        gameover=true;
                    }
                    else
                    {
                        info.setText(R.string.player1win);
                        player2counter++;
                        player2count.setText(Integer.toString(player2counter));
                        gameover=true;
                    }
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
            info.setText(R.string.player2);
        }
        else
            buttons[location].setTextColor(Color.RED);
            info.setText(R.string.player1);
    }


}




