import junit.framework.*;
import java.util.Date;

public class PunchTest extends TestCase
{
    public static void testMultNums()
   {
       Date testDate = new Date();
       testDate.setTime(testDate.parse("Nov 11 2023 08:00"));
       Date testDate2 = new Date();
       testDate2.setTime(testDate2.parse("Nov 11 2023 15:15"));
       Punch testPunch = new Punch(testDate, testDate2, 100.0);
       assertEquals("Correct Elapsed Time", 7.25, testPunch.getElapsedTime());
       assertEquals("Correct Wage", 725.0, testPunch.getWageEarned());
       testDate.setTime(testDate.parse("Nov 13 2023 21:30"));
       testDate2.setTime(testDate2.parse("Nov 14 2023 07:15"));
       testPunch = new Punch(testDate, testDate2, 50.0);
       assertEquals("Correct Elapsed Time 2", 9.75, testPunch.getElapsedTime());
       assertEquals("Correct Wage 2", 487.5, testPunch.getWageEarned());
       testDate.setTime(testDate.parse("Nov 14 2023 12:00"));
       testPunch = new Punch(testDate, testDate, 25);
       assertEquals("Correct Elapsed Time 3", 0.0, testPunch.getElapsedTime());
       assertEquals("Correct Wage 3", 0.0, testPunch.getWageEarned());
   }
   public static void main(String[] args)
   {
       junit.textui.TestRunner.run( PunchTest.class );
   }
}