import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collections;


class Anwendung
{
    
    public static final String INFO_MESSAGE ="asd";
    public static void main(String[] args) {


        ArrayList<Interval> intervals = new ArrayList<>();

        try {
            BufferedReader file = new BufferedReader(new FileReader(args[0]));
            for(String zeile = file.readLine(); zeile != null; zeile = file.readLine())
            {
                StringTokenizer st = new StringTokenizer(zeile, ",");
                Interval interval = new Interval(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

                intervals.add(interval);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(INFO_MESSAGE);
            System.exit(0);
        }
        
        System.out.print("Es wurden " + intervals.size() + " Intervalle eingelesen: ");
        printIntervalList(intervals);

        System.out.print("Sortiert: ");
        Collections.sort(intervals);
        printIntervalList(intervals);

        System.out.print("Berechnetes interval scheduling: ");
        ArrayList<Interval> schedule = intervalScheduling(intervals);
        printIntervalList(schedule);

    }

    public static void printIntervalList(ArrayList<Interval> intervals)
    {
        System.out.print("[");
        for(int i = 0; i < intervals.size(); i++)
        {
            if(i != 0)
                System.out.print(", ");
            System.out.print(intervals.get(i));
        }
        System.out.println("]");
    }

    public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals)
    {
        if (intervals == null)
            return null;
        if(intervals.size() == 0)
            return null;


        ArrayList<Interval> ret = new ArrayList<>();
        ret.add(intervals.get(0));

        int j = 0;
        for(int i = 1; i < intervals.size(); i++)
        {
            if(intervals.get(i).getStart() >= intervals.get(j).getEnd())
            {
                ret.add(intervals.get(i));
                j = i;
            }
        }

        return ret;
    }
}