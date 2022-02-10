package com.company.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ELinkedHashTable <K, V> implements IHashTable<K, V> {

    private final LItem<K, V>[] data;
    private int size;

    static class Item<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "{key=" + key +", value=" + value+"}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item<?, ?> item = (Item<?, ?>) o;
            return Objects.equals(key, item.key) && Objects.equals(value, item.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    class LItem<K, V>  {

        List<Item<K, V>> list;

        public LItem(){
            list = new ArrayList<>();
        }

        public boolean contains(K key, V value){
            Item i = new Item<>(key, value);
            return list.contains(i);
        }

        public void add(K key, V value){
            list.add(new Item<>(key, value));
        }

        @Override
        public String toString() {
            if (list == null) return "--";
            else {
                String st="";
                for(int i=0;i<list.size();i++){
                    st+= list.get(i).toString()+", ";
                }
                return st;
            }
        }
    }

    public ELinkedHashTable(int initialCapacity) {
        data = new LItem[initialCapacity];
    }

    // Хеш-функция
    private int hashFunc(K key) {
        return Math.abs(key.hashCode() % data.length);
    }


    @Override
    public boolean put(K key, V value) {
        int index = hashFunc(key);

        if(data[index]==null) {
            data[index] = new LItem<>();
            size++;
            data[index].add(key, value);
            return true;
        }else{
            if (data[index].contains(key, value)){
                System.out.println("Элемент"+ new Item<>(key, value).toString()+" уже существует");
                return false;
            }
            else {
                size++;
                data[index].add(key, value);
                return true;
            }
        }
    }

    // двойное хеширование (константа д.б. меньше размера массива)
    private int getStepDoubleHash(K key) {
        return 5 - (key.hashCode() % 5);
    }

    // квадратичное пробитие
    private int getStepQuadro(int n) {
        return (int) Math.pow(n, 2);
    }

    // линейное пробитие
    private int getStepLinear() {
        return 1;
    }

    private boolean isKeyEquals(HashTableImpl.Item<K, V> item, K key) {
        return (item.getKey() == null) ? (key == null) : item.getKey().equals(key);
    }


    @Override
    public V get(K key) {
        int index = hashFunc(key);

        if(data[index]==null || (!data[index].contains(key, null))) {
            System.out.println("Элемент "+key+" не найден");
            return null;
        }
        else  return null;

    }

    private int indexOf(K key) {
//        int index = hashFunc(key);
//        int count = 0;
//        while (count++ < data.length) {
//            if (data[index] == null) break;
//            if (isKeyEquals(data[index], key)) {
//                return index;
//            }
//            else index+=getStepDoubleHash(key);
//            index%= data.length;
//        }
       return -1;
    }

    @Override
    public V remove(K key) {
//        int index = indexOf(key);
//        if (index==-1) return null;
//
//        HashTableImpl.Item<K, V> removed = data[index];
//        data[index] = emptyItem;
//
//        return removed.getValue();
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if(data[i]==null) sb.append("---\n");
            else
            sb.append(String.format("%s = %s\n", i, data[i]));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ELinkedHashTable ht = new ELinkedHashTable(5);
        ht.put(new Product(1, "Orange"), 150);//1
        ht.put(new Product(60, "Lemon"), 200);//0
        ht.put(new Product(70, "Milk"), 250);//0
        ht.put(new Product(9, "Potato"), 266);//4
        ht.put(new Product(4, "Berry"), 452);//4

        ht.put(new Product(70, "Milk"), 250);// проверка добавления дубликата

//        System.out.println(ht.get(new Product(60, "Lemon")));
        System.out.println(ht.get(new Product(62, "Lemon"))); // нет
        System.out.println(ht.get(new Product(70, "Milk"))); // есть

      //  System.out.println(ht.remove(new Product(60, "Lemon")));
     //   ht.put(new Product(1000, "Avokado"), 765);//0
        System.out.println("Size = " + ht.size());
        ht.display();
    }
}
