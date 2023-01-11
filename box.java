import java.math.*;
public class box 
{
    public static int rng()
    {
        
        double x = (Math.random()*100);
        int n = (int)x;
        //System.out.printf("\n\n"+n+"\n\n");
        return n;
    }
    public static void generateItem(int j)
    {
        int k = kartdriver.getPosOfDriver(j);
        int m = rng()%20;
        //System.out.printf("\n\nm = "+m+"\n\n");
        if(k == 1)
        {
            if(m >= 0 && m <= 9)
            {
                kartdriver.giveItem(j, 1);
            }
            else if(m >= 10 && m <= 16)
            {
                kartdriver.giveItem(j, 2);
            }
            else if(m >= 17 && m <= 18)
            {
                kartdriver.giveItem(j, 4);
            }
            else
            {
                kartdriver.giveItem(j, 6);
            }
        }
        else if(k == 2)
        {
            if(m >= 0 && m <= 2)
            {
                kartdriver.giveItem(j, 1);
            }
            else if(m >= 3 && m <= 7)
            {
                kartdriver.giveItem(j, 2);
            }
            else if(m >= 8 && m <= 14)
            {
                kartdriver.giveItem(j, 3);
            }
            else if(m >= 15 && m <= 16)
            {
                kartdriver.giveItem(j, 4);
            }
            else
            {
                kartdriver.giveItem(j, 6);
            }
        }
        else if(k == 3)
        {
            if(m >= 0 && m <= 1)
            {
                kartdriver.giveItem(j, 1);
            }
            else if(m >= 2 && m <= 5)
            {
                kartdriver.giveItem(j, 2);
            }
            else if(m >= 6 && m <= 11)
            {
                kartdriver.giveItem(j, 3);
            }
            else if(m >= 12 && m <= 13)
            {
                kartdriver.giveItem(j, 4);
            }
            else if(m >= 14 && m <= 15)
            {
                kartdriver.giveItem(j, 5);
            }
            else if(m >= 16 && m <= 18)
            {
                kartdriver.giveItem(j, 6);
            }
            else
            {
                kartdriver.giveItem(j, 7);
            }
        }
        else if(k == 4)
        {
            if(m >= 0 && m <= 0)
            {
                kartdriver.giveItem(j, 1);
            }
            else if(m >= 1 && m <= 2)
            {
                kartdriver.giveItem(j, 2);
            }
            else if(m >= 3 && m <= 6)
            {
                kartdriver.giveItem(j, 3);
            }
            else if(m >= 7 && m <= 8)
            {
                kartdriver.giveItem(j, 4);
            }
            else if(m >= 9 && m <= 10)
            {
                kartdriver.giveItem(j, 5);
            }
            else if(m >= 11 && m <= 14)
            {
                kartdriver.giveItem(j, 6);
            }
            else if(m >= 15 && m <= 18)
            {
                kartdriver.giveItem(j, 7);
            }
            else
            {
                kartdriver.giveItem(j, 8);
            }
        }
        else
        {
            if(m >= 0 && m <= 0)
            {
                kartdriver.giveItem(j, 1);
            }
            else if(m >= 1 && m <= 1)
            {
                kartdriver.giveItem(j, 2);
            }
            else if(m >= 2 && m <= 2)
            {
                kartdriver.giveItem(j, 3);
            }
            else if(m >= 3 && m <= 4)
            {
                kartdriver.giveItem(j, 4);
            }
            else if(m >= 5 && m <= 6)
            {
                kartdriver.giveItem(j, 5);
            }
            else if(m >= 7 && m <= 11)
            {
                kartdriver.giveItem(j, 6);
            }
            else if(m >= 12 && m <= 16)
            {
                kartdriver.giveItem(j, 7);
            }
            else
            {
                kartdriver.giveItem(j, 8);
            }
        }
    }
}