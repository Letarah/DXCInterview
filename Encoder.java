package dxc.interview;

import java.util.*;

public class Encoder {

    static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter Message to be Encoded:");
        String plainTxt = kb.nextLine();
        plainTxt = plainTxt.toUpperCase();
        String encoded = encode(plainTxt);
        System.out.println(encoded);
        String decoded = decode(encoded);
        System.out.println(decoded);
        // TODO code application logic here
    }

    public static String encode(String plainTxt) {
        Random ran = new Random();
        String txt = "A" + plainTxt;
        int offset = ran.nextInt(44);
        String encodedTxt = "";
        for (int i = 0; i < txt.length(); i++) {
            char a = txt.charAt(i);
            if (a == ' ') {
                encodedTxt += " ";
            } else if (lexicon.indexOf(a) + offset < 44) {
                encodedTxt += lexicon.charAt(lexicon.indexOf(a) + offset);
            } else if (lexicon.indexOf(a) + offset >= 44) {
                encodedTxt += lexicon.charAt(lexicon.indexOf(a) + offset - 44);
            }
        }
        return encodedTxt;
    }

    public static String decode(String encodedTxt) {
        String decodedTxt = "";
        int offset = lexicon.indexOf(encodedTxt.charAt(0));
        for (int i = 1; i < encodedTxt.length(); i++) {
            char a = encodedTxt.charAt(i);
            if (a == ' ') {
                decodedTxt += " ";
            } else if (lexicon.indexOf(a) - offset < 0) {
                decodedTxt += lexicon.charAt(lexicon.indexOf(a) - offset + 44);
            } else if (lexicon.indexOf(a) + offset >= 0) {
                decodedTxt += lexicon.charAt(lexicon.indexOf(a) - offset);
            }
        }
        return decodedTxt;
    }
}
