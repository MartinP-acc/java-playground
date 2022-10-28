package pl.com.calmandwritecode;

import model.AllTabView;
import model.DataSource;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DataSource dataSource = new DataSource();

        if (dataSource.open()) {
            System.out.println("Database is open");

            System.out.print("Search by title: ");
            Scanner scanner = new Scanner(System.in);
            String song = scanner.nextLine();
            dataSource.createViewOfAllTables();
            List<AllTabView> view = dataSource.queryAllTablesView("%"+song+"%");
            printTable(view);

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
            System.out.println("The result list is empty or couldn't query the table");
        }else {
            for (Object row : list) System.out.println(row.toString());
        }
    }
}
