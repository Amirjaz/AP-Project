package user_interface;

import java.util.ArrayList;
import business_domain.*;

/**
 * Creating menus for different parts of the GUI.
 */
public class Menu {
    public static void shopMenu(Shop shop) {

        Window window = new Window();
        window.addButton("Add Order",            "1");
        window.addButton("Add Customer",         "2");
        window.addButton("Add Item",             "3");
        window.addButton("View Customers",       "4");
        window.addButton("View Orders",          "5");
        window.addButton("View Items",           "6");
        window.addButton("View Orders By State", "7");
        window.addButton("Exit",                 "0");
        while(true){
            GUI.showWindow(window);
            System.out.println("------------------------------------------");
            System.out.println("Choose your next action:");
            System.out.println("    1 : Add Order");
            System.out.println("    2 : Add Customer");
            System.out.println("    3 : Add Item");
            System.out.println("    4 : View Customers");
            System.out.println("    5 : View Orders");
            System.out.println("    6 : View Items");
            System.out.println("    7 : View Orders By State");
            System.out.println("  0 : Exit");
            String option = UI.nextLine();
            if(option.equals("1")){
                var order = UI.inputOrder(shop.getCustomers(), shop.getItems());
                if(order != null){
                    shop.addOrder(order);
                    System.out.println("Order added successfully");
                }
            }
            if(option.equals("2")){
                var customer = UI.inputCustomer();
                if(customer != null){
                    shop.addCustomer(customer);
                }
            }
            if(option.equals("3")){
                var item = UI.inputItem();
                if(item != null){
                    shop.addItem(item);
                }
            }
            if(option.equals("4")){
                var customer = UI.chooseCustomer(shop.getCustomers());
                if(customer == null){
                    continue;
                }
                CustomerMenu(customer);
            }
            if(option.equals("5")){
                OrdersMenu(shop.getOrders());
            }
            if(option.equals("6")){
                ItemsMenu(shop.getItems());
            }
            if(option.equals("7")){
                var state = UI.chooseOrderState();
                if(state == null){
                    continue;
                }
                OrdersMenu(shop.getOrdersByState(state));
            }
            if(option.equals("0")){
                return;
            }
        }
    }
    public static void CustomerMenu(Customer customer){


        while(true){
            Window window = new Window();
            window.addText(customer.getContact().getName() + "       " + customer.getContact().getPhoneNumber());
            window.addButton("View Addresses",          "1");
            window.addButton("View Orders",             "2");
            window.addButton("View Contact Information","3");
            window.addButton("Add Address",             "4");
            window.addButton("Back",                    "0");
            GUI.showWindow(window);
            System.out.println("------------------------------------------");
            System.out.println(customer.getContact().getName() + "       " + customer.getContact().getPhoneNumber());
            System.out.println("Choose your next action:");
            System.out.println("    1 : View Addresses");
            System.out.println("    2 : View Orders");
            System.out.println("    3 : View Contact Information");
            System.out.println("    4 : Add Address");
            System.out.println("  0 : Back");
            String option = UI.nextLine();
            if(option.equals("1")){
                var address = UI.chooseAddress(customer.getAddresses());
                if(address == null){
                    continue;
                }
                AddressMenu(address);
            }
            if(option.equals("2")){
                OrdersMenu(customer.getOrders());
            }
            if(option.equals("3")){
                ContactMenu(customer.getContact());
            }
            if(option.equals("4")){
                var address = UI.inputAddress();
                if(address == null){
                    continue;
                }
                customer.addAddress(address);
                System.out.println("Address added successfully.");
            }
            if(option.equals("0")){
                return;
            }
        }
    }

    public static void AddressMenu(Address address){


        while(true){
            Window window = new Window();
            window.addText(address.getName() + "\n    " + address.getFullAddress());
            window.addButton("Edit Address",          "1");
            window.addButton("Back",                  "0");
            GUI.showWindow(window);
            System.out.println("------------------------------------------");
            System.out.println(address.getName());
            System.out.println("    " + address.getFullAddress());
            System.out.println("Choose your next action:");
            System.out.println("    1 : Edit Address");
            System.out.println("  0 : Back");
            String option = UI.nextLine();
            if(option.equals("1")){
                var temp = UI.inputAddress();
                if(temp != null){
                    address.copy(temp);
                }
            }
            if(option.equals("0")){
                return;
            }
        }
    }
    public static void OrdersMenu(ArrayList<Order> orders){
        while(true){
            Window window = new Window();
            for(int i = orders.size() - 1; i >= 0; i--){
                var order = orders.get(i);
                window.addButton( "By " + order.getCustomer().getContact().getName() + " at " + order.getTimeOrdered().getDateString() + "     ---     " + order.getOrderState().name(), "" + (i + 1));
            }
            window.addButton("Back", "0");
            GUI.showWindow(window);

            System.out.println("------------------------------------------");
            System.out.println("Choose Order:");
            for(int i = orders.size() - 1; i >= 0; i--){
                var order = orders.get(i);
                System.out.printf("    %d : By %s at %s     ---     %s\n", i + 1, order.getCustomer().getContact().getName(), order.getTimeOrdered().getDateString(), order.getOrderState().name());
            }
            System.out.println("  0 : Back");
            int option = UI.nextInt();
            if(option == 0){
                return;
            }
            int index = option - 1;
            if(0 <= index && index < orders.size()) {
                OrderMenu(orders.get(index));
            }
        }
    }
    public static void OrderMenu(Order order){

        while(true) {
            System.out.println("------------------------------------------");
            System.out.println(order.getCustomer().getContact().getName() + "     " + order.getTimeOrdered().getDateTimeString() + "     " + order.getOrderState().name());
            System.out.println("Goes to " + order.getAddress().getName() + ": " + order.getAddress().getFullAddress());
            var orderItemList = order.getOrderItemList();
            for(int i = 0; i < orderItemList.size(); i++){
                System.out.println("    " + (i + 1) + ". " + String.format("%-15s" , orderItemList.get(i).getSubItem().getItem().getTitle()) + "  " + String.format("%-15s", orderItemList.get(i).getSubItem().getTitle()) + "    x" + orderItemList.get(i).getCnt());
            }
            System.out.println("    " + "  Total Price:    $" + order.getTotalPrice());
            System.out.println("Choose your next action:");
            System.out.println("    1 : Add OrderItem");
            System.out.println("    2 : Remove OrderItem");
            System.out.println("    3 : Update OrderStatus");
            System.out.println("  0 : Back");


            Window window = new Window();
            window.addText(order.getCustomer().getContact().getName() + "     " + order.getTimeOrdered().getDateTimeString() + "     " + order.getOrderState().name());
            window.addText("Goes to " + order.getAddress().getName() + ": " + order.getAddress().getFullAddress());
            for(int i = 0; i < orderItemList.size(); i++){
                window.addText("    " + (i + 1) + ". " + String.format("%-15s" , orderItemList.get(i).getSubItem().getItem().getTitle()) + "  " + String.format("%-15s", orderItemList.get(i).getSubItem().getTitle()) + "    x" + orderItemList.get(i).getCnt());
            }
            window.addText("Total Price:    $" + order.getTotalPrice());
            window.addButton("Add OrderItem",          "1");
            window.addButton("Remove OrderItem",       "2");
            window.addButton("Update OrderStatus",     "3");
            window.addButton("Back",                   "0");
            GUI.showWindow(window);

            String option = UI.nextLine();
            if(option.equals("0")){
                return;
            }
            if(option.equals("1")){
                var orderItem = UI.chooseOrderItem(order.getShop().getItems());
                if(orderItem == null) {
                    continue;
                }
                order.addOrderItem(orderItem);
            }
            if(option.equals("2")){
                var windowRem = new Window();
                windowRem.addText("Enter the index of the item you want to remove:");
                windowRem.addInputField();
                windowRem.addButton("Back", "0");
                windowRem.addEnterButton();
                GUI.showWindow(windowRem);

                System.out.println("Enter the index of the item you want to remove:");
                int index = UI.nextInt() - 1;
                if(index == -1){
                    continue;
                }
                order.removeOrderItemByIndex(index);
            }
            if(option.equals("3")){
                var state = UI.chooseOrderState();
                if(state == null){
                    continue;
                }
                order.setOrderState(state);
            }
        }
    }
    public static void ContactMenu(Contact contact){
        while(true){

            Window window = new Window();
            window.addText(contact.getName() + "        " + contact.getPhoneNumber());
            window.addText("Email Address: " + contact.getEmailAddress());
            window.addButton("Edit contact",          "1");
            window.addButton("Back",                  "0");
            GUI.showWindow(window);

            System.out.println("------------------------------------------");
            System.out.println(contact.getName() + "        " + contact.getPhoneNumber());
            System.out.println("Email Address: " + contact.getEmailAddress());
            System.out.println("Choose your next action:");
            System.out.println("    1 : Edit Contact");
            System.out.println("  0 : Back");

            String option = UI.nextLine();
            if(option.equals("1")){
                var temp = UI.inputContact();
                if(temp == null){
                    continue;
                }
                contact.copy(temp);
            }
            if(option.equals("0")){
                return;
            }
        }
    }
    public static void ItemsMenu(ArrayList<Item> items){
        while(true){

            Window window = new Window();
            for(int i = 0; i < items.size(); i++){
                var item = items.get(i);
                System.out.println("    " + (i + 1) + ". " + item.getTitle());
                window.addButton(item.getTitle(), "" + (i + 1));
            }
            window.addButton("Back",                   "0");
            GUI.showWindow(window);

            System.out.println("------------------------------------------");
            System.out.println("Choose Item:");
            for(int i = 0; i < items.size(); i++){
                var item = items.get(i);
                System.out.println("    " + (i + 1) + ". " + item.getTitle());
            }
            System.out.println("  0 : Back");
            int option = UI.nextInt();
            if(option == 0){
                return;
            }
            if(0 < option && option <= items.size()) {
                int index = option - 1;
                ItemMenu(items.get(index));
            }
        }
    }
    public static void ItemMenu(Item item){
        while(true){

            Window window = new Window();
            var subItems = item.getPackages();
            for(int i = 0; i < subItems.size(); i++){
                var subItem = subItems.get(i);
                window.addText("    " + (i + 1) + ". " + String.format("%-15s", subItem.getTitle()) + "     " + String.format("%-15s", "$" + subItem.getPrice()) + "    sold: x" + subItem.getSoldCnt());
            }
            window.addButton("Remove Item",            "1");
            window.addButton("Back",                   "0");
            GUI.showWindow(window);

            System.out.println("------------------------------------------");
            subItems = item.getPackages();
            for(int i = 0; i < subItems.size(); i++){
                var subItem = subItems.get(i);
                System.out.println("    " + (i + 1) + ". " + subItem.getTitle() + "     $" + subItem.getPrice() + "    sold: x" + subItem.getSoldCnt());
            }
            System.out.println("Choose your next action:");
            System.out.println("    1 : Remove Item");
            System.out.println("  0 : Back");
            String option = UI.nextLine();
            if(option.equals("0")){
                return;
            }
            if(option.equals("1")){
                var windowConfirm = new Window();
                windowConfirm.addText("Are you sure you want to remove Item \"" + item.getTitle() +"\"?");
                windowConfirm.addButton("Yes", "Y");
                windowConfirm.addButton("No", "N");
                GUI.showWindow(windowConfirm);

                System.out.println("Are you sure you want to remove Item \"" + item.getTitle() +"\"? (Y/N)");
                String inp = UI.nextLine();
                System.out.println(inp);
                if(!inp.equals("Y")){
                    continue;
                }
                item.getShop().removeItem(item);
                System.out.println("Item removed successfully.");
                return;
            }
        }
    }
}
