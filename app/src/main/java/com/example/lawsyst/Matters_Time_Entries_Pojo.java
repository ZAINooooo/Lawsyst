package com.example.lawsyst;

class Matters_Time_Entries_Pojo
{



    private String id;

    private String matterid;

    private String contactid;

    private String itemid;

    private String userid;

    private String timeentry_status;

    private String hourlyrate;

    private String mattername;

    private String resource;

    private String item;

    private String billrefno;



    public String getId ()
    {
        return id;
    }
    public void setId (String id)
    {
        this.id = id;
    }

    public String getMatterid ()
    {
        return matterid;
    }
    public void setMatterid (String matterid)
    {
        this.matterid = matterid;
    }

    public String getContactid ()
    {
        return contactid;
    }
    public void setContactid (String contactid)
    {
        this.contactid = contactid;
    }

    public String getItemid ()
    {
        return itemid;
    }
    public void setItemid (String itemid)
    {
        this.itemid = itemid;
    }

    public String getUserid ()
    {
        return userid;
    }
    public void setUserid (String userid)
    {
        this.userid = userid;
    }



    public String getTimeentry_status ()
    {
        return timeentry_status;
    }
    public void setTimeentry_status (String timeentry_status)
    {
        this.timeentry_status = timeentry_status;
    }

    public String getHourlyrate ()
    {
        return hourlyrate;
    }
    public void setHourlyrate (String hourlyrate)
    {
        this.hourlyrate = hourlyrate;
    }


    public String getMattername ()
    {
        return mattername;
    }
    public void setMattername (String mattername)
    {
        this.mattername = mattername;
    }



    public String getResource ()
    {
        return resource;
    }
    public void setResource (String resource)
    {
        this.resource = resource;
    }

    public String getItem ()
    {
        return item;
    }
    public void setItem (String item)
    {
        this.item = item;
    }



    public String getBillrefno ()
    {
        return billrefno;
    }
    public void setBillrefno (String billrefno)
    {
        this.billrefno = billrefno;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", matterid = "+matterid+", contactid = "+contactid+", itemid = "+itemid+", userid = "+userid+", timeentry_status = "+timeentry_status+", hourlyrate = "+hourlyrate+", mattername = "+mattername+", resource = "+resource+", item = "+item+", billrefno = "+billrefno+"]";
    }
}
