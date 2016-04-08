package appdevtask1.bhargav.updatedtictactoe;

import java.util.Random;

/**
 * Created by lenvopc on 07-04-2016.
 */
public class Gameclass {

    private char Board[];
    private final static int B_size=9;

    public static final char HUMAN_PLAYER= 'X';
    public static final char ANDROID_PLAYER='O';
    public static final char EMPTY_SPACE=' ';

    public Random rndm;

    public static int get_B_Size()
    {
        return B_size;
    }

    public Gameclass()
    {
        Board =new char[B_size];
        for(int i=0;i<B_size;i++)
        {
            Board[i]=EMPTY_SPACE;
        }

        rndm= new Random();
    }

    public void clearBoard()
    {
        for(int i=0;i < B_size ; i++)
        {
            Board[i]=EMPTY_SPACE;
        }
    }

    public void setMove(char player,int location)
    {
        Board[location]=player;
    }

    public int computerMove()
    {
        int move;

        for(int i=0;i< get_B_Size();i++)
        {
            if(Board[i]!=HUMAN_PLAYER && Board[i]!=ANDROID_PLAYER)
            {
                char curr=Board[i];
                Board[i]=ANDROID_PLAYER;
                if(checkforwinner()== 3)
                {
                    setMove(ANDROID_PLAYER,i);
                    return i;
                }
                else
                {
                    Board[i]=curr;
                }
            }
        }

        for(int i=0;i< get_B_Size();i++)
        {
            if(Board[i]!=HUMAN_PLAYER && Board[i]!=ANDROID_PLAYER)
            {
                char curr=Board[i];
                Board[i]=HUMAN_PLAYER;
                if(checkforwinner()== 2)
                {
                    setMove(ANDROID_PLAYER,i);
                    return i;
                }
                else
                {
                    Board[i]=curr;
                }
            }
        }

        do
        {
            move=rndm.nextInt(get_B_Size());
        }while(Board[move]==HUMAN_PLAYER || Board[move]==ANDROID_PLAYER);

        setMove(ANDROID_PLAYER,move);
        return move;
    }

    public int checkforwinner()
    {
        for(int i=0;i<=6;i+=3)
        {
            if(Board[i]== HUMAN_PLAYER &&
                    Board[i+1] == HUMAN_PLAYER &&
                    Board[i+2] == HUMAN_PLAYER )
            {
                return 2;
            }
            if(Board[i]== ANDROID_PLAYER &&
                    Board[i+1] == ANDROID_PLAYER &&
                    Board[i+2] == ANDROID_PLAYER )
            {
                return 3;
            }
        }


        for(int i=0;i<=2;i++)
        {

            if(Board[i]== HUMAN_PLAYER &&
                    Board[i+3] == HUMAN_PLAYER &&
                    Board[i+6] == HUMAN_PLAYER )
            {
                return 2;
            }

            if(Board[i]== ANDROID_PLAYER &&
                    Board[i+3] == ANDROID_PLAYER &&
                    Board[i+6] == ANDROID_PLAYER )
            {
                return 3;
            }
        }

        if(Board[0]==HUMAN_PLAYER && Board[4]==HUMAN_PLAYER && Board[8]==HUMAN_PLAYER  ||
                Board[2]==HUMAN_PLAYER && Board[4]==HUMAN_PLAYER && Board[6]==HUMAN_PLAYER)
            return 2;

        if(Board[0]==ANDROID_PLAYER && Board[4]==ANDROID_PLAYER && Board[8]==ANDROID_PLAYER  ||
                Board[2]==ANDROID_PLAYER && Board[4]==ANDROID_PLAYER && Board[6]==ANDROID_PLAYER)
            return 3;

        for(int i=0;i< B_size ;i++)
        {
            if(Board[i]!=ANDROID_PLAYER && Board[i]!=HUMAN_PLAYER)
            {
                return 0;
            }
        }

        return 1;
    }

}


