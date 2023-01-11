import java.util.*;
import java.math.*;
public class engine 
{
    private static Scanner obj = new Scanner(System.in);
    private static boolean[] display = new boolean[5];

    public static void resetDisplay(int i)
    {
        display[i] = true;
    }
    public static void finishRace(int i)
    {
        display[i] = false;
    }

    public static void moveDrivers()
    {
        int d; int s;
        for(int i = 0; i < 5; i++)
        {
            d = kartdriver.getDriverInPos(5-i);
            s = kartdriver.getSpeed(d);
            kartdriver.setDriverSection(d, kartdriver.getDriverSection(d)+s);
        }
        command.repositionDrivers();
    }
    public static void roundAI(int i)
    {
        int m = box.rng()%50;
        int l = box.rng()%50;
        if(l < 20)
        {
            command.changeLaneAI(i, (l%5));
        }
        l = box.rng()%50;
        if(m < 30 && kartdriver.isDraggingItem(i))
        {
            if(m < 20)
            {
                command.throwFront(i);
            }
            else
            {
                command.throwBack(i);
            }
        }
        else if(m < 40 && kartdriver.isDraggingItem(i) == false && kartdriver.getItem(i) != 4 && kartdriver.getItem(i) != 5)
        {
            if(m < 25 && item.canDrag(kartdriver.getItem(i)))
            {
                command.drag(i);
            }
            else if(m < 25 && item.canThrow(kartdriver.getItem(i)) == false)
            {
                command.use(i);
            }
            else if(m < 33 && item.canThrow(kartdriver.getItem(i)))
            {
                command.throwFront(i);
            }
            else if(item.canThrow(kartdriver.getItem(i)))
            {
                command.throwBack(i);
            }
        }
        else if(kartdriver.getItem(i) == 4 || kartdriver.getItem(i) == 5)
        {
            if(m < 20)
            {
                command.throwFront(i);
            }
            else if(m < 30)
            {
                command.throwBack(i);
            }
        }
        if(l < 20)
        {
            command.changeLaneAI(i, (l%5));
        }
    }
    public static void scan()
    {
        for(int j = kartdriver.getDriverSection(0); j < kartdriver.getDriverSection(0)+5; j++)
        {
            for(int k = 0; k < 5; k++)
            {
                if(location.bananaCheck(j, k))
                {
                    System.out.println("Warning: banana in Lane "+(k+1));
                    System.out.println();
                }
            }
        }
        System.out.println(); System.out.println();
        System.out.printf("Current Lane: "+(kartdriver.getDriverLane(0)+1)+"\n\n");
        displayTable();
    }
    public static void displayTable()
    {
        String[][] table = new String[6][7]; int[] max = {0,0,0,0,0,0,0};
        table[0][0] = "Position"; table[0][1] = "Character"; table[0][2] = "Item"; table[0][3] = "Dragged Item"; table[0][4] = "Distance"; table[0][5] = "Speed"; table[0][6] = "Lane";
        table[1][0] = "1st"; table[2][0] = "2nd"; table[3][0] = "3rd"; table[4][0] = "4th"; table[5][0] = "5th";
        for(int i = 0; i < 5; i++)
        {
            table[i+1][1] = kartdriver.getNameOfDriver(kartdriver.getDriverInPos(i+1));
        }
        for(int i = 0; i < 5; i++)
        {
            if(display[kartdriver.getDriverInPos(i+1)] == true)
            {
                table[i+1][2] = item.getName(kartdriver.getItem(kartdriver.getDriverInPos(i+1)));
            }
            else
            {
                table[i+1][2] = "Finish";
            }
        }
        for(int i = 0; i < 5; i++)
        {
            if(display[kartdriver.getDriverInPos(i+1)] == true)
            {
                table[i+1][3] = item.getName(kartdriver.getDraggedItem(kartdriver.getDriverInPos(i+1)));
            }
            else
            {
                table[i+1][3] = "";
            }
        }
        for(int i = 0; i < 5; i++)
        {
            if(display[kartdriver.getDriverInPos(i+1)] == true)
            {
                table[i+1][4] = String.format("%4.0fm", (double)(kartdriver.getDriverSection(kartdriver.getDriverInPos(i+1))));
            }
            else
            {
                table[i+1][4] = "";
            }
        }
        for(int i = 0; i < 5; i++)
        {
            if(display[kartdriver.getDriverInPos(i+1)] == true && kartdriver.isStop(kartdriver.getDriverInPos(i+1)) == false)
            {
                table[i+1][5] = String.format("%2.0fm/s", (double)(kartdriver.getSpeed(kartdriver.getDriverInPos(i+1))));
            }
            else if(display[kartdriver.getDriverInPos(i+1)] == true && kartdriver.isStop(kartdriver.getDriverInPos(i+1)) == true)
            {
                table[i+1][5] = String.format("%2.0fm/s", (double)(0));
            }
            else
            {
                table[i+1][5] = "";
            }
        }
        for(int i = 0; i < 5; i++)
        {
            if(display[kartdriver.getDriverInPos(i+1)] == true)
            {
                table[i+1][6] = String.format("%2.0f", (double)(kartdriver.getDriverLane(kartdriver.getDriverInPos(i+1))+1));
            }
            else
            {
                table[i+1][6] = "";
            }
        }
        for(int j = 0; j < 6; j++)
        {
            for(int k = 0; k < 7; k++)
            {
                if(table[j][k].length() > max[k])
                {
                    max[k] = table[j][k].length();
                }
            }
        }
        for(int j = 0; j < 6; j++)
        {
            for(int k = 0; k < 7; k++)
            {
                System.out.printf(table[j][k]);
                for(int l = max[k] + 5; l > table[j][k].length(); l--)
                {
                    System.out.printf(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void resultsTable()
    {
        String[][] table = new String[6][2]; int[] max = {0,0};
        table[0][0] = "Position"; table[0][1] = "Character";
        table[1][0] = "1st"; table[2][0] = "2nd"; table[3][0] = "3rd"; table[4][0] = "4th"; table[5][0] = "5th";
        for(int i = 0; i < 5; i++)
        {
            table[i+1][1] = kartdriver.getNameOfDriver(kartdriver.getDriverInPos(i+1));
        }
        for(int j = 0; j < 6; j++)
        {
            for(int k = 0; k < 2; k++)
            {
                if(table[j][k].length() > max[k])
                {
                    max[k] = table[j][k].length();
                }
            }
        }
        for(int j = 0; j < 6; j++)
        {
            for(int k = 0; k < 2; k++)
            {
                System.out.printf(table[j][k]);
                for(int l = max[k] + 5; l > table[j][k].length(); l--)
                {
                    System.out.printf(" ");
                }
            }
            System.out.println();
        }
        System.out.println(); System.out.println();
        if(kartdriver.getPosOfDriver(0) == 1)
        {
            System.out.printf("Congratulations! "+kartdriver.getNameOfDriver(0)+" got first place!");
        }
        else if(kartdriver.getPosOfDriver(0) == 2)
        {
            System.out.printf("Great job! "+kartdriver.getNameOfDriver(0)+" got second place!");
        }
        else if(kartdriver.getPosOfDriver(0) == 3)
        {
            System.out.printf("Good job! "+kartdriver.getNameOfDriver(0)+" got third place!");
        }
        else if(kartdriver.getPosOfDriver(0) == 4)
        {
            System.out.printf("Better luck next time! "+kartdriver.getNameOfDriver(0)+" got fourth place!");
        }
        else if(kartdriver.getPosOfDriver(0) == 5)
        {
            System.out.printf("Sorry, you lose! "+kartdriver.getNameOfDriver(0)+" got fifth place!");
        }
    }
    public static void roundPlayer()
    {
        boolean q = false; char s = 'o';
        System.out.printf("\nYour turn!\n\n");
        while(q == false)
        {
            scan();
            System.out.printf("\n\nOptions:\n\n");
            boolean a = false; boolean b = false; boolean c = false;
            System.out.printf("L: change lane\n");
            if(kartdriver.isDraggingItem(0))
            {
                System.out.println("F: throw "+item.getName(kartdriver.getDraggedItem(0))+" forward");
                System.out.println("B: throw "+item.getName(kartdriver.getDraggedItem(0))+" backward");
                a = true;
            }
            else if(kartdriver.hasItem(0))
            {
                if(item.canThrow(kartdriver.getItem(0)))
                {
                    System.out.println("F: throw "+item.getName(kartdriver.getItem(0))+" forward");
                    System.out.println("B: throw "+item.getName(kartdriver.getItem(0))+" backward");
                    a = true;
                    if(item.canDrag(kartdriver.getItem(0)) == true && kartdriver.isDraggingItem(0) == false)
                    {
                        System.out.println("D: drag "+item.getName(kartdriver.getItem(0)));
                        b = true;
                    }
                }
                else
                {
                    System.out.println("U: use "+item.getName(kartdriver.getItem(0)));
                    c = true;
                }
            }
            System.out.printf("E: end turn\n");
            System.out.printf("Q: quit game\n");
            s = obj.next().charAt(0);
            System.out.println();
            if((s == 'F' || s == 'f') && a == true)
            {
                command.throwFront(0);
            }
            else if((s == 'B' || s == 'b') && a == true)
            {
                command.throwBack(0);
            }
            else if((s == 'U' || s == 'u') && c == true)
            {
                command.use(0);
            }
            else if((s == 'L' || s == 'l'))
            {
                command.changeLanePlayer();
            }
            else if((s == 'D' || s == 'd') && b == true)
            {
                command.drag(0);
            }
            else if((s == 'E' || s == 'e'))
            {
                q = true;
            }
            else if((s == 'Q' || s == 'q'))
            {
                mariokart.quitGame();
                q = true;
            }
            else if((s == 'F' || s == 'f') || (s == 'B' || s == 'b') || (s == 'U' || s == 'u') || (s == 'D' || s == 'd'))
            {
                System.out.println("Error: command not available");
            }
            else
            {
                System.out.println("Error: command does not exist");
            }
            System.out.println();
        }
        System.out.println(); System.out.println();
    }
}