package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    public UserTest() {
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
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        User instance = new User();
        String expResult = null;
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class User.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        User instance = new User();
        instance.setUsername(username);
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = new User();
        String expResult = null;
        String result = instance.getPassword();
        assertEquals(expResult, result);    
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        User instance = new User();
        instance.setPassword(password);
     }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        User instance = new User();
        String expResult = null;
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        User instance = new User();
        instance.setEmail(email);
    }

    /**
     * Test of getTitle method, of class User.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        User instance = new User();
        String expResult = null;
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class User.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "";
        User instance = new User();
        instance.setTitle(title);
    }

    /**
     * Test of getfName method, of class User.
     */
    @Test
    public void testGetfName() {
        System.out.println("getfName");
        User instance = new User();
        String expResult = null;
        String result = instance.getfName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setfName method, of class User.
     */
    @Test
    public void testSetfName() {
        System.out.println("setfName");
        String fName = "";
        User instance = new User();
        instance.setfName(fName);
    }

    /**
     * Test of getsName method, of class User.
     */
    @Test
    public void testGetsName() {
        System.out.println("getsName");
        User instance = new User();
        String expResult = null;
        String result = instance.getsName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setsName method, of class User.
     */
    @Test
    public void testSetsName() {
        System.out.println("setsName");
        String sName = "";
        User instance = new User();
        instance.setsName(sName);
    }

    /**
     * Test of getAddressLine1 method, of class User.
     */
    @Test
    public void testGetAddressLine1() {
        System.out.println("getAddressLine1");
        User instance = new User();
        String expResult = null;
        String result = instance.getAddressLine1();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddressLine1 method, of class User.
     */
    @Test
    public void testSetAddressLine1() {
        System.out.println("setAddressLine1");
        String addressLine1 = "";
        User instance = new User();
        instance.setAddressLine1(addressLine1);
    }

    /**
     * Test of getStreet method, of class User.
     */
    @Test
    public void testGetStreet() {
        System.out.println("getStreet");
        User instance = new User();
        String expResult = null;
        String result = instance.getStreet();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStreet method, of class User.
     */
    @Test
    public void testSetStreet() {
        System.out.println("setStreet");
        String street = "";
        User instance = new User();
        instance.setStreet(street);
    }

    /**
     * Test of getCity method, of class User.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        User instance = new User();
        String expResult = null;
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCity method, of class User.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        String city = "";
        User instance = new User();
        instance.setCity(city);
    }

    /**
     * Test of getPostcode method, of class User.
     */
    @Test
    public void testGetPostcode() {
        System.out.println("getPostcode");
        User instance = new User();
        String expResult = null;
        String result = instance.getPostcode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPostcode method, of class User.
     */
    @Test
    public void testSetPostcode() {
        System.out.println("setPostcode");
        String postcode = "";
        User instance = new User();
        instance.setPostcode(postcode);
    }
    
}
