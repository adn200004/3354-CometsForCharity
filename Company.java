 
import java.util.Date;


public class Company
{
    private LinkedList<Employee> employees;
    private HashTable<Punch> employeePunches;
    private HashTable<Punch> priorEmployeePunches;
        //here, at each entry in the hashtable, use the separate chain method; 
        //linkedlist of each employee whose ID is hashed to that array location, 
        //then use a linkedlist with headers to display whose info is whose; 
            //each linkedlist node contains another linkedlist 
            //(containing an employee's punch record) and a separate 
            //data field, which holds the employee's ID number to whom
            //the second level linkedlist of punches belongs to
    private double runningHours;
    private double runningExpense;
    private double oldHours;
    private double oldExpense;
    private String name;
    int payDay1;//these fields store what day of the month employees get paid
    int payDay2;
     
    
    public Company()
    {
        employees = new LinkedList<Employee>();
        employeePunches = new HashTable<Punch>();
        priorEmployeePunches = new HashTable<Punch>();
        runningHours = 0.0;
        runningExpense = 0.0;
        oldHours = 0.0;
        oldExpense = 0.0;
        name = "Company_XYZ";
        payDay1 = 1;
        payDay2 = 16;
    }
    
    public void setEmployeePunches(HashTable<Punch> ht, int newLoad)
    {
        if (ht != null)
        {
            employeePunches = ht;
            employeePunches.setLoad(newLoad);
        }
    }
    public int getEmployeeTableSize()
    {
        return employeePunches.getSize();
    }
    public void setPriorEmployeePunches(HashTable<Punch> ht, int newLoad)
    {
        if (ht != null)
        {
            priorEmployeePunches = ht;
            priorEmployeePunches.setLoad(newLoad);
        }
    }
    public int getPriorEmployeeTableSize()
    {
        return priorEmployeePunches.getSize();
    }
    
    public void punchInEmployee(int ID, Date punchTime)
    {
        Employee employee = getEmployee(ID);
        if (employee.getMostRecentClockIn() != null)
        {
            //employee is trying to clock in when they are still clocked in - 
                //forgot to clock out
            Punch newPunch = new Punch(employee.getMostRecentClockIn(), employee.getMostRecentClockIn(), employee.getWage());
            employeePunches.insert(newPunch, ID);
            employee.setMostRecentClockIn(null);
        }
        employee.setMostRecentClockIn(punchTime);
    }
    public void punchOutEmployee(int ID, Date punchTimeOG)
    {
        Date punchTime = new Date();
        if (punchTimeOG != null)
        {
            punchTime.setTime(punchTimeOG.getTime());
        }
        Employee employee = getEmployee(ID);
        Punch newPunch = new Punch(employee.getMostRecentClockIn(), punchTime, employee.getWage());
        if (newPunch.getTimeIn() == null)
        {
            newPunch.setTimeIn(punchTime);
        }
        employeePunches.insert(newPunch, ID);
        if (employee.getMostRecentClockIn() != null)
        {
            //employee has both a start and end time for this shift
            //need to add their hours and hours * wages to running totals
            runningHours += (((double)(newPunch.getTimeOut().getTime() - newPunch.getTimeIn().getTime()))/((double)(3600000)));
            runningExpense += (employee.getWage()) * (((double)(newPunch.getTimeOut().getTime() - newPunch.getTimeIn().getTime()))/((double)(3600000)));
        }
        employee.setMostRecentClockIn(null);
    }
    public LinkedList<Employee> getEmployeesList()
    {
        return employees;
    }
    public Object[] getEmployees()
    {
        return employees.toArray();
    }
    public Employee getEmployee(int ID)
    {
        Object[] empArr = employees.toArray();
        for (int a = 0; a < empArr.length; a += 1)
        {
            if (((Employee)empArr[a]).getID() == ID)
            {
                return (Employee)(employees.getAtIndex(a + 1));
            }
        }
        return null;
    }
    public void addEmployee(Employee e)
    {
        Employee eCopy = new Employee();
        eCopy.setID(e.getID());
        eCopy.setWage(e.getWage());
        eCopy.setFirst(e.getFirst());
        eCopy.setLast(e.getLast());
        eCopy.setMostRecentClockIn(e.getMostRecentClockIn());
        employees.enqueue(eCopy);
    }
    public Employee removeEmployee(int eID)
    {
        Employee tempE = getEmployee(eID);
        return removeEmployee(tempE);
    }
    public Employee removeEmployee(Employee e)
    {
        return employees.remove(e);
    }
    public int getNumEmployees()
    {
        return employees.getSize();
    }
    public void setRunningHours(double r)
    {
        runningHours = r;
    }
    public void addToRunningHours(double r)
    {
        runningHours += r;
    }
    public void setRunningExpense(double r)
    {
        runningExpense = r;
    }
    public void addToRunningExpense(double r)
    {
        runningExpense += r;
    }
    public void setOldHours(double r)
    {
        oldHours = r;
    }
    public void addToOldHours(double r)
    {
        oldHours += r;
    }
    public void setOldExpense(double r)
    {
        oldExpense = r;
    }
    public void addToOldExpense(double r)
    {
        oldExpense += r;
    }
    public double getRunningHours()
    {
        return runningHours;
    }
    public double getRunningExpense()
    {
        return runningExpense;
    }
    public double getOldHours()
    {
        return oldHours;
    }
    public double getOldExpense()
    {
        return oldExpense;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String n)
    {
        name = n;
    }
    public int getPayDay1()
    {
        return payDay1;
    }
    public int getPayDay2()
    {
        return payDay2;
    }
    public void setPayDay1(int d)
    {
        if ((d > 0) && (d < 31))
        {
            payDay1 = d;
        }
    }
    public void setPayDay2(int d)
    {
        if ((d > 0) && (d < 31))
        {
            payDay2 = d;
        }
    }
    
    public LinkedList<Punch> getEmployeePriorPunches(int ID)
    {
        Employee e = new Employee();
        e.setID(ID);
        return getEmployeePriorPunches(e);
    }
    public LinkedList<Punch> getEmployeePriorPunches(Employee e)
    {
        int Iouter = priorEmployeePunches.hash(e.getID());
        int Iinner = priorEmployeePunches.getKeyIndex(e.getID());
        if (((LinkedList)(priorEmployeePunches.getAtIndex(Iouter))).getSize() > 1)
        {
            //have multiple employee's punches at this index
            return ((LinkedList)((LinkedList)(priorEmployeePunches.getAtIndex(Iouter))).getAtIndex(Iinner));
        }
        else if (((LinkedList)(priorEmployeePunches.getAtIndex(Iouter))).getSize() == 0)
        {
            //no entries in current location - probably means error
            return null;
        }
        else
        {
            //there is only 1 entry in list at this location in array
            return ((LinkedList)((LinkedList)(priorEmployeePunches.getAtIndex(Iouter))).getAtIndex(1));
        }
        
    }
    
    public LinkedList<Punch> getEmployeePunches(int ID)
    {
        Employee e = new Employee();
        e.setID(ID);
        return getEmployeePunches(e);
    }
    public LinkedList<Punch> getEmployeePunches(Employee e)
    {
        int Iouter = employeePunches.hash(e.getID());
        int Iinner = employeePunches.getKeyIndex(e.getID());
        if (((LinkedList)(employeePunches.getAtIndex(Iouter))).getSize() > 1)
        {
            //have multiple employee's punches at this index
            return ((LinkedList)((LinkedList)(employeePunches.getAtIndex(Iouter))).getAtIndex(Iinner));
        }
        else if (((LinkedList)(employeePunches.getAtIndex(Iouter))).getSize() == 0)
        {
            //no entries in current location - probably means error
            return null;
        }
        else
        {
            //there is only 1 entry in list at this location in array
            return ((LinkedList)((LinkedList)(employeePunches.getAtIndex(Iouter))).getAtIndex(1));
        }
        
    }
    
    public void runTimecards()
    {
        //make sure CSV's are run before this - else the current data won't
            //show up on the report
        priorEmployeePunches = employeePunches;
        employeePunches = new HashTable<Punch>();
        oldHours = runningHours;
        oldExpense = runningExpense;
        runningHours = 0;
        runningExpense = 0;
    }
    
    
    public String punchesToString()
    {
        return employeePunches.toString();
    }
    public String priorPunchesToString()
    {
        return priorEmployeePunches.toString();
    }
    public String[] toCSV()
    {
        String[] strArr = new String[employeePunches.getLoad()];
        int currIndex = 0;
        Employee tempEmployee = null;
        LinkedList<Punch> tempPunches = null;
        for (int a = 1; a < employees.getSize(); a += 1)
        {
            tempEmployee = employees.getAtIndex(a);
            tempPunches = getEmployeePunches(tempEmployee);
            for (int b = 1; b <= tempPunches.getSize(); b += 1)
            {
                strArr[currIndex] = tempPunches.getAtIndex(b).toCSV() + "," + 
                    tempEmployee.getID() + ",\"" + tempEmployee.getLast() + 
                    ", " + tempEmployee.getFirst() + "\"";
                currIndex += 1;
            }
        }
    
        return strArr;
    }
}