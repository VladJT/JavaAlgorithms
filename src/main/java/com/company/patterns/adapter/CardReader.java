package com.company.patterns.adapter;


/**
 * Адаптер — это структурный паттерн, который позволяет подружить несовместимые объекты.
 * Адаптер выступает прослойкой между двумя объектами, превращая вызовы одного в вызовы понятные другому.
 * <p>
 * Признаки применения паттерна: Адаптер получает конвертируемый объект в конструкторе или через параметры своих методов.
 * Методы Адаптера обычно совместимы с интерфейсом одного объекта. Они делегируют вызовы вложенному объекту,
 * превратив перед этим параметры вызова в формат, поддерживаемый вложенным объектом.
 */

interface USB {
    void connectWithUsbCabel();
}


class MemoryCard {
    private int size;

    MemoryCard(int size) {
        this.size = size;
    }

    public void insert() {
        System.out.println("Карта вставлена. Size = " + size);
    }

    public void copyData() {
        System.out.println("Данные скопированы");
    }
}

/**
 * Адаптируемый класс (карта памяти) становится одним из полей адаптера.
 * В отличие от карты памяти, у адаптера есть общий интерфейс с компьютером. У него есть USB-кабель, то есть он может соединяться с другими устройствами по USB.
 * Поэтому в программе наш класс CardReader реализует интерфейс USB. Но что же происходит внутри этого метода?
 * Адаптер делегирует выполнение работы нашей карте памяти.
 * Его задача — только связать компьютер и карту памяти, чтобы карта могла сделать свою работу и скопировать файлы!
 * Наш адаптер позволяет ей сделать это, предоставив свой интерфейс (метод connectWithUsbCable()) для «нужд» карты памяти.
 */
class CardAdapter implements USB {
    private MemoryCard memoryCard;

    public CardAdapter(MemoryCard mc) {
        this.memoryCard = mc;
    }

    @Override
    public void connectWithUsbCabel() {
        this.memoryCard.insert();
        this.memoryCard.copyData();
    }
}

class Main {
    public static void main(String[] args) {
        MemoryCard sd = new MemoryCard(32);
        USB cardReader = new CardAdapter(sd);
        cardReader.connectWithUsbCabel();
    }
}