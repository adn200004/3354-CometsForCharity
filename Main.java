 
import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javafx.scene.layout.Border;
import java.text.DateFormatSymbols;

//Note: want to use JOptionPane to get user input - can run outside BlueJ
//Note: when user closes home screen, want to save data to a file - 
    //read from that file next time home screen opens
//Note: will need a way to convert hashtable
    //array into string for employee data storage
//Note: need to add in idea of PTO for time system
public class Main
{
    static int currIndex = 0;
    
    
    
    
    
    private static class OutlineLabel extends JLabel {

        private Color outlineColor = Color.WHITE;
        private boolean isPaintingOutline = false;
        private boolean forceTransparent = false;
    
        private OutlineLabel() {
            super();
        }
    
        private OutlineLabel(String text) {
            super(text);
        }
    
        private OutlineLabel(String text, int horizontalAlignment) {
            super(text, horizontalAlignment);
        }
    
        private Color getOutlineColor() {
            return outlineColor;
        }
    
        private void setOutlineColor(Color outlineColor) {
            this.outlineColor = outlineColor;
            this.invalidate();
        }
    
        @Override
        public Color getForeground() {
            if ( isPaintingOutline ) {
                return outlineColor;
            } else {
                return super.getForeground();
            }
        }
    
        @Override
        public boolean isOpaque() {
            if ( forceTransparent ) {
                return false;
            } else {
                return super.isOpaque();
            }
        }
    
        @Override
        public void paint(Graphics g) {
    
            String text = getText();
            if ( text == null || text.length() == 0 ) {
                super.paint(g);
                return;
            }
    
            // 1 2 3
            // 8 9 4
            // 7 6 5
    
            if ( isOpaque() )
                super.paint(g);
    
            forceTransparent = true;
            isPaintingOutline = true;
            g.translate(-1, -1); super.paint(g); // 1 
            g.translate( 1,  0); super.paint(g); // 2 
            g.translate( 1,  0); super.paint(g); // 3 
            g.translate( 0,  1); super.paint(g); // 4
            g.translate( 0,  1); super.paint(g); // 5
            g.translate(-1,  0); super.paint(g); // 6
            g.translate(-1,  0); super.paint(g); // 7
            g.translate( 0, -1); super.paint(g); // 8
            g.translate( 1,  0); // 9
            isPaintingOutline = false;
    
            super.paint(g);
            forceTransparent = false;
    
        }
        /*
        private static void main(String[] args) {
            JFrame w = new JFrame();
            w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            OutlineLabel label = new OutlineLabel("Test", OutlineLabel.CENTER);
            label.setOpaque(true);
            w.setContentPane(new JPanel(new BorderLayout()));
            w.add(label, BorderLayout.CENTER);
            w.pack();
            w.setVisible(true);
        }*/
    }
    
    static Company c = null;
    
    
    
    
    
    private static void displayClocks()
    {
        //display all clocks currently avlbl for certain employee
    }
    private static void displayAllCurrentClockedIn()
    {
        //display all clocks (with employeeID of who clocked in) of people
            //currently clocked in
    }
    
    static Date date = new Date();
    static Date secondDate = new Date();
    private static void printTimes()
    {
        
        date.setTime(date.parse("Aug 31 12:34:00 CDT 2023"));
        System.out.println(date);
        secondDate = new Date();
        System.out.println(secondDate);
        int timeBetween = (int)(secondDate.getTime() - date.getTime())/1000;
        if (timeBetween >= 60)
        {
            int timeBetweenMins = timeBetween/60;
            timeBetween %= 60;
            if (timeBetweenMins >= 60)
            {
                int timeBetweenHrs = timeBetweenMins/60;
                timeBetweenMins %= 60;
                if (timeBetweenHrs >= 24)
                {
                    int timeBetweenDays = timeBetweenHrs/24;
                    timeBetweenHrs %= 24;
                    System.out.println(timeBetweenDays + " days, " + 
                        timeBetweenHrs + " hours, " + 
                        timeBetweenMins + " minutes, and " + 
                        timeBetween + " seconds");//number of seconds between times
                }
                else
                {
                    System.out.println(timeBetweenHrs + " hours, " + 
                        timeBetweenMins + " minutes, and " + 
                        timeBetween + " seconds");//number of seconds between times
                }
            }
            else
            {
                System.out.println(timeBetweenMins + " minutes and " + 
                    timeBetween + " seconds");//number of seconds between times
            }
        }
        else
        {
            System.out.println(timeBetween + " seconds");//number of seconds between times
        }
        
        
        /*
        boolean contOrNot = true;
        while (contOrNot)
        {
            displayClocks();
            
            
        }
        */
    }
    
    private static void testJPane()
    {
        int a = JOptionPane.showOptionDialog(null, "",
            "Title", JOptionPane.DEFAULT_OPTION, 
            -1, null,  
            new String[] {"Punch In", "Punch Out", "Get Timecard"}, "Test");
        System.out.println("You chose option " + (a + 1));
        //a = -1 if just click on the "x"
    }
    private static void confirmExitPassword()
    {
        JFrame frame = new JFrame();
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        JLabel label = new JLabel("Enter Admin Password: ");
        JPasswordField pass = new JPasswordField(15);
        panelTop.add(label, BorderLayout.LINE_START);
        panelTop.add(pass, BorderLayout.LINE_END);
        frame.add(panelTop, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.PAGE_END);
        
        JButton enterBTN = new JButton();
        JButton cancelBTN = new JButton();
        enterBTN.setPreferredSize(new Dimension(90,30));
        enterBTN.setText("Enter");
        enterBTN.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String password = new String(pass.getPassword());
                if (password.equals("password"))
                {
                    frame.dispose();
                    storeCompanyData(c);
                    try
                    {
                        Thread.sleep(250);
                    }
                    catch (Exception except)
                    {
                        
                    }
                    System.exit(0);
                }
                else
                {
                    label.setText("<html><font color='red'>Invalid Password: </font>Enter Admin Password</html>");
                    frame.setLocationRelativeTo(null);
                    frame.setLocation(451,360);
                    frame.getRootPane().setDefaultButton(enterBTN);
                    pass.requestFocusInWindow();
                    frame.revalidate();
                    frame.setVisible(true);
                    frame.pack();
                }
                
            }
        });
        cancelBTN.setPreferredSize(new Dimension(90,30));
        cancelBTN.setText("Cancel");
        cancelBTN.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                employeeView();
            }
        });
        panelBottom.add(enterBTN, BorderLayout.LINE_START);
        panelBottom.add(cancelBTN, BorderLayout.LINE_END);
        frame.getRootPane().setDefaultButton(enterBTN);
        pass.requestFocusInWindow();
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLocation(501,360);
        frame.pack();
        pass.requestFocusInWindow();
    }
    private static void confirmExitPasswordTimes()
    {
        JFrame frame = new JFrame();
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        JPanel panelOuter = new JPanel();
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel();
        JLabel label = new JLabel("Enter Admin Password: ");
        JPasswordField pass = new JPasswordField(15);
        panelTop.add(label, BorderLayout.LINE_START);
        panelTop.add(pass, BorderLayout.LINE_END);
        //panelTop.add(title, BorderLayout.PAGE_START);
        
        title.setText("Run Timesheets: ");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(0, 100, 180));
        titlePanel.add(title, BorderLayout.PAGE_START);
        
        panelOuter.setLayout(new BorderLayout());
        panelOuter.add(panelTop, BorderLayout.CENTER);
        panelOuter.add(titlePanel, BorderLayout.PAGE_START);
        
        //frame.add(panelTop, BorderLayout.CENTER);
        frame.add(panelOuter, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.PAGE_END);
        
        JButton enterBTN = new JButton();
        JButton cancelBTN = new JButton();
        enterBTN.setPreferredSize(new Dimension(90,30));
        enterBTN.setText("Enter");
        enterBTN.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String password = new String(pass.getPassword());
                if (password.equals("password"))
                {
                    frame.dispose();
                    storeCompanyDataAsCSV(c);
                    c.runTimecards();
                    
                    companyView();
                    //frame.dispose();
                    //System.exit(0);
                    //Here, need to run timecards
                }
                else
                {
                    label.setText("<html><font color='red'>Invalid Password: </font>Enter Admin Password</html>");
                    frame.setLocationRelativeTo(null);
                    frame.setLocation(451,336);
                    frame.getRootPane().setDefaultButton(enterBTN);
                    pass.requestFocusInWindow();
                    frame.revalidate();
                    frame.setVisible(true);
                    frame.pack();
                }
                
            }
        });
        cancelBTN.setPreferredSize(new Dimension(90,30));
        cancelBTN.setText("Cancel");
        cancelBTN.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                companyView();
            }
        });
        panelBottom.add(enterBTN, BorderLayout.LINE_START);
        panelBottom.add(cancelBTN, BorderLayout.LINE_END);
        frame.getRootPane().setDefaultButton(enterBTN);
        pass.requestFocusInWindow();
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLocation(501,336);
        frame.pack();
        pass.requestFocusInWindow();
    }
    private static void punchInWindow()
    {
        JFrame frame = new JFrame();
        JPanel titlePanel = new JPanel();
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        JButton enterBTN = new JButton();
        JButton cancelBTN = new JButton();
        JLabel IDPrompt = new JLabel();
        JLabel title = new JLabel();
        JTextField IDField = new JTextField();
        IDPrompt.setText("Enter your employee ID: ");
        IDField.setBackground(Color.WHITE);
        IDField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        IDField.setPreferredSize(new Dimension(80, 25));
        enterBTN.setText("Enter");
        enterBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               int temp = 0;
               boolean isError = false;
               boolean employeeExists = false;
               try 
               {
                   temp = (int)(Long.parseLong(IDField.getText()));
                   isError = !Employee.validateID(temp);
                   if ((!isError) && ((c.getEmployee(temp)) != null))
                   {
                       employeeExists = true;
                   }
               }
               catch (Exception exc)
               {
                   isError = true;
               }
               if ((isError == false) && (employeeExists == true))
               {
                   //need to clock in employee here
                   Date rightNow = new Date();
                   c.punchInEmployee(Integer.parseInt(IDField.getText()), rightNow);
                   frame.dispose();
                   employeeView();
               }
               else
               {
                   frame.setLocation(496,300);
                   IDPrompt.setText("<html><font color='red'>Invalid ID: </font>Enter your employee ID: </html>");
                   
                   IDField.requestFocusInWindow();
                   frame.pack();
               }
           }
        });
        cancelBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               employeeView();
           }
        });
        cancelBTN.setText("Cancel");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setText("Clock In: ");
        title.setForeground(new Color(0, 100, 180));
        titlePanel.add(title, BorderLayout.CENTER);
        panelTop.add(IDPrompt, BorderLayout.CENTER);
        panelTop.add(IDField, BorderLayout.LINE_END);
        panelBottom.add(enterBTN, BorderLayout.LINE_START);
        panelBottom.add(cancelBTN, BorderLayout.LINE_END);
        IDField.requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        frame.setLocation(525,300);
        frame.add(panelTop, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.PAGE_END);
        frame.add(titlePanel, BorderLayout.PAGE_START);
        IDField.requestFocusInWindow();
        frame.getRootPane().setDefaultButton(enterBTN);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.pack();
        frame.setVisible(true);
        IDField.requestFocusInWindow();
        IDField.requestFocusInWindow();
        frame.pack();
    }
    private static void punchOutWindow()
    {
        JFrame frame = new JFrame();
        JPanel titlePanel = new JPanel();
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        JButton enterBTN = new JButton();
        JButton cancelBTN = new JButton();
        JLabel IDPrompt = new JLabel();
        JLabel title = new JLabel();
        JTextField IDField = new JTextField();
        IDPrompt.setText("Enter your employee ID: ");
        IDField.setBackground(Color.WHITE);
        IDField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        IDField.setPreferredSize(new Dimension(80, 25));
        enterBTN.setText("Enter");
        enterBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               int temp = 0;
               boolean isError = false;
               boolean employeeExists = false;
               try 
               {
                   temp = (int)(Long.parseLong(IDField.getText()));
                   isError = !Employee.validateID(temp);
                   if ((!isError) && ((c.getEmployee(temp)) != null))
                   {
                       employeeExists = true;
                   }
               }
               catch (Exception exc)
               {
                   isError = true;
               }
               if ((isError == false) && (employeeExists == true))
               {
                   //need to clock out employee here
                   Date rightNow = new Date();
                   c.punchOutEmployee(Integer.parseInt(IDField.getText()), rightNow);
                   frame.dispose();
                   employeeView();
               }
               else
               {
                   frame.setLocation(496,300);
                   IDPrompt.setText("<html><font color='red'>Invalid ID: </font>Enter your employee ID: </html>");
                   
                   IDField.requestFocusInWindow();
                   frame.pack();
               }
           }
        });
        cancelBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               employeeView();
           }
        });
        cancelBTN.setText("Cancel");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setText("Clock Out: ");
        title.setForeground(new Color(0, 100, 180));
        titlePanel.add(title, BorderLayout.CENTER);
        panelTop.add(IDPrompt, BorderLayout.CENTER);
        panelTop.add(IDField, BorderLayout.LINE_END);
        panelBottom.add(enterBTN, BorderLayout.LINE_START);
        panelBottom.add(cancelBTN, BorderLayout.LINE_END);
        IDField.requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        frame.setLocation(525,300);
        frame.add(panelTop, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.PAGE_END);
        frame.add(titlePanel, BorderLayout.PAGE_START);
        IDField.requestFocusInWindow();
        frame.getRootPane().setDefaultButton(enterBTN);
        frame.setUndecorated(true);
        frame.setVisible(true);
        
        
        frame.pack();
        frame.setVisible(true);
        IDField.requestFocusInWindow();
        IDField.requestFocusInWindow();
        frame.pack();
    }
    private static String comboBoxStringUpdate(int id, int sel)
    {
        Punch[] punchArr = null;
        LinkedList<Punch> currPunchList = c.getEmployeePunches(id);
        LinkedList<Punch> oldPunchList = c.getEmployeePriorPunches(id);
        if (currPunchList == null)
        {
            currPunchList = new LinkedList<Punch>();
        }
        if (oldPunchList == null)
        {
            oldPunchList = new LinkedList<Punch>();
        }
        punchArr = new Punch[currPunchList.getSize() + oldPunchList.getSize()];
        int currPunchIndex = 0;
        for (int a = 1; a <= oldPunchList.getSize(); a += 1)
        {
            punchArr[currPunchIndex] = oldPunchList.getAtIndex(a);
            currPunchIndex += 1;
        }
        for (int a = 1; a <= currPunchList.getSize(); a += 1)
        {
            punchArr[currPunchIndex] = currPunchList.getAtIndex(a);
            currPunchIndex += 1;
        }
        
        currPunchIndex = 0;
        Punch[] tempPunchArr = new Punch[punchArr.length];
        Punch[] toDisplay;
        Date timeUntil = new Date();
        String temp = timeUntil.toString();//current time/date
        int currentDay = Integer.parseInt(temp.substring(8,10));
        int currentMonth = (timeUntil.getMonth()%12) + 1;
        int currentYear = Integer.parseInt(temp.substring(24));
        Date timeStop = new Date();
        
        if (sel == 0)//this pay period
        {
            int smaller = c.getPayDay1();
            int bigger = c.getPayDay2();
            if (c.getPayDay2() < smaller)
            {
                bigger = smaller;
                smaller = c.getPayDay2();
            }
            //take all clocks starting from last payday
            if (currentDay < smaller)
            {
                //from date bigger of last month to now
                if (currentMonth == 1)
                {
                    timeStop.setTime(timeStop.parse(
                            "Jan " + bigger + " " + (currentYear - 1)));
                }
                else
                {
                    timeStop.setTime(timeStop.parse(new DateFormatSymbols().getMonths()[currentMonth - 1]
                             + " " + bigger + " " + currentYear));
                }
            }
            else if (currentDay > bigger)
            {
                //from date bigger to now
                timeStop.setTime(timeStop.parse(new DateFormatSymbols().getMonths()[currentMonth - 1] + 
                        " " + bigger + " " + currentYear));
            }
            else if (currentDay <= bigger)
            {
                //from date smaller til date bigger of current month
                timeStop.setTime(timeStop.parse(new DateFormatSymbols().getMonths()[currentMonth - 1] + 
                        " " + smaller + " " + currentYear));
            }
        }
        else if (sel == 1)//month-to-date
        {
            timeStop.setTime(timeStop.parse(new DateFormatSymbols().getMonths()[currentMonth - 1] + 
                " 1 " + currentYear));
            
        }
        else//past 31 days
        {
            timeStop.setTime(((timeUntil.getTime()/1000) - 2678400)*1000);
            
        }
        for (int a = 0; a < punchArr.length; a += 1)
        {
            if (punchArr[a].getTimeIn().getTime() >= timeStop.getTime())
            {
                tempPunchArr[currPunchIndex] = punchArr[a];
                currPunchIndex += 1;
            }
        }
        
        toDisplay = new Punch[currPunchIndex];
        for (int a = 0; a < toDisplay.length; a += 1)
        {
            toDisplay[a] = tempPunchArr[a];
        }
        String displayString = "";
        for (int a = 0; a < toDisplay.length; a += 1)
        {
            displayString += "Time In: " + toDisplay[a].getTimeIn() + 
                    "; Time Out: " + toDisplay[a].getTimeOut() + "\n"; 
        }
        return displayString;
    }
    private static void timecardWindow2(int id)
    {
        JFrame frame = new JFrame();
        JLabel title = new JLabel();
        JLabel empID = new JLabel();
        JTextArea reportedTime = new JTextArea();
        JButton escapeBTN = new JButton();
        JPanel topPanel = new JPanel();
        JPanel topBorderPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel totalPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        String[] timeframeOptions = new String[] {"Current Pay Period", 
                "Month to Date", "Last 31 Days"};
        JComboBox timeframe = new JComboBox(timeframeOptions);
        title.setText("Employee Timecard: ");
        empID.setText("ID: " + id);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(0, 100, 180));
        escapeBTN.setPreferredSize(new Dimension(125, 35));
        escapeBTN.setText("Return");
        escapeBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               employeeView();
           }
        });
        
        String displayString = comboBoxStringUpdate(id, timeframe.getSelectedIndex());
        reportedTime.setText(displayString);
        timeframe.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String displayString = comboBoxStringUpdate(id, timeframe.getSelectedIndex());
                reportedTime.setText(displayString);
                frame.revalidate();
                frame.pack();
            }
        });
        topBorderPanel.setLayout(new BorderLayout());
        titlePanel.add(title, BorderLayout.PAGE_START);
        //topPanel.add(title, BorderLayout.PAGE_START);
        topPanel.add(empID, BorderLayout.LINE_START);
        topPanel.add(timeframe, BorderLayout.LINE_END);
        topBorderPanel.add(titlePanel, BorderLayout.PAGE_START);
        topBorderPanel.add(topPanel, BorderLayout.CENTER);
        topBorderPanel.add(reportedTime, BorderLayout.PAGE_END);
        //bottomPanel.add(reportedTime, BorderLayout.PAGE_START);
        bottomPanel.add(escapeBTN, BorderLayout.PAGE_END);
        totalPanel.setLayout(new BorderLayout());
        totalPanel.add(topBorderPanel, BorderLayout.PAGE_START);
        totalPanel.add(bottomPanel, BorderLayout.PAGE_END);
        reportedTime.setEditable(false);
        frame.add(totalPanel);
        frame.getRootPane().setDefaultButton(escapeBTN);
        timeframe.requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        frame.setLocation(415, 300);
        frame.setUndecorated(true);
        frame.setVisible(true);
        timeframe.requestFocusInWindow();
        frame.pack();
    }
    private static void getTimecardWindow()
    {
        JFrame frame = new JFrame();
        JPanel titlePanel = new JPanel();
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        JButton enterBTN = new JButton();
        JButton cancelBTN = new JButton();
        JLabel IDPrompt = new JLabel();
        JLabel title = new JLabel();
        JTextField IDField = new JTextField();
        IDPrompt.setText("Enter your employee ID: ");
        IDField.setBackground(Color.WHITE);
        IDField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        IDField.setPreferredSize(new Dimension(80, 25));
        enterBTN.setText("Enter");
        enterBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               int temp = 0;
               boolean isError = false;
               boolean employeeExists = false;
               try 
               {
                   temp = (int)(Long.parseLong(IDField.getText()));
                   isError = !Employee.validateID(temp);
                   if ((!isError) && ((c.getEmployee(temp)) != null))
                   {
                       employeeExists = true;
                   }
               }
               catch (Exception exc)
               {
                   isError = true;
               }
               if ((isError == false) && (employeeExists == true))
               {
                   //need to report employee timecard here;
                   frame.dispose();
                   timecardWindow2(temp);
               }
               else
               {
                   frame.setLocation(496,300);
                   IDPrompt.setText("<html><font color='red'>Invalid ID: </font>Enter your employee ID: </html>");
                   
                   IDField.requestFocusInWindow();
                   frame.pack();
               }
           }
        });
        cancelBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               employeeView();
           }
        });
        cancelBTN.setText("Cancel");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setText("View Timecard: ");
        title.setForeground(new Color(0, 100, 180));
        titlePanel.add(title, BorderLayout.CENTER);
        panelTop.add(IDPrompt, BorderLayout.CENTER);
        panelTop.add(IDField, BorderLayout.LINE_END);
        panelBottom.add(enterBTN, BorderLayout.LINE_START);
        panelBottom.add(cancelBTN, BorderLayout.LINE_END);
        IDField.requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        frame.setLocation(525,300);
        frame.add(panelTop, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.PAGE_END);
        frame.add(titlePanel, BorderLayout.PAGE_START);
        IDField.requestFocusInWindow();
        frame.getRootPane().setDefaultButton(enterBTN);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.pack();
        frame.setVisible(true);
        IDField.requestFocusInWindow();
        IDField.requestFocusInWindow();
        frame.pack();
    }
    private static void showCurrentEmployeesWindow()
    {
        if ((c == null) || (c.getEmployees() == null) || (c.getEmployees().length < 1))
        {
            companyView();
            return;
        }
        JFrame frame = new JFrame();
        JLabel numEmployees = new JLabel();
        JButton nextEmployeeBTN = new JButton();
        JButton prevEmployeeBTN = new JButton();
        JLabel currentEmployee = new JLabel();
        JButton escapeBTN = new JButton();
        JLabel title = new JLabel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel totalPanel = new JPanel();
        JPanel flowPanelTitle = new JPanel();
        JPanel flowPanelNumEmployees = new JPanel();
        JPanel flowPanelCurrentEmployee = new JPanel();
        JPanel flowPanelButtons = new JPanel();
        JPanel flowPanelEscapeButton = new JPanel();
        
        currIndex = 0;
        
        nextEmployeeBTN.setPreferredSize(new Dimension(150, 35));
        prevEmployeeBTN.setPreferredSize(new Dimension(150, 35));
        escapeBTN.setPreferredSize(new Dimension(300, 35));
        
        
        Object[] empArr = c.getEmployees();
        numEmployees.setText("Total Number of Employees: " + empArr.length);
        nextEmployeeBTN.setText("Next Employee");
        prevEmployeeBTN.setText("Previous Employee");
        escapeBTN.setText("Return to Company Dashboard");
        title.setText("View Current Employees: ");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(0, 100, 180));
        currentEmployee.setText(((Employee)empArr[currIndex]).toString());
        currentEmployee.setHorizontalAlignment(SwingConstants.CENTER);
        
        nextEmployeeBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               currIndex += 1;
               currIndex %= empArr.length;
               currentEmployee.setText(((Employee)empArr[currIndex]).toString());
               frame.revalidate();
               frame.setVisible(true);
               frame.pack();
           }
        });
        prevEmployeeBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               currIndex -= 1;
               if (currIndex < 0)
               {
                   currIndex += empArr.length;
               }
               currIndex %= empArr.length;
               currentEmployee.setText(((Employee)empArr[currIndex]).toString());
               frame.revalidate();
               frame.setVisible(true);
               frame.pack();
           }
        });
        escapeBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               currIndex = 0;
               frame.dispose();
               companyView();
           }
        });
        
        topPanel.setLayout(new BorderLayout());
        flowPanelTitle.add(title, BorderLayout.CENTER);
        topPanel.add(flowPanelTitle, BorderLayout.PAGE_START);
        flowPanelNumEmployees.add(numEmployees, BorderLayout.CENTER);
        topPanel.add(flowPanelNumEmployees, BorderLayout.CENTER);
        flowPanelCurrentEmployee.add(currentEmployee, BorderLayout.CENTER);
        topPanel.add(flowPanelCurrentEmployee, BorderLayout.PAGE_END);
        bottomPanel.setLayout(new BorderLayout());
        flowPanelButtons.add(prevEmployeeBTN, BorderLayout.LINE_START);
        flowPanelButtons.add(nextEmployeeBTN, BorderLayout.LINE_END);
        
        bottomPanel.add(flowPanelButtons, BorderLayout.PAGE_START);
        flowPanelEscapeButton.add(escapeBTN, BorderLayout.PAGE_END);
        bottomPanel.add(flowPanelEscapeButton, BorderLayout.PAGE_END);
        totalPanel.setLayout(new BorderLayout());
        totalPanel.add(topPanel, BorderLayout.PAGE_START);
        totalPanel.add(bottomPanel, BorderLayout.PAGE_END);
        
        
        frame.setLocationRelativeTo(null);
        frame.setLocation(485,233);
        
        frame.add(totalPanel, BorderLayout.CENTER);
        
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.pack();
        nextEmployeeBTN.requestFocusInWindow();
        
        frame.setVisible(true);
        frame.pack();
    }
    private static void companyView()
    {
        JFrame frame = new JFrame();
        JPanel titlePanel = new JPanel();
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        JPanel panelBottomPlusTop = new JPanel();
        JPanel companyStatsPanel = new JPanel();
        JPanel panelMiddle = new JPanel();
        JButton addEmployee = new JButton();
        JButton removeEmployee = new JButton();
        JButton editEmployeeBTN = new JButton();
        JButton toEmployeeViewBTN = new JButton();
        JButton runTimesheetsBTN = new JButton();
        JButton showCurrentEmployeesBTN = new JButton();
        JButton exitBTN = new JButton();
        OutlineLabel title = new OutlineLabel();
        OutlineLabel viewTitle = new OutlineLabel();
        JLabel statsLabel = new JLabel();
        //JLabel title = new JLabel();
        //JLabel viewTitle = new JLabel();
        addEmployee.setPreferredSize(new Dimension(150, 35));
        removeEmployee.setPreferredSize(new Dimension(150, 35));
        editEmployeeBTN.setPreferredSize(new Dimension(150, 35));
        toEmployeeViewBTN.setPreferredSize(new Dimension(219, 35));
        runTimesheetsBTN.setPreferredSize(new Dimension(228, 35));
        showCurrentEmployeesBTN.setPreferredSize(new Dimension(228, 35));
        exitBTN.setPreferredSize(new Dimension(60, 35));
        editEmployeeBTN.setText("Edit Employee");
        toEmployeeViewBTN.setText("Switch to Employee View");
        runTimesheetsBTN.setText("Run Timecards");
        showCurrentEmployeesBTN.setText("Show Current Employee List");
        exitBTN.setText("Exit");
        addEmployee.setText("Add Employee");
        addEmployee.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               addEmployeeWindow();
           }
        });
        removeEmployee.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               removeEmployeeWindow();
           }
        });
        editEmployeeBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               editEmployeeWindow();
           }
        });
        toEmployeeViewBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               employeeView();
           }
        });
        exitBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               confirmExitPassword();
           }
        });
        runTimesheetsBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               confirmExitPasswordTimes();
           }
        });
        showCurrentEmployeesBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               showCurrentEmployeesWindow();
           }
        });
        removeEmployee.setText("Remove Employee");
        statsLabel.setText("<html>Running Company Hours: \t" + String.format("%.2f", c.getRunningHours()) + 
                        "<br>Running Company Expense: \t$" + String.format("%.2f", c.getRunningExpense()) + "</html>");
        companyStatsPanel.add(statsLabel, BorderLayout.LINE_START);
        BorderLayout titleLayout = new BorderLayout(0,11);
        titlePanel.setLayout(titleLayout);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setText(c.getName());
        title.setForeground(new Color(0, 100, 180));
        title.setOutlineColor(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        viewTitle.setText("Company Dashboard");
        viewTitle.setForeground(new Color(50, 150, 170));
        viewTitle.setFont(new Font("Arial", Font.BOLD, 17));
        viewTitle.setHorizontalAlignment(SwingConstants.CENTER);
        runTimesheetsBTN.setLayout(null);
        runTimesheetsBTN.setSize(150, 35);
        runTimesheetsBTN.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(companyStatsPanel, BorderLayout.LINE_END);
        titlePanel.add(title, BorderLayout.PAGE_START);
        titlePanel.add(viewTitle, BorderLayout.LINE_START);
        //titlePanel.add(runTimesheetsBTN, BorderLayout.PAGE_END);
        //panelTop.setLayout(new BorderLayout(11,11));
        //panelTop.add(runTimesheetsBTN, BorderLayout.PAGE_START);
        panelTop.add(addEmployee, BorderLayout.LINE_START);
        panelTop.add(removeEmployee, BorderLayout.CENTER);
        panelTop.add(editEmployeeBTN, BorderLayout.LINE_END);
        panelBottom.add(toEmployeeViewBTN, BorderLayout.LINE_START);
        panelBottom.add(exitBTN, BorderLayout.LINE_END);
        panelBottomPlusTop.setLayout(new BorderLayout(1, 0));
        panelBottomPlusTop.add(panelBottom, BorderLayout.PAGE_END);
        panelBottomPlusTop.add(panelTop, BorderLayout.CENTER);
        panelMiddle.add(runTimesheetsBTN, BorderLayout.LINE_START);
        panelMiddle.add(showCurrentEmployeesBTN, BorderLayout.LINE_END);
        panelBottomPlusTop.add(panelMiddle, BorderLayout.PAGE_START);
        //panelBottomPlusTop.add(runTimesheetsBTN, BorderLayout.PAGE_START);
        frame.setLocationRelativeTo(null);
        frame.setLocation(413,229);
        //frame.add(panelTop, BorderLayout.CENTER);
        //frame.add(panelBottom, BorderLayout.PAGE_END);
        frame.add(panelBottomPlusTop, BorderLayout.PAGE_END);
        frame.add(titlePanel, BorderLayout.PAGE_START);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.pack();
        addEmployee.requestFocusInWindow();
        frame.setVisible(true);
        frame.pack();
    }
    private static void addEmployeeWindow()
    {
        JFrame frame = new JFrame();
        JPanel titlePanel = new JPanel();
        //JPanel IDPanel = new JPanel();
        JPanel panelBottom = new JPanel();
        //JPanel wagePanel = new JPanel();
        //JPanel firstPanel = new JPanel();
        //JPanel lastPanel = new JPanel();
        //JPanel namePanel = new JPanel();
        //JPanel otherInfoPanel = new JPanel();
        //JPanel middlePanel = new JPanel();
        JButton enterBTN = new JButton();
        JButton cancelBTN = new JButton();
        JLabel IDPrompt = new JLabel();
        JLabel title = new JLabel();
        JLabel wagePrompt = new JLabel();
        JTextField wageField = new JTextField();
        JLabel firstPrompt = new JLabel();
        JTextField firstField = new JTextField();
        JLabel lastPrompt = new JLabel();
        JTextField lastField = new JTextField();
        wagePrompt.setText("Enter the new employee's hourly wage: ");
        wageField.setBackground(Color.WHITE);
        wageField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        wageField.setPreferredSize(new Dimension(80, 25));
        firstPrompt.setText("Enter the new employee's first name:  ");
        firstField.setBackground(Color.WHITE);
        firstField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        firstField.setPreferredSize(new Dimension(80, 25));
        lastPrompt.setText("Enter the new employee's last name:   ");
        lastField.setBackground(Color.WHITE);
        lastField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        lastField.setPreferredSize(new Dimension(80, 25));
        JTextField IDField = new JTextField();
        IDPrompt.setText("Enter new employee's ID: ");
        IDField.setBackground(Color.WHITE);
        IDField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        IDField.setPreferredSize(new Dimension(80, 25));
        enterBTN.setText("Enter");
        enterBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               int temp = 0;
               boolean isIDError = false;
               boolean isWageError = false;
               double tempDouble = 0.0;
               try 
               {
                   temp = (int)(Long.parseLong(IDField.getText()));
                   isIDError = !Employee.validateID(temp);
               }
               catch (Exception exc)
               {
                   isIDError = true;
               }
               try
               {
                    tempDouble = (double)(Double.parseDouble(wageField.getText()));
                    if (tempDouble <= 0.0)
                    {
                        isWageError = true;
                    }
               }
               catch (Exception exc)
               {
                   isWageError = true;
               }
               if ((isIDError == false) && (isWageError == false))
               {
                   //need to add employee here
                   Employee newEmp = new Employee();
                   newEmp.setID(temp);
                   newEmp.setWage(tempDouble);
                   newEmp.setFirst(firstField.getText());
                   newEmp.setLast(lastField.getText());
                   c.addEmployee(newEmp);
                   frame.dispose();
                   companyView();
               }
               else if (isIDError == true)
               {
                   frame.setLocation(476,262);
                   IDPrompt.setText("<html><font color='red'>Invalid ID: </font>Enter new employee's ID: </html>");
                   
                   IDField.requestFocusInWindow();
                   frame.pack();
               }
               if (isWageError == true)
               {
                   wagePrompt.setText("<html><font color='red'>Invalid Wage: </font>Enter new employee's wage: </html>");
                   
                   frame.setLocation(468,262);
                   IDField.requestFocusInWindow();
                   frame.pack();
               }
               if (isWageError == false)
               {
                   wagePrompt.setText("Enter new employee's wage: ");
                   
                   frame.setLocation(476,262);
                   IDField.requestFocusInWindow();
                   frame.pack();
               }
               if (isIDError == false)
               {
                   IDPrompt.setText("Enter new employee's ID: ");
                   
                   frame.setLocation(476,262);
                   IDField.requestFocusInWindow();
                   frame.pack();
               }
           }
        });
        cancelBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               companyView();
           }
        });
        cancelBTN.setText("Cancel");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setText("Add Employee: ");
        title.setForeground(new Color(0, 100, 180));
        GridBagLayout gbl = new GridBagLayout();
        
        //titlePanel.add(title, BorderLayout.CENTER);
        //frame.setSize(300, 300);
        //titlePanel.setSize(300, 300);
        titlePanel.setLayout(gbl);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.CENTER;
        //gc.weighty = 10;
        gc.gridx = 0;
        gc.gridy = 0;
        //frame.add(title, gc);
        titlePanel.add(title, gc);
        gc.insets = new Insets(1, 5, 1, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridwidth = 1;
        gc.gridx = 0;
        gc.gridy = 2;
        //frame.add(IDPrompt, gc);
        titlePanel.add(IDPrompt, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        //frame.add(IDField, gc);
        titlePanel.add(IDField, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        //frame.add(wagePrompt, gc);
        titlePanel.add(wagePrompt, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_END;
        //frame.add(wageField, gc);
        titlePanel.add(wageField, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.LINE_START;
        //frame.add(firstPrompt, gc);
        titlePanel.add(firstPrompt, gc);
        gc.gridx = 1;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.LINE_END;
        //frame.add(firstField, gc);
        titlePanel.add(firstField, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.LINE_START;
        //frame.add(lastPrompt, gc);
        titlePanel.add(lastPrompt, gc);
        gc.gridx = 1;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.LINE_END;
        //frame.add(lastField, gc);
        titlePanel.add(lastField, gc);
        gc.gridx = 0;
        gc.gridy = 6;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(5, 5, 5, 5);
        //frame.add(enterBTN, gc);
        panelBottom.add(enterBTN, BorderLayout.LINE_START);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        //frame.add(cancelBTN, gc);
        panelBottom.add(cancelBTN, BorderLayout.LINE_END);
        frame.add(titlePanel, BorderLayout.PAGE_START);
        frame.add(panelBottom, BorderLayout.PAGE_END);
        /*
        IDPanel.add(IDPrompt, BorderLayout.CENTER);
        IDPanel.add(IDField, BorderLayout.LINE_END);
        wagePanel.add(wagePrompt, BorderLayout.CENTER);
        wagePanel.add(wageField, BorderLayout.LINE_END);
        firstPanel.add(firstPrompt, BorderLayout.CENTER);
        firstPanel.add(firstField, BorderLayout.LINE_END);
        lastPanel.add(lastField, BorderLayout.CENTER);
        lastPanel.add(lastField, BorderLayout.LINE_END);
        */
        /*
        IDPrompt.setVerticalAlignment(SwingConstants.TOP);
        wagePrompt.setVerticalAlignment(SwingConstants.BOTTOM);
        firstPrompt.setVerticalAlignment(SwingConstants.TOP);
        lastPrompt.setVerticalAlignment(SwingConstants.BOTTOM);
        */
        /*
        namePanel.add(firstPanel, BorderLayout.PAGE_START);
        namePanel.add(lastPanel, BorderLayout.PAGE_END);
        otherInfoPanel.add(IDPanel, BorderLayout.PAGE_START);
        otherInfoPanel.add(wagePanel, BorderLayout.PAGE_END);
        middlePanel.add(otherInfoPanel, BorderLayout.PAGE_START);
        middlePanel.setLayout(new BorderLayout(1,1));
        middlePanel.add(namePanel, BorderLayout.PAGE_END);
        panelBottom.add(enterBTN, BorderLayout.LINE_START);
        panelBottom.add(cancelBTN, BorderLayout.LINE_END);
        */
        IDField.requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        //frame.setLocation(525,300);
        frame.setLocation(476,262);
        /*
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.PAGE_END);
        frame.add(titlePanel, BorderLayout.PAGE_START);
        */
        IDField.requestFocusInWindow();
        frame.getRootPane().setDefaultButton(enterBTN);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.pack();
        frame.setVisible(true);
        IDField.requestFocusInWindow();
        IDField.requestFocusInWindow();
        frame.pack();
    }
    private static void editEmployeeWindow()
    {
        JFrame frame = new JFrame();
        JPanel titlePanel = new JPanel();
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        JButton enterBTN = new JButton();
        JButton cancelBTN = new JButton();
        JLabel IDPrompt = new JLabel();
        JLabel title = new JLabel();
        JTextField IDField = new JTextField();
        IDPrompt.setText("Enter ID of employee to edit: ");
        IDField.setBackground(Color.WHITE);
        IDField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        IDField.setPreferredSize(new Dimension(80, 25));
        enterBTN.setText("Enter");
        enterBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               int temp = 0;
               boolean isError = false;
               boolean employeeExists = false;
               try 
               {
                   temp = (int)(Long.parseLong(IDField.getText()));
                   isError = !Employee.validateID(temp);
                   
                   if ((!isError) && ((c.getEmployee(temp)) != null))
                   {
                       employeeExists = true;
                   }
               }
               catch (Exception exc)
               {
                   isError = true;
               }
               if ((isError == false) && (employeeExists == true))
               {
                   //need to remove employee here
                   /*System.out.println("Removed employee ID: " + temp);
                   frame.dispose();
                   companyView();*/
                   IDPrompt.setText("Enter ID of employee to edit: ");
                   frame.setLocationRelativeTo(null);
                   frame.setLocation(513,300);
                   frame.revalidate();
                   frame.pack();
                   MouseListener ml = null;
                   final int IDediting = temp;
                   JFrame frame2 = new JFrame();
                   JPanel glass = new JPanel();
                   frame.setGlassPane(glass);
                   glass.setVisible(true);
                   glass.setOpaque(false);
                   ml = new MouseAdapter() { 
                   public void mousePressed(MouseEvent e)
                   {
                       frame2.setState(JFrame.NORMAL);
                       frame2.toFront();
                       frame2.requestFocus();
                   }
                   };
                   glass.addMouseListener(ml);
                   
                   JButton enterBTN2 = new JButton();
                   JButton cancelBTN2 = new JButton();
                   
                   JPanel titlePanel = new JPanel();
                   //JPanel IDPanel = new JPanel();
                   JPanel panelBottom = new JPanel();
                   //JPanel wagePanel = new JPanel();
                   //JPanel firstPanel = new JPanel();
                   //JPanel lastPanel = new JPanel();
                   //JPanel namePanel = new JPanel();
                   //JPanel otherInfoPanel = new JPanel();
                   //JPanel middlePanel = new JPanel();
                   JLabel title2 = new JLabel();
                   JLabel wagePrompt = new JLabel();
                   JTextField wageField = new JTextField();
                   JLabel firstPrompt = new JLabel();
                   JTextField firstField = new JTextField();
                   JLabel lastPrompt = new JLabel();
                   JTextField lastField = new JTextField();
                   wagePrompt.setText("Enter the employee's updated hourly wage: ");
                   wageField.setBackground(Color.WHITE);
                   wageField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                   wageField.setPreferredSize(new Dimension(80, 25));
                   firstPrompt.setText("Enter the employee's updated first name:  ");
                   firstField.setBackground(Color.WHITE);
                   firstField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                   firstField.setPreferredSize(new Dimension(80, 25));
                   lastPrompt.setText("Enter the employee's updated last name:   ");
                   lastField.setBackground(Color.WHITE);
                   lastField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                   lastField.setPreferredSize(new Dimension(80, 25));
                   enterBTN2.setText("Enter");
                   enterBTN2.addActionListener(new ActionListener()
                   {
                      public void actionPerformed(ActionEvent e)
                      {

                          boolean isIDError = false;
                          boolean isWageError = false;
                          double tempDouble = 0.0;
                          try
                          {
                               tempDouble = (double)(Double.parseDouble(wageField.getText()));
                               if (tempDouble <= 0.0)
                               {
                                   isWageError = true;
                               }
                          }
                          catch (Exception exc)
                          {
                              isWageError = true;
                          }
                          if (isWageError == false)
                          {
                              //need to edit employee here
                              Employee editingEmployee = c.getEmployee(Integer.parseInt(IDField.getText()));
                              if (!firstField.getText().equals(""))
                              {
                                  editingEmployee.setFirst(firstField.getText());
                              }
                              if (!lastField.getText().equals(""))
                              {
                                  editingEmployee.setLast(lastField.getText());
                              }
                              editingEmployee.setWage(tempDouble);
                              frame2.dispose();
                              frame.dispose();
                              companyView();
                          }
                          if (isWageError == true)
                          {
                              wagePrompt.setText("<html><font color='red'>Invalid Wage: </font>Enter employee's updated wage: </html>");
                              
                              frame2.setLocation(457,262);
                              frame2.revalidate();
                              frame2.setVisible(true);
                              IDField.requestFocusInWindow();
                              frame2.pack();
                          }
                      }
                   });
                   cancelBTN2.addActionListener(new ActionListener()
                   {
                      public void actionPerformed(ActionEvent e)
                      {
                          frame2.dispose();
                          glass.setVisible(false);
                          IDField.requestFocusInWindow();
                          //frame.dispose();
                          //companyView();
                      }
                   });
                   cancelBTN2.setText("Cancel");
                   title2.setFont(new Font("Arial", Font.BOLD, 20));
                   title2.setText("Edit Employee: " + temp);
                   title2.setForeground(new Color(0, 100, 180));
                   GridBagLayout gbl = new GridBagLayout();
                   
                   //titlePanel.add(title, BorderLayout.CENTER);
                   //frame.setSize(300, 300);
                   //titlePanel.setSize(300, 300);
                   titlePanel.setLayout(gbl);
                   GridBagConstraints gc = new GridBagConstraints();
                   gc.fill = GridBagConstraints.NONE;
                   gc.insets = new Insets(5, 5, 5, 5);
                   gc.gridwidth = 2;
                   gc.anchor = GridBagConstraints.CENTER;
                   //gc.weighty = 10;
                   gc.gridx = 0;
                   gc.gridy = 0;
                   //frame.add(title, gc);
                   titlePanel.add(title2, gc);
                   gc.insets = new Insets(1, 5, 1, 5);
                   gc.anchor = GridBagConstraints.LINE_START;
                   gc.gridwidth = 1;
                   gc.gridx = 0;
                   gc.gridy = 2;
                   //frame.add(IDPrompt, gc);
                   //titlePanel.add(IDPrompt, gc);
                   gc.gridx = 1;
                   gc.gridy = 2;
                   gc.anchor = GridBagConstraints.LINE_END;
                   //frame.add(IDField, gc);
                   //titlePanel.add(IDField, gc);
                   gc.gridx = 0;
                   gc.gridy = 3;
                   gc.anchor = GridBagConstraints.LINE_START;
                   //frame.add(wagePrompt, gc);
                   titlePanel.add(wagePrompt, gc);
                   gc.gridx = 1;
                   gc.gridy = 3;
                   gc.anchor = GridBagConstraints.LINE_END;
                   //frame.add(wageField, gc);
                   titlePanel.add(wageField, gc);
                   gc.gridx = 0;
                   gc.gridy = 4;
                   gc.anchor = GridBagConstraints.LINE_START;
                   //frame.add(firstPrompt, gc);
                   titlePanel.add(firstPrompt, gc);
                   gc.gridx = 1;
                   gc.gridy = 4;
                   gc.anchor = GridBagConstraints.LINE_END;
                   //frame.add(firstField, gc);
                   titlePanel.add(firstField, gc);
                   gc.gridx = 0;
                   gc.gridy = 5;
                   gc.anchor = GridBagConstraints.LINE_START;
                   //frame.add(lastPrompt, gc);
                   titlePanel.add(lastPrompt, gc);
                   gc.gridx = 1;
                   gc.gridy = 5;
                   gc.anchor = GridBagConstraints.LINE_END;
                   //frame.add(lastField, gc);
                   titlePanel.add(lastField, gc);
                   gc.gridx = 0;
                   gc.gridy = 6;
                   gc.anchor = GridBagConstraints.LINE_START;
                   gc.insets = new Insets(5, 5, 5, 5);
                   //frame.add(enterBTN, gc);
                   panelBottom.add(enterBTN2, BorderLayout.LINE_START);
                   gc.gridx = 1;
                   gc.anchor = GridBagConstraints.LINE_END;
                   //frame.add(cancelBTN, gc);
                   panelBottom.add(cancelBTN2, BorderLayout.LINE_END);
                   frame2.add(titlePanel, BorderLayout.PAGE_START);
                   frame2.add(panelBottom, BorderLayout.PAGE_END);
                   /*
                   IDPanel.add(IDPrompt, BorderLayout.CENTER);
                   IDPanel.add(IDField, BorderLayout.LINE_END);
                   wagePanel.add(wagePrompt, BorderLayout.CENTER);
                   wagePanel.add(wageField, BorderLayout.LINE_END);
                   firstPanel.add(firstPrompt, BorderLayout.CENTER);
                   firstPanel.add(firstField, BorderLayout.LINE_END);
                   lastPanel.add(lastField, BorderLayout.CENTER);
                   lastPanel.add(lastField, BorderLayout.LINE_END);
                   */
                   /*
                   IDPrompt.setVerticalAlignment(SwingConstants.TOP);
                   wagePrompt.setVerticalAlignment(SwingConstants.BOTTOM);
                   firstPrompt.setVerticalAlignment(SwingConstants.TOP);
                   lastPrompt.setVerticalAlignment(SwingConstants.BOTTOM);
                   */
                   /*
                   namePanel.add(firstPanel, BorderLayout.PAGE_START);
                   namePanel.add(lastPanel, BorderLayout.PAGE_END);
                   otherInfoPanel.add(IDPanel, BorderLayout.PAGE_START);
                   otherInfoPanel.add(wagePanel, BorderLayout.PAGE_END);
                   middlePanel.add(otherInfoPanel, BorderLayout.PAGE_START);
                   middlePanel.setLayout(new BorderLayout(1,1));
                   middlePanel.add(namePanel, BorderLayout.PAGE_END);
                   panelBottom.add(enterBTN, BorderLayout.LINE_START);
                   panelBottom.add(cancelBTN, BorderLayout.LINE_END);
                   */
                   //IDField.requestFocusInWindow();
                   frame2.setLocationRelativeTo(null);
                   //frame.setLocation(525,300);
                   frame2.setLocation(466,262);
                   /*
                   frame.add(middlePanel, BorderLayout.CENTER);
                   frame.add(panelBottom, BorderLayout.PAGE_END);
                   frame.add(titlePanel, BorderLayout.PAGE_START);
                   */
                   //IDField.requestFocusInWindow();
                   frame2.getRootPane().setDefaultButton(enterBTN2);
                   frame2.setUndecorated(true);
                   frame2.setVisible(true);
                   frame2.pack();
                   frame2.setVisible(true);
                   //IDField.requestFocusInWindow();
                   //IDField.requestFocusInWindow();
                   frame2.pack();
               }
               else
               {
                   frame.setLocation(484,300);
                   IDPrompt.setText("<html><font color='red'>Invalid ID: </font>Enter ID of employee to edit: </html>");
                   
                   IDField.requestFocusInWindow();
                   frame.pack();
               }
           }
        });
        cancelBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               companyView();
           }
        });
        cancelBTN.setText("Cancel");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setText("Edit Employee: ");
        title.setForeground(new Color(0, 100, 180));
        titlePanel.add(title, BorderLayout.CENTER);
        panelTop.add(IDPrompt, BorderLayout.CENTER);
        panelTop.add(IDField, BorderLayout.LINE_END);
        panelBottom.add(enterBTN, BorderLayout.LINE_START);
        panelBottom.add(cancelBTN, BorderLayout.LINE_END);
        IDField.requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        frame.setLocation(513,300);
        frame.add(panelTop, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.PAGE_END);
        frame.add(titlePanel, BorderLayout.PAGE_START);
        IDField.requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        frame.setLocation(513,300);
        frame.getRootPane().setDefaultButton(enterBTN);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setLocation(513,300);
        frame.setVisible(true);
        frame.pack();
        frame.setVisible(true);
        IDField.requestFocusInWindow();
        IDField.requestFocusInWindow();
        frame.pack();
    }
    private static void removeEmployeeWindow()
    {
        JFrame frame = new JFrame();
        JPanel titlePanel = new JPanel();
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        JButton enterBTN = new JButton();
        JButton cancelBTN = new JButton();
        JLabel IDPrompt = new JLabel();
        JLabel title = new JLabel();
        JTextField IDField = new JTextField();
        IDPrompt.setText("Enter ID of employee to remove: ");
        IDField.setBackground(Color.WHITE);
        IDField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        IDField.setPreferredSize(new Dimension(80, 25));
        enterBTN.setText("Enter");
        enterBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               int temp = 0;
               boolean isError = false;
               boolean employeeExists = false;
               try 
               {
                   temp = (int)(Long.parseLong(IDField.getText()));
                   isError = !Employee.validateID(temp);
                   
                   if ((!isError) && ((c.getEmployee(temp)) != null))
                   {
                       employeeExists = true;
                       IDPrompt.setText("Enter ID of employee to remove: ");
                       frame.setLocation(500,300);
                       frame.revalidate();
                       frame.pack();
                   }
               }
               catch (Exception exc)
               {
                   isError = true;
               }
               if ((isError == false) && (employeeExists == true))
               {
                   //need to remove employee here
                   /*System.out.println("Removed employee ID: " + temp);
                   frame.dispose();
                   companyView();*/
                   
                   MouseListener ml = null;
                   
                   JFrame frame2 = new JFrame();
                   JLabel confirmMSG = new JLabel();
                   JPanel panel2 = new JPanel();
                   JPanel glass = new JPanel();
                   JButton enterBTN2 = new JButton();
                   JButton cancelBTN2 = new JButton();
                   ml = new MouseAdapter() { 
                           public void mousePressed(MouseEvent e)
                           {
                               frame2.setState(JFrame.NORMAL);
                               frame2.toFront();
                               frame2.requestFocus();
                           }
                           };
                   confirmMSG.setText("Are you sure you want to remove employee " + 
                        IDField.getText());
                   enterBTN2.setText("Remove");
                   cancelBTN2.setText("Cancel");
                   enterBTN2.addActionListener(new ActionListener() 
                   {
                       public void actionPerformed(ActionEvent ev)
                       {
                           //remove employee here
                           c.removeEmployee(Integer.parseInt(IDField.getText()));
                           frame2.dispose();
                           frame.dispose();
                           companyView();
                           
                       }
                   });
                   
                   cancelBTN2.addActionListener(new ActionListener()
                   {
                       public void actionPerformed(ActionEvent ev)
                       {
                           frame2.dispose();
                           IDField.requestFocusInWindow();
                           glass.setVisible(false);
                           glass.setOpaque(false);
                           glass.setVisible(false);
                           frame.revalidate();
                           frame.setVisible(true);
                           frame.pack();
                           frame.setVisible(true);
                           frame.pack();
                       }
                   });
                   
                   
                   frame.setGlassPane(glass);
                   glass.setVisible(true);
                   glass.setOpaque(false);
                   //glass.setLayout(null);
                   //glass.setBounds(0, 0, 1300, 700);
                   glass.addMouseListener(ml);
                   frame.getRootPane().setDefaultButton(cancelBTN2);
                   panel2.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                   cancelBTN2.requestFocusInWindow();
                   panel2.add(confirmMSG, BorderLayout.PAGE_START);
                   panel2.add(enterBTN2, BorderLayout.LINE_START);
                   panel2.add(cancelBTN2, BorderLayout.LINE_END);
                   frame2.add(panel2);
                   
                   frame2.setUndecorated(true);
                   frame2.setLocationRelativeTo(null);
                   frame2.setLocation(395, 300);
                   frame2.setVisible(true);
                   frame2.pack();
                   frame2.setVisible(true);
                   frame2.pack();
               }
               else
               {
                   frame.setLocation(471,300);
                   IDPrompt.setText("<html><font color='red'>Invalid ID: </font>Enter ID of employee to remove: </html>");
                   
                   IDField.requestFocusInWindow();
                   frame.pack();
               }
           }
        });
        cancelBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               companyView();
           }
        });
        cancelBTN.setText("Cancel");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setText("Remove Employee: ");
        title.setForeground(new Color(0, 100, 180));
        titlePanel.add(title, BorderLayout.CENTER);
        panelTop.add(IDPrompt, BorderLayout.CENTER);
        panelTop.add(IDField, BorderLayout.LINE_END);
        panelBottom.add(enterBTN, BorderLayout.LINE_START);
        panelBottom.add(cancelBTN, BorderLayout.LINE_END);
        IDField.requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        frame.setLocation(500,300);
        frame.add(panelTop, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.PAGE_END);
        frame.add(titlePanel, BorderLayout.PAGE_START);
        IDField.requestFocusInWindow();
        frame.getRootPane().setDefaultButton(enterBTN);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.pack();
        frame.setVisible(true);
        IDField.requestFocusInWindow();
        IDField.requestFocusInWindow();
        frame.pack();
    }
    private static void employeeView()
    {
        JFrame frame = new JFrame();
        JPanel titlePanel = new JPanel();
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        JButton punchInBTN = new JButton();
        JButton punchOutBTN = new JButton();
        JButton viewTimecardBTN = new JButton();
        JButton toCompanyViewBTN = new JButton();
        JButton exitBTN = new JButton();
        OutlineLabel title = new OutlineLabel();
        OutlineLabel viewTitle = new OutlineLabel();
        //JLabel title = new JLabel();
        //JLabel viewTitle = new JLabel();
        punchInBTN.setPreferredSize(new Dimension(135, 35));
        punchOutBTN.setPreferredSize(new Dimension(135, 35));
        viewTimecardBTN.setPreferredSize(new Dimension(135, 35));
        toCompanyViewBTN.setPreferredSize(new Dimension(200, 35));
        exitBTN.setPreferredSize(new Dimension(60, 35));
        viewTimecardBTN.setText("View Timecard");
        toCompanyViewBTN.setText("Switch to Company View");
        exitBTN.setText("Exit");
        punchInBTN.setText("Clock In");
        punchInBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               punchInWindow();
           }
        });
        punchOutBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               punchOutWindow();
           }
        });
        viewTimecardBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               getTimecardWindow();
           }
        });
        toCompanyViewBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               companyView();
           }
        });
        exitBTN.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.dispose();
               confirmExitPassword();
           }
        });
        punchOutBTN.setText("Clock Out");
        BorderLayout titleLayout = new BorderLayout(5,2);
        titlePanel.setLayout(titleLayout);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setText(c.getName());
        title.setForeground(new Color(0, 100, 180));
        title.setOutlineColor(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        viewTitle.setText("Employee Dashboard");
        viewTitle.setForeground(new Color(50, 150, 170));
        viewTitle.setFont(new Font("Arial", Font.BOLD, 17));
        viewTitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        titlePanel.add(title, BorderLayout.PAGE_START);
        titlePanel.add(viewTitle, BorderLayout.PAGE_END);
        panelTop.add(punchInBTN, BorderLayout.LINE_START);
        panelTop.add(punchOutBTN, BorderLayout.CENTER);
        panelTop.add(viewTimecardBTN, BorderLayout.LINE_END);
        panelBottom.add(toCompanyViewBTN, BorderLayout.LINE_START);
        panelBottom.add(exitBTN, BorderLayout.LINE_END);
        frame.setLocationRelativeTo(null);
        frame.setLocation(437,300);
        frame.add(panelTop, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.PAGE_END);
        frame.add(titlePanel, BorderLayout.PAGE_START);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.pack();
        punchInBTN.requestFocusInWindow();
        frame.setVisible(true);
        frame.pack();
    }
    public static void main(String[] args)
    {
        c = retrieveCompanyData();
        //storeCompanyDataAsCSV(c);
        
        employeeView();
        //String path = "data\\CompanyDataAutosave.txt";
        //while (1 == 1)
        {
            //try
            {
                //every 5 minutes, autosaves the file
                //Thread.sleep(300000);
                //storeCompanyData(c, path);
            }
            //catch (Exception e)
            {
                //System.out.println("Failed to autosave");
            }
        }
        
    }
    
    private static void optionalMain()
    {
        int result = JOptionPane.showOptionDialog(null, "", 
                "Company Name Here", JOptionPane.DEFAULT_OPTION, 
                -1, null, new String[] {"Punch In", "Punch Out", "View Timecard", "Switch to Company View"}, 
                "Test");
        switch (result)
        {
            case -1: 
                confirmExitPassword();
                break;
            case 0: 
                punchInWindow();
                break;
            case 1: 
                punchOutWindow();
                break;
            case 2: 
                getTimecardWindow();
                break;
            case 3: 
                companyView();
                break;
            default: 
                
                break;
        }
    }
    
    
    
    private static Company retrieveCompanyData()
    {
        Company c = new Company();
        String path = "data\\CompanyData.txt";
        File f = new File(path);
        String data = "";
        try
        {
            data = concatenateFileToSingleString(f);
        }
        catch (Exception e)
        {
            System.out.println("Failed to load company data file");
        }
        int state = 0;
        int tempInt = -1;
        int tempSize = -1;
        int tempIndex = -1;
        int tempLoad = 0;
        int tempLoadOld = 0;
        String tempStr = "";
        String tempIn = "";
        String tempOut = "";
        Date tempDateIn = new Date();
        Date tempDateOut = new Date();
        double tempWage = 0.0;
        boolean tempPTO = false;
        double tempOldHours = 0.0;
        double tempOldExpense = 0.0;
        HashTable<Punch> tempHT = new HashTable<Punch>();
        HashTable<Punch> tempHTOld = new HashTable<Punch>();
        LinkedList<Integer> tempKeysList = new LinkedList<Integer>();
        LinkedList<LinkedList<Punch>> tempPunchesList = new LinkedList<LinkedList<Punch>>();
        LinkedList<Punch> temptempPunchesList = new LinkedList<Punch>();
        Punch tempPunch = null;
        while (data.length() > 0)
        {
            if (state == 0)
            {
                tempInt = data.indexOf("\n");
                tempStr = data.substring(0, tempInt);
                c.setName(tempStr);
                data = data.substring(tempInt + 1);
                tempInt = data.indexOf(",");
                tempStr = data.substring(0, tempInt);
                c.setRunningHours(Double.parseDouble(tempStr));
                data = data.substring(tempInt + 1 + 2);
                tempInt = data.indexOf("\n");
                tempStr = data.substring(0, tempInt);
                c.setRunningExpense(Double.parseDouble(tempStr));
                data = data.substring(tempInt + 1);
                tempInt = data.indexOf(":");
                data = data.substring(tempInt + 2);
                tempInt = data.indexOf(",");
                tempStr = data.substring(0, tempInt);
                c.setPayDay1(Integer.parseInt(tempStr));
                data = data.substring(tempInt + 2);
                tempInt = data.indexOf(":");
                data = data.substring(tempInt + 2);
                tempInt = data.indexOf("\n");
                tempStr = data.substring(0, tempInt);
                c.setPayDay2(Integer.parseInt(tempStr));
                data = data.substring(tempInt + 1);
                tempInt = data.indexOf("\n");//removing line "Employees: "
                data = data.substring(tempInt + 1);
                state = 1;
            }
            else if (state == 1)
            {
                if (data.indexOf("\n") <= 1)
                {
                    //finished parsing employees
                    state = 2;
                    tempInt = data.indexOf("\n");
                    data = data.substring(tempInt + 1);
                    tempInt = data.indexOf("Current-");
                    data = data.substring(tempInt + 8);
                    tempInt = data.indexOf(":");
                    data = data.substring(tempInt + 2);
                    tempInt = data.indexOf("\n");
                    tempStr = data.substring(0, tempInt);
                    tempSize = Integer.parseInt(tempStr);
                    data = data.substring(tempInt + 1);
                    tempHT.resize(tempSize);
                }
                else
                {
                    Employee e = new Employee();
                    tempInt = data.indexOf(",");
                    tempStr = data.substring(0, tempInt);
                    if (tempStr.equals("_"))
                    {
                        e.setLast("");
                    }
                    else
                    {
                        e.setLast(tempStr);
                    }
                    data = data.substring(tempInt + 2);
                    tempInt = data.indexOf("\n");
                    tempStr = data.substring(0, tempInt);
                    if (tempStr.equals("_"))
                    {
                        e.setFirst("");
                    }
                    else
                    {
                        e.setFirst(tempStr);
                    }
                    data = data.substring(tempInt + 1 + 1);
                    tempInt = data.indexOf("\n");
                    tempStr = data.substring(0, tempInt);
                    e.setID(Integer.parseInt(tempStr));
                    data = data.substring(tempInt + 1 + 1);
                    tempInt = data.indexOf("\n");
                    tempStr = data.substring(0, tempInt);
                    e.setWage(Double.parseDouble(tempStr));
                    data = data.substring(tempInt + 1 + 1);
                    tempInt = data.indexOf("\n");
                    tempStr = data.substring(0, tempInt);
                    if (tempStr.equals("_"))
                    {
                        e.setMostRecentClockIn(null);
                    }
                    else
                    {
                        Date d = new Date();
                        d.setTime(d.parse(tempStr));
                        e.setMostRecentClockIn(d);
                    }
                    data = data.substring(tempInt + 1);
                    c.addEmployee(e);
                }
            }
            else if (state == 2)
            {
                while (data.substring(0,1).equals("\n"))
                {
                    data = data.substring(1);
                }
                tempInt = data.indexOf(":");
                tempStr = data.substring(0, tempInt);
                if (tempStr.charAt(0) == 'O')
                {
                    //start priorEmployeePunchesHT
                    c.setEmployeePunches(tempHT, tempLoad);
                    state = 3;
                    tempInt = data.indexOf("Old-");
                    data = data.substring(tempInt + 5);
                    tempInt = data.indexOf(":");
                    data = data.substring(tempInt + 2);
                    tempInt = data.indexOf("\n");
                    tempStr = data.substring(0, tempInt);
                    tempSize = Integer.parseInt(tempStr);
                    tempHTOld.resize(tempSize);
                    data = data.substring(tempInt + 1);
                    tempInt = data.indexOf(":");
                    tempStr = data.substring(0, tempInt);
                }
                else
                {
                    tempIndex = Integer.parseInt(tempStr);
                    data = data.substring(tempInt + 1 + 8);
                    tempInt = data.indexOf("}");
                    tempKeysList = new LinkedList<Integer>();
                    while (data.indexOf(",") < tempInt)
                    {
                        //there are multiple keys to read in
                        tempInt = data.indexOf(",");
                        tempStr = data.substring(0, tempInt);
                        tempKeysList.enqueue(Integer.parseInt(tempStr));
                        data = data.substring(tempInt + 4);
                        tempInt = data.indexOf("}");
                    }
                    tempStr = data.substring(0, tempInt);
                    tempKeysList.enqueue(Integer.parseInt(tempStr));
                    data = data.substring(tempInt + 1 + 13);
                    tempPunchesList = new LinkedList<LinkedList<Punch>>();
                    temptempPunchesList = new LinkedList<Punch>();
                    tempInt = data.indexOf("}");
                    boolean contToRead = true;
                    while (contToRead)
                    {
                        //there is still data to read in
                        
                        tempIn = "";
                        tempOut = "";
                        tempDateIn = new Date();
                        tempDateOut = new Date();
                        tempPTO = false;
                        temptempPunchesList = new LinkedList<Punch>();
                        while (!data.substring(0,1).equals("}"))
                        {
                            //temptempPunchesList = new LinkedList<Punch>();
                            //read in all punches for this employee
                            tempIn = "";
                            tempOut = "";
                            tempDateIn = new Date();
                            tempDateOut = new Date();
                            tempPTO = false;
                            tempInt = data.indexOf(",");
                            tempStr = data.substring(0, tempInt);
                            tempIn = tempStr;
                            data = data.substring(tempInt + 1);
                            tempInt = data.indexOf(",");
                            tempStr = data.substring(0, tempInt);
                            tempOut = tempStr;
                            data = data.substring(tempInt + 1);
                            tempInt = data.indexOf(",");
                            tempStr = data.substring(0, tempInt);
                            tempWage = Double.parseDouble(tempStr);
                            data = data.substring(tempInt + 1);
                            if (data.indexOf(",") < data.indexOf("}"))
                            {
                                tempInt = data.indexOf(",");
                            }
                            else
                            {
                                tempInt = data.indexOf("}");
                            }
                            tempStr = data.substring(0, tempInt);
                            tempPTO = Boolean.parseBoolean(tempStr);
                            data = data.substring(tempInt);
                            tempDateIn.setTime(tempDateIn.parse(tempIn));
                            tempDateOut.setTime(tempDateOut.parse(tempOut));
                            tempPunch = new Punch(tempDateIn, tempDateOut, tempWage, tempPTO);
                            temptempPunchesList.enqueue(tempPunch);
                            tempLoad += 1;
                            if (data.substring(0,1).equals(","))
                            {
                                data = data.substring(4);
                            }
                        }
                        tempPunchesList.enqueue(temptempPunchesList);
                        data = data.substring(1);
                        if (data.substring(0, 1).equals(","))
                        {
                            //there are other employee's times to be read
                            data = data.substring(5);
                        }
                        else
                        {
                            //end of the file
                            contToRead = false;
                        }
                        tempInt = data.indexOf("}");
                    }
                    data = data.substring(1);
                    tempHT.setKeys(tempKeysList, tempIndex);
                    tempHT.setPunches(tempPunchesList, tempIndex);
                }
                
            }
            else if (state == 3)
            {
                //reading in prior punches
                while ((data.substring(0,1).equals("\n")) || (data.substring(0,1).equals(";")))
                {
                    data = data.substring(1);
                }
                tempInt = data.indexOf(":");
                tempStr = data.substring(0, tempInt);
                if (tempStr.equals("Hours"))
                {
                    //end of file
                    data = data.substring(tempInt + 2);
                    tempInt = data.indexOf(",");
                    tempStr = data.substring(0, tempInt);
                    tempOldHours = Double.parseDouble(tempStr);
                    data = data.substring(tempInt + 2);
                    tempInt = data.indexOf("$");
                    data = data.substring(tempInt + 1);
                    tempInt = data.indexOf("\n");
                    tempStr = data.substring(0, tempInt);
                    tempOldExpense = Double.parseDouble(tempStr);
                    data = data.substring(tempInt + 1);
                    c.setOldHours(tempOldHours);
                    c.setOldExpense(tempOldExpense);
                    c.setPriorEmployeePunches(tempHTOld, tempLoadOld);
                    return c;
                }
                else
                {
                    tempIndex = Integer.parseInt(tempStr);
                    data = data.substring(tempInt + 1 + 8);
                    tempInt = data.indexOf("}");
                    tempKeysList = new LinkedList<Integer>();
                    while (data.indexOf(",") < tempInt)
                    {
                        //there are multiple keys to read in
                        tempInt = data.indexOf(",");
                        tempStr = data.substring(0, tempInt);
                        tempKeysList.enqueue(Integer.parseInt(tempStr));
                        data = data.substring(tempInt + 4);
                        tempInt = data.indexOf("}");
                    }
                    tempStr = data.substring(0, tempInt);
                    tempKeysList.enqueue(Integer.parseInt(tempStr));
                    data = data.substring(tempInt + 1 + 13);
                    tempPunchesList = new LinkedList<LinkedList<Punch>>();
                    temptempPunchesList = new LinkedList<Punch>();
                    tempInt = data.indexOf("}");
                    boolean contToRead = true;
                    while (contToRead)
                    {
                        //there is still data to read in
                        
                        tempIn = "";
                        tempOut = "";
                        tempDateIn = new Date();
                        tempDateOut = new Date();
                        tempPTO = false;
                        temptempPunchesList = new LinkedList<Punch>();
                        while (!data.substring(0,1).equals("}"))
                        {
                            //temptempPunchesList = new LinkedList<Punch>();
                            //read in all punches for this employee
                            tempIn = "";
                            tempOut = "";
                            tempDateIn = new Date();
                            tempDateOut = new Date();
                            tempPTO = false;
                            tempInt = data.indexOf(",");
                            tempStr = data.substring(0, tempInt);
                            tempIn = tempStr;
                            data = data.substring(tempInt + 1);
                            tempInt = data.indexOf(",");
                            tempStr = data.substring(0, tempInt);
                            tempOut = tempStr;
                            data = data.substring(tempInt + 1);
                            tempInt = data.indexOf(",");
                            tempStr = data.substring(0, tempInt);
                            tempWage = Double.parseDouble(tempStr);
                            data = data.substring(tempInt + 1);
                            if (data.indexOf(",") < data.indexOf("}"))
                            {
                                tempInt = data.indexOf(",");
                            }
                            else
                            {
                                tempInt = data.indexOf("}");
                            }
                            tempStr = data.substring(0, tempInt);
                            tempPTO = Boolean.parseBoolean(tempStr);
                            data = data.substring(tempInt);
                            tempDateIn.setTime(tempDateIn.parse(tempIn));
                            tempDateOut.setTime(tempDateOut.parse(tempOut));
                            tempPunch = new Punch(tempDateIn, tempDateOut, tempWage, tempPTO);
                            temptempPunchesList.enqueue(tempPunch);
                            tempLoadOld += 1;
                            if (data.substring(0,1).equals(","))
                            {
                                data = data.substring(4);
                            }
                        }
                        tempPunchesList.enqueue(temptempPunchesList);
                        data = data.substring(1);
                        if (data.substring(0, 1).equals(","))
                        {
                            //there are other employee's times to be read
                            data = data.substring(5);
                        }
                        else
                        {
                            //end of the file
                            contToRead = false;
                        }
                        tempInt = data.indexOf("}");
                    }
                    data = data.substring(1);
                    tempHTOld.setKeys(tempKeysList, tempIndex);
                    tempHTOld.setPunches(tempPunchesList, tempIndex);
                }
            }
            else
            {
                tempInt = data.indexOf("\n");
                data = data.substring(tempInt + 1);
            }
        }
        return c;
    }
    private static void storeCompanyData(Company c)
    {
        storeCompanyData(c, "data\\CompanyData.txt");
    }
    private static void storeCompanyData(Company c, String path)
    {
        //String path = "data\\CompanyData.txt";
        File file = new File(path);
        String toPrint = c.getName() + "\n";
        toPrint += c.getRunningHours() + ", $" + c.getRunningExpense() + "\n";
        toPrint += "PayDay1: " + c.getPayDay1() + ", PayDay2: " + c.getPayDay2() + "\n";
        toPrint += "Employees: \n";
        Object[] employeeList = c.getEmployees();
        if (employeeList != null)
        {
            for (int a = 0; a < employeeList.length; a += 1)
            {
                if (((Employee)(employeeList[a])).getLast().equals(""))
                {
                    toPrint += "_, ";
                }
                else
                {
                    toPrint += ((Employee)(employeeList[a])).getLast() + ", ";
                }
                if (((Employee)(employeeList[a])).getFirst().equals(""))
                {
                    toPrint += "_\n";
                }
                else
                {
                    toPrint += ((Employee)(employeeList[a])).getFirst() + "\n";
                }
                toPrint += "\t" + ((Employee)(employeeList[a])).getID() + "\n";
                toPrint += "\t" + ((Employee)(employeeList[a])).getWage() + "\n";
                if (((Employee)(employeeList[a])).getMostRecentClockIn() == null)
                {
                    toPrint += "\t_\n";
                }
                else
                {
                    toPrint += "\t" + ((Employee)(employeeList[a])).getMostRecentClockIn() + "\n";
                }
            }
        }
        
        toPrint += "\nCurrent-";
        toPrint += "\nPunches: " + c.getEmployeeTableSize() + "\n";
        toPrint += c.punchesToString();
        toPrint += "\n\nOld-\nPunches: " + c.getPriorEmployeeTableSize() + "\n";
        toPrint += c.priorPunchesToString();
        toPrint += "\n\nHours: " + c.getOldHours() + ", Expense: $" + c.getOldExpense();
        toPrint += "\n\nEnd:;)},\n";
        try 
        {
            FileWriter fileWrite = new FileWriter(file);
            fileWrite.write(toPrint);
            fileWrite.close();
        }
        catch (java.io.IOException e)
        {
            System.out.println("Failed to print to file");
        }
        
    }
    private static String concatenateFileToSingleString(File file) throws java.io.FileNotFoundException
    {
        String s = "";
        Scanner input = new Scanner(file);
        while (input.hasNextLine())
        {
            s = s + input.nextLine();
            s = s + "\n";
        }
        input.close();
        return s;
    }
    private static void storeCompanyDataAsCSV(Company c)
    {
        storeCompanyDataAsCSV(c, "data\\CompanyDataCSV.csv");
        
    }
    private static void storeCompanyDataAsCSV(Company c, String path)
    {
        //String path = "data\\CompanyDataCSV.csv";
        File file = new File(path);
        
        
        LinkedList<Employee> emps = c.getEmployeesList();
        String[] empArr = new String[emps.getSize()];
        Employee currEmployee = null;
        for (int a = 1; a <= emps.getSize(); a += 1)
        {
            currEmployee = emps.getAtIndex(a);
            empArr[a-1]=currEmployee.getFirst() + " " + currEmployee.getLast() + 
                "," + currEmployee.getID() + "," + currEmployee.getWage();
        }
        String[] punchArr = c.toCSV();
        Date now = new Date();
        String csvOutput = "PunchIn,PunchOut,Wage,PTO,EID,Name,,,," + 
            "Employee Name,EID,Wage,,,," + "Company Name" + ",RunningHours: " + 
            ",RunningExpense: ,Run on " + now + "\n";
        int greaterNum = punchArr.length;
        if (empArr.length > punchArr.length)
        {
            greaterNum = empArr.length;
        }
        if (greaterNum <= 0)
        {
            csvOutput += ",,,,,,,,,,,,," + 
                    c.getName() + "," + c.getRunningHours() + "," + 
                    "$" + c.getRunningExpense();
        }
        for (int a = 0; a < greaterNum; a += 1)
        {
            if (a < punchArr.length)
            {
                csvOutput += punchArr[a] + ",,,,";
            }
            else
            {
                //add in commas to match cells
                csvOutput += ",,,,,,,,";
            }
            if (a < empArr.length)
            {
                csvOutput += empArr[a] + ",,,,";
            }
            else
            {
                csvOutput += ",,,,,";
            }
            if (a == 0)
            {
                csvOutput += c.getName() + "," + c.getRunningHours() + "," + 
                    "$" + c.getRunningExpense();
            }
            csvOutput += "\n";
        }
        
        try 
        {
            FileWriter fileWrite = new FileWriter(file);
            fileWrite.write(csvOutput);
            fileWrite.close();
        }
        catch (java.io.IOException e)
        {
            System.out.println("Failed to print to file");
        }
    }
}