package com.cyl.datastructure.recursion;

/**
 * @author：Java陈序员
 * @date：2023/11/11 21:37
 * @description：递归-迷宫问题
 */
public class MazeDemo {

    public static void main(String[] args) {
        int[][] array = init();
        print(array);

        System.out.println("迷宫路线：");
//        setWayOne(array, 1, 1);
        setWayOne(array, 1, 2);
        print(array);
    }

    /**
     * 初始化数组
     *
     * @return
     */
    public static int[][] init() {
        int[][] array = new int[8][7];

        for (int i = 0; i < array.length; i++) {
            array[i][0] = 1;
            array[i][array[i].length - 1] = 1;
        }

        for (int i = 0; i < array[0].length; i++) {
            array[0][i] = 1;
            array[array.length - 1][i] = 1;
        }

        array[3][1] = 1;
        array[3][2] = 1;

        return array;
    }

    /**
     * 打印数组
     */
    public static void print(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }

            System.out.println();
        }
    }

    /**
     *说明：
     *  1.map 表示地图 i、j 表示起点
     *  2.规定：map[i][j] = 0 时表示为墙，走不通；map[i][j] = 2 时表示为可以走；map[i][j] = 3 时表示已走过，但走不通；
     *  3.终点为：map[6][5] = 2
     *  4.可以制定不同的走路策略：下-右-上-左 等
     */
    /**
     * 求路径，方向：下-右-上-左
     *
     * @return
     */
    public static boolean setWayOne(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 表示走到终点，退出递归
            return true;
        } else {
            if (map[i][j] == 0) {
                // 如果当前节点还没有走过
                // 假定当前节点是可以走
                map[i][j] = 2;
                if (setWayOne(map, i + 1, j)) {
                    // 先向下走
                    return true;
                } else if (setWayOne(map, i, j + 1)) {
                    // 再向右走
                    return true;
                } else if (setWayOne(map, i - 1, j)) {
                    // 再向上走
                    return true;
                } else if (setWayOne(map, i, j - 1)) {
                    return true;
                } else {
                    // 说明改点是死路，走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
