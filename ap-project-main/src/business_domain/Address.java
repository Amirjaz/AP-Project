package business_domain;

import java.io.Serializable;

/**
 * Address Class used to simplify saving addresses for <Code>Customer</Code>
 */
public class Address implements Serializable {
    private String name;
    private String fullAddress;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setFullAddress(String fullAddress){
        this.fullAddress = fullAddress;
    }
    public String getFullAddress(){
        return this.fullAddress;
    }

    /**
     * Copy the contents of an <Code>Address</Code> object to this object, to avoid reference collisions.
     * @param address the object you want to copy from
     */
    public void copy(Address address){
        this.name = address.name;
        this.fullAddress = address.fullAddress;
    }
}
