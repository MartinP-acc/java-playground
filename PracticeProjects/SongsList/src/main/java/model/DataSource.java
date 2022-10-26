package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private Connection connection;

    public static final String DB_NAME = "sound.db";
    public static final String DB_PATH = "src/main/resources/db/";
    public static final String CONNECTION_STRING = "jdbc:sqlite:"+DB_PATH+DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COL_ALBUM_ID = "_id";
    public static final String COL_ALBUM_NAME = "name";
    public static final String COL_ALBUM_ARTIST = "artist";

    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COL_ARTIST_ID = "_id";
    public static final String COL_ARTIST_NAME = "name";

    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COL_SONG_ID = "_id";
    public static final String COL_SONG_TITLE = "title";
    public static final String COL_SONG_TRACK = "track";
    public static final String COL_SONG_ALBUM = "album";

    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    public static final int NO_ORDER = 0;
    public static final int ASC_ORDER = 1;
    public static final int DESC_ORDER = 2;

    public boolean open(){
        try{
            connection = DriverManager.getConnection(CONNECTION_STRING);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    public boolean close(){
        try {
            if (connection != null){
                connection.close();
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    public List<Artist> queryArtists(int sortType){
        ArrayList<Artist> artistsList = new ArrayList<>();

        StringBuilder queryString = new StringBuilder("SELECT * FROM "+TABLE_ARTISTS);
        if (sortType != NO_ORDER) {
            queryString.append(" ORDER BY ").append(COL_ARTIST_NAME).append(" COLLATE NOCASE ");
            if (sortType == ASC_ORDER) {
                queryString.append("ASC");
            } else {
                queryString.append("DESC");
            }
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(queryString.toString())){
            while (resultSet.next()){
                int id = resultSet.getInt(COL_ARTIST_ID);
                String name = resultSet.getString(COL_ARTIST_NAME);

                Artist artist = new Artist();
                artist.setId(id);
                artist.setName(name);
                artistsList.add(artist);
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return artistsList;
    }
}
