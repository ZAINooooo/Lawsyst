package com.example.lawsyst;

class Matters_Invoice_Bills_Listing_Pojo
{

    private String billrefno;
    private String entrydate;
    private double billamount;
    private double billtaxamount;
    public String STATUS;
    private String TYPE;
    private String matter_name;
    private String due_date;


    public String getDue_date ()
    {
        return due_date;
    }
    public void setDue_date (String due_date)
    {
        this.due_date = due_date;
    }

    public String getMatter_name ()
    {
        return matter_name;
    }
    public void setMatter_name (String matter_name)
    {
        this.matter_name = matter_name;
    }

    public String getBillrefno ()
    {
        return billrefno;
    }
    public void setBillrefno (String billrefno)
    {
        this.billrefno = billrefno;
    }

    public String getEntrydate ()
    {
        return entrydate;
    }
    public void setEntrydate (String entrydate)
    {
        this.entrydate = entrydate;
    }

    public double getBillamount()
    {
        return billamount;
    }
    public void setBillamount (double billamount)
    {
        this.billamount = billamount;
    }

    public double getBilltaxamount()
    {
        return billtaxamount;
    }
    public void setBilltaxamount (double billtaxamount)
    {
        this.billtaxamount = billtaxamount;
    }

    public String getSTATUS()
    {
        return STATUS;
    }
    public void setSTATUS (String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getTYPE()
    {
        return TYPE;
    }
    public void setTYPE (String TYPE)
    {
        this.TYPE = TYPE;
    }

}
