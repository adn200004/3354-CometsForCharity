 
import java.util.Date;

public class Employee
{
    private double wage;
    private int ID;//allow only 8-digit numbers beginning with digit 8
    private String first;
    private String last;
    private Date recentClockIn;
    
    public Employee()
    {
        wage = 7.5;
        ID = 0000000;
        first = "";
        last = "";
        recentClockIn = null;
    }
    
    public static boolean validateID(int testID)
    {
        if ((testID < 80000000) || (testID > 89999999))
            return false;
        return true;
    }
    public void setID(int i)
    {
        if (validateID(i) == false)
        {
            System.out.println("Error: Invalid Employee ID");
        }
        else
        {
            ID = i;
        }
    }
    public int getID()
    {
        return ID;
    }
    
    public String getFirst()
    {
        return first;
    }
    public String getLast()
    {
        return last;
    }
    public double getWage()
    {
        return wage;
    }
    public Date getMostRecentClockIn()
    {
        return recentClockIn;
    }
    
    public void setFirst(String f)
    {
        if (f == null)
        {
            first = "";
        }
        else
        {
            first = f;
        }
    }
    public void setLast(String l)
    {
        if (l == null)
        {
            last = "";
        }
        else
        {
            last = l;
        }
    }
    public void setWage(double w)
    {
        wage = w;
    }
    public void setMostRecentClockIn(Date d)
    {
        if (d != null)
        {
            if (recentClockIn == null)
            {
                recentClockIn = new Date();
            }
            recentClockIn.setTime(d.getTime());
        }
        else
        {
            recentClockIn = null;
        }
    }
    
    public boolean equals(Employee e)
    {
        //two employees considered equal if ID's match
        if (e.ID == ID)
        {
            return true;
        }
        return false;
    }
    public String toString()
    {
        String temp = "";
        
        temp = "<html>Name: " + first + " " + last + "<br>  ";
        temp += "  Wage: " + String.format("%.2f", wage) + "<br>  ";
        temp += "  ID:   " + ID + "<br></html>";
        return temp;
    }
    
}
