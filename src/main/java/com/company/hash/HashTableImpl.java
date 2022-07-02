package com.company.hash;

class HashTableImpl<K, V> implements IHashTable<K, V> {

    private final Item<K, V>[] data;
    private int size;
    private Item<K, V> emptyItem;

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
        emptyItem = new Item<>(null, null);
    }

    // Хеш-функция
    private int hashFunc(K key) {
        return Math.abs(key.hashCode() % data.length);
    }


    @Override
    public boolean put(K key, V value) {
        if (size() == data.length) return false;
        int index = hashFunc(key);
        size++;
        int n = 0;

        while (data[index] != null && data[index] != emptyItem) {
            if (isKeyEquals(data[index], key)) {
                return true;
            }
            index += getStepDoubleHash(key);
//            index += getStepQuadro(n++ );
//            index += getStepLinear();
            index = index % data.length;
        }
        data[index] = new Item<>(key, value);
        return true;
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

    private boolean isKeyEquals(Item<K, V> item, K key) {
        if (item == emptyItem) return false;
        return (item.getKey() == null) ? (key == null) : item.getKey().equals(key);
    }


    @Override
    public V get(K key) {
        int index = indexOf(key);
        return (index == -1) ? null : data[index].getValue();
    }

    private int indexOf(K key) {
        int index = hashFunc(key);
        int count = 0;
        while (count++ < data.length) {
            if (data[index] == null) break;
            if (isKeyEquals(data[index], key)) {
                return index;
            } else index += getStepDoubleHash(key);
            index %= data.length;
        }
        return -1;
    }

    @Override
    public V remove(K key) {
        int index = indexOf(key);
        if (index == -1) return null;

        Item<K, V> removed = data[index];
        data[index] = emptyItem;

        return removed.getValue();
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
        for (int i = 0; i < data.length; i++) {
            sb.append(String.format("%s=[%s]%n", i, data[i]));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        HashTableImpl ht = new HashTableImpl(5);
        ht.put(new Product(1, "Orange"), 150);//1
        ht.put(new Product(60, "Lemon"), 200);//0
        ht.put(new Product(70, "Milk"), 250);//2
        ht.put(new Product(9, "Potato"), 266);//9

//        System.out.println(ht.get(new Product(60, "Lemon")));
//        System.out.println(ht.get(new Product(62, "Lemon")));
//        System.out.println(ht.get(new Product(70, "Milk")));

        System.out.println(ht.remove(new Product(60, "Lemon")));
        ht.put(new Product(1000, "Avokado"), 765);//0
        System.out.println("Size = " + ht.size());
        ht.display();
    }

}
