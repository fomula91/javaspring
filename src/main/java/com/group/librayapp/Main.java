package com.group.librayapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //숫자를 입력해서 해당 주사위가 몇번 나왔는지 나오는 프로그램
        System.out.print("숫자를 입력하세요 : ");
        //입력을 받습니다.
        Scanner scanner = new Scanner(System.in);
        int getScanedNumber = scanner.nextInt();
        //주사위를 초기화합니다.
        int diceNmuber = 6;
        int[] dice = new int[diceNmuber];

        for(int i = 0; i < getScanedNumber; i++) {
            //랜덤 주사위를 던집니다.
            double getRandomDice = Math.random() * 6;
            //주사위의 결과를 저장합니다.
            dice[(int)getRandomDice]++;
        }

        //출력합니다.
        for(int i = 0; i < dice.length; i++) {
            System.out.printf("%d은 %d번 나왔습니다. \n", i + 1, dice[i]);
        }

    }
}

