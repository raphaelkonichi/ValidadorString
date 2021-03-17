package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws IOException {

        HashMap<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put('[',']');
        bracketMap.put('{','}');
        bracketMap.put('(',')');

        try {
            String finalValues = "";
            File myObj = new File("prog.txt");
            Scanner scan = new Scanner(myObj);
            while (scan.hasNextLine()) {
                String entry = scan.nextLine().trim();
                finalValues += entry + " - ";
                boolean hasError = false;
                Stack<Character> characterStack = new Stack<>();

                for (int i = 0; i < entry.length(); i++) {

                    if (bracketMap.containsKey(entry.charAt(i))) {
                        characterStack.push(entry.charAt(i));
                    } else {
                        if (characterStack.isEmpty()){
                            finalValues += "Inválido";
                            hasError = true;
                            break;
                        }
                        char lastBracket = characterStack.pop();

                        if (bracketMap.get(lastBracket) != entry.charAt(i)) {
                            finalValues += "Inválido";
                            hasError = true;
                            break;
                        }
                    }
                }
                if (!hasError){
                    finalValues += characterStack.isEmpty() ? "OK" : "Inválido";
                }
                finalValues += "\r\n";
            }
            Path pathFile = Paths.get("prog-check.txt");
            List<String> finalValuesList = Arrays.asList(finalValues);
            Files.write(pathFile, finalValuesList, StandardCharsets.UTF_8);
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
