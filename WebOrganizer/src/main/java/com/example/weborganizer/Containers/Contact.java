package com.example.weborganizer.Containers;

/**
 * Created by Sasha on 23.11.13.
 */
public class Contact {

    public int contactId;
    public String contactName;
    public String contactSurname;
    public int contactPhone;
    public String coonTactEmail;
    public String coontactBirth;

    public Contact(int contactId,String contactName,String contactSurname,int contactPhone,String coonTactEmail,
                   String coontactBirth){
        this.contactId=contactId;
        this.contactName=contactName;
        this.contactSurname=contactSurname;
        this.contactPhone=contactPhone;
        this.coonTactEmail=coonTactEmail;
        this.coontactBirth =coontactBirth;
    }

    @Override
    public String toString() {
        return contactId+" | "+contactName+" | "+contactSurname+" | "+coontactBirth+" | "+
                coonTactEmail+" | "+contactPhone;
    }
}
