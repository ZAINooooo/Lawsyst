package com.example.lawsyst;

public class Matters_Pojo
{
    private String matternumber;

    private String STATUS;

    private String statusdescription;

    private String landlord_id;

    private String clientname;

    private String landlord_lname;

    private String landlord_fname;

    private String id;

    private String assigntoid;

    private String mattername;

    private String assignto;




    public String getMatternumber ()
    {
        return matternumber;
    }

    public void setMatternumber (String matternumber)
    {
        this.matternumber = matternumber;
    }

    public String getSTATUS ()
    {
        return STATUS;
    }

    public void setSTATUS (String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getStatusdescription ()
    {
        return statusdescription;
    }

    public void setStatusdescription (String statusdescription)
    {
        this.statusdescription = statusdescription;
    }

    public String getLandlord_id ()
    {
        return landlord_id;
    }

    public void setLandlord_id (String landlord_id)
    {
        this.landlord_id = landlord_id;
    }

    public String getClientname ()
    {
        return clientname;
    }

    public void setClientname (String clientname)
    {
        this.clientname = clientname;
    }

    public String getLandlord_lname ()
    {
        return landlord_lname;
    }

    public void setLandlord_lname (String landlord_lname)
    {
        this.landlord_lname = landlord_lname;
    }

    public String getLandlord_fname ()
    {
        return landlord_fname;
    }

    public void setLandlord_fname (String landlord_fname)
    {
        this.landlord_fname = landlord_fname;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getAssigntoid ()
    {
        return assigntoid;
    }

    public void setAssigntoid (String assigntoid)
    {
        this.assigntoid = assigntoid;
    }

    public String getMattername ()
    {
        return mattername;
    }

    public void setMattername (String mattername)
    {
        this.mattername = mattername;
    }

    public String getAssignto ()
    {
        return assignto;
    }

    public void setAssignto (String assignto)
    {
        this.assignto = assignto;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [matternumber = "+matternumber+", STATUS = "+STATUS+", statusdescription = "+statusdescription+", landlord_id = "+landlord_id+", clientname = "+clientname+", landlord_lname = "+landlord_lname+", landlord_fname = "+landlord_fname+", id = "+id+", assigntoid = "+assigntoid+", mattername = "+mattername+", assignto = "+assignto+"]";
    }

    public int getStatusColor() {
        int magnitudeColorResourceId;
        switch (Integer.valueOf(this.STATUS)) {
            case 5:
                magnitudeColorResourceId = R.color.statusFive;
                break;
            default:
                magnitudeColorResourceId = R.color.statusDefault;
                break;
        }
        return magnitudeColorResourceId;
    }
}

