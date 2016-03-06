
package Model;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgreementTest {
    
    public AgreementTest() {
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
     * Test of getAgreement_id method, of class Agreement.
     */
    @Test
    public void testGetAgreement_id() {
        System.out.println("getAgreement_id");
        Agreement instance = new Agreement();
        String expResult = null;
        String result = instance.getAgreement_id();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setAgreement_id method, of class Agreement.
     */
    @Test
    public void testSetAgreement_id() {
        System.out.println("setAgreement_id");
        String agreement_id = "";
        Agreement instance = new Agreement();
        instance.setAgreement_id(agreement_id);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getInsured method, of class Agreement.
     */
    @Test
    public void testGetInsured() {
        System.out.println("getInsured");
        Agreement instance = new Agreement();
        Insured expResult = null;
        Insured result = instance.getInsured();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      
    }

    /**
     * Test of setInsured method, of class Agreement.
     */
    @Test
    public void testSetInsured() {
        System.out.println("setInsured");
        Insured insured = null;
        Agreement instance = new Agreement();
        instance.setInsured(insured);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getInsurer method, of class Agreement.
     */
    @Test
    public void testGetInsurer() {
        System.out.println("getInsurer");
        Agreement instance = new Agreement();
        Insurer expResult = null;
        Insurer result = instance.getInsurer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setInsurer method, of class Agreement.
     */
    @Test
    public void testSetInsurer() {
        System.out.println("setInsurer");
        Insurer insurer = null;
        Agreement instance = new Agreement();
        instance.setInsurer(insurer);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getIns_req method, of class Agreement.
     */
    @Test
    public void testGetIns_req() {
        System.out.println("getIns_req");
        Agreement instance = new Agreement();
        InsuranceRequirement expResult = null;
        InsuranceRequirement result = instance.getIns_req();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      
    }

    /**
     * Test of setIns_req method, of class Agreement.
     */
    @Test
    public void testSetIns_req() {
        System.out.println("setIns_req");
        InsuranceRequirement ins_req = null;
        Agreement instance = new Agreement();
        instance.setIns_req(ins_req);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getDate method, of class Agreement.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Agreement instance = new Agreement();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      
    }

    /**
     * Test of setDate method, of class Agreement.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date date = null;
        Agreement instance = new Agreement();
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
    
    }
    
}
