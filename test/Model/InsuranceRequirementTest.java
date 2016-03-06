
package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InsuranceRequirementTest {
    
    public InsuranceRequirementTest() {
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
     * Test of getId method, of class InsuranceRequirement.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        InsuranceRequirement instance = new InsuranceRequirement();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class InsuranceRequirement.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        InsuranceRequirement instance = new InsuranceRequirement();
        instance.setId(id);
    }

    /**
     * Test of getInsured method, of class InsuranceRequirement.
     */
    @Test
    public void testGetInsured() {
        System.out.println("getInsured");
        InsuranceRequirement instance = new InsuranceRequirement();
        Insured expResult = null;
        Insured result = instance.getInsured();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInsured method, of class InsuranceRequirement.
     */
    @Test
    public void testSetInsured() {
        System.out.println("setInsured");
        Insured insured = null;
        InsuranceRequirement instance = new InsuranceRequirement();
        instance.setInsured(insured);
    }

    /**
     * Test of getGadget method, of class InsuranceRequirement.
     */
    @Test
    public void testGetGadget() {
        System.out.println("getGadget");
        InsuranceRequirement instance = new InsuranceRequirement();
        Gadget expResult = null;
        Gadget result = instance.getGadget();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGadget method, of class InsuranceRequirement.
     */
    @Test
    public void testSetGadget() {
        System.out.println("setGadget");
        Gadget gadget = null;
        InsuranceRequirement instance = new InsuranceRequirement();
        instance.setGadget(gadget);
    }

    /**
     * Test of getTypeOfCover method, of class InsuranceRequirement.
     */
    @Test
    public void testGetTypeOfCover() {
        System.out.println("getTypeOfCover");
        InsuranceRequirement instance = new InsuranceRequirement();
        String expResult = null;
        String result = instance.getTypeOfCover();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTypeOfCover method, of class InsuranceRequirement.
     */
    @Test
    public void testSetTypeOfCover() {
        System.out.println("setTypeOfCover");
        String typeOfCover = "";
        InsuranceRequirement instance = new InsuranceRequirement();
        instance.setTypeOfCover(typeOfCover);
    }

    /**
     * Test of getCoverPeriod method, of class InsuranceRequirement.
     */
    @Test
    public void testGetCoverPeriod() {
        System.out.println("getCoverPeriod");
        InsuranceRequirement instance = new InsuranceRequirement();
        int expResult = 0;
        int result = instance.getCoverPeriod();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCoverPeriod method, of class InsuranceRequirement.
     */
    @Test
    public void testSetCoverPeriod() {
        System.out.println("setCoverPeriod");
        int coverPeriod = 0;
        InsuranceRequirement instance = new InsuranceRequirement();
        instance.setCoverPeriod(coverPeriod);
    }

    /**
     * Test of isIsSelected method, of class InsuranceRequirement.
     */
    @Test
    public void testIsIsSelected() {
        System.out.println("isIsSelected");
        InsuranceRequirement instance = new InsuranceRequirement();
        boolean expResult = false;
        boolean result = instance.isIsSelected();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIsSelected method, of class InsuranceRequirement.
     */
    @Test
    public void testSetIsSelected() {
        System.out.println("setIsSelected");
        boolean isSelected = false;
        InsuranceRequirement instance = new InsuranceRequirement();
        instance.setIsSelected(isSelected);
    }
    
}
