package com.example.lawsyst;

class WorkTypes
{

    private int id;
    private String description;
    private String spreadsheet_string;

    public int getId ()
    {
        return id;
    }
    public void setId (int id)
    {
        this.id = id;
    }



    public String getDescription ()
    {
        return description;
    }
    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getSpreadsheet_string ()
    {
        return spreadsheet_string;
    }
    public void setSpreadsheet_string (String spreadsheet_string)
    {
        this.spreadsheet_string = spreadsheet_string;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", description = "+description+", spreadsheet_string = "+spreadsheet_string+"]";
    }
}
