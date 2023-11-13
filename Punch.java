 
import java.util.Date;


public class Punch
{
    //STILL NEED TO DO: fill out the toString method****
    //time in, time out
    private Date timeIn;
    private Date timeOut;
    private boolean PTO;//true if the elapsed time was PTO hours used
    private double wage;
    
    Punch(Date in, Date out, double w)
    {
        timeIn = in;
        timeOut = out;
        PTO = false;
        wage = w;
    }
    Punch(Date in, Date out, double w, boolean p)
    {
        timeIn = in;
        timeOut = out;
        PTO = p;
        wage = w;
    }
    
    
    public Date getTimeIn()
    {
        return timeIn;
    }
    public Date getTimeOut()
    {
        return timeOut;
    }
    public boolean getPTO()
    {
        return PTO;
    }
    public void setTimeIn(Date d)
    {
        timeIn.setTime(d.getTime());
    }
    public void setTimeOut(Date d)
    {
        timeOut.setTime(d.getTime());
    }
    public void setPTO(boolean p)
    {
        PTO = p;
    }
    
    public double getElapsedTime()
    {
        return ((double)(timeOut.getTime() - timeIn.getTime()))/((double)(3600000));
    }
    public double getWageEarned()
    {
        return (wage) * ((double)(timeOut.getTime() - timeIn.getTime()))/((double)(3600000));
    }
    
    public String toString()
    {
        String s = "";
        s += timeIn + ", " + timeOut +", " + wage + ", " + PTO;
        return s;
    }
    public String toCSV()
    {
        String tempString = "";
        tempString = timeIn + "," + timeOut +"," + wage + "," + PTO;
        return tempString;
    }
}