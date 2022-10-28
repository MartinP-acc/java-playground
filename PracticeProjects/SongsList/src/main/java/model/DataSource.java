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

    public static final String ALL_TAB_VIEW = "all_tables";
    public static final String VIEW_ARTIST_NAME = "artist_name";
    public static final String VIEW_ALBUM_NAME = "album_name";
    public static final String VIEW_SONG_TRACK = "song_track";
    public static final String VIEW_SONG_TITLE = "song_title";

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

    public List<AllTabView> queryAllTablesView(){
        ArrayList<AllTabView> array = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(stringSelectAllTablesView())){
            while (resultSet.next()){
                AllTabView view = new AllTabView();
                view.setArtistName(resultSet.getString(1));
                view.setAlbumName(resultSet.getString(2));
                view.setSongTrack(resultSet.getInt(3));
                view.setSongTitle(resultSet.getString(4));
                array.add(view);
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return array;
    }

    public List<AllTabView> queryAllTablesView(String song){
        ArrayList<AllTabView> array = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(stringSearchAllTablesBySong())){
            statement.setString(1,song);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                AllTabView view = new AllTabView();
                view.setArtistName(resultSet.getString(1));
                view.setAlbumName(resultSet.getString(2));
                view.setSongTrack(resultSet.getInt(3));
                view.setSongTitle(resultSet.getString(4));
                array.add(view);
            }
            resultSet.close();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return array;
    }

    public void createViewOfAllTables(){
        try (Statement statement = connection.createStatement()){
            statement.execute(stringViewOfAllTables());
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public String stringViewOfAllTables(){
        BuildStatement builder = new BuildStatement();
        String str = builder.createView(ALL_TAB_VIEW+"("+VIEW_ARTIST_NAME+","+VIEW_ALBUM_NAME+
                ","+VIEW_SONG_TRACK+","+VIEW_SONG_TITLE+")")
                .selectMoreTabs(TABLE_ARTISTS,COL_ARTIST_NAME,
                        TABLE_ALBUMS,COL_ALBUM_NAME,
                        TABLE_SONGS,COL_SONG_TRACK,
                        TABLE_SONGS,COL_SONG_TITLE)
                .from(TABLE_SONGS)
                .innerJoin(TABLE_ALBUMS,TABLE_SONGS,COL_SONG_ALBUM)
                .innerJoin(TABLE_ARTISTS,TABLE_ALBUMS,COL_ALBUM_ARTIST)
                .orderBy(TABLE_ARTISTS,COL_ARTIST_NAME,
                        TABLE_ALBUMS,COL_ALBUM_NAME,
                        TABLE_SONGS,COL_SONG_TRACK)
                .end();
        System.out.println(str);
        return str;
    }

    public String stringSelectAllTablesView(){
        BuildStatement builder = new BuildStatement();
        String str = builder.selectSingleTab("*").from(ALL_TAB_VIEW).end();
        System.out.println(str);
        return str;
    }

    public String stringSearchAllTablesBySong(){
        BuildStatement builder = new BuildStatement();
        String str = builder.selectSingleTab("*").from(ALL_TAB_VIEW)
                .where(VIEW_SONG_TITLE).like("?").end();
        System.out.println(str);
        return str;
    }

    private class BuildStatement{

        String statementString;

        public BuildStatement(){
            this.statementString = "";
        }

        public BuildStatement selectSingleTab(String... columns){
            statementString += "SELECT ";
            for (String col : columns) statementString += col+", ";
            statementString = statementString.substring(0,statementString.length()-2);
            return this;
        }

        public BuildStatement selectMoreTabs(String... tab_col){
            statementString += "SELECT ";
            for (int i=0; i<tab_col.length; i++){
                statementString += tab_col[i]+".";
                i++;
                statementString += tab_col[i]+", ";
            }
            statementString = statementString.substring(0,statementString.length()-2);
            return this;
        }

        public BuildStatement from(String table){
            statementString += " FROM "+table;
            return this;
        }

        public BuildStatement innerJoin(String joinedTable, String onTable, String onColumn){
            statementString += " INNER JOIN "+joinedTable+" ON "+onTable+"."+onColumn+"="+joinedTable+"._id";
            return this;
        }

        public BuildStatement where(String table, String column){
            statementString += " WHERE "+table+"."+column;
            return this;
        }

        public BuildStatement where(String column){
            statementString += " WHERE "+column;
            return this;
        }

        public BuildStatement like(String value){
            statementString += " LIKE "+value;
            return this;
        }

        public BuildStatement orderBy(String ... tab_col){
            statementString += " ORDER BY ";
            for (int i=0; i<tab_col.length;i++){
                statementString += tab_col[i]+".";
                i++;
                statementString += tab_col[i]+", ";
            }
            statementString = statementString.substring(0,statementString.length()-2);
            return this;
        }

        public BuildStatement createView(String viewName){
            statementString += "CREATE VIEW IF NOT EXISTS "+viewName+" AS ";
            return this;
        }

        public String end(){
            return statementString;
        }

    }
}
