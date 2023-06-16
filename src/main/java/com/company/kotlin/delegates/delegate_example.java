package com.company.kotlin.delegates;


public class delegate_example {
    public void main(String[] args) {
        Showable view = new View();
        Showable customView = new CustomView();
        new Screen(view).show(); // View.show()
        new Screen(customView).show(); // CustomView.show()
    }

    /**
     * Условно делегирование можно разделить на:
     * ● явное, которое можно имплементировать в любом объектно-ориентированном языке;
     * ● неявное, которое поддерживается на уровне языка.
     */

    interface Showable {
        void show();
    }

    class View implements Showable {
        @Override
        public void show() {
            System.out.println("View.show()");
        }
    }

    class CustomView implements Showable {
        @Override
        public void show() {
            System.out.println("CustomView.show()");
        }
    }

    class Screen implements Showable {
        private Showable showable;

        Screen(Showable showable) {
            this.showable = showable;
        }

        @Override
        public void show() {
            showable.show();
        }
    }
}
