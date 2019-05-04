import java.io.BufferedReader;
import java.io.FileReader;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collections;


class Anwendung
{
    
    public static final String INFO_MESSAGE ="Es werden 2 Parameter benoetigt\n" + 
                                             "\t1.Parameter: Job/Interval\n" + 
                                             "\t2.Parameter: Path";
    public static final String INTERVAL_STRING = "Interval";
    public static final String JOB_STRING = "Job";

    public static void main(String[] args) {

        String mode = null;
        BufferedReader file = null;
        try {
            if(args.length != 2)
                throw new InvalidParameterException();
            if(args[0].equals(INTERVAL_STRING))
                mode = INTERVAL_STRING;
            else if(args[0].equals(JOB_STRING))
                mode = JOB_STRING;
            else
                throw new InvalidParameterException();

            file = new BufferedReader(new FileReader(args[1]));

        }catch (Exception e)
        {
            System.out.println(INFO_MESSAGE);
            System.exit(0);
        }
        System.out.println("Bearbeitete Datei \""+ args[1] + "\"");
        if(mode.equals(INTERVAL_STRING))
            interval(file);
        else
            job(file);

    }

    private static void interval(BufferedReader file)
    {
        ArrayList<Interval> intervals = new ArrayList<>();

        try{
            for(String zeile = file.readLine(); zeile != null; zeile = file.readLine())
            {
                StringTokenizer st = new StringTokenizer(zeile, ",");
                Interval interval = new Interval(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

                intervals.add(interval);
            }
        }catch(Exception e)
        {
            System.out.println("Etwas ist schief gegangen");
        }
                
        System.out.print("Es wurden " + intervals.size() + " Intervalle eingelesen: ");
        printIntervalList(intervals);

        System.out.print("Sortiert: ");
        Collections.sort(intervals);
        printIntervalList(intervals);

        System.out.print("Berechnetes Intervalscheduling: ");
        ArrayList<Interval> schedule = intervalScheduling(intervals);
        printIntervalList(schedule);
    }

    private static void job(BufferedReader file)
    {
        ArrayList<Job> jobs = new ArrayList<>();

        try{
            for(String zeile = file.readLine(); zeile != null; zeile = file.readLine())
            {
                StringTokenizer st = new StringTokenizer(zeile, ",");
                Job job = new Job(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

                jobs.add(job);
            }
        }catch(Exception e)
        {
            System.out.println("Etwas ist schief gegangen");
        }
                
        System.out.print("Es wurden " + jobs.size() + " Jobs eingelesen: ");
        printJobList(jobs);

        System.out.print("Sortiert: ");
        Collections.sort(jobs);
        printJobList(jobs);

        System.out.print("Berechnetes Latenessscheduling: ");
        int[] schedule = latenessScheduling(jobs);
        System.out.print("[");
        for(int i = 0; i < schedule.length; i++)
        {
            if(i != 0)
                System.out.print(", ");
            System.out.print(schedule[i]);
        }
        System.out.println("]");

        System.out.print("Berechnete maximale verspätung: ");
        int versp = schedule[schedule.length-1] + jobs.get(jobs.size()-1).getLength() - jobs.get(jobs.size()-1).getDeadline();
        versp = versp <= 0 ? 0 : versp;

        System.out.println(versp);
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
    public static void printJobList(ArrayList<Job> jobs)
    {
        System.out.print("[");
        for(int i = 0; i < jobs.size(); i++)
        {
            if(i != 0)
                System.out.print(", ");
            System.out.print(jobs.get(i));
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
    public static int[] latenessScheduling(ArrayList<Job> jobs)
    {
        int[] schedule = new int[jobs.size()];
        int z = 0;
        for(int i = 0;i<jobs.size();i++)
        {
            schedule[i] = z;
            z+=jobs.get(i).getLength();
        }
        return schedule;
    }
    
}