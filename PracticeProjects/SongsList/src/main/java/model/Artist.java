package model;

public class Artist{

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return DataSource.COL_ARTIST_ID+": "+id+"\t"+
                DataSource.COL_ARTIST_NAME+": "+name;
    }
}
