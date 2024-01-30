package business_domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Shop Class.
 */
public class Shop implements Serializable {
    private Contact contact;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> removedItems = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    /**
     * Add a customer to this shop.
     * @param customer the customer you want to add
     * @return the index of the added customer
     */
    public int addCustomer(Customer customer){ // returns index of customer
        customers.add(customer);
        return customers.size() - 1;
    }
    /**
     * Add an item to this shop.
     * @param item the item you want to add
     * @return the index of the added item
     */
    public int addItem(Item item){ // returns index of item
        item.setShop(this);
        items.add(item);
        return items.size() - 1;
    }
    public ArrayList<Customer> getCustomers(){
        return this.customers;
    }
    public ArrayList<Item> getItems(){
        return this.items;
    }

    /**
     * Get a customer that matches the provided contact information if such customer exists in the shop.
     * @param contact contact information of the customer you are looking for
     * @return the customer, <Code>null</Code> if not found
     */
    public Customer getCustomerByContact(Contact contact){
        for(Customer customer : this.customers){
            if(customer.getContact().matches(contact)){
                return customer;
            }
        }
        return null;
    }
    public Customer getCustomerByCustomerId(int customerId){
        for(Customer customer : this.customers){
            if(customer.getCustomerId() == customerId){
                return customer;
            }
        }
        return null;
    }
    public ArrayList<Order> getOrders(){
        return this.orders;
    }

    /**
     * Get all orders that are in a certain <Code>Order.State</Code>
     * @param state the state you are looking for
     * @return the list of orders with this state
     */
    public ArrayList<Order> getOrdersByState(Order.State state){
        ArrayList<Order> ordersByState = new ArrayList<>();
        for(Order order : this.orders){
            if(order.getOrderState() == state){
                ordersByState.add(order);
            }
        }
        return ordersByState;
    }

    /**
     * Invoice an order in the shop.
     * @param order the order
     */
    public void addOrder(Order order){
        assert order.getOrderState() == Order.State.INCOMPLETE;
        order.setShop(this);
        order.setOrderState(Order.State.WAITING_FOR_PAYMENT);
        var customer = order.getCustomer();
        customer.addOrder(order);
        this.orders.add(order);
    }

    /**
     * Remove an item that exists in the shop.
     * These items are stored in the database in case they might be needed later.
     * @param item the item you want to remove (needs to exist in the shop or will cause an <Code>Exception</Code>)
     */
    public void removeItem(Item item){
        this.items.remove(item);
        this.removedItems.add(item);
    }
}
