package com.example.lawsyst;

public class Bills_Pojo
{

    private String id;
    private String mattername;
    private String billrefno;
    private String bill_id;
    private String receiveddate;
    private String entrydate;
    private String duedate;
    private String supplier;
    private String clientid;
    private String CLIENT;
    private String billamount;

    private String currency;
    private String STATUS;
    private String addedby;



    public String getId ()
    {
        return id;
    }
    public void setId (String id)
    {
        this.id = id;
    }

    public String getMattername ()
    {
        return mattername;
    }
    public void setMattername (String mattername)
    {
        this.mattername = mattername;
    }

    public String getBillreference ()
    {
        return billrefno;
    }
    public void setBillreference (String billrefno)
    {
        this.billrefno = billrefno;
    }

    public String getBill_id ()
    {
        return bill_id;
    }
    public void setBill_id (String bill_id)
    {
        this.bill_id = bill_id;
    }

    public String getReceiveddate ()
    {
        return receiveddate;
    }
    public void setReceiveddate (String receiveddate)
    {
        this.receiveddate = receiveddate;
    }

    public String getEntrydate ()
    {
        return entrydate;
    }
    public void setEntrydate (String entrydate)
    {
        this.entrydate = entrydate;
    }

    public String getDuedate ()
    {
        return duedate;
    }
    public void setDuedate (String duedate)
    {
        this.duedate = duedate;
    }

    public String getSupplier ()
    {
        return supplier;
    }
    public void setSupplier (String supplier)
    {
        this.supplier = supplier;
    }



    public String getClientid ()
    {
        return clientid;
    }
    public void setClientid (String clientid)
    {
        this.clientid = clientid;
    }




    public String getCLIENT()
    {
        return CLIENT;
    }
    public void setCLIENT (String CLIENT)
    {
        this.CLIENT = CLIENT;
    }



    public String getBillamount ()
    {
        return billamount;
    }
    public void setBillamount (String billamount)
    {
        this.billamount = billamount;
    }




    public String getCurrency ()
    {
        return currency;
    }
    public void setCurrency (String currency)
    {
        this.currency = currency;
    }



    public String getSTATUS ()
    {
        return STATUS;
    }
    public void setSTATUS (String STATUS)
    {
        this.STATUS = STATUS;
    }



    public String getAddedby ()
    {
        return addedby;
    }
    public void setAddedby (String addedby)
    {
        this.addedby = addedby;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", mattername = "+mattername+", billtypeid = "+billrefno+", bill_id = "+bill_id+", STATUS = "+STATUS+", receiveddate = "+receiveddate+", entrydate = "+entrydate+", duedate = "+duedate+",supplier = "+supplier+" , clientid = "+clientid+", billamount = "+billamount+" ,currency = "+currency+",STATUS = "+STATUS+",addedby = "+addedby+",addedby = "+addedby+"]";
    }
}

