package com.company.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class HashApp {

    public static void main(String[] args) throws IOException{
        Item aDataItem;
        int aKey, size, n, keysPerCell;
        // Ввод размеров
        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        keysPerCell = 10;
        // Создание таблицы
        HashTable theHashTable = new HashTable(size);
        for(int j=0; j<n; j++){
            aKey = (int)(java.lang.Math.random() * keysPerCell * size);
            aDataItem = new Item(aKey);
            theHashTable.insert(aDataItem);
        }
        while(true){
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find: ");
            char choice = getChar();
            switch(choice){
                case 's':
                    theHashTable.display();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem = new Item(aKey);
                    theHashTable.insert(aDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem = theHashTable.find(aKey);
                    if(aDataItem != null){
                        System.out.println("Found " + aKey);
                    }else
                        System.out.println("Could not find " + aKey);
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }
        }
    }
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
    public static char getChar() throws IOException{
        String s = getString();
        return s.charAt(0);
    }
    public static int getInt() throws IOException{
        String s = getString();
        return Integer.parseInt(s);
    }

}