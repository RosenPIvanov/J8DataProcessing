package com.my;

import java.io.*;

public class Python {
    public static void main(String[] args) throws IOException {
        usingProcess();
        usingProcessBuilder();

    }

    static void usingProcess() {
        try {

            String prg = "import sys\nprint int(sys.argv[1])+int(sys.argv[2])\n";
            BufferedWriter out = new BufferedWriter(new FileWriter("test1.py"));
            out.write(prg);
            out.close();
            int number1 = 10;
            int number2 = 32;

            Process p = Runtime.getRuntime().exec("python test1.py " + number1 + " " + number2);

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int ret = new Integer(in.readLine()).intValue();
            System.out.println("value is : " + ret);
        } catch (Exception e) {
        }
    }

    static void usingProcessBuilder() {
        try {

            String prg = "import sys\nprint int(sys.argv[1])+int(sys.argv[2])\n";
            BufferedWriter out = new BufferedWriter(new FileWriter("test1.py"));
            out.write(prg);
            out.close();
            int number1 = 10;
            int number2 = 32;

            ProcessBuilder pb = new ProcessBuilder("python", "test1.py", "" + number1, "" + number2);
            Process p = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int ret = new Integer(in.readLine()).intValue();
            System.out.println("value is : " + ret);
        } catch (Exception e) {
            System.out.println(e);
        }
    }




}
