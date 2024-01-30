package business_domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Item Class for containing the data of each item in the shop.
 */
public class Item implements Serializable {
    private static int lastItemId = 0;
    public static int nextItemId(){
        return ++lastItemId;
    }
    public Item(int itemId){
        this.itemId = itemId;
    }

    /**
     * SubItem for each package the item comes in.
     */
    public static class SubItem implements Serializable{
        private Item item;
        private String title;
        private int price;

        private int soldCnt;
        public SubItem(Item item) {
            this.item = item;
        }
        public SubItem(Item item, String title, int price) {
            this.item = item;
            this.title = title;
            this.price = price;
        }
        public void setItem(Item item){
            this.item = item;
        }
        public Item getItem(){
            return this.item;
        }
        public void setPrice(int price){
            this.price = price;
        }
        public int getPrice(){
            return this.price;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
        public int getSoldCnt(){
            return this.soldCnt;
        }

        /**
         * Increment the number of times this SubItem has been sold.
         * @param x the number of newly sold items
         */
        public void incSoldCnt(int x){
            this.soldCnt += x;
        }
    }
    private int itemId;
    private String title;
    private Shop shop;
    private ArrayList<SubItem> packages = new ArrayList<>();
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setPackages(ArrayList<SubItem> packages){
        this.packages = packages;
    }
    public void addPackage(SubItem subItem){
        packages.add(subItem);
    }
    public ArrayList<SubItem> getPackages(){
        return this.packages;
    }
    public void setShop(Shop shop){
        this.shop = shop;
    }
    public Shop getShop(){
        return this.shop;
    }

}
