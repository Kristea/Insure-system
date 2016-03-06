/**
 * Gadget.java; Models a Gadget. Contains fields, 2 sets of constructors and
 * accessor & mutator methods for each field.
 */
package Model;

public class Gadget {

    private String gadget_id;
    private String type;
    private String make;
    private String model;
    private double price;

    public Gadget() {
    }

    public Gadget(String gadget_id, String type, String make, String model, double price) {
        this.gadget_id = gadget_id;
        this.type = type;
        this.make = make;
        this.model = model;
        this.price = price;

    }

    /**
     * @return gadget id
     */
    public String getGadget_id() {
        return gadget_id;
    }

    /**
     * Sets gadget_id to a given id
     * @param gadget_id : given id
     */
    public void setGadget_id(String gadget_id) {
        this.gadget_id = gadget_id;
    }

    /**
     * @return type of gadget
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type of gadget to a given type
     * @param type : given type of gadget
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return make; make of gadget
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets make (of gadget) to a given make
     * @param make : given make of gadget
     */
    public void setMake(String make) {
        this.make = make;
    }

      /**
     * @return model; model of gadget
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets model (of gadget) to a given model
     * @param model : given model of gadget
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return price; price of gadget
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price (of gadget) to a given price
     * @param price : given price
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
