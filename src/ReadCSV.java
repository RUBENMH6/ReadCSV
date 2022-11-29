import java.io.*;
import java.util.Scanner;

public class ReadCSV {
    public static void main(String[] args) throws IOException {
        checkArgument(args);

        String[] fieldNames;
        int[] accumulators;
        BufferedReader in = null;

        try {
            int fieldsCounter = getNumberOfFields(args[0]);
            String filename = args[0];
            fieldNames = new String[fieldsCounter];
            accumulators = new int[fieldsCounter];
            in = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = in.readLine())!=null) {
                //System.out.println(line);
                Scanner parser = null;
                try {
                    parser = new Scanner(line);
                    parser.useDelimiter(",");
                    int i = 0;
                    while (parser.hasNext()){
                        String numStr = parser.next().replaceAll(" ","");
                        accumulators[i] += Integer.parseInt(numStr);
                        i++;
                    }

                } finally {
                    parser.close();
                }
            }

            for(int i = 0; i < fieldNames.length ; i++)  {
                System.out.println(fieldNames[i] + ": " + accumulators[i] );
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

    private static void initializeArrays(String[] fieldNames, int[] accumulators, BufferedReader in) throws IOException {
        Scanner parser = null;
        try {
            String header = in.readLine();
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
    }

    private static void checkArgument(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: ReadCSV <filename>");
            System.exit(0);
        }
    }
    public static int getNumberOfFields(String fileName) throws FileNotFoundException, IOException {
        BufferedReader inMethod = null;
        inMethod = new BufferedReader(new
                FileReader(fileName));

        String header = inMethod.readLine();
        inMethod.close();
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
        return fieldsCounter;
    }
}

