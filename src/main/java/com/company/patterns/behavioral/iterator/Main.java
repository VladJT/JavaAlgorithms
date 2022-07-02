package com.company.patterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Итератор (Iterator) </h1>
 * <h2><font color="#fa8e47">Сложность:⭐⭐</font>⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐⭐</font><p></h2>
 * поведенческий паттерн, позволяющий последовательно обходить сложную коллекцию, без раскрытия деталей её реализации<br>
 * <p>
 * <font color="#fa8e47">Применимость:<br></font>
 * Паттерн можно часто встретить в Java-коде, особенно в программах, работающих с разными типами коллекций, и где требуется обход разных сущностей.<br>
 * <p>
 * <font color="#fa8e47">Примеры в стандартных библиотеках Java:<br></font>
 * Все реализации java.util.Iterator (среди прочего также java.util.Scanner).<br>
 * Все реализации java.util.Enumeration
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна:<br></font>
 * Итератор легко определить по методам навигации (например, получения следующего/предыдущего элемента и т. д.). Код использующий итератор зачастую вообще не имеет ссылок на коллекцию, с которой работает итератор. Итератор либо принимает коллекцию в параметрах конструкторе при создании, либо возвращается самой коллекцией.
 * <p>
 * <font color="ffcc00">Важно! </font>
 */


//Предположим, у нас есть список радиоканалов, и клиентская программа хочет пройти через них один за другим или в зависимости от типа канала.
// Например, некоторые клиентские программы заинтересованы только в английских каналах и хотят обрабатывать только их, они не хотят обрабатывать другие типы каналов.


// Java enum, который определяет все различные типы каналов.
enum ChannelTypeEnum {
    ENGLISH, HINDI, FRENCH, ALL;
}


// радиоканал (частота + тип)
class Channel {
    private double frequency;
    private ChannelTypeEnum TYPE;

    public Channel(double freq, ChannelTypeEnum type) {
        this.frequency = freq;
        this.TYPE = type;
    }

    public double getFrequency() {
        return frequency;
    }

    public ChannelTypeEnum getTYPE() {
        return TYPE;
    }

    @Override
    public String toString() {
        return "Frequency=" + this.frequency + ", Type=" + this.TYPE;
    }

}

// Интерфейс ChannelCollection определяет контракт для нашей реализации класса collection.
// Обратите внимание, что существуют методы добавления и удаления канала, но нет метода, возвращающего список каналов.
// ChannelCollection имеет метод, который возвращает итератор для обхода
interface ChannelCollection {

    public void addChannel(Channel c);

    public void removeChannel(Channel c);

    public ChannelIterator iterator(ChannelTypeEnum type);

}

interface ChannelIterator {
    public boolean hasNext();

    public Channel next();
}


// Обратите внимание на внутреннюю реализацию класса интерфейса итератора, так что реализация не может быть использована какой-либо другой коллекцией.
// Такому же подходу следуют и классы коллекций, и все они имеют внутреннюю реализацию класса интерфейса итератора.
class ChannelCollectionImpl implements ChannelCollection {
    private List<Channel> channelsList;

    public ChannelCollectionImpl() {
        channelsList = new ArrayList<>();
    }

    public void addChannel(Channel c) {
        this.channelsList.add(c);
    }

    public void removeChannel(Channel c) {
        this.channelsList.remove(c);
    }

    @Override
    public ChannelIterator iterator(ChannelTypeEnum type) {
        return new ChannelIteratorImpl(type, this.channelsList);
    }

    private class ChannelIteratorImpl implements ChannelIterator {

        private ChannelTypeEnum type;
        private List<Channel> channels;
        private int position;

        public ChannelIteratorImpl(ChannelTypeEnum ty,
                                   List<Channel> channelsList) {
            this.type = ty;
            this.channels = channelsList;
        }

        @Override
        public boolean hasNext() {
            while (position < channels.size()) {
                Channel c = channels.get(position);
                if (c.getTYPE().equals(type) || type.equals(ChannelTypeEnum.ALL)) {
                    return true;
                } else
                    position++;
            }
            return false;
        }

        @Override
        public Channel next() {
            Channel c = channels.get(position);
            position++;
            return c;
        }

    }//ChannelIteratorImpl
}


class Main {

    public static void main(String[] args) {
        ChannelCollection channels = initChannels();
        ChannelIterator baseIterator = channels.iterator(ChannelTypeEnum.ALL);
        while (baseIterator.hasNext()) {
            Channel c = baseIterator.next();
            System.out.println(c.toString());
        }
        System.out.println("---------------");
        // Channel Type Iterator
        ChannelIterator englishIterator = channels.iterator(ChannelTypeEnum.ENGLISH);
        while (englishIterator.hasNext()) {
            Channel c = englishIterator.next();
            System.out.println(c.toString());
        }
    }

    private static ChannelCollection initChannels() {
        ChannelCollection channels = new ChannelCollectionImpl();
        channels.addChannel(new Channel(98.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(99.5, ChannelTypeEnum.HINDI));
        channels.addChannel(new Channel(100.5, ChannelTypeEnum.FRENCH));
        channels.addChannel(new Channel(101.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(102.5, ChannelTypeEnum.HINDI));
        channels.addChannel(new Channel(103.5, ChannelTypeEnum.FRENCH));
        channels.addChannel(new Channel(104.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(105.5, ChannelTypeEnum.HINDI));
        channels.addChannel(new Channel(106.5, ChannelTypeEnum.FRENCH));
        return channels;
    }

}
