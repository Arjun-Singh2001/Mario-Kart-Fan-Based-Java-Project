public class kartdriver 
{
    private static int[] pos = new int[5];
    private static int[] driverItem = new int[5];
    private static int[] draggedItem = new int[5];
    private static boolean[] itemDrag = new boolean[5];
    private static String[] name = new String[5];
    private static int[] speed = new int[5];
    private static int[] mushSpeed = new int[5];
    private static int[] starSpeed = new int[5];
    private static boolean[] mushroom = new boolean[5];
    private static boolean[] star = new boolean[5];
    private static boolean[] small = new boolean[5];
    private static boolean[] stop = new boolean[5];
    private static boolean[] isFinish = new boolean[5];
    private static int[] lane = new int[5];
    private static int[] section = new int[5];
    private static int[] mushroomTimer = new int[5];
    private static int[] stopTimer = new int[5];
    private static int[] smallTimer = new int[5];
    private static int[] starTimer = new int[5];

    public static void nameDriver(int i, String j)
    {
        name[i] = j;
    }
    public static String getNameOfDriver(int i)
    {
        return name[i];
    }

    public static int getPosOfDriver(int i)
    {
        return pos[i];
    }
    public static int getDriverInPos(int i)
    {
        int s = 6;
        for(int n = 0; n < 5; n++)
        {
            if(pos[n] == i)
            {
                s = n;
            }
        }
        return s;
    }
    public static void setDriverPosition(int i, int j)
    {
        pos[i] = j;
    }

    public static int getDriverLane(int i)
    {
        return lane[i];
    }
    public static int getDriverSection(int i)
    {
        return section[i];
    }
    public static void setDriverSection(int i, int j)
    {
        section[i] = j;
    }
    public static void setDriverLane(int i, int j)
    {
        lane[i] = j;
    }

    public static int getSpeed(int i)
    {
        return speed[i];
    }
    public static void setSpeed(int i, int j)
    {
        speed[i] = j;
    }
    public static void updateSpeed(int i, int j)
    {
        speed[i] += j;
    }
    public static boolean isStar(int i)
    {
        return star[i];
    }

    public static int getMushSpeed(int i)
    {
        return mushSpeed[i];
    }
    public static void addMushSpeed(int i, int j)
    {
        mushSpeed[i] += j;
    }
    public static void resetMushSpeed(int i)
    {
        mushSpeed[i] = 0;
    }

    public static int getStarSpeed(int i)
    {
        return starSpeed[i];
    }
    public static void addStarSpeed(int i, int j)
    {
        starSpeed[i] += j;
    }
    public static void resetStarSpeed(int i)
    {
        starSpeed[i] = 0;
    }

    public static boolean isSmall(int i)
    {
        return small[i];
    }
    public static boolean isStop(int i)
    {
        return stop[i];
    }
    public static boolean isMushroom(int i)
    {
        return mushroom[i];
    }

    public static void makeStar(int i)
    {
        star[i] = true;
    }
    public static void makeNotStar(int i)
    {
        star[i] = false;
    }
    public static void makeSmall(int i)
    {
        if(small[i] == false && isStar(i) == false)
        {
            small[i] = true;
        }
    }
    public static void makeBig(int i)
    {
        small[i] = false;
        updateSpeed(i, 1);
    }
    public static void resetStopStatus(int i)
    {
        stop[i] = false;
    }
    public static void mushroomOn(int i)
    {
        mushroom[i] = true;
    }
    public static void mushroomOff(int i)
    {
        mushroom[i] = false;
    }

    public static void setMushroomTimer(int i, int t)
    {
        mushroomOn(i);
        mushroomTimer[i] = 2;
        updateSpeed(i, 2);
        addMushSpeed(i, 2);
    }
    public static void updateMushroomTimer(int i)
    {
        mushroomTimer[i] -= 1;
        if(mushroomTimer[i] <= 0 && isMushroom(i))
        {
            updateSpeed(i, -(getMushSpeed(i)));
            resetMushSpeed(i);
            mushroomTimer[i] = 0;
        }
        else if(mushroomTimer[i] <= 0)
        {
            mushroomTimer[i] = 0;
        }
    }
    public static void setStopTimer(int i, int t)
    {
        stopTimer[i] = t;
        stop[i] = true;
    }
    public static void updateStopTimer(int i)
    {
        stopTimer[i] -= 1;
        if(stopTimer[i] <= 0)
        {
            stopTimer[i] = 0;
            stop[i] = false;
        }
    }
    public static void setSmallTimer(int i)
    {
        smallTimer[i] = 3;
        if(isSmall(i) == false && isStar(i) == false)
        {
            updateSpeed(i, -1);
            makeSmall(i);
            setStopTimer(i, 1);;
        }
        else if(isStar(i) == false)
        {
            setStopTimer(i, 1);
        }
    }
    public static void updateSmallTimer(int i)
    {
        smallTimer[i] -= 1;
        if(smallTimer[i] <= 0)
        {
            smallTimer[i] = 0;
            if(isSmall(i) == true)
            {
                makeBig(i);
            }
        }
    }
    public static void setStarTimer(int i)
    {
        starTimer[i] = 4;
        if(isStar(i) == false)
        {
            makeStar(i);
            updateSpeed(i, 2);
            addStarSpeed(i, 2);
        }
    }
    public static void updateStarTimer(int i)
    {
        starTimer[i] -= 1;
        if(starTimer[i] == 1)
        {
            System.out.println(""+getNameOfDriver(i)+"'s star is running out!\n");
        }
        if(starTimer[i] <= 0)
        {
            if(star[i] == true)
            {
                updateSpeed(i, -getStarSpeed(i));
                resetStarSpeed(i);
                makeNotStar(i);
            }
        }
    }
    public static void resetTimers(int i)
    {
        mushroomTimer[i] = 0;
        starTimer[i] = 0;
        smallTimer[i] = 0;
        stopTimer[i] = 0;
    }

    public static void dragItem(int i)
    {
        itemDrag[i] = true;
        draggedItem[i] = driverItem[i];
        driverItem[i] = 0;
    }
    public static void undragItem(int i)
    {
        itemDrag[i] = false;
        draggedItem[i] = 0;
    }
    public static boolean isDraggingItem(int i)
    {
        return itemDrag[i];
    }
    public static int getDraggedItem(int i)
    {
        return draggedItem[i];
    }
    public static int getItem(int i)
    {
        return driverItem[i];
    }
    public static boolean hasItem(int i)
    {
        if(driverItem[i] != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static void giveItem(int i, int j)
    {
        driverItem[i] = j;
    }
    public static void removeItem(int i)
    {
        driverItem[i] = 0;
    }
    
    public static void makeFinish(int i)
    {
        isFinish[i] = true;
    }
    public static void resetFinish(int i)
    {
        isFinish[i] = false;
    }
    public static boolean queryFinish(int i)
    {
        return isFinish[i];
    }
}