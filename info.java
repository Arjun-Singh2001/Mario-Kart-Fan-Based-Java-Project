import java.util.Scanner;

public class info 
{
    private static Scanner obj = new Scanner(System.in);
    public static void menu()
    {
        boolean q = false; char s = 'o';
        while(q != true)
        {
            System.out.printf("\n\nInformation Menu\n\nOptions:\n\nR: get information about racing and rules\nI: get information about items\nB: get item box distribution table\nQ: return to previous menu\n");
            s = obj.next().charAt(0);
            if(s == 'R' || s == 'r')
            {
                race();
            }
            else if(s == 'I' || s == 'i')
            {
                items();
            }
            else if(s == 'B' || s == 'b')
            {
                box();
            }
            else if(s == 'Q' || s == 'q')
            {
                q = true;
            }
            else
            {
                System.out.println("\n\nError: unknown command\n");
            }
            System.out.println(); System.out.println();
        }
    }
    
    public static void race()
    {
        System.out.printf("\n\nRacing and Rules Information\n\n");
        System.out.println("In this game, you race with 4 other drivers in a competition for first place.\nRaces take place on a 100-meter-long track with 5 lanes, each 1 meter wide.");
        System.out.println("You start in 5th place in Lane 1 with 4th place 1 meter ahead in Lane 2, 3rd 2 meters ahead in Lane 3, 2nd 3 meters ahead in Lane 4, and 1st 4 meters ahead in Lane 5.");
        System.out.println("The finish line is 100 meters away from where you start.\nEvery 8 meters starting at the 8th meter is an item box.");
        System.out.println("Item boxes give you items, which are necessary to passing other drivers and winning the race.\nEvery driver has a normal speed of 3 meters per round (written in meters per seconds).");
        System.out.println("Some items will make you faster, while others may slow or stop other drivers.\nOther drivers may also use items against you.\nPassing another driver requires getting at least 1 meter ahead, otherwise you will remain behind them.");
        System.out.println("This rule also applies to the other drivers.\nHappy karting!\n");
    }
    public static void items()
    {
        System.out.printf("\n\nItem Information\n\n");
        System.out.println("Items are used to gain positions, harass opponents, and steal victory.\nTheir are 8 different items, each with their own properties and abilities.");
        System.out.println("These incluse the ability to drag, which protects you from certain attacks from behind, as well as the ability to receive another item as you drag the first.");
        System.out.println("Not all items can be dragged, however.\nWhen an item is being dragged, it must be thrown before a driver can interact the item in their main slot.\nNon-explosive items attacks will stop drivers for 1 round, and explosive item attacks will stop drivers for 2 rounds.\n\nHere is the list of items:");
        System.out.println("Banana: can be dragged, thrown forward, or thrown backward. When thrown, it becomes a hazard on the track. Thrown forward, it will be 10 meters ahead of the thrower. Thrown behind, it will be 1 meter behind the thrower.");
        System.out.println("Green Shell: can be dragged, thrown forward, or thrown backward. When thrown, it travels either ahead or behind the driver while remaining in the driver's lane.\n     It can be blocked by a banana on the track or by a dragged item if approaching its target from behind.");
        System.out.println("Red Shell: can be dragged, thrown forward, or thrown backward. When thrown forward, it targets the driver in the position immediately ahead of its thrower, and can be blocked by a dragged item.\n     If the thrower is in 1st, it will have no effect when thrown forward. When thrown backward, it behives identically to a green shell.");
        System.out.println("Bob-omb: can be thrown forward or backward. Creates an immediate explosion that affects any driver in a 3x3 area. Thrown forward, the center is 10 meters ahead of the thrower, thrown behind, it is 2 meters behind the thrower.");
        System.out.println("Blue Shell: can be thrown in either direction, but has the same behavior either way: creates a 3x3 explosion centered on the driver in 1st place.");
        System.out.println("Mushroom: when used, it gives a 2-round speed boost. If a driver is within range of a blue shell explosion while holding a mushroom, and is not under the effect of a star,\n     the mushroom will be used automatically to dodge the blue shell, regardless of whether or not an item is being dragged.\n     Using another mushroom while under the effect a first will add on to the first boost and extend the effect of the first boost until the second boost runs out.");
        System.out.println("Star: gives a 4-round speed boost and immunity to the effects of any type of attack from any item. Mushroom speed is added independently and will not be affected by the star's speed boost.\n     However, unlike mushrooms, the speed boost from a star cannot be increased by another star.");
        System.out.println("Lightning: shocks all other drivers, unless they are under the effect of a star. This shock will stop each affected driver for 1 round and then cause a small reduction in speed for the next 2 rounds.\n");
    }
    public static void box()
    {
        String[][] table = new String[10][7]; int[] max = {0,0,0,0,0,0,0,0,0,0};
        table[0][0] = " "; table[0][1] = " "; table[0][2] = "1st"; table[0][3] = "2nd"; table[0][4] = "3rd"; table[0][5] = "4th"; table[0][6] = "5th";
        table[1][0] = " "; table[1][1] = " "; table[1][2] = " "; table[1][3] = " "; table[1][4] = " "; table[1][5] = " "; table[1][6] = " ";
        table[2][0] = "Banana"; table[2][1] = ""; table[2][2] = "50"; table[2][3] = "15"; table[2][4] = "10"; table[2][5] = " 5"; table[2][6] = " 5";
        table[3][0] = "Green Shell"; table[3][1] = " "; table[3][2] = "35"; table[3][3] = "25"; table[3][4] = "20"; table[3][5] = "10"; table[3][6] = " 5";
        table[4][0] = "Red Shell"; table[4][1] = " "; table[4][2] = " "; table[4][3] = "35"; table[4][4] = "30"; table[4][5] = "20"; table[4][6] = " 5";
        table[5][0] = "Bob-omb"; table[5][1] = " "; table[5][2] = "10"; table[5][3] = "10"; table[5][4] = "10"; table[5][5] = "10"; table[5][6] = "10";
        table[6][0] = "Blue Shell"; table[6][1] = " "; table[6][2] = " "; table[6][3] = ""; table[6][4] = "10"; table[6][5] = "10"; table[6][6] = "10";
        table[7][0] = "Mushroom"; table[7][1] = " "; table[7][2] = " 5"; table[7][3] = "15"; table[7][4] = "15"; table[7][5] = "20"; table[7][6] = "25";
        table[8][0] = "Star"; table[8][1] = " "; table[8][2] = ""; table[8][3] = ""; table[8][4] = " 5"; table[8][5] = "20"; table[8][6] = "25";
        table[9][0] = "Lightning"; table[9][1] = " "; table[9][2] = " "; table[9][3] = " "; table[9][4] = " "; table[9][5] = " 5"; table[9][6] = "15";
        for(int j = 0; j < 7; j++)
        {
            for(int k = 0; k < 10; k++)
            {
                if(table[k][j].length() > max[k])
                {
                    max[k] = table[k][j].length();
                }
            }
        }
        System.out.printf("\n\n\n");
        for(int j = 0; j < 7; j++)
        {
            for(int k = 0; k < 10; k++)
            {
                for(int l = 0; l < max[k] - table[k][j].length(); l++)
                {
                    System.out.printf(" ");
                }
                System.out.printf(table[k][j]);
                for(int l = 0; l < 5; l++)
                {
                    System.out.printf(" ");
                }
            }
            System.out.println();
        }
        System.out.printf("\n\n");
    }

    public static void about()
    {
        System.out.printf("\n\nThis game was programmed April-May 2021 for Introduction to Computer Science I at Towson University.\nCreators: Timothy DeLloyd and Arjun Singh\n\nThis game is inspired by the Mario Kart franchise produced by Nintendo.\nThe creators of this game are not affiliated with Nintendo in any way.\n\n--Timothy DeLloyd\nMay 10, 2021\n\n");
    }
}