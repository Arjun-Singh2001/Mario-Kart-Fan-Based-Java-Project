public class location 
{
    private static int sections = 110;
    private static int lanes = 5;
    private static boolean[][] trackBanana = new boolean[sections][lanes];
    private static int[][] bananaID = new int[sections][lanes];

    public static void updateBananaLoc(int h, int j, int k)
    {
        trackBanana[j][k] = true;
        bananaID[j][k] = h;
    }
    public static void resetBananaLoc(int h, int j, int k)
    {
        trackBanana[j][k] = false;
        bananaID[j][k] = h;
    }
    public static int getBananaID(int j, int k)
    {
        return bananaID[j][k];
    }
    public static boolean bananaCheck(int j, int k)
    {
        if(j < 0 || j > 49)
        {
            j = 0;
        }
        return trackBanana[j][k];
    }
    public static int getSections()
    {
        return sections;
    }
    public static int getLanes()
    {
        return lanes;
    }
}