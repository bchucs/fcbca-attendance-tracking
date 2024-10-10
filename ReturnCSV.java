import java.io.*;
import java.util.Scanner;

public class ReturnCSV {
    private File mainFile;
    Scanner scan = null;

    public ReturnCSV(File mainFile) {
        this.mainFile = mainFile;
    }

    public int[] returnTimes() {
        int cm = 0;
        int ss = 0;
        try {
            scan = new Scanner(mainFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        scan.nextLine();
        while (scan.hasNextLine()) {
            String[] data = scan.nextLine().split(",");
            if (data.length != 2) {
                continue;
            }
            String checkInTime = data[0].split(" ")[1];
            String checkOutTime = data[1].split(" ")[1];

            double timeIn = Double.parseDouble(checkInTime.split("\\.")[0])
            + Double.parseDouble(checkInTime.split("\\.")[1])/60
            + Double.parseDouble(checkInTime.split("\\.")[2])/3600;

            double timeOut = Double.parseDouble(checkOutTime.split("\\.")[0])
            + Double.parseDouble(checkOutTime.split("\\.")[1])/60
            + Double.parseDouble(checkOutTime.split("\\.")[2])/3600;

            if (timeIn <= 10.25 && timeOut >= 11.25) {
                ss++;
                cm++;
            } else if (timeOut <= 10.8) {
                ss++;
            } else if (timeIn >= 10.30) {
                cm++;
            } else {
                System.out.println("Invalid time: " + data[0] + ' ' + data[1]);
            }
        }
        int [] times = {ss, cm};
        return times;
    }

    public static void main(String[] args) {
        File file = new File("test.csv");
        ReturnCSV csv = new ReturnCSV(file);
        int [] times = csv.returnTimes();
        System.out.println("Sunday School: " + times[0] + " Children Ministry: " + times[1]);
    }
}