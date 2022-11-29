import java.io.*;
import java.util.Scanner;

public class ReadCSV {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: ReadCSV <filename>");
            System.exit(0);
        }

        String[] fieldNames;
        int[] accumulators;

        BufferedReader in = null;
        try {
            in = new BufferedReader(new
                    FileReader(args[0]));

            String header = in.readLine();
            Scanner parser = null;
            int fieldsCounter = 0;
            try {
                parser = new Scanner(header);
                parser.useDelimiter(",");
                while (parser.hasNext()) {
                    parser.next();
                    fieldsCounter++;
                }
            } finally {
                parser.close();
            }

            fieldNames = new String[fieldsCounter];
            accumulators = new int[fieldsCounter];

            try {
                parser = new Scanner(header);
                parser.useDelimiter(",");
                int i = 0;
                while (parser.hasNext()) {
                    fieldNames[i] = parser.next();
                    accumulators[i] = 0;
                    i++;
                }
                System.out.println();
            } finally {
                parser.close();
            }
            String line;
            while ((line = in.readLine())!=null) {
                System.out.println(line);
                try {
                    parser = new Scanner(line);
                    parser.useDelimiter(",");



                } finally {
                    parser.close();
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File " + args[0] + " not found.");
            System.exit(1);
        } catch (IOException ex) {
            System.err.println("Error reading file " + args[0]);
            System.exit(1);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}

