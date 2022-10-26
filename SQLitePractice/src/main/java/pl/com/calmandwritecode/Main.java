package pl.com.calmandwritecode;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        int[] id = new int[10];
        String[] title = new String[10];
        int[] year = new int[10];
        int index = 0;

        try{
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS games(_id INTEGER PRIMARY KEY, title TEXT, year INTEGER)");
            statement.execute("INSERT INTO games(title, year) VALUES ('Tomb Raider',1996)");
            statement.execute("INSERT INTO games(title, year) VALUES ('The Elder Scrolls: Skyrim',2011)");
            statement.execute("INSERT INTO games(title, year) VALUES ('Fallout 2',1998)");
            statement.execute("INSERT INTO games(title, year) VALUES ('Stardew Valley',2016)");
            statement.execute("SELECT * FROM games");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()){
                id[index] = resultSet.getInt("_id");
                title[index] = resultSet.getString("title");
                year[index] = resultSet.getInt("year");
                index++;
            }

            resultSet.close();
            statement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        for (int i=0 ; i<index; i++){
            System.out.println("_id : "+id[i]);
            System.out.println("title : "+title[i]);
            System.out.println("year : "+year[i]);
            System.out.println();
        }
    }
}
