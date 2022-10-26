package pl.com.calmandwritecode;

import model.Artist;
import model.DataSource;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DataSource dataSource = new DataSource();

        if (dataSource.open()) {
            System.out.println("Database is open");

            List<Artist> artists = dataSource.queryArtists(DataSource.DESC_ORDER);
            printTable(artists);

            if (dataSource.close()) {
                System.out.println("Database is closed");
            } else {
                System.out.println("Couldn't close database");
            }

        } else {
            System.out.println("Couldn't open database");
        }

    }

    public static void printTable(List<?> list){
        if (list.isEmpty()){
            System.out.println("The table is empty or couldn't query the table");
        }else {
            for (Object row : list) System.out.println(row.toString());
        }
    }
}
