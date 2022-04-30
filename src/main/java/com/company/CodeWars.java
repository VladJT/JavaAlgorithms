package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class CodeWars {


    public static int[] theLift(final int[][] queues, final int capacity) {
        System.out.println("capacity: " + capacity);
        printFloors(queues);

        ArrayList<Integer> lift = new ArrayList<>(); // lift [capacity]
        ArrayList<Integer> route = new ArrayList<>();// lift's route
        int liftPos = 0;
        char direction = '^'; // = ^ or v
        moveLift(liftPos, direction, lift, capacity, queues);
        route.add(liftPos);

        while (true) {
            int maxFloorWithPeople = getMaxFloorWithPeople(queues);
            int minFloorWithPeople = getMinFloorWithPeople(queues);

            int maxFloorByPeopleInLift = -1;
            if (!lift.isEmpty()) maxFloorByPeopleInLift = Collections.max(lift);
            int upDestination = Math.max(maxFloorWithPeople, maxFloorByPeopleInLift);

            int minFloorByPeopleInLift = -1;
            if (!lift.isEmpty()) minFloorByPeopleInLift = Collections.min(lift);
            int downDestination = 0;
            if (minFloorWithPeople >= 0) downDestination = minFloorWithPeople;
            if (minFloorByPeopleInLift >= 0 && minFloorByPeopleInLift < downDestination)
                downDestination = minFloorByPeopleInLift;

            boolean hasStop = false;

            if (direction == '^') {
                if (liftPos < upDestination) {
                    hasStop = moveLift(++liftPos, direction, lift, capacity, queues);
                } else {
                    direction = 'v';
                    hasStop = moveLift(liftPos, direction, lift, capacity, queues);// try to set peoples in Lift
                }
            }
            else {//if (direction == 'v')
                if (liftPos > downDestination) {
                    hasStop = moveLift(--liftPos, direction, lift, capacity, queues);
                } else {
                    direction = '^';
                    hasStop = moveLift(liftPos, direction, lift, capacity, queues);
                }
            }
            if (hasStop && (route.get(route.size() - 1) != liftPos)) route.add(liftPos);

            if (isEmpty(queues) && lift.isEmpty()) break;
        }

        if (liftPos != 0) route.add(0);
        return route.stream().mapToInt(i -> i).toArray();
    }

    private static boolean moveLift(int stage, char direction, ArrayList<Integer> lift, int capacity, int[][] queues) {
        boolean hasStop = false;
        hasStop = getOupFromLift(stage, lift);
        for (int i = 0; i < queues[stage].length; i++) {
            if (direction == '^' && queues[stage][i] > stage && queues[stage][i] != -1) {
                setPeoplesToLift(lift, capacity, queues, stage, i);
                hasStop = true;
            }
            if (direction == 'v' && queues[stage][i] < stage && queues[stage][i] != -1) {
                setPeoplesToLift(lift, capacity, queues, stage, i);
                hasStop = true;
            }
        }
        return hasStop;
    }

    private static void setPeoplesToLift(ArrayList<Integer> lift, int capacity, int[][] queues, int stage, int index) {
        if (lift.size() < capacity) {// set to lift
            lift.add(queues[stage][index]);
            queues[stage][index] = -1; // no people
        }
    }

    private static boolean getOupFromLift(int stage, ArrayList<Integer> lift) {
        boolean hasStop = false;
        while (lift.contains((Object) stage)) {
            lift.remove((Object) stage);// get out peoples
            hasStop = true;
        }
        return hasStop;
    }

    private static void printFloors(int[][] queues) {
        for (int i = queues.length - 1; i >= 0; i--) {
            System.out.print(i + "|| ");
            for (int j = 0; j < queues[i].length; j++) {
                System.out.print(queues[i][j] + ", ");
            }
            System.out.println();
        }
        System.out.println("------");
    }

    private static int getMinFloorWithPeople(int[][] queues) {
        for (int i = 0; i < queues.length; i++) {
            for (int j = 0; j < queues[i].length; j++) {
                if (queues[i][j] != -1) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static int getMaxFloorWithPeople(int[][] queues) {
        for (int i = queues.length - 1; i >= 0; i--) {
            for (int j = 0; j < queues[i].length; j++) {
                if (queues[i][j] != -1) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static boolean isEmpty(int[][] queues) {
        return Arrays.stream(queues).flatMapToInt(Arrays::stream).filter(i -> i != -1).count() == 0;
    }

    static int[][] queues_2 = {
            new int[0], // G
            new int[0], // 1
            new int[]{5, 5, 5}, // 2
            new int[0], // 3
            new int[0], // 4
            new int[0], // 5
            new int[0], // 6
    };
    static int[][] queues_1 = {
            new int[0], // G
            new int[]{3}, // 1
            new int[]{4}, // 2
            new int[0], // 3
            new int[]{5}, // 4
            new int[0], // 5
            new int[0], // 6
    };

    static int[][] queues_3 = {
            new int[0], // G
            new int[0], // 1
            new int[]{1, 1}, // 2
            new int[0], // 3
            new int[0], // 4
            new int[0], // 5
            new int[0], // 6
    };

    static int[][] queues_4 = {
            new int[0], // G
            new int[0], // 1
            new int[]{4, 4, 4, 4}, // 2
            new int[0], // 3
            new int[]{2, 2, 2, 2}, // 4
            new int[0], // 5
            new int[0], // 6
    };

    static int[][] queues_5 = {
            new int[0], // G
            new int[]{0, 0, 0, 0}, // 1
            new int[]{0, 0, 0, 0}, // 2
            new int[]{0, 0, 0, 0}, // 3
            new int[]{0, 0, 0, 0}, // 4
            new int[]{0, 0, 0, 0}, // 5
            new int[]{0, 0, 0, 0}, // 6
    };

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //-------------------------
        System.out.println(Arrays.toString(theLift(queues_5, 5)));
        System.out.println("===========================");
        System.out.println(Arrays.toString(theLift(queues_4, 5)));//0,5,4,3,2,1,0
        System.out.println("===========================");
        System.out.println(Arrays.toString(theLift(queues_3, 5)));//0,2,1,0
        System.out.println("===========================");
        System.out.println(Arrays.toString(theLift(queues_1, 5)));//0,1,2,3,4,5,0
        System.out.println("===========================");
        System.out.println(Arrays.toString(theLift(queues_2, 5)));//0,2,5,0
        System.out.println("===========================");

        //-------------------------
        System.out.println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime));
    }


}


