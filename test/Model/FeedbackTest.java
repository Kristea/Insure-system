
package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FeedbackTest {
    
    public FeedbackTest() {
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
     * Test of getFeedback_id method, of class Feedback.
     */
    @Test
    public void testGetFeedback_id() {
        System.out.println("getFeedback_id");
        Feedback instance = new Feedback();
        String expResult = null;
        String result = instance.getFeedback_id();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setFeedback_id method, of class Feedback.
     */
    @Test
    public void testSetFeedback_id() {
        System.out.println("setFeedback_id");
        String feedback_id = "";
        Feedback instance = new Feedback();
        instance.setFeedback_id(feedback_id);
        
    }

    /**
     * Test of getInsured method, of class Feedback.
     */
    @Test
    public void testGetInsured() {
        System.out.println("getInsured");
        Feedback instance = new Feedback();
        Insured expResult = null;
        Insured result = instance.getInsured();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setInsured method, of class Feedback.
     */
    @Test
    public void testSetInsured() {
        System.out.println("setInsured");
        Insured insured = null;
        Feedback instance = new Feedback();
        instance.setInsured(insured);
        
    }

    /**
     * Test of getInsurer method, of class Feedback.
     */
    @Test
    public void testGetInsurer() {
        System.out.println("getInsurer");
        Feedback instance = new Feedback();
        Insurer expResult = null;
        Insurer result = instance.getInsurer();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setInsurer method, of class Feedback.
     */
    @Test
    public void testSetInsurer() {
        System.out.println("setInsurer");
        Insurer insurer = null;
        Feedback instance = new Feedback();
        instance.setInsurer(insurer);
        
    }

    /**
     * Test of getRating method, of class Feedback.
     */
    @Test
    public void testGetRating() {
        System.out.println("getRating");
        Feedback instance = new Feedback();
        int expResult = 0;
        int result = instance.getRating();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setRating method, of class Feedback.
     */
    @Test
    public void testSetRating() {
        System.out.println("setRating");
        int rating = 0;
        Feedback instance = new Feedback();
        instance.setRating(rating);
      }

    /**
     * Test of getComments method, of class Feedback.
     */
    @Test
    public void testGetComments() {
        System.out.println("getComments");
        Feedback instance = new Feedback();
        String expResult = null;
        String result = instance.getComments();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setComments method, of class Feedback.
     */
    @Test
    public void testSetComments() {
        System.out.println("setComments");
        String comments = "";
        Feedback instance = new Feedback();
        instance.setComments(comments);
        
    }
    
}
