import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Scanner magicInput = new Scanner(System.in);
        int number = 2;

        System.out.print("\033[H\033[2J");

        while (number % 2 == 0 || number > 26 || number <= 1) {
            System.out.print("Input an Odd Number: ");
            number = magicInput.nextInt();

            if (number <= 1) {
                System.out.println("A magic square of size 1 is already solved. A Magic square of a size less than one would be imposiible to create. Please input a number greater than 1.");
            } else if (number % 2 == 0 && number > 26) {
                System.out.println("The number you inputted is not an odd number and is too large for this program to create a magic square.");
            } else if (number % 2 == 0) {
                System.out.println("The number you inputted is not an odd number.");
            } else if (number > 26) {
                System.out.println("This program cannot create a magic square of this size. Please input a number less than to 26.");
            }
        }

        int magic_sum = (number * (number * number + 1)) / 2;

        System.out.print("\033[H\033[2J");

        int[][] magicSquare = new int[number][number];
        int x_coordinate = 0;
        int y_coordinate = ((number - 1) / 2);

        int num_rows = magicSquare.length;
        int num_columns = magicSquare[0].length;

        magicSquare[x_coordinate][y_coordinate] = 1;

        for (int cellValue = 2; cellValue <= number * number; cellValue = cellValue + 1) {

            int new_x = (x_coordinate - 1 + num_rows) % num_rows;
            int new_y = (y_coordinate + 1) % num_columns;

            if (magicSquare[new_x][new_y] == 0) {
                x_coordinate = new_x;
                y_coordinate = new_y;
            } else {
                x_coordinate = (x_coordinate + 1) % num_rows;   
            }
            magicSquare[x_coordinate][y_coordinate] = cellValue;

        }

        for (int i = 0; i < number * number; i = i + 1) {
            Random random = new Random();

            int random_rows = random.nextInt(num_rows);
            int random_columns = random.nextInt(num_columns);

            boolean valid_up = true;
            boolean valid_down = true;
            boolean valid_left = true;
            boolean valid_right = true;

            try {
                int random_up = magicSquare[random_rows - 1][random_columns];
            } catch (Exception e) {
                valid_up = false;
            }

            try {
                int random_down = magicSquare[random_rows + 1][random_columns];
            } catch (Exception e) {
                valid_down = false;
            }

            try {
                int random_left = magicSquare[random_rows][random_columns - 1];
            } catch (Exception e) {
                valid_left = false;
            }

            try {
                int random_right = magicSquare[random_rows][random_columns + 1];
            } catch (Exception e) {
                valid_right = false;
            }

            boolean decided_swap = false;

            while (decided_swap == false) {
                int random_direction = random.nextInt(4);
                if (random_direction == 0) {
                    if (valid_up == true) {
                        int temp = magicSquare[random_rows][random_columns];
                        magicSquare[random_rows][random_columns] = magicSquare[random_rows - 1][random_columns];
                        magicSquare[random_rows - 1][random_columns] = temp;

                        decided_swap = true;    
                    }
                } else if (random_direction == 1) {
                    if (valid_down == true) {
                        int temp = magicSquare[random_rows][random_columns];
                        magicSquare[random_rows][random_columns] = magicSquare[random_rows + 1][random_columns];
                        magicSquare[random_rows + 1][random_columns] = temp;

                        decided_swap = true;
                    }
                } else if (random_direction == 2) {
                    if (valid_left == true) {
                        int temp = magicSquare[random_rows][random_columns];
                        magicSquare[random_rows][random_columns] = magicSquare[random_rows][random_columns - 1];
                        magicSquare[random_rows][random_columns - 1] = temp;

                        decided_swap = true;
                    }
                } else if (random_direction == 3) {
                    if (valid_right == true) {
                        int temp = magicSquare[random_rows][random_columns];
                        magicSquare[random_rows][random_columns] = magicSquare[random_rows][random_columns + 1];
                        magicSquare[random_rows][random_columns + 1] = temp;

                        decided_swap = true;
                    }
                }

            }
        }
        
        System.out.println("The magic sum of the magic square is: " + magic_sum);
        System.err.println("The Magic Square has been generated and its values swapped " + number * number + " times.");
        System.err.println();

        StringBuilder sb = new StringBuilder();

        for (char c = 'a'; c < 'a' + number && c <= 'z'; c++) {

            sb.append('\t');
            sb.append(c);
        }
        String result = sb.toString();

        System.out.println(result);
        for (int i = 0; i < number; i = i + 1) {
           System.out.print(i + 1 + " :");
           for (int j = 0; j < number; j = j + 1) {
               System.out.print("\t" + magicSquare[i][j]);
            }
          System.out.println();
        }

        String first_pos = new String();
        boolean gameover = false;


        int moves = 0;

        while (gameover == false) {
            moves = moves + 1;
            System.out.print("Input the initial cell you want to swap: ");
            first_pos = magicInput.next();

            boolean first_pos_decided = false;

            Map<Character, Integer> alphabetMap = new HashMap<>();
            char letter = 'a';
            int corrosponding_number = 1;

            for (int i = 0; i < 26; i = i + 1) {
                alphabetMap.put(letter, corrosponding_number);
                letter++;
                corrosponding_number++;
            }


            int selected_cell_x = 0;
            int selected_cell_y = 0;

                while (first_pos_decided == false) {

                    if (first_pos.length() == 2) {
                        char rawfirstChar = first_pos.charAt(0);
                        char firstChar = Character.toLowerCase(rawfirstChar);
                        char secondChar = first_pos.charAt(1);

                        if (Character.isLetter(firstChar) && Character.isDigit(secondChar)) {
                            if (alphabetMap.get(firstChar) <= number && Character.getNumericValue(secondChar) <= number) {
                                System.out.println("The initial selected cell to swap is: " + first_pos);
                                selected_cell_x = Character.getNumericValue(secondChar) - 1;
                                selected_cell_y = alphabetMap.get(firstChar) - 1;

                                first_pos_decided = true;
                            } else {
                                System.out.println("Invalid Cell position");
                                System.out.println("Cell must be within the range of the magic square");
                                System.out.print("Input the initial cell you want to swap: ");
                                first_pos = magicInput.next();
                            }

                        } else {
                            System.out.println("Invalid Cell position");
                            System.out.println("Cell must be composed of 1 letter and 1 number");
                            System.out.print("Input the initial cell you want to swap: ");
                            first_pos = magicInput.next();
                        }

                    } else {
                        System.out.println("Invalid Cell position");
                        System.out.println("Cell must be composed of 2 characters");
                        System.out.print("Input the initial cell you want to swap: ");
                        first_pos = magicInput.next();
                    }
                }



            boolean selected_up_valid = true;
            boolean selected_down_valid = true;
            boolean selected_left_valid = true;
            boolean selected_right_valid = true;

            try {
                int selected_up = magicSquare[selected_cell_x - 1][selected_cell_y];
            } catch (Exception e) {
                selected_up_valid = false;
            }

            try {
                int selected_down = magicSquare[selected_cell_x + 1][selected_cell_y];
            } catch (Exception e) {
                selected_down_valid = false;
            }

            try {
                int selected_left = magicSquare[selected_cell_x][selected_cell_y - 1];
            } catch (Exception e) {
                selected_left_valid = false;
            }

            try {
                int selected_right = magicSquare[selected_cell_x][selected_cell_y + 1];
            } catch (Exception e) {
                selected_right_valid = false;
            }

            if (selected_up_valid == true) {
                System.out.println("You can swap the cell " + first_pos + " with the cell above it by typing 'up'");
            }
            if (selected_down_valid == true) {
                System.out.println("You can swap the cell " + first_pos + " with the cell below it by typing 'down'");
            }
            if (selected_left_valid == true) {
                System.out.println("You can swap the cell " + first_pos + " with the cell to the left of it by typing 'left'");
            }
            if (selected_right_valid == true) {
                System.out.println("You can swap the cell " + first_pos + " with the cell to the right of it by typing 'right'");
            }

            boolean decided_swap = false;

            while (decided_swap == false) {
                System.out.print("Input the direction you want to swap: ");
                String raw_direction = new String();
                raw_direction = magicInput.next();

                String direction = raw_direction.toLowerCase();

                if (direction.equals("up") && selected_up_valid == true) {
                    int temp = magicSquare[selected_cell_x][selected_cell_y];
                    magicSquare[selected_cell_x][selected_cell_y] = magicSquare[selected_cell_x - 1][selected_cell_y];
                    magicSquare[selected_cell_x - 1][selected_cell_y] = temp;
                    decided_swap = true;
                    System.out.print("\033[H\033[2J");
                } else if (direction.equals("down") && selected_down_valid == true) {
                    int temp = magicSquare[selected_cell_x][selected_cell_y];
                    magicSquare[selected_cell_x][selected_cell_y] = magicSquare[selected_cell_x + 1][selected_cell_y];
                    magicSquare[selected_cell_x + 1][selected_cell_y] = temp;
                    decided_swap = true;
                    System.out.print("\033[H\033[2J");
                } else if (direction.equals("left") && selected_left_valid == true) {
                    int temp = magicSquare[selected_cell_x][selected_cell_y];
                    magicSquare[selected_cell_x][selected_cell_y] = magicSquare[selected_cell_x][selected_cell_y - 1];
                    magicSquare[selected_cell_x][selected_cell_y - 1] = temp;
                    decided_swap = true;
                    System.out.print("\033[H\033[2J");
                } else if (direction.equals("right") && selected_right_valid == true) {
                    int temp = magicSquare[selected_cell_x][selected_cell_y];
                    magicSquare[selected_cell_x][selected_cell_y] = magicSquare[selected_cell_x][selected_cell_y + 1];
                    magicSquare[selected_cell_x][selected_cell_y + 1] = temp;
                    decided_swap = true;
                    System.out.print("\033[H\033[2J");
                } else {
                    System.out.println("Invalid direction");
                }
            }

            boolean sum_magic_valid = true;

            for (int i = 0; i < number; i = i + 1) {
                int sum_row = 0;
                for (int j = 0; j < number; j = j + 1) {
                    sum_row = sum_row + magicSquare[i][j];
                }
                if (sum_row != magic_sum) {
                    sum_magic_valid = false;
                    break;
                } else {
                }
            }

            for (int i = 0; i < number; i = i + 1) {
                int sum_column = 0;
                for (int j = 0; j < number; j = j + 1) {
                    sum_column = sum_column + magicSquare[j][i];
                }
                if (sum_column != magic_sum) {
                    sum_magic_valid = false;
                    break;
                } else {
                }
            }

            int sum_diagonal1 = 0;
            for (int i = 0; i < number; i = i + 1) {
                sum_diagonal1 = sum_diagonal1 + magicSquare[i][i]; 
            }
            if (sum_diagonal1 != magic_sum) {
                sum_magic_valid = false;
            }
            int sum_diagonal2 = 0;
            for (int i = 0; i < number; i = i + 1) {
                sum_diagonal2 = sum_diagonal2 + magicSquare[i][number - 1 - i];
            }
            if (sum_diagonal2 != magic_sum) {
                sum_magic_valid = false;
            }

            if (sum_magic_valid == true) {
                gameover = true;
                System.out.println("Congratulations! You have solved the magic square!");
                System.out.println("The number of moves you took to solve the magic square is: " + moves);
                break;
            } else {
                System.out.println("The magic sum of the magic square is: " + magic_sum);
                System.out.println("The new magic square is: ");
                System.out.println();
                System.out.println(result);
                for (int i = 0; i < number; i = i + 1) {
                    System.out.print(i + 1 + " :");
                    for (int j = 0; j < number; j = j + 1) {
                        System.out.print("\t" + magicSquare[i][j]);
                        }
                    System.out.println();
                }   
            }
        }

    }
}
