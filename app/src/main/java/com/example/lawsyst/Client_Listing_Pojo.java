package com.example.lawsyst;

public class Client_Listing_Pojo
{

    private String landlordid;
    private String name;
    private String CurrencyShortDesc;
    private String referenceno;
    private String country;
    private String landlord_mobile;
    private String landlord_email;
    private String company_name;

    public String getLandlordid ()
    {
        return landlordid;
    }
    public void setLandlordid (String landlordid)
    {
        this.landlordid = landlordid;
    }

    public String getName ()
    {
        return name;
    }
    public void setName (String name)
    {
        this.name = name;
    }

    public String getCurrencyShortDesc ()
    {
        return CurrencyShortDesc;
    }
    public void setCurrencyShortDesc (String CurrencyShortDesc)
    {
        this.CurrencyShortDesc = CurrencyShortDesc;
    }


    public String getReferenceno ()
    {
        return referenceno;
    }

    public void setReferenceno (String referenceno)
    {
        this.referenceno = referenceno;
    }


    public String getCountry ()
    {
        return country;
    }
    public void setCountry (String country)
    {
        this.country = country;
    }




    public String getLandlord_mobile ()
    {
        return landlord_mobile;
    }
    public void setLandlord_mobile (String landlord_mobile)
    {
        this.landlord_mobile = landlord_mobile;
    }




    public String getLandlord_email ()
    {
        return landlord_email;
    }
    public void setLandlord_email (String landlord_email)
    {
        this.landlord_email = landlord_email;
    }




    public String getCompany_name ()
    {
        return company_name;
    }

    public void setCompany_name (String company_name)
    {
        this.company_name = company_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [landlordid = "+landlordid+",  name = "+name+", CurrencyShortDesc = "+CurrencyShortDesc+", referenceno = "+referenceno+", country = "+country+", landlord_mobile = "+landlord_mobile+", landlord_email = "+landlord_email+", company_name = "+company_name+"]";
    }
}

