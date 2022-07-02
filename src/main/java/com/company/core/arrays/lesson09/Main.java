package com.company.core.arrays.lesson09;


class MyArraySizeException extends Exception {
    public MyArraySizeException() {
        super("Размерность массива не соответствует требованиям 4х4");
    }
}

class MyArrayDataException extends Exception {
    public MyArrayDataException(int i, int j, String errValue) {
        super("В ячейке с индексом " + i + ", " + j + " лежит некорректное значение = " + errValue);
    }
}


class Main {
    public static void main(String[] args) {
        String[][] arr = {
                {"5", "6", "2", "-8xFFF"},
                {"5", "Streams", "0", "1"},
                {"12", "13", "E2E4", "7"},
                {"8", "-50", "14", "2"}
        };

        try {
            int sum = sumStringArray(arr);
            System.out.println("Сумма элементов массива = " + sum);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }

    }

    private static int sumStringArray(String[][] arr) throws MyArraySizeException {
        if (arr.length != 4 || arr[0].length != 4 || arr[1].length != 4 || arr[2].length != 4 || arr[3].length != 4) {
            throw new MyArraySizeException();
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    if (!arr[i][j].matches("[-+]?\\d+")) {
                        throw new MyArrayDataException(i, j, arr[i][j]);
                    }
                    sum += Integer.parseInt(arr[i][j]);
                } catch (MyArrayDataException e) {
                    e.printStackTrace();
                }
            }// for j
        }//for i
        return sum;
    }

}