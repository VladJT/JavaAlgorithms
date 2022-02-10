package com.company.hash;

import java.util.Arrays;

public class HashTableImpl<K, V> implements IHashTable<K, V> {

    private final Item<K, V>[] data;
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
            return "Item{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public HashTableImpl(int initialCapacity) {
        data = new Item[initialCapacity * 2];
    }


    @Override
    public boolean put(K key, V value) {
        if (size() == data.length) return false;
        int index = hashFunc(key);
        size++;

        while (data[index] != null) {
            if (isKeyEquals(data[index], key)) {
                return true;
            }
            index += getStepLinear();
            index = index % data.length;
        }
        data[index] = new Item<>(key, value);
        return true;
    }

    // линейное пробитие
    private int getStepLinear() {
        return 1;
    }

    private boolean isKeyEquals(Item<K, V> item, K key) {
        return (item.getKey() == null) ? (key == null) : item.getKey().equals(key);
    }

    private int hashFunc(K key) {
        return Math.abs(key.hashCode() % data.length);
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i <data.length;i++){
            sb.append(String.format("%s=[%s]%n",i,data[i]));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        HashTableImpl ht = new HashTableImpl(5);
        ht.put(new Product(1,"Orange"), 150);//1
        ht.put(new Product(60,"Lemon"), 150);//0
        ht.put(new Product(70,"Milk"), 150);//2
        ht.put(new Product(9,"Potato"), 150);//9

        System.out.println("Size = "+ht.size());
        ht.display();
    }

}
