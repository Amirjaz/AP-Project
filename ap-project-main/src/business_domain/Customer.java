package business_domain;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Customer Class for containing the data of each customer in the shop.
 */
public class Customer implements Serializable {

    private static int lastCustomerId;
    private int customerId;
    private Contact contact;
    private ArrayList<Address> addresses = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public Customer(int customerId){
        this.customerId = customerId;
    }

    public static int nextCustomerId(){
        return ++lastCustomerId;
    }
    public int getCustomerId(){
        return this.customerId;
    }
    public Contact getContact(){
        return this.contact;
    }
    public void setContact(Contact contact){
        this.contact = contact;
    }
    public ArrayList<Address> getAddresses(){
        return this.addresses;
    }

    /**
     * Add an address to the list of addresses for this customer.
     * @param address the address you want to add
     */
    public void addAddress(Address address){
        this.addresses.add(address);
    }

    /**
     * Add what the customer has just ordered.
     * @param order the new order you want to add
     */
    public void addOrder(Order order){
        this.orders.add(order);
    }
    public ArrayList<Order> getOrders(){
        return this.orders;
    }

}
