package com.example.lawsyst;

class FilterResultPojo
{

    public String matternumber;
    private String old_referencenum;
    private String clientname;
    private String propertytype;
    private String Billto;
    private String assignto;

    private String price;

    public String statusdescription;
    private String status;












    public String getMatternumber ()
    {
        return matternumber;
    }
    public void setMatternumber (String matternumber)
    {
        this.matternumber = matternumber;
    }

    public String getOld_referencenum ()
    {
        return old_referencenum;
    }
    public void setOld_referencenum (String old_referencenum)
    {
        this.old_referencenum = old_referencenum;
    }



    public String getClientname ()
    {
        return clientname;
    }
    public void setClientname (String clientname)
    {
        this.clientname = clientname;
    }



    public String getPropertytype ()
    {
        return propertytype;
    }
    public void setPropertytype (String propertytype)
    {
        this.propertytype = propertytype;
    }

    public String getBillto()
    {
        return Billto;
    }
    public void setBillto (String Billto)
    {
        this.Billto = Billto;
    }


    public String getPrice()
    {
        return price;
    }
    public void setPrice (String price)
    {
        this.price = price;
    }


    public String getAssignto()
    {
        return assignto;
    }
    public void setAssignto (String assignto)
    {
        this.assignto = assignto;
    }

    public String getStatusdescription()
    {
        return statusdescription;
    }
    public void setStatusdescription (String statusdescription)
    {
        this.statusdescription = statusdescription;
    }

    public String getStatus()
    {
        return status;
    }
    public void setStatus (String status)
    {
        this.status = status;
    }

}
