package user_interface;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import business_domain.*;

/**
 * Class for communicating with the user.
 * Overall consists of a bunch of methods that each get certain things from the user,
 * so I won't get into the details of each method as they are clear and need no further explanation.
 */
public class UI {
    private static class MyThread extends Thread{

        private boolean running = true;
        private static Scanner scanner = new Scanner(System.in);

        public void turnOff(){
            running = false;
        }
        public void run(){
            String inp;
            while(running){
                inp = scanner.nextLine();
                if(inp.equals("")){
                    continue;
                }
                UI.newLine(inp);
            }
        }
    }
    private static Vector<String> input = new Vector<>();

    public static void start(){
        var thread = new MyThread();
        thread.start();
    }
    public static String nextLine(){
        while(input.size() == 0){

        }
        String inp = input.get(0);
        input.remove(0);
        System.out.println("read: " + inp);
        return inp;
    }
    public static int nextInt(){
        var inp = UI.nextLine();
        int x = -1;
        try {
            System.out.println(inp);
            x = Integer.parseInt(inp);
        } finally {
            System.out.println(inp + " " + x);
            return x;
        }
    }

    /**
     * Deliberately add a line to the input.
     * @param inp the fucking line?
     */
    public static void newLine(String inp){
        input.add(inp);
    }
    public static Customer inputCustomer(){
        System.out.println("Getting Customer Information");
        var customer = new Customer(Customer.nextCustomerId());
        var contact = UI.inputContact();
        if(contact == null){
            return null;
        }
        var address = UI.inputAddress();
        if(address == null){
            return null;
        }
        customer.setContact(contact);
        customer.addAddress(address);
        return customer;
    }
    public static Customer chooseCustomer(ArrayList<Customer> customers){

        Window window = new Window();

        System.out.println("Choose a customer:");
        for(int i = 0; i < customers.size(); i++){
            System.out.println("    " + (i + 1) + ": " + customers.get(i).getContact().getName());
            window.addButton(customers.get(i).getContact().getName(), "" + (i + 1));
        }
        System.out.println("  0 : cancel");
        window.addButton("Cancel", "0");
        GUI.showWindow(window);
        int index = UI.nextInt() - 1;
        if(index == -1){
            return null;
        }
        while(!(0 <= index && index < customers.size())){
            System.out.println("Index out of range! Please try again:");
            index = UI.nextInt() - 1;
            if(index == -1){
                return null;
            }
        }
        return customers.get(index);
    }
    public static Address inputAddress(){
        System.out.println("Getting Address Information");
        Address address = new Address();

        Window window = new Window();
        window.addText("Enter Address name:");
        window.addInputField();
        window.addText("Enter fullAddress:");
        window.addInputField();
        window.addButton("Back", "back");
        window.addEnterButton();
        GUI.showWindow(window);

        String inp;
        System.out.println("Enter Address name");
        inp = UI.nextLine();
        if(inp.equals("back")){
            return null;
        }
        address.setName(inp);
        System.out.println("Enter fullAddress");
        inp = UI.nextLine();
        if(inp.equals("back")){
            return null;
        }
        address.setFullAddress(inp);
        return address;
    }
    public static Address chooseAddress(ArrayList<Address> addresses){

        Window window = new Window();

        System.out.println("Choose an address:");
        for(int i = 0; i < addresses.size(); i++){
            System.out.println("    " + (i + 1) + ": " + addresses.get(i).getName());
            window.addButton(addresses.get(i).getName(), "" + (i + 1));
        }
        System.out.println("  0 : cancel");
        window.addButton("Cancel", "0");

        GUI.showWindow(window);
        int index = UI.nextInt() - 1;
        if(index == -1){
            return null;
        }
        while(!(0 <= index && index < addresses.size())){
            System.out.println("Index out of range! Please try again:");
            index = UI.nextInt() - 1;
            if(index == -1){
                return null;
            }
        }
        return addresses.get(index);
    }
    public static Contact inputContact(){
        System.out.println("Getting Contact information");
        Contact contact = new Contact();

        Window window = new Window();
        window.addText("Enter contact name:");
        window.addInputField();
        window.addText("Enter Contact phoneNumber:");
        window.addInputField();
        window.addText("Enter Contact emailAddress:");
        window.addInputField();
        window.addButton("Back", "back");
        window.addEnterButton();
        GUI.showWindow(window);

        String inp;
        System.out.println("Enter Contact name");
        inp = UI.nextLine();
        if(inp.equals("back")){
            return null;
        }
        contact.setName(inp);
        System.out.println("Enter Contact phoneNumber");
        inp = UI.nextLine();
        if(inp.equals("back")){
            return null;
        }
        contact.setPhoneNumber(inp);
        System.out.println("Enter Contact emailAddress");
        inp = UI.nextLine();
        if(inp.equals("back")){
            return null;
        }
        contact.setEmailAddress(inp);
        return contact;
    }

    public static Item inputItem(){
        String inp;
        var item = new Item(Item.nextItemId());

        Window window = new Window();
        window.addText("Enter Item title:");
        window.addInputField();
        window.addText("Enter number of packages:");
        window.addInputField();
        window.addButton("Back", "back");
        window.addEnterButton();
        GUI.showWindow(window);

        System.out.println("Enter Item title:");
        inp = UI.nextLine();
        if(inp.equals("back")){
            return null;
        }
        item.setTitle(inp);
        System.out.println("Enter number of packages");
        inp = UI.nextLine();
        if(inp.equals("back")){
            return null;
        }
        int n = 0;
        try{
            n = Integer.parseInt(inp);
        } catch (NumberFormatException e) {
            return null;
        }
        for(int i = 0; i < n; i++){
            var subItem = UI.inputSubItem(item);
            if(subItem == null){
                return null;
            }
            item.addPackage(subItem);
        }
        return item;
    }
    public static Item chooseItem(ArrayList<Item> items){
        System.out.println("Choose the Item:");
        var window = new Window();
        for(int i = 0; i < items.size(); i++){
            System.out.println("    " + (i + 1) + ": " + items.get(i).getTitle());
            window.addButton(items.get(i).getTitle(), "" + (i + 1));
        }
        System.out.println("  0 : cancel");
        window.addButton("Cancel", "0");
        GUI.showWindow(window);

        int index = UI.nextInt() - 1;
        if(index == -1){
            return null;
        }
        while(!(0 <= index && index < items.size())){
            System.out.println("Index out of range! Please try again:");
            index = UI.nextInt() - 1;
            if(index == -1){
                return null;
            }
        }
        return items.get(index);
    }
    public static Item.SubItem chooseSubItem(ArrayList<Item.SubItem> items){
        System.out.println("Choose the SubItem:");
        var window = new Window();
        for(int i = 0; i < items.size(); i++){
            System.out.println("    " + (i + 1) + ": " + items.get(i).getTitle());
            window.addButton(items.get(i).getTitle(), "" + (i + 1));
        }
        System.out.println("  0 : cancel");
        window.addButton("Cancel", "0");
        GUI.showWindow(window);
        int index = UI.nextInt() - 1;
        if(index == -1){
            return null;
        }
        while(!(0 <= index && index < items.size())){
            System.out.println("Index out of range! Please try again:");
            index = UI.nextInt() - 1;
            if(index == -1){
                return null;
            }
        }
        return items.get(index);
    }
    public static Item.SubItem inputSubItem(Item item){
        Item.SubItem subItem = new Item.SubItem(item);

        Window window = new Window();
        window.addText("Enter SubItem title:");
        window.addInputField();
        window.addText("Enter SubItem price:");
        window.addInputField();
        window.addButton("Back", "back");
        window.addEnterButton();
        GUI.showWindow(window);

        String inp;
        System.out.println("Enter SubItem title:");
        inp = UI.nextLine();
        if(inp.equals("back")){
            return null;
        }
        subItem.setTitle(inp);
        System.out.println("Enter SubItem price:");
        inp = UI.nextLine();
        if(inp.equals("back")){
            return null;
        }
        try {
            subItem.setPrice(Integer.parseInt(inp));
        } catch (NumberFormatException e){
            return null;
        }
        return subItem;
    }
    public static Order inputOrder(ArrayList<Customer> customers, ArrayList<Item> items){
        var customer = UI.chooseCustomer(customers);
        if(customer == null){
            return null;
        }
        Order order = new Order(Order.nextOrderId());
        order.setCustomer(customer);
        var address = UI.chooseAddress(customer.getAddresses());
        if(address == null){
            return null;
        }
        var newAddress = new Address();
        newAddress.copy(address);
        order.setAddress(newAddress);
        String inp;
        int n;

        Window window = new Window();
        window.addText("Enter number of Order Items:");
        window.addInputField();
        window.addButton("Back", "back");
        window.addEnterButton();
        GUI.showWindow(window);

        System.out.println("Enter number of Order Items:");
        inp = UI.nextLine();
        if(inp.equals("back")){
            return null;
        }
        try{
            n = Integer.parseInt(inp);
        } catch (NumberFormatException e){
            return null;
        }
        for(int i = 0; i < n; i++){
            var orderItem = UI.chooseOrderItem(items);
            if(orderItem == null){
                return null;
            }
            order.addOrderItem(orderItem);
        }
        return order;
    }
    public static Order.State chooseOrderState(){
        var stateList = Order.State.values();
        int index = 1;

        Window window = new Window();

        System.out.println("Choose OrderState:");
        for(Order.State st : stateList){
            System.out.println("    " + index + ": " + st.name());
            window.addButton(st.name(), "" + index);
            index++;
        }
        System.out.println("  0: Back");
        window.addButton("Cancel", "0");
        GUI.showWindow(window);

        int ind = UI.nextInt() - 1;
        if(0 <= ind && ind < index - 1) return stateList[ind];
        return null;
    }
    public static Order.OrderItem inputOrderItem(Item.SubItem item){
        String inp;
        var orderItem = new Order.OrderItem();
        orderItem.setSubItem(item);

        Window window = new Window();
        window.addText("How many:");
        window.addInputField();
        window.addButton("Back", "back");
        window.addEnterButton();
        GUI.showWindow(window);

        System.out.println("How many?");
        inp = UI.nextLine();
        if(inp.equals("back")){
            return null;
        }
        try{
            orderItem.setCnt(Integer.parseInt(inp));
        }catch (NumberFormatException e){
            return null;
        }
        return orderItem;
    }
    public static Order.OrderItem chooseOrderItem(ArrayList<Item> items){
        var item = UI.chooseItem(items);
        if(item == null){
            return null;
        }
        var subItem = UI.chooseSubItem(item.getPackages());
        if(subItem == null){
            return null;
        }
        return UI.inputOrderItem(subItem);
    }
}
