package com.company.hash;

import java.util.*;


public class ELinkedHashTable<K, V> implements IHashTable<K, V> {

    private final LItem<K, V>[] data;
    private int size;

    // класс для 1 элемента хранения
    class Item<K, V> implements Entry<K, V> {
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
            return "{key " + key + ", value " + value + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item<?, ?> item = (Item<?, ?>) o;
            return Objects.equals(key, item.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }


    // класс для хранения цепочек (предотвращение коллизий)
    class LItem<K, V> {

        List<Item<K, V>> list;

        public LItem() {
            list = new ArrayList<>();
        }

        public boolean contains(K key, V value) {
            Item i = new Item<>(key, value);
            return list.contains(i);
        }

        public boolean contains(K key) {
            Item i = new Item<>(key, null);
            return list.contains(i);
        }

        public V getItemValue(K key) {
            for (Item<K, V> i : list) {
                if (i.getKey().equals(key)) return i.getValue();
            }
            return null;
        }

        public void add(K key, V value) {
            list.add(new Item<>(key, value));
        }

        @Override
        public String toString() {
            if (list == null) return "--";
            else {
                String st = "";
                for (int i = 0; i < list.size(); i++) {
                    st += list.get(i).toString();
                    if (i != list.size() - 1) st += ", ";
                }
                return st;
            }
        }

        public V deleteItem(K key) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getKey().equals(key)) {
                    V temp = list.get(i).getValue();
                    list.remove(i);
                    return temp;
                }
            }
            return null;
        }
    }

    public ELinkedHashTable(int initialCapacity) {
        data = new LItem[initialCapacity];
    }

    // Хеш-функция
    private int hashFunc(K key) {
        return Math.abs(key.hashCode() % data.length);
    }

    // двойное хеширование (константа д.б. меньше размера массива)
    private int getStepDoubleHash(K key) {
        return 3 - (key.hashCode() % 3);
    }


    @Override
    public boolean put(K key, V value) {
        int index = hashFunc(key);

        if (data[index] == null) {
            data[index] = new LItem<>();
            size++;
            data[index].add(key, value);
            return true;
        } else {
            if (data[index].contains(key, value)) {
                System.out.println("Элемент с ключом " + key.toString() + " уже существует");
                return false;
            } else {
                size++;
                data[index].add(key, value);
                return true;
            }
        }
    }


    @Override
    public V get(K key) {
        int index = hashFunc(key);

        if (data[index] == null || (!data[index].contains(key))) {
            //System.out.println("Элемент " + key + " не найден");
            return null;
        } else return data[index].getItemValue(key);

    }

    @Override
    public V remove(K key) {
        int index = hashFunc(key);
        if (data[index] == null || (!data[index].contains(key))) {
            return null;
        } else {
            size--;
            return data[index].deleteItem(key);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Хеш-таблица:\n");
        System.out.println("Количество элементов в хеш-таблице = " + size());
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) sb.append(i + " = ---\n");
            else
                sb.append(String.format("%s = %s\n", i, data[i]));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ELinkedHashTable ht = new ELinkedHashTable(5);
        ht.put(new Product(1, "Orange"), 120);//1
        ht.put(new Product(60, "Lemon"), 90);//0
        ht.put(new Product(70, "Milk"), 85);//0
        ht.put(new Product(8, "Potato"), 77);//3
        ht.put(new Product(4, "Berry"), 452);//4
        ht.put(new Product(6, "Carrot"), 101);//1

        ht.display();

        System.out.println("Пробуем добавить дубль:");
        ht.put(new Product(70, "Milk"), 250);// проверка добавления дубликата
        System.out.println();

        System.out.println("Тестируем поиск:");
        System.out.println("[62 - Lemon] результат поиска: " + ht.get(new Product(62, "Lemon"))); // нет
        System.out.println("[70 - Milk] результат поиска: " + ht.get(new Product(70, "Milk"))); // есть
        System.out.println();

        System.out.println("Тестируем удаление: ");
        System.out.println("[62 - Lemon] результат удаления: " + ht.remove(new Product(62, "Lemon"))); // нет
        System.out.println("[70 - Milk] результат удаления: " + ht.remove(new Product(70, "Milk"))); // есть

        ht.display();
    }
}
