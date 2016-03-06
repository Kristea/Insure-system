package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GadgetTest {
    
    public GadgetTest() {
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
     * Test of getGadget_id method, of class Gadget.
     */
    @Test
    public void testGetGadget_id() {
        System.out.println("getGadget_id");
        Gadget instance = new Gadget();
        int expResult = 0;
        int result = instance.getGadget_id();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGadget_id method, of class Gadget.
     */
    @Test
    public void testSetGadget_id() {
        System.out.println("setGadget_id");
        int gadget_id = 0;
        Gadget instance = new Gadget();
        instance.setGadget_id(gadget_id);
    }

    /**
     * Test of getType method, of class Gadget.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Gadget instance = new Gadget();
        String expResult = null;
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Gadget.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "";
        Gadget instance = new Gadget();
        instance.setType(type);
    }

    /**
     * Test of getMake method, of class Gadget.
     */
    @Test
    public void testGetMake() {
        System.out.println("getMake");
        Gadget instance = new Gadget();
        String expResult = null;
        String result = instance.getMake();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMake method, of class Gadget.
     */
    @Test
    public void testSetMake() {
        System.out.println("setMake");
        String make = "";
        Gadget instance = new Gadget();
        instance.setMake(make);
    }

    /**
     * Test of getAge method, of class Gadget.
     */
    @Test
    public void testGetAge() {
        System.out.println("getAge");
        Gadget instance = new Gadget();
        int expResult = 0;
        int result = instance.getAge();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAge method, of class Gadget.
     */
    @Test
    public void testSetAge() {
        System.out.println("setAge");
        int age = 0;
        Gadget instance = new Gadget();
        instance.setAge(age);
    }

    /**
     * Test of getPrice method, of class Gadget.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Gadget instance = new Gadget();
        double expResult = 0.0;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPrice method, of class Gadget.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 0.0;
        Gadget instance = new Gadget();
        instance.setPrice(price);
    }

    /**
     * Test of getCondition method, of class Gadget.
     */
    @Test
    public void testGetCondition() {
        System.out.println("getCondition");
        Gadget instance = new Gadget();
        String expResult = null;
        String result = instance.getCondition();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCondition method, of class Gadget.
     */
    @Test
    public void testSetCondition() {
        System.out.println("setCondition");
        String condition = "";
        Gadget instance = new Gadget();
        instance.setCondition(condition);
    }

    /**
     * Test of getValue method, of class Gadget.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Gadget instance = new Gadget();
        double expResult = 0.0;
        double result = instance.getValue();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setValue method, of class Gadget.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        double value = 0.0;
        Gadget instance = new Gadget();
        instance.setValue(value);
    }

    /**
     * Test of calcValue method, of class Gadget.
     */
    @Test
    public void testCalcValue() {
        System.out.println("calcValue");
        Gadget instance = new Gadget();
        double expResult = 0.0;
        double result = instance.calcValue();
        assertEquals(expResult, result, 0.0);
    }
    
}
