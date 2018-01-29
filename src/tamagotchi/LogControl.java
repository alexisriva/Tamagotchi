/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.io.File;
import java.io.PrintWriter;
//import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author USER
 */
public class LogControl {
    public static LinkedList<Log> logs = new LinkedList<>();
    
    public static LinkedList<Log> readFile() {
        try {
            Scanner scanner = new Scanner(new File("/src/tamagotchi/historial.txt"));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String campos[] = line.split(":");
                String subc1[] = campos[0].split("-");
                String subc2[] = campos[1].split("-");
                Log log = new Log(subc1[0], subc1[1], subc2[0], subc2[1]);
                logs.add(log);
            }
        } catch (Exception e) { System.err.println(e); }
        
        return logs;
    }
    
    public static void writeFile(Log log) {
        readFile();
        logs.add(log);
        try {
            PrintWriter writer = new PrintWriter("/src/tamagotchi/historial.txt");
            logs.forEach((l) -> {
                writer.println(l.toString());
            });
            
            writer.close();
        } catch (Exception e) { System.err.println(e); }
    }
}
