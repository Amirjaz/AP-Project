package business_domain;

import java.io.Serializable;

/**
 * Simply used for saving contact information for customers.
 */
public class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    /**
     * Check if this contact matches the provided contact.
     * We say it's a match if at least one of their attributes are equal.
     * @param contact the contact you want to check with
     * @return true if they match
     */
    public boolean matches(Contact contact){
        if(this.getPhoneNumber().equals(contact.getPhoneNumber())) return true;
        if(this.getName().equals(contact.getName())) return true;
        if(this.getEmailAddress().equals(contact.getEmailAddress())) return true;
        return false;
    }
    /**
     * Copy the contents of an <Code>Contact</Code> object to this object, to avoid reference collisions.
     * @param contact the object you want to copy from
     */
    public void copy(Contact contact){
        this.setName(contact.getName());
        this.setPhoneNumber(contact.getPhoneNumber());
        this.setEmailAddress(contact.getEmailAddress());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
