
package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InsurerTest {
    
    public InsurerTest() {
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
     * Test of getRiskT method, of class Insurer.
     */
    @Test
    public void testGetRiskT() {
        System.out.println("getRiskT");
        Insurer instance = new Insurer();
        RiskThreshold expResult = null;
        RiskThreshold result = instance.getRiskT();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRiskT method, of class Insurer.
     */
    @Test
    public void testSetRiskT() {
        System.out.println("setRiskT");
        RiskThreshold riskT = null;
        Insurer instance = new Insurer();
        instance.setRiskT(riskT);
    }

    /**
     * Test of isDecision method, of class Insurer.
     */
    @Test
    public void testIsDecision() {
        System.out.println("isDecision");
        Insurer instance = new Insurer();
        boolean expResult = false;
        boolean result = instance.isDecision();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDecision method, of class Insurer.
     */
    @Test
    public void testSetDecision() {
        System.out.println("setDecision");
        boolean decision = false;
        Insurer instance = new Insurer();
        instance.setDecision(decision);
    }

    /**
     * Test of isNotification method, of class Insurer.
     */
    @Test
    public void testIsNotification() {
        System.out.println("isNotification");
        Insurer instance = new Insurer();
        boolean expResult = false;
        boolean result = instance.isNotification();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNotification method, of class Insurer.
     */
    @Test
    public void testSetNotification() {
        System.out.println("setNotification");
        boolean notification = false;
        Insurer instance = new Insurer();
        instance.setNotification(notification);
    }
    
}
