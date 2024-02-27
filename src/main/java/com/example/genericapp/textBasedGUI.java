package com.example.genericapp;

import com.example.genericapp.Application.Array.GenericArrayManager;

import java.util.Scanner;

public class textBasedGUI {
    public static void main(String[] args) {
        GenericArrayManager app = new GenericArrayManager("GenericsKB.txt");
        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while(run == true) {
            System.out.println("Choose an action from the menu:\n" +
                    "1. Load a knowledge base from a file\n" +
                    "2. Add a new statement to the knowledge base\n" +
                    "3. Search for an item in the knowledge base by term\n" +
                    "4. Search for a item in the knowledge base by term and sentence\n" +
                    "5. Quit\n" +
                    "Enter your choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch(choice){
                case 1:
                    //TODO: load new data from a file to app
                    System.out.print("Enter File Name: ");
                    String fileName = sc.nextLine();
                    break;
                case 2:
                    //TODO: add new statement
                    break;
                case 3:
                    System.out.println("Enter Term: ");
                    String term = sc.nextLine();
                    System.out.println(app.getSearchItem(term));
                    break;
                case 4:
                    //TODO: add advanced search
                    break;
                case 5:
                    run = false;
                    break;
            }
        }
    }
}