package com.example.lawsyst;

public class Invoice_Listing_Pojo
{

    private String invoicenum;
    private String invoiceid;
    private String issued_date;
    private String duedate;
    private String STATUS;
    private String invoicetotalamount;
    private String invoicerefno;
    private String landlord_email;
    private String landlord_fname;
    private String invoicefilename;
    private String mattername;





    public String getInvoicenum ()
    {
        return invoicenum;
    }
    public void setInvoicenum (String invoicenum)
    {
        this.invoicenum = invoicenum;
    }

    public String getInvoiceid ()
    {
        return invoiceid;
    }
    public void setInvoiceid (String invoiceid)
    {
        this.invoiceid = invoiceid;
    }

    public String getIssued_date ()
    {
        return issued_date;
    }
    public void setIssued_date (String issued_date)
    {
        this.issued_date = issued_date;
    }

    public String getDuedate ()
    {
        return duedate;
    }
    public void setDuedate (String duedate)
    {
        this.duedate = duedate;
    }

    public String getSTATUS ()
    {
        return STATUS;
    }
    public void setSTATUS (String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getInvoicetotalamount ()
    {
        return invoicetotalamount;
    }
    public void setInvoicetotalamount (String invoicetotalamount)
    {
        this.invoicetotalamount = invoicetotalamount;
    }

    public String getInvoicerefno ()
    {
        return invoicerefno;
    }

    public void setInvoicerefno (String invoicerefno)
    {
        this.invoicerefno = invoicerefno;
    }

    public String getLandlord_email ()
    {
        return landlord_email;
    }
    public void setLandlord_email (String landlord_email)
    {
        this.landlord_email = landlord_email;
    }



    public String getLandlord_fname ()
    {
        return landlord_fname;
    }
    public void setLandlord_fname (String landlord_fname)
    {
        this.landlord_fname = landlord_fname;
    }




    public String getInvoicefilename ()
    {
        return invoicefilename;
    }
    public void setInvoicefilename (String invoicefilename)
    {
        this.invoicefilename = invoicefilename;
    }



    public String getMattername ()
    {
        return mattername;
    }
    public void setMattername (String mattername)
    {
        this.mattername = mattername;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [invoicenum = "+invoicenum+", invoiceid = "+invoiceid+", issued_date = "+issued_date+", duedate = "+duedate+", STATUS = "+STATUS+", invoicetotalamount = "+invoicetotalamount+", invoicerefno = "+invoicerefno+", landlord_email = "+landlord_email+",landlord_fname = "+landlord_fname+" , invoicefilename = "+invoicefilename+", mattername = "+mattername+"]";
    }
}

