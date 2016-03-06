
package Model;

import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InsuredTest {
    
    public InsuredTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDob method, of class Insured.
     */
    @Test
    public void testGetDob() {
        System.out.println("getDob");
        Insured instance = new Insured();
        Date expResult = null;
        Date result = instance.getDob();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDob method, of class Insured.
     */
    @Test
    public void testSetDob() {
        System.out.println("setDob");
        Date dob = null;
        Insured instance = new Insured();
        instance.setDob(dob);
    }

    /**
     * Test of getOccupation method, of class Insured.
     */
    @Test
    public void testGetOccupation() {
        System.out.println("getOccupation");
        Insured instance = new Insured();
        String expResult = null;
        String result = instance.getOccupation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOccupation method, of class Insured.
     */
    @Test
    public void testSetOccupation() {
        System.out.println("setOccupation");
        String occupation = "";
        Insured instance = new Insured();
        instance.setOccupation(occupation);
    }

    /**
     * Test of getInsReq method, of class Insured.
     */
    @Test
    public void testGetInsReq() {
        System.out.println("getInsReq");
        Insured instance = new Insured();
        ArrayList<InsuranceRequirement> expResult = null;
        ArrayList<InsuranceRequirement> result = instance.getInsReq();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInsReq method, of class Insured.
     */
    @Test
    public void testSetInsReq() {
        System.out.println("setInsReq");
        ArrayList<InsuranceRequirement> insReq = null;
        Insured instance = new Insured();
        instance.setInsReq(insReq);
    }

    /**
     * Test of calcAge method, of class Insured.
     */
    @Test
    public void testCalcAge() {
        System.out.println("calcAge");
        Insured instance = new Insured();
        int expResult = 0;
        int result = instance.calcAge();
        assertEquals(expResult, result);
    }
    
}
