package com.example.lawsyst;

import android.util.Log;

import java.util.Comparator;

public class Contacts_Listing_Pojo
{

    private String id;
    private String name;
    private String lname;
    private String mobile_number;
    private String email;
    private String countryid;
    private String country_code_no;
    private String agency_name;
    private String agency_id;
    private String contacttypename;




    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }



    public String getName ()
    {
        return name;
    }
    public void setName (String name)
    {
        this.name = name;
    }

    public String getLname ()
    {
        return lname;
    }
    public void setLname (String lname)
    {
        this.lname = lname;
    }

    public String getMobile_number ()
    {
        return mobile_number;
    }
    public void setMobile_number (String mobile_number)
    {
        this.mobile_number = mobile_number;
    }

    public String getEmail ()
    {
        return email;
    }
    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getCountryid ()
    {
        return countryid;
    }
    public void setCountryid (String countryid)
    {
        this.countryid = countryid;
    }

    public String getCountry_code_no ()
    {
        return country_code_no;
    }

    public void setCountry_code_no (String country_code_no)
    {
        this.country_code_no = country_code_no;
    }

    public String getAgency_name ()
    {
        return agency_name;
    }
    public void setAgency_name (String agency_name)
    {
        this.agency_name = agency_name;
    }



    public String getAgency_id ()
    {
        return agency_id;
    }
    public void setAgency_id (String agency_id)
    {
        this.agency_id = agency_id;
    }




    public String getContacttypename ()
    {
        return contacttypename;
    }

    public void setContacttypename (String contacttypename)
    {
        this.contacttypename = contacttypename;
    }



    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", name = "+name+", lname = "+lname+", phoneno = "+mobile_number+", email = "+email+", countryid = "+countryid+", country_code_no = "+country_code_no+", agency_name = "+agency_name+", agency_id = "+agency_id+"]";
    }



//    public static Comparator<Contacts_Listing_Pojo> customerSort = (o1, o2) -> {
//
//        double balance1 = Double.parseDouble(o1.g());
//        double balance2 = Double.parseDouble(o2.getBalance());
//
//
//        Log.i("List","B1: "+o1.getBalance()+" B2"+o2.getBalance());
//        /*For ascending order*/
//        return (int) (balance2-balance1);
//
//        /*For descending order*/
//        //rollno2-rollno1;
//    };


}

