import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class Lotto {

    public List<Integer> numbers;

    public Integer numberSize;

    public void Start() throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer numbersToDraw = 0;
        Integer numberList = 0;
        this.numberSize = numbersToDraw;
        boolean retry = false;
        System.out.println("This program picks up random numbers from the pool of numbers");
        System.out.println("You have to choose amount of numbers you would like to draw and then to choose the max range of numbers you would like to choose from.");

        do {
            try {
                System.out.println("\n" + "Enter numerically how many numbers you want to draw:");
                sc = new Scanner(System.in);
                numbersToDraw = sc.nextInt();
                retry = false;
            } catch (Exception e) {
                System.out.println("It's not valid value");
                retry = true;
            }
        } while (retry == true);

        do {
            try {
                System.out.println("\n" + "Enter numerically max range of numbers you would like to choose from:");
                sc = new Scanner(System.in);
                numberList = sc.nextInt();
                retry = false;
            } catch (Exception e) {
                System.out.println("It's not valid value");
                retry = true;
            }
        } while (retry == true);

        init(numberList);
        System.out.println("Number pool: " + numbers);
        System.out.println("\n" + "Your " + numbersToDraw + " draw numbers from number pool are:");
        System.out.println();

        List<Integer> sortedNumbers = new ArrayList<>();

        for (int i = 0; i < numbersToDraw; i++) {
            sortedNumbers.add(draw());
        }
        Collections.sort(sortedNumbers);
        System.out.println(sortedNumbers);

        File file = new File("lottoNumbers.txt");
        file.createNewFile();
        PrintWriter writer = new PrintWriter(file);
        writer.println("Your " + numbersToDraw + " draw numbers are: " + sortedNumbers);
        writer.close();
    }

    // ----  Creating number collection  ----------------------------------------------
    public void init(Integer numbersList) {
        numbers = new LinkedList<Integer>();
        for (int i = 1; i < numbersList + 1; i++) {
            numbers.add(new Integer(i));
        }
    }

    // ----  Number draw  ----------------------------------------------
    public Integer draw() {
        Integer randomNumber = (int) (Math.random() * numberSize);
        randomNumber = numbers.get(randomNumber);
        numbers.remove(randomNumber);
        this.numberSize = numbers.size();
        return randomNumber;
    }
}