import java.util.*;
public class mariokart 
{
    public static void main(String[] args)
    {
        boolean q = false; Scanner obj = new Scanner(System.in); char s = 'o';
        while(q == false)
        {
            System.out.println("Main Menu\n\nEnter S to start\nEnter I to play information about rules and gameplay\nEnter A to get information about the creation of this game\nEnter Q to quit");
            s = obj.next().charAt(0);
            if(s == 's' || s == 'S')
            {
                game();
            }
            else if(s == 'i' || s == 'I')
            {
                info.menu();
            }
            else if(s == 'a' || s == 'A')
            {
                info.about();
            }
            else if(s == 'q' || s == 'Q')
            {
                q = true;
            }
        }
    }
    public static boolean gameQuit;
    public static void game()
    {
        gameQuit = false; int c = 0; boolean displayResults = false;
        command.initialize();
        command.assignCharacters();
        while(gameQuit != true)
        {
            if(c < 5)
            {
                if(kartdriver.getDriverInPos(c+1) == 0)
                {
                    engine.roundPlayer();
                }
                else if(kartdriver.queryFinish(kartdriver.getDriverInPos(c+1)) == false)
                {
                    engine.roundAI(kartdriver.getDriverInPos(c+1));
                }
            }
            else if(c >= 6)
            {
                int[] moves = new int[5]; int max = 0;
                for(int d = 0; d < 5; d++)
                {
                    if(kartdriver.isStop(d))
                    {
                        moves[d] = 0;
                    }
                    else
                    {
                        moves[d] = kartdriver.getSpeed(d);
                        if(moves[d] > max)
                        {
                            max = moves[d];
                        }
                    }
                }
                for(int j = max; j > 0; j--)
                {
                    for(int k = 0; k < 5; k++)
                    {
                        if(moves[kartdriver.getDriverInPos(k+1)] > 0)
                        {
                            kartdriver.setDriverSection(kartdriver.getDriverInPos(k+1), kartdriver.getDriverSection(kartdriver.getDriverInPos(k+1))+1);
                            if(location.bananaCheck(kartdriver.getDriverSection(kartdriver.getDriverInPos(k+1)), kartdriver.getDriverLane(kartdriver.getDriverInPos(k+1))))
                            {
                                command.hit(1, location.getBananaID(kartdriver.getDriverSection(kartdriver.getDriverInPos(k+1)), kartdriver.getDriverLane(kartdriver.getDriverInPos(k+1))), kartdriver.getDriverInPos(k+1));
                                if(kartdriver.isStar(k+1) == false)
                                {
                                    kartdriver.setStopTimer(kartdriver.getDriverInPos(k+1), 2);
                                }
                                location.resetBananaLoc(5, kartdriver.getDriverSection(kartdriver.getDriverInPos(k+1)), kartdriver.getDriverLane(kartdriver.getDriverInPos(k+1)));
                            }
                        }
                        else
                        {
                            moves[kartdriver.getDriverInPos(k+1)]++;
                        }
                        moves[kartdriver.getDriverInPos(k+1)]--;
                        if(kartdriver.getDriverSection(kartdriver.getDriverInPos(k+1))%8 == 0 && kartdriver.hasItem(kartdriver.getDriverInPos(k+1)) == false && kartdriver.getDriverSection(kartdriver.getDriverInPos(k+1)) != 0 && kartdriver.queryFinish(kartdriver.getDriverInPos(k+1)) == false)
                        {
                            box.generateItem(kartdriver.getDriverInPos(k+1));
                            System.out.printf(""+kartdriver.getNameOfDriver(kartdriver.getDriverInPos(k+1))+" got a "+item.getName(kartdriver.getItem(kartdriver.getDriverInPos(k+1)))+"!\n\n");
                        }
                        if(kartdriver.getDriverSection(kartdriver.getDriverInPos(k+1)) >= location.getSections()-10)
                        {
                            command.repositionDrivers();
                            if(kartdriver.getDriverInPos(k+1) == 0)
                            {
                                for(int l = 0; l < 5; l++)
                                {
                                    kartdriver.setDriverSection(kartdriver.getDriverInPos(l+1), (6-l)*location.getSections());
                                    engine.finishRace(kartdriver.getDriverInPos(l+1));
                                    kartdriver.makeFinish(kartdriver.getDriverInPos(l+1));
                                }
                                j = 0; k = 5; gameQuit = true; displayResults = true;
                            }
                            else
                            {
                                kartdriver.setDriverSection(kartdriver.getDriverInPos(k+1), (6-k)*location.getSections());
                                engine.finishRace(kartdriver.getDriverInPos(k+1));
                                kartdriver.makeFinish(kartdriver.getDriverInPos(k+1));
                            }
                        }
                    }
                }
                for(int i = 0; i < 5; i++)
                {
                    kartdriver.updateSmallTimer(i);
                    kartdriver.updateMushroomTimer(i);
                    kartdriver.updateStarTimer(i);
                    kartdriver.updateStopTimer(i);
                }
                command.repositionDrivers();
            }
            c++;
            if(c >= 7)
            {
                c = 0;
            }
        }
        if(displayResults == true)
        {
            System.out.println(); System.out.println();
            engine.resultsTable();
        }
        System.out.println(); System.out.println(); System.out.println();
    }
    public static void quitGame()
    {
        gameQuit = true;
    }
}