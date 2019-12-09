package com.example.lawsyst;

public class Matters_Communication_Pojo
{

    private String id;
    private String matterid;
    private String fromname;
    private String datetime;
    private String receivername ;
    private String communicationstatus;
    private String commtype;

    private String directPath;

    private String date;
    private String mattername;
    private String httppath;
    private String senderid;
    private String inoutward;
    private String file_name;



    public String getId ()
    {
        return id;
    }
    public void setId (String id)
    {
        this.id = id;
    }




    public String getCommunicationstatus ()
    {
        return communicationstatus;
    }
    public void setCommunicationstatus (String communicationstatus)
    {
        this.communicationstatus = communicationstatus;
    }



    public String getFile_name ()
    {
        return file_name;
    }
    public void setFile_name (String file_name)
    {
        this.file_name = file_name;
    }


    public String getMatterid ()
    {
        return matterid;
    }
    public void setMatterid (String matterid)
    {
        this.matterid = matterid;
    }

    public String getFromname ()
    {
        return fromname;
    }
    public void setFromname (String fromname)
    {
        this.fromname = fromname;
    }

    public String getDatetime ()
    {
        return datetime;
    }

    public void setDatetime (String datetime)
    {
        this.datetime = datetime;
    }

    public String getReceivername ()
    {
        return receivername;
    }
    public void setReceivername (String receivername)
    {
        this.receivername = receivername;
    }

    public String getCommtype ()
    {
        return commtype;
    }
    public void setCommtype (String commtype)
    {
        this.commtype = commtype;
    }


    public String getDirectPath ()
    {
        return directPath;
    }
    public void setDirectPath (String directPath)
    {
        this.directPath = directPath;
    }




    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }


    public String getMattername ()
    {
        return mattername;
    }

    public void setMattername (String mattername)
    {
        this.mattername = mattername;
    }


    public String getHttppath ()
    {
        return httppath;
    }
    public void setHttppath (String httppath)
    {
        this.httppath = httppath;
    }

    public String getSenderid ()
    {
        return senderid;
    }
    public void setSenderid (String senderid)
    {
        this.senderid = senderid;
    }



    public String getInoutward ()
    {
        return inoutward;
    }
    public void setInoutward (String inoutward)
    {
        this.inoutward = inoutward;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+",  matterid = "+matterid+", fromname = "+fromname+", datetime = "+datetime+", receivername = "+receivername+", commtype = "+commtype+", communicationstatus = "+date+", mattername = "+mattername+", httppath = "+httppath+", senderid = "+senderid+", inoutward = "+inoutward+"]";
    }
}

