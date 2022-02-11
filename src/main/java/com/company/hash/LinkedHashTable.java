package com.company.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// хеш-таблица, где коллизии устраняются методом цепочек (через связанный список)
public class LinkedHashTable {
    class Item {

        private List<Integer> data;

        public Item() {
            data = new ArrayList<>();
        }

        public Item(int i) {
            data = new ArrayList<>();
            data.add(i);
        }

        public void display() {
            System.out.println(Arrays.toString(data.toArray()));
        }

        public int search(int searchElement) {
            for (int i = 0; i < data.size(); i++) {
                if (searchElement == data.get(i)) return i;
            }
            return -1;
        }

        public void delete(int index) {
            data.remove(index);
        }

        public void add(int value) {
            data.add(value);
        }
    }

    private Item[] hashArr;
    private int arrSize;

    public LinkedHashTable(int size) {
        this.arrSize = size;
        hashArr = new Item[arrSize];
    }

    public void display() {
        for (int i = 0; i < arrSize; i++) {
            if (hashArr[i] != null) {
                hashArr[i].display();
            } else {
                System.out.println("***");
            }
        }
    }

    public int hashFunc(int key) {
        return key % arrSize;
    }

    public void insert(int key) {
        int hashVal = hashFunc(key);
        if (hashArr[hashVal] == null) {
            hashArr[hashVal] = new Item(key);
        } else {
            if (hashArr[hashVal].search(key) != -1) System.out.println("Уже есть такой элемент");
            else hashArr[hashVal].add(key);
        }
    }

    public boolean delete(int key) {
        int hashVal = hashFunc(key);
        if (hashArr[hashVal] != null) {
            int deleteIndex = hashArr[hashVal].search(key);
            if (deleteIndex != -1) {
                hashArr[hashVal].delete(deleteIndex);
                System.out.println("Успешно удалено");
                return true;
            }
        }
        System.out.println("Такой элемент не найден");
        return false;
    }

    public boolean find(int key) {
        int hashVal = hashFunc(key);
        if (hashArr[hashVal] != null) {
            int findIndex = hashArr[hashVal].search(key);
            if (findIndex != -1) {
                System.out.println(key + " успешно найден. hashCode " + hashVal + ", index " + findIndex);
                return true;
            }
        }
        System.out.println("Такой элемент не найден");
        return false;
    }

}
