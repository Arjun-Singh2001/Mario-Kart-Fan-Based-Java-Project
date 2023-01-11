import java.util.*;
public class command 
{
    private static Scanner obj = new Scanner(System.in);
    private static String[] character = {"Mario","Luigi","Peach","Toad","Bowser","Yoshi","Donkey Kong","Wario","Waluigi","Daisy","Koopa Troopa","Lakitu","Shy Guy","Rosalina","Diddy Kong","Funky Kong","Bowser Jr.","Dry Bones","Toadette","King Boo","Dry Bowser","Baby Mario","Baby Luigi","Baby Peach","Baby Daisy"};

    public static void initialize()
    {
        int a = location.getSections();
        int b = location.getLanes();
        item.initializeArrayList();
        for(int j = 0; j < a; j++)
        {
            for(int k = 0; k < b; k++)
            {
                location.resetBananaLoc(5, j, k);
            }
        }
        for(int j = 0; j < 5; j++)
        {
            kartdriver.removeItem(j);
            kartdriver.undragItem(j);
            kartdriver.nameDriver(j, "");
            kartdriver.resetStopStatus(j);
            kartdriver.makeBig(j);
            kartdriver.mushroomOff(j);
            kartdriver.resetMushSpeed(j);
            kartdriver.resetTimers(j);
            kartdriver.resetFinish(j);
            kartdriver.setSpeed(j, 3);
            kartdriver.resetStarSpeed(j);
            engine.resetDisplay(j);
            kartdriver.setDriverPosition(j, 5 - j);
            kartdriver.setDriverLane(j, j);
            kartdriver.setDriverSection(j, j);
        }
    }
    public static void assignCharacters()
    {
        boolean q = false; String u; int[] n = new int[5]; char o = 'o'; String dump;
        System.out.printf("Select Character: \n\n");
        for(int i = 0; i < character.length; i++)
        {
            System.out.printf(character[i]+"\n");
        }
        while(q == false)
        {
            //System.out.printf(""+character.length+"\n");
            System.out.println();
            System.out.printf("Enter character name: ");
            u = obj.nextLine();
            for(int i = 0; i < character.length; i++)
            {
                if(character[i].contains(u))
                {
                    n[0] = i;
                    i = character.length;
                }
            }
            System.out.printf("\nSelection: "+character[n[0]]+"\nIs this who you want?\nY or N: ");
            o = obj.next().charAt(0);
            switch(o)
            {
                case 'Y':
                case 'y':
                    q = true;
                    break;
                default:
                    break;
            }
            dump = obj.nextLine();
        }
        for(int i = 1; i < 5; i++)
        {
            n[i] = box.rng()%character.length;
            for(int h = 0; h < i; h++)
            {
                if(n[i] == n[h])
                {
                    i--;
                }
            }
        }
        for(int i = 0; i < 5; i++)
        {
            kartdriver.nameDriver(i, character[n[i]]);
        }
    }
    /*
    i = item
    j = user
    k = victim
    */

    public static void throwFront(int j)
    {
        int i;
        if(kartdriver.isDraggingItem(j) == true)
        {
            i = kartdriver.getDraggedItem(j);
            kartdriver.undragItem(j);
        }
        else
        {
            i = kartdriver.getItem(j);
            kartdriver.removeItem(j);
        }
        int a; int b;
        int p = kartdriver.getPosOfDriver(j);
        if(item.canThrow(i) == true)
        {
            a = kartdriver.getDriverSection(j);
            b = kartdriver.getDriverLane(j);
            if(i == 1)
            {
                location.updateBananaLoc(j, a+10, b);
            }
            else if(i == 2)
            {
                boolean r = false;
                for(int q = p-1; q > 0; q--)
                {
                    if(kartdriver.getDriverLane(kartdriver.getDriverInPos(q)) == b && kartdriver.getDriverSection(kartdriver.getDriverInPos(q)) > kartdriver.getDriverSection(j))
                    {
                        boolean s = true;
                        for(int n = kartdriver.getDriverSection(kartdriver.getDriverInPos(q)); n > kartdriver.getDriverSection(j); n--)
                        {
                            if(location.bananaCheck(n, kartdriver.getDriverLane(j)) == true)
                            {
                                s = false; r = true;
                                System.out.println(""+kartdriver.getNameOfDriver(j)+"'s green shell hit "+kartdriver.getNameOfDriver(location.getBananaID(n, kartdriver.getDriverLane(j)))+"'s banana!\n");
                                location.resetBananaLoc(5, n, kartdriver.getDriverLane(j));
                            }
                        }
                        if(s == true && kartdriver.isDraggingItem(kartdriver.getDriverInPos(q)) == false)
                        {
                            hit(i, j, kartdriver.getDriverInPos(q));
                            r = true;
                        }
                        else if(s == true && kartdriver.isDraggingItem(kartdriver.getDriverInPos(q)) == true)
                        {
                            block(i, j, kartdriver.getDriverInPos(q));
                            r = true;
                        }
                        q = 0;
                    }
                }
                if(r == false)
                {
                    miss(i, j);
                }
            }
            else if(i == 3)
            {
                if(p != 1 && kartdriver.isDraggingItem(kartdriver.getDriverInPos(p-1)) == false)
                {
                    hit(i, j, kartdriver.getDriverInPos(p-1));
                }
                else if(p != 1 && kartdriver.isDraggingItem(kartdriver.getDriverInPos(p-1)) == true)
                {
                    block(i, j, kartdriver.getDriverInPos(p-1));
                }
                else
                {
                    miss(i, j);
                }
            }
            else if(i == 4)
            {
                for(int q = p-1; q > 0; q--)
                {
                    if(Math.abs(b - kartdriver.getDriverLane(kartdriver.getDriverInPos(q))) < 2 && Math.abs(a + 10 - kartdriver.getDriverSection(kartdriver.getDriverInPos(q))) < 2)
                    {
                        explode(i, j, kartdriver.getDriverInPos(q));
                    }
                }
            }
            else if(i == 5)
            {
                explode(i, j, kartdriver.getDriverInPos(1));
                int c = kartdriver.getDriverSection(kartdriver.getDriverInPos(1));
                int d = kartdriver.getDriverLane(kartdriver.getDriverInPos(1));
                for(int q = 5; q > 1; q--)
                {
                    if(Math.abs(c - kartdriver.getDriverSection(kartdriver.getDriverInPos(q))) < 2 && Math.abs(d - kartdriver.getDriverLane(kartdriver.getDriverInPos(q))) < 2)
                    {
                        explode(i, j, kartdriver.getDriverInPos(q));
                    }
                }
            }
        }
    }
    public static void throwBack(int j)
    {
        int i;
        if(kartdriver.isDraggingItem(j) == true)
        {
            i = kartdriver.getDraggedItem(j);
            kartdriver.undragItem(j);
        }
        else
        {
            i = kartdriver.getItem(j);
            kartdriver.removeItem(j);
        }
        int a; int b;
        int p = kartdriver.getPosOfDriver(j);
        if(item.canThrow(i) == true)
        {
            a = kartdriver.getDriverSection(j);
            b = kartdriver.getDriverLane(j);
            if(i == 1)
            {
                location.updateBananaLoc(j, a-1, b);
            }
            else if(i == 2 || i == 3)
            {
                boolean r = false;
                for(int q = p+1; q < 6; q++)
                {
                    if(kartdriver.getDriverLane(kartdriver.getDriverInPos(q)) == b && kartdriver.getDriverSection(kartdriver.getDriverInPos(q)) <= a)
                    {
                        boolean s = true;
                        for(int n = kartdriver.getDriverSection(kartdriver.getDriverInPos(q)); n < kartdriver.getDriverSection(j); n++)
                        {
                            if(location.bananaCheck(n, kartdriver.getDriverLane(kartdriver.getDriverInPos(q))) == true)
                            {
                                s = false;
                                System.out.println(""+kartdriver.getNameOfDriver(j)+"'s "+item.getName(i)+" hit "+kartdriver.getNameOfDriver(location.getBananaID(n, kartdriver.getDriverLane(j)))+"'s banana!\n");
                                location.resetBananaLoc(5, n, kartdriver.getDriverLane(kartdriver.getDriverInPos(q)));
                            }
                        }
                        if(s == true)
                        {
                            hit(i, j, kartdriver.getDriverInPos(q));
                            r = true;
                        }
                        else
                        {
                            miss(i, j);
                            r = true;
                        }
                        q = 6;
                    }
                }
                if(r == false)
                {
                    miss(i, j);
                }
            }
            else if(i == 4)
            {
                for(int q = p+1; q < 6; q++)
                {
                    if(Math.abs(b - kartdriver.getDriverLane(kartdriver.getDriverInPos(q))) < 2 && Math.abs(a - 2 - kartdriver.getDriverSection(kartdriver.getDriverInPos(q))) < 2)
                    {
                        explode(i, j, kartdriver.getDriverInPos(q));
                    }
                }
            }
            else if(i == 5)
            {
                explode(i, j, kartdriver.getDriverInPos(1));
                int c = kartdriver.getDriverSection(kartdriver.getDriverInPos(1));
                int d = kartdriver.getDriverLane(kartdriver.getDriverInPos(1));
                for(int q = 5; q > 1; q--)
                {
                    if(Math.abs(c - kartdriver.getDriverSection(kartdriver.getDriverInPos(q))) < 2 && Math.abs(d - kartdriver.getDriverLane(kartdriver.getDriverInPos(q))) < 2)
                    {
                        explode(i, j, kartdriver.getDriverInPos(q));
                    }
                }
            }
        }
    }
    public static void use(int j)
    {
        int i = kartdriver.getItem(j);
        if(kartdriver.isDraggingItem(j) == false)
        {
            kartdriver.removeItem(j);
            if(item.canThrow(i) == false && i != 0)
            {
                if(i == 6)
                {
                    kartdriver.setMushroomTimer(j, 2);
                    System.out.println(""+kartdriver.getNameOfDriver(j)+" used a mushroom!\n");
                }
                else if(i == 7)
                {
                    kartdriver.setStarTimer(j);
                    System.out.println(""+kartdriver.getNameOfDriver(j)+" used a star!\n");
                }
                else if(i == 8)
                {
                    for(int q = 1; q < 6; q++)
                    {
                        if(q != kartdriver.getPosOfDriver(j))
                        {
                            shock(i, j, kartdriver.getDriverInPos(q));
                        }
                    }
                }
            }
        }
        else if(i == 6)
        {
            kartdriver.setMushroomTimer(j, 2);
        }
    }
    public static void drag(int i)
    {
        if(item.canDrag(kartdriver.getItem(i)) == true)
        {
            kartdriver.dragItem(i);
        }
    }

    public static void hit(int i, int j, int k)
    {
        if(kartdriver.isStar(k) == false)
        {
            kartdriver.setStopTimer(k, 1);
            System.out.printf(""+kartdriver.getNameOfDriver(j)+" hit "+kartdriver.getNameOfDriver(k)+" with a "+item.getName(i)+"!\n\n");
        }
        else
        {
            block(i, j, k);
        }
    }
    public static void explode(int i, int j, int k)
    {
        if(kartdriver.isStar(k))
        {
            System.out.println(""+kartdriver.getNameOfDriver(k)+" dodged "+kartdriver.getNameOfDriver(j)+"'s "+item.getName(i)+" using a star!\n");
        }
        else if(kartdriver.getItem(k) == 6 && i == 5)
        {
            System.out.println(""+kartdriver.getNameOfDriver(k)+" dodged "+kartdriver.getNameOfDriver(j)+"'s blue shell using a mushroom!\n");
            use(k);
        }
        else
        {
            kartdriver.setStopTimer(k, 2);
            System.out.printf(""+kartdriver.getNameOfDriver(j)+" exploded "+kartdriver.getNameOfDriver(k)+" with a "+item.getName(i)+"!\n\n");
        }
    }
    public static void miss(int i, int j)
    {
        System.out.printf(""+kartdriver.getNameOfDriver(j)+"'s attack with their "+item.getName(i)+" was a miss!\n\n");
    }
    public static void block(int i, int j, int k)
    {
        
        if(kartdriver.isStar(k))
        {
            System.out.println(""+kartdriver.getNameOfDriver(k)+" was protected from "+kartdriver.getNameOfDriver(j)+"'s "+item.getName(i)+" by their star!\n");
        }
        else
        {
            System.out.println(""+kartdriver.getNameOfDriver(k)+" blocked "+kartdriver.getNameOfDriver(j)+"'s "+item.getName(i)+" with a "+item.getName(kartdriver.getDraggedItem(k))+"!\n");
            kartdriver.undragItem(k);
        }
    }
    public static void shock(int i, int j, int k)
    {
        if(kartdriver.isStar(k) == false)
        {
            kartdriver.setSmallTimer(k);
            System.out.printf(""+kartdriver.getNameOfDriver(j)+" shocked "+kartdriver.getNameOfDriver(k)+" with lightning!\n\n");
        }
        else
        {
            block(i, j, k);
        }
    }

    public static void changeLaneAI(int i, int l)
    {
        kartdriver.setDriverLane(i, l);
    }

    public static void changeLanePlayer()
    {
        System.out.printf("Please choose a lane from 1 to 5.\n");
        boolean q = false; int l;
        while(q == false)
        {
            l = obj.nextInt();
            if(l > 0 && l < 6)
            {
                kartdriver.setDriverLane(0, l-1);
                q = true;
            }
            else
            {
                System.out.println("You may only enter an integer between 1 and 5.\nCurrent lane: "+kartdriver.getDriverLane(0)+1);
            }
        }
    }

    public static void repositionDrivers()
    {
        int[] tally = new int[5];
        int[] oldPos = new int[5];
        for(int m = 0; m < 5; m++)
        {
            oldPos[m] = kartdriver.getPosOfDriver(m);
            tally[m] = 0;
        }
        for(int m = 0; m < 5; m++)
        {
            for(int n = 0; n < 5; n++)
            {
                if(m != n)
                {
                    if(kartdriver.getDriverSection(m) > kartdriver.getDriverSection(n))
                    {
                        tally[m]++;
                    }
                    else if(kartdriver.getDriverSection(m) == kartdriver.getDriverSection(n) && oldPos[m] < oldPos[n])
                    {
                        tally[m]++;
                    }
                }
            }
        }
        for(int m = 0; m < 5; m++)
        {
            kartdriver.setDriverPosition(m, 5-tally[m]);
        }
    }
}
