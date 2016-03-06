
package Model;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RiskThresholdTest {
    
    public RiskThresholdTest() {
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
     * Test of getRiskT_id method, of class RiskThreshold.
     */
    @Test
    public void testGetRiskT_id() {
        System.out.println("getRiskT_id");
        RiskThreshold instance = new RiskThreshold();
        int expResult = 0;
        int result = instance.getRiskT_id();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRiskT_id method, of class RiskThreshold.
     */
    @Test
    public void testSetRiskT_id() {
        System.out.println("setRiskT_id");
        int riskT_id = 0;
        RiskThreshold instance = new RiskThreshold();
        instance.setRiskT_id(riskT_id);
    }

    /**
     * Test of getGadgets method, of class RiskThreshold.
     */
    @Test
    public void testGetGadgets() {
        System.out.println("getGadgets");
        RiskThreshold instance = new RiskThreshold();
        ArrayList<Gadget> expResult = null;
        ArrayList<Gadget> result = instance.getGadgets();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGadgets method, of class RiskThreshold.
     */
    @Test
    public void testSetGadgets() {
        System.out.println("setGadgets");
        ArrayList<Gadget> gadgets = null;
        RiskThreshold instance = new RiskThreshold();
        instance.setGadgets(gadgets);
    }

    /**
     * Test of getInsuredAge method, of class RiskThreshold.
     */
    @Test
    public void testGetInsuredAge() {
        System.out.println("getInsuredAge");
        RiskThreshold instance = new RiskThreshold();
        int expResult = 0;
        int result = instance.getInsuredAge();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInsuredAge method, of class RiskThreshold.
     */
    @Test
    public void testSetInsuredAge() {
        System.out.println("setInsuredAge");
        int insuredAge = 0;
        RiskThreshold instance = new RiskThreshold();
        instance.setInsuredAge(insuredAge);
    }

    /**
     * Test of getInsuredOccupation method, of class RiskThreshold.
     */
    @Test
    public void testGetInsuredOccupation() {
        System.out.println("getInsuredOccupation");
        RiskThreshold instance = new RiskThreshold();
        String expResult = null;
        String result = instance.getInsuredOccupation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInsuredOccupation method, of class RiskThreshold.
     */
    @Test
    public void testSetInsuredOccupation() {
        System.out.println("setInsuredOccupation");
        String insuredOccupation = "";
        RiskThreshold instance = new RiskThreshold();
        instance.setInsuredOccupation(insuredOccupation);
    }

    /**
     * Test of getTypeOfCover method, of class RiskThreshold.
     */
    @Test
    public void testGetTypeOfCover() {
        System.out.println("getTypeOfCover");
        RiskThreshold instance = new RiskThreshold();
        String expResult = null;
        String result = instance.getTypeOfCover();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTypeOfCover method, of class RiskThreshold.
     */
    @Test
    public void testSetTypeOfCover() {
        System.out.println("setTypeOfCover");
        String typeOfCover = "";
        RiskThreshold instance = new RiskThreshold();
        instance.setTypeOfCover(typeOfCover);
    }

    /**
     * Test of getCoverPeriod method, of class RiskThreshold.
     */
    @Test
    public void testGetCoverPeriod() {
        System.out.println("getCoverPeriod");
        RiskThreshold instance = new RiskThreshold();
        int expResult = 0;
        int result = instance.getCoverPeriod();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCoverPeriod method, of class RiskThreshold.
     */
    @Test
    public void testSetCoverPeriod() {
        System.out.println("setCoverPeriod");
        int coverPeriod = 0;
        RiskThreshold instance = new RiskThreshold();
        instance.setCoverPeriod(coverPeriod);
    }

    /**
     * Test of isIsMatched method, of class RiskThreshold.
     */
    @Test
    public void testIsIsMatched() {
        System.out.println("isIsMatched");
        RiskThreshold instance = new RiskThreshold();
        boolean expResult = false;
        boolean result = instance.isIsMatched();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIsMatched method, of class RiskThreshold.
     */
    @Test
    public void testSetIsMatched() {
        System.out.println("setIsMatched");
        boolean isMatched = false;
        RiskThreshold instance = new RiskThreshold();
        instance.setIsMatched(isMatched);
    }
    
}
