package Animal_register;

import Animal_register.Animals.*;
import Animal_register.Navigation.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Register {

    private static String PATH = "C:\\Users\\rexid\\Desktop\\final_test\\Animal_register\\Register.txt";
    private static ArrayList<Animal> list = new ArrayList<>();
    private static File txt = new File(PATH);

    public static void pullTxt() {

        list.clear();
        for (String line : getTxt()) {

            String[] data = line.split(" ");
            Animal animal = castAnimal(data[1]);
            animal.setName(data[4]);
            String commands = new String();
            for (int i = 7; i < data.length - 3; i++) {
                commands = commands + data[i] + " ";
            }
            commands = commands.substring(0, commands.length() - 1);
            animal.setCommands(commands);
            animal.birthday.setDay(Integer.parseInt(data[data.length - 1].split("-")[0]));
            animal.birthday.setMonth(Integer.parseInt(data[data.length - 1].split("-")[1]));
            animal.birthday.setYear(Integer.parseInt(data[data.length - 1].split("-")[2]));
            list.add(animal);
        }
    }

    public static ArrayList<String> getTxt() {

        ArrayList<String> data = new ArrayList<>();
        String line = new String();

        try (FileReader fr = new FileReader(txt);
                BufferedReader br = new BufferedReader(fr)) {

            line = br.readLine();
            while (line != null) {
                data.add(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println(Messages.criticalError);
            e.printStackTrace();
        }
        return data;
    }

    public static void flush() {

        String data = new String();
        for (Animal animal : list) {
            if (animal != null) {
                data += String.format("Class: %s | Name: %s | Skills: %s | Birthday: %d-%d-%d\n",
                        animal.getClass().toString().substring(30),
                        animal.getName(), animal.getCommands(),
                        animal.birthday.getDay(), animal.birthday.getMonth(), animal.birthday.getYear());
            }
        }
        try (FileWriter fw = new FileWriter(txt)) {
            fw.write(data);
            fw.close();
        } catch (Exception e) {
            System.out.println(Messages.criticalError);
            e.printStackTrace();
        }
    }

    public static void changeTxt(int index, String newClass) throws IOException {

        String newData = new String();
        ArrayList<String> data = getTxt();
        String[] newLine = data.get(index).split(" ");
        newLine[1] = newClass;
        for (String line : newLine) {
            newData = newData + line + " ";
        }
        data.set(index, newData.substring(0, newData.length() - 1));
        FileWriter fw = new FileWriter(txt);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String line : data) {
            bw.write(line + "\n");
        }
        bw.close();
        fw.close();
    }

    public static Animal castAnimal(String type) throws RuntimeException {

        switch (type.toLowerCase()) {
            case "cat":
                return new Cat();
            case "dog":
                return new Dog();
            case "hamster":
                return new Hamster();
            case "camel":
                return new Camel();
            case "horse":
                return new Horse();
            case "donkey":
                return new Donkey();
            default:
                throw new RuntimeException();
        }
    }

    public static boolean delete(Animal animal) {

        if (list.remove(animal))
            return true;
        return false;
    }

    public static boolean addList(Animal animal) {

        if (list.add(animal))
            return true;
        return false;
    }

    public static Animal getAnimal(int index) throws IndexOutOfBoundsException {

        return list.get(index);
    }

    public static int getIndex(Animal animal) {

        return list.indexOf(animal);
    }

    public static int getSize() {

        return list.size();
    }
}