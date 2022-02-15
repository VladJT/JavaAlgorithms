package com.company.hash;


class Item {
    private int data;

    public Item(int data) {
        this.data = data;
    }

    public int getKey() {
        return this.data;
    }
}


// TODO Хеширование — это преобразование массива данных или набора символов в число.
// хеш-таблица, где коллизии устраняются методом открытой адресации
class HashTable {


    private Item[] hashArr;
    private int arrSize;
    private Item nonItem;

    public HashTable(int size) {
        this.arrSize = size;
        hashArr = new Item[arrSize];
        nonItem = new Item(-1);
    }

    public void display() {
        for (int i = 0; i < arrSize; i++) {
            if (hashArr[i] != null) {
                System.out.println(hashArr[i].getKey());
            } else {
                System.out.println("***");
            }
        }
    }

    public int hashFunc(int key) {
        return key % arrSize;
    }

    /*   Для устранения проблем, связанных с первичной и вторичной группировкой, используется метод двойного хеширования.
    Создаются две хеш-функции, которые будут генерировать разную последовательность для ключей, хешируемых в один и тот же индекс.
       Такая задача решается за счет повторного хеширования ключа другой хеш-функцией.
       Она не должна совпадать с первой функцией, а ее результат никогда не должен быть равен 0.
       смещение=константа-(ключ % константа)
       где константа — простое число, которое меньше размера массива.*/
    public int hashFuncDouble(int key) {
        return 3 - key % 3;
    }

    public void insert(Item item) {
        int key = item.getKey();
        int hashVal = hashFunc(key);
        int stepSize = hashFuncDouble(key);
        while (hashArr[hashVal] != null && hashArr[hashVal].getKey() != -1) {
            hashVal += stepSize;
            hashVal %= arrSize;
        }

        hashArr[hashVal] = item;
    }

    public Item delete(int key) {
        int hashVal = hashFunc(key);
        int stepSize = hashFuncDouble(key);
        while (hashArr[hashVal] != null) {
            if (hashArr[hashVal].getKey() == key) {
                Item temp = hashArr[hashVal];
                hashArr[hashVal] = nonItem;
                return temp;
            }
            hashVal += stepSize;
            hashVal %= arrSize;
        }
        return null;
    }

    public Item find(int key) {
        int hashVal = hashFunc(key);
        int stepSize = hashFuncDouble(key);
        while (hashArr[hashVal] != null) {
            if (hashArr[hashVal].getKey() == key) {
                return hashArr[hashVal];
            }
            hashVal += stepSize;
            hashVal %= arrSize;
        }
        return null;
    }


    /*  Хеш-таблица может переполниться. Чтобы этого избежать, можно увеличить размерность массива.
      Но в Java для этого нужно создать новый массив и скопировать в него значения, так как массивы являются статическими.
      Функция хеширования учитывает размер массива, и в новом элементы будут находиться в других ячейках.
      Чтобы скопировать все значения в новый массив, для каждого элемента нужно выполнить метод insert.
      Как правило, новый массив, должен быть вдвое больше предыдущего.
      Размер массива должен быть простым числом, и его вычисление является частью процесса перехеширования.
      Создадим метод getPrime, который возвращает следующее простое число после текущего, которое и будет новой размерностью массива.*/
    private int getPrime(int min) {
        for (int i = min + 1; true; i++)
            if (isPrime(i))
                return i;
    }

    private boolean isPrime(int n) {
        for (int j = 2; (j * j <= n); j++)
            if (n % j == 0)
                return false;
        return true;
    }


}
