import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    MainClass mainClassObject = new MainClass();

    @Test
    public void testGetLocalNumber()
    {
        int a = this.mainClassObject.getLocalNumber();
        if (a == 14)
        {
            System.out.println("getLocalNumber returned 14");
        } else
            {
            Assert.assertTrue("Number isn't 14", a == 14);
            }
    }

    @Test
    public void testGetClassNumber()
    {
        int b = this.mainClassObject.getClassNumber();
        if (b > 45)
        {
            System.out.println("getClassNumber returned number > 45");
        } else
            {
                Assert.assertTrue("Number is < 45", b > 45);
            }

    }

    @Test
    public void testGetClassString()
    {
        String stringToCheck = this.mainClassObject.getClassString();
        String stringToCompare = "hello";

        boolean result = stringToCheck.regionMatches(true, 0, stringToCompare, 0, stringToCompare.length());
        if (result)
        {
            System.out.println(result);
        } else
            {
                Assert.assertTrue("The words 'Hello' and 'hello' are missing", result == true );
        }


    }



}
