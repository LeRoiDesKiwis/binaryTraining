package fr.leroideskiwis.ex;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;

public class Main implements Runnable{

    private Scanner scan = new Scanner(System.in);

    public String generateWord(int bounds){

        String finalStr = "";

        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

        for(int i = 0; i < bounds; i++){

            finalStr += chars[new Random().nextInt(chars.length)];

        }

        return finalStr;

    }

    public Main() throws Throwable{

        String randomW = generateWord(2);
        Long start = System.currentTimeMillis();
        byte[] bytes = randomW.getBytes();

        StringBuilder builder = new StringBuilder();

        for(byte b : bytes){

            builder.append(Integer.toBinaryString(b));
            if(b != bytes[bytes.length-1]) builder.append(", ");

        }

        System.out.println("Les bytes sont : "+builder.toString());

        String scanned = scan.nextLine();
        Long end = System.currentTimeMillis();
        scan.close();

        builder = new StringBuilder();

        System.out.println(randomW+" : la chaîne de caractères qu'il fallait trouver");

        for(int i = 0; i < randomW.length(); i++){

            char scannedChar = scanned.length() > i ? scanned.toCharArray()[i] : 0;
            char randomChar = randomW.toCharArray()[i];

            if(scannedChar != 0 && scannedChar == randomChar) builder.append("O");
            else builder.append("X");

        }

        System.out.println(builder.toString());
        System.out.println(scanned+" : la chaîne de caractères que vous avez trouvé");

        System.out.println("Vous avez trouvé en "+(end/1000-start/1000)+"s");

    }

    public static void main(String... args){

        try {
            new Thread(new Main(), "main").start();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Override
    public void run() {

    }
}
