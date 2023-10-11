package com.cyl.datastructure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author：Java陈序员
 * @date：2023/10/11 9:01
 * @description：稀疏数组练习
 */
public class SparseArrayPractice {

    public static void main(String[] args) {

        int[][] chessBoard1 = new int[11][11];
        chessBoard1[1][2] = 1;
        chessBoard1[2][3] = 2;
        chessBoard1[3][4] = 3;
        printTowArray(chessBoard1);

        int[][] sparseArray1 = toSparseArray(chessBoard1);
        System.out.println("二维数组转成稀疏数组为：");
        printTowArray(sparseArray1);

        write(sparseArray1, "sparseArray.txt");

//        int[][] chessBoard2 = toTowArray(sparseArray);
//        System.out.println("稀疏数组转成二维数组为：");
//        printTowArray(chessBoard2);

        try {
            int[][] sparseArray2 = read(new File("sparseArray.txt"));
            System.out.println("从文件中获取的稀疏数组为：");
            printTowArray(sparseArray2);
            int[][] chessBoard2 = toTowArray(sparseArray1);
            System.out.println("稀疏数组转成二维数组为：");
            printTowArray(chessBoard2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[][] toSparseArray(int[][] twoArray) {
        // 遍历获取二维数组中非0元素的个数
        int sum = 0;
        for (int i = 0; i < twoArray.length; i++) {
            for (int j = 0; j < twoArray[i].length; j++) {
                if (twoArray[i][j] != 0) {
                    sum++;
                }
            }
        }

        int sparseArray[][] = new int[sum + 1][3];
        sparseArray[0][0] = twoArray.length;
        sparseArray[0][1] = twoArray[0].length;
        sparseArray[0][2] = sum;

        int count = 0;
        for (int i = 0; i < twoArray.length; i++) {
            for (int j = 0; j < twoArray[i].length; j++) {
                if (twoArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = twoArray[i][j];
                }
            }
        }

        return sparseArray;
    }

    public static int[][] toTowArray(int[][] sparseArray) {

        int twoArray[][] = new int[sparseArray[0][0]][sparseArray[0][1]];

        // 遍历稀疏数组转成二维数组
        for (int i = 1; i < sparseArray.length; i++) {
            twoArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        return twoArray;
    }

    public static void printTowArray(int[][] twoArray) {
        for (int array[] : twoArray) {
            for (int element : array) {
                System.out.printf("%d\t", element);
            }
            System.out.println();
        }
    }

    public static void write(int[][] array, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for (int i = 0; i < array.length; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < array[i].length; j++) {
                    stringBuilder.append(array[i][j]);
                    stringBuilder.append("\t");
                }
                stringBuilder.append("\n");
                fileWriter.write(stringBuilder.toString());
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int[][] read(File file) throws Exception {
        try {
            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file));
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
            String line;
            int sum = 0;
            while ((line = bufferedReader1.readLine()) != null) {
                sum++;
            }
            int sparseArray[][] = new int[sum][3];

            int count = 0;
            String lineContent;
            while ((lineContent = bufferedReader2.readLine()) != null) {
                String[] split = lineContent.split("\t");
                for (int i = 0; i < split.length; i++) {
                    sparseArray[count][i] = Integer.parseInt(split[i]);
                }
                count++;

            }

            bufferedReader1.close();
            bufferedReader2.close();

            return sparseArray;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}
