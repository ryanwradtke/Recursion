/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Ryan W Radtke <RyanWRadtke@gmail.com>
 */
public class InClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] myArray = {14, 23, 32, 45, 67, 98, 45, 98};

        listContents(myArray, myArray.length);

        System.out.println(sumOf(25));

    }

    public static void listContents(int[] array, int index) {
        if (index != 0) {
            System.out.println(array[index - 1]);
            listContents(array, index - 1);
        }
    }

    public static int sumOf(int n) {
        return (n == 1) ? n : n + sumOf(n - 1);
    }

}
