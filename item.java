import java.util.ArrayList;
public class item 
{
    //"----","banana","green shell","red shell","bob-omb","blue shell","mushroom","star","lightning";
    private static ArrayList<String> names = new ArrayList<>();
    public static void initializeArrayList()
    {
        names.add("----");
        names.add("banana");
        names.add("green shell");
        names.add("red shell");
        names.add("bob-omb");
        names.add("blue shell");
        names.add("mushroom");
        names.add("star");
        names.add("lightning");
    }
    public static String getName(int i)
    {
        return names.get(i);
    }
    public static boolean canThrow(int i)
    {
        if(i > 0 && i <= 5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean canDrag(int i)
    {
        if(i > 0 && i <= 3)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean makesInvincible(int i)
    {
        if(i == 7)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean givesSpeedBoost(int i)
    {
        if(i == 6 || i == 7)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean canExplode(int i)
    {
        if(i == 4 || i == 5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}