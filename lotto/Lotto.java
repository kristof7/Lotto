import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class Lotto {

    public List<Integer> numbers;

    public Integer numberSize;

    public void Start() throws IOException, InterruptedException {
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
                System.out.println("It's not valid value, try again..");
                retry = true;
            }
            if (numbersToDraw < 0 && retry == false) {
                System.out.println("Wrong Value. Value should be positive, try again.. ");
            }
        } while (numbersToDraw < 0 || retry == true);

        do {
            try {
                System.out.println("\n" + "Enter numerically max range of numbers you would like to choose from:");
                sc = new Scanner(System.in);
                numberList = sc.nextInt();
                retry = false;
            } catch (Exception e) {
                System.out.println("It's not valid value, try again..");
                retry = true;
            }
            if (numberList < 0 && retry == false) {
                System.out.println("Wrong Value. Value should be positive, try again.. ");
            }
        } while (numberList < 0 || retry == true);

        init(numberList);
        System.out.println("Number pool: " + numbers);


        // ---- Sort numbers from minimum and displays them in an interval of one second
        System.out.println("\n" + "Your " + numbersToDraw + " draw numbers from number pool are:");
        List<Integer> sortedNumbers = new ArrayList<>();
        for (int i = 0; i < numbersToDraw; i++) {
            sortedNumbers.add(draw());
        }
        for (int i = 0; i < numbersToDraw; i++) {
            Thread.sleep(1000);
            System.out.println(sortedNumbers.get(i));
        }
        Collections.sort(sortedNumbers);

        System.out.println("\n" + "Your " + numbersToDraw + " draw numbers from number pool are: " + sortedNumbers);

        // ---- Writes out numbers to lottoNumbers.txt
        File file = new File("lottoNumbers.txt");
        file.createNewFile();
        PrintWriter writer = new PrintWriter(file);
        writer.println("Your " + numbersToDraw + " draw numbers are: " + sortedNumbers);
        writer.close();
    }

    // ---- Creating number collection  ----------------------------------------------
    public void init(Integer numbersList) {
        numbers = new LinkedList<Integer>();
        for (int i = 1; i < numbersList + 1; i++) {
            numbers.add(new Integer(i));
        }
    }

    // ---- Number draw  ----------------------------------------------
    public Integer draw() {
        Integer randomNumber = (int) (Math.random() * numberSize);
        randomNumber = numbers.get(randomNumber);
        numbers.remove(randomNumber);
        this.numberSize = numbers.size();
        return randomNumber;
    }
}