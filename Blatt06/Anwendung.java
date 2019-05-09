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
        // Parameter einlesen
        String mode = null;
        BufferedReader file = null;
        try {
            // Man benötigt genau 2 Parameter
            if(args.length != 2)
                throw new InvalidParameterException();
            // Job oder Interval
            if(args[0].equals(INTERVAL_STRING))
                mode = INTERVAL_STRING;
            else if(args[0].equals(JOB_STRING))
                mode = JOB_STRING;
            else
                throw new InvalidParameterException();
            // Datei erzeugen
            file = new BufferedReader(new FileReader(args[1]));

        }catch (Exception e)
        {
            System.out.println(INFO_MESSAGE);
            System.exit(0);
        }
        System.out.println("Bearbeitete Datei \""+ args[1] + "\"");
        // Auswahl von job / Interval abhängig von der eingabe
        if(mode.equals(INTERVAL_STRING))
            interval(file);
        else
            job(file);

    }

    private static void interval(BufferedReader file)
    {
        // Einlesen der intervalle von der datei
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
            System.out.println("Etwas ist schief gegangen. Datei konnte nicht eingelesen weren");
        }
         
        // Ausgabe
        System.out.print("Es wurden " + intervals.size() + " Intervalle eingelesen: ");
        printIntervalList(intervals);

        // Sortieren + Ausgabe
        System.out.print("Sortiert: ");
        Collections.sort(intervals);
        printIntervalList(intervals);

        // Scheduling + ausgabe
        System.out.print("Berechnetes Intervalscheduling: ");
        ArrayList<Interval> schedule = intervalScheduling(intervals);
        printIntervalList(schedule);
    }

    private static void job(BufferedReader file)
    {
        // Jobs einelesen aus der geegebenen datei
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
            System.out.println("Etwas ist schief gegangen. Datei konnte nicht eingelesen werden");
        }
        
        // Ausgabe
        System.out.print("Es wurden " + jobs.size() + " Jobs eingelesen: ");
        printJobList(jobs);

        // Sortieren + ausgabe
        System.out.print("Sortiert: ");
        Collections.sort(jobs);
        printJobList(jobs);

        // Scheduling + ausgabe
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

        // berechnung maximale verspätung + ausgabe
        System.out.print("Berechnete maximale verspätung: ");
        int versp = schedule[schedule.length-1] + jobs.get(jobs.size()-1).getLength() - jobs.get(jobs.size()-1).getDeadline();
        
        // wenn die vorher berechnete verspätung negativ isst, gibt es keine verspätung. Job wird vor deadline fertiggestellt
        versp = versp <= 0 ? 0 : versp;

        System.out.println(versp);
    }

    // Ausgabe einer interval arraylist
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

    // Ausgabe einer Job arraylist
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

    // Greedy Algorithmus für interval scheduling problem
    public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals)
    {
        // kein interval vorhanden -> kein scheduling möglich
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

    // lateness scheduling algorithmus
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