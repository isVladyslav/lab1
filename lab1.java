import java.util.Scanner;

public class lab1{
    public static void main(String[] args){
        int a = 2;
        int[][] matrix = matrix_generator(); // Створення матриці
        multiply(a, matrix); // Множення матриці на число
        average(matrix); // Визначення середнього значення матриці
        matrix_viewer(matrix); // Виведення матриці на екран
    }

    public static int[][] matrix_generator(){
        boolean isDone = false;
        Scanner scan = new Scanner(System.in);
        String answer;
        while (isDone == false){
            System.out.printf("\n\nUse default matrix (yes/no)?\n");
            answer = scan.nextLine();
            if (answer.equals("yes")){
                return new int[][] {{1, 15, 31, 2, 10, 32, 5, 14},
                                    {2, 5, 10, 16, 8, 46, 95, 0},
                                    {41, 84, 1, 0, 49, 75, 2, 10},
                                    {9, 14, 86, 4, 9, 92, 4, 3},
                                    {1, 4, 0, 9, 4, 2, 6, 42},
                                    {41, 12, 43, 4, 6, 0, 91, 4}
                                    }; // Стандартна матриця
            }
            else if (answer.equals("no")){
                isDone = true;
            }
            else{
                System.out.printf("\n\nInvalid answer. Please, use only \'yes\' or \'no\'\n\n");
            }
        }
        isDone = false;
        int Y = 0; // Кількість рядків матриці
        int X = 0; // Кількість елементів рядка
        while (isDone == false){
            try{
                System.out.printf("\n\n======MATRIX_SIZE======\n\n");
                System.out.println("Enter Y value (number of rows) for the matrix");
                Y = scan.nextInt();
                if (Y > 0){
                    isDone = true;
                }
                else{
                    System.out.println("Number of rows cannot be less than 1");
                }
            }
            catch(Exception ex){
                System.out.println("Wrong input");
                System.out.println("Remember: ONLY integers are allowed");
                System.out.println(ex);
                scan.next();
            }
        }

        isDone = false;
        while (isDone == false){
            try{
                System.out.println("Enter X value (number of elements in each row) for the matrix");
                X = scan.nextInt();
                if (X > 0){
                    isDone = true;
                }
                else{
                    System.out.println("Number of elements cannot be less than 1");
                }
            }
            catch(Exception ex){
                System.out.println("Wrong input");
                System.out.println("Remember: ONLY integers are allowed");
                System.out.println(ex);
                scan.next();
            }
        }
        isDone = false;
        int element;
        int[][] matrix = new int[Y][X]; // Створення матриці
        for (int i = 0; i < Y; i++){
            for (int j = 0; j < X; j++){
                while (isDone == false){
                    try{
                        System.out.printf("Enter [%d][%d] element of the matrix\n", i, j);
                        element = scan.nextInt();
                        matrix[i][j] = element; // Наповнення матриці елементами
                        isDone = true;
                    }
                    catch(Exception ex){
                        System.out.println("Wrong input");
                        System.out.println("Remember: ONLY integers are allowed");
                        System.out.println(ex);
                        scan.next();
                    }
                }
                isDone = false;
            }
        }
        return matrix;
    }

    public static void multiply(int a, int[][] matrix){
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                matrix[i][j] = matrix[i][j] * a; // Множення кожного елемента матриці на число
            }
        }
    }

    public static void average(int[][] matrix){
        float average = 0;
        int count = 0;
        for (int[] row : matrix){
            for (int element : row){
                average += element; // Сума всіх елементів матриці
                count++; // Кількість елементів у матриці
            }
        }
        average = average / count; // Визначення середнього значення
        System.out.printf("\n\n======AVERAGE_VALUE======\n\n");

        System.out.printf(Float.toString(average));

        System.out.printf("\n\n=========================\n\n");
    }

    public static void matrix_viewer(int[][] matrix){
        System.out.printf("\n\n======MATRIX_VIEWER======\n\n");
        for (int[] row : matrix){
            for (int element : row){
                System.out.printf(Integer.toString(element) + " "); // Виведення елемента матриці
            }
            System.out.printf("\n");
        }
        System.out.printf("\n\n=========================\n\n");
    }
}
