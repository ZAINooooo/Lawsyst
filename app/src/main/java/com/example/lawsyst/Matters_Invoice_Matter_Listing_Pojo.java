package com.example.lawsyst;

class Matters_Invoice_Matter_Listing_Pojo
{

    private int invoicenum;
    private int invoiceid;
    private String issued_date;
    private String duedate;
    private String STATUS;
    private double invoicetotalamount;
    private String invoicerefno;

    private String landlord_email;
    private String landlord_fname;
    private String invoicepath;
    private String invoicefilename;
    private  String completepath;
    private  String mattername;




    public int getInvoicenum ()
    {
        return invoicenum;
    }
    public void setInvoicenum (int invoicenum)
    {
        this.invoicenum = invoicenum;
    }

    public int getInvoiceid ()
    {
        return invoiceid;
    }
    public void setInvoiceid (int invoiceid)
    {
        this.invoiceid = invoiceid;
    }

    public String getIssued_date()
    {
        return issued_date;
    }
    public void setIssued_date (String issued_date)
    {
        this.issued_date = issued_date;
    }

    public String getDuedate()
    {
        return duedate;
    }
    public void setDuedate (String duedate)
    {
        this.duedate = duedate;
    }




    public String getSTATUS()
    {
        return STATUS;
    }
    public void setSTATUS (String STATUS)
    {
        this.STATUS = STATUS;
    }



    public double getInvoicetotalamount()
    {
        return invoicetotalamount;
    }
    public void setInvoicetotalamount (double invoicetotalamount)
    {
        this.invoicetotalamount = invoicetotalamount;
    }




    public String getInvoicerefno()
    {
        return invoicerefno;
    }
    public void setInvoicerefno (String invoicerefno)
    {
        this.invoicerefno = invoicerefno;
    }

    public String getLandlord_email()
    {
        return landlord_email;
    }
    public void setLandlord_email (String landlord_email)
    {
        this.landlord_email = landlord_email;
    }

    public String getLandlord_fname()
    {
        return landlord_fname;
    }
    public void setLandlord_fname (String landlord_fname)
    {
        this.landlord_fname = landlord_fname;
    }

    public String getInvoicepath()
    {
        return invoicepath;
    }
    public void setInvoicepath (String invoicepath)
    {
        this.invoicepath = invoicepath;
    }

    public String getInvoicefilename()
    {
        return invoicefilename;
    }

    public void setInvoicefilename (String invoicefilename)
    {
        this.invoicefilename = invoicefilename;
    }

    public String getCompletepath()
    {
        return completepath;
    }
    public void setCompletepath (String completepath)
    {
        this.completepath = completepath;
    }

    public String getMattername()
    {
        return mattername;
    }
    public void setMattername (String mattername)
    {
        this.mattername = mattername;
    }

}
