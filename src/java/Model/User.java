/**
 * User.java; Models the an User user. Contains fields, 2 sets of constructors
 * and accessor & mutator methods for each field.
 */
package Model;

import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private String email;
    private String title;
    private String fName;
    private String sName;
    private String addressLine1;
    private String street;
    private String city;
    private String postcode;
    private String role;
    private int ratingCount;
    private double rating;
    private ArrayList<Request> requests;
    private ArrayList<Agreement> agreements;

    public User() {
    }

    public User(String username, String password, String email, String title, String fName,
            String sName, String addressLine1, String street, String city, String postcode, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.title = title;
        this.fName = fName;
        this.sName = sName;
        this.addressLine1 = addressLine1;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.role = role;
        requests = new ArrayList<Request>();
        agreements = new ArrayList<Agreement>();
        rating = 0;
        ratingCount = 0;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username to a given username
     *
     * @param username : given username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password to a given password
     *
     * @param password : given password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email to a given email
     *
     * @param email : given email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title to a given title
     *
     * @param title : given title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return fName; first name of User
     */
    public String getfName() {
        return fName;
    }

    /**
     * Sets the fName to a given first name
     *
     * @param fName : given first name
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * @return sName; surname of User
     */
    public String getsName() {
        return sName;
    }

    /**
     * Sets the sName to a given surname
     *
     * @param sName : given surname
     */
    public void setsName(String sName) {
        this.sName = sName;
    }

    /**
     * @return addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the addressLine1 to a given address line
     *
     * @param addressLine1 : given address line
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street to a given street
     *
     * @param street : given street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city to a given city
     *
     * @param city : given city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the postcode to a given postcode
     *
     * @param postcode : given postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role to a given role
     *
     * @param role : given role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     *
     * @return ArrayList of requests
     */
    public ArrayList<Request> getRequests() {
        return requests;
    }

    /**
     * sets Arraylist of requests
     *
     * @param requests
     */
    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    /**
     *
     * @return ArrayList of agreements
     */
    public ArrayList<Agreement> getAgreements() {
        return agreements;
    }

    /**
     * sets ArrayList of agreements
     *
     * @param agreements : list to set to
     */
    public void setAgreements(ArrayList<Agreement> agreements) {
        this.agreements = agreements;
    }

    /**
     * @return ratingCount
     */
    public int getRatingCount() {
        return ratingCount;
    }

    /**
     * @return rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Calculate the updated rating by adding a new int and increment the count
     * of ratings and get the average of all ratings
     *
     * @param newRating
     */
    public void calcRating(int newRating) {
        this.ratingCount++;
        this.rating = (this.rating + newRating) / ratingCount;

    }

    /**
     * Sets the rating to a given rating
     *
     * @param rating : given rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Sets the ratingCount to a given ratingCount
     *
     * @param ratingCount : given ratingCount
     */
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }


}
