package Animal_register.Navigation;

import Animal_register.Animals.*;
import Animal_register.Counter;
import Animal_register.Register;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    public static void startMenu(Counter counter) throws IOException {

        greetings();
        boolean flag = true;
        BufferedReader req = new BufferedReader(new InputStreamReader(System.in));

        while (flag) {

            String request = req.readLine();
            request = request.toLowerCase();
            switch (request) {
                case "1":
                    seeList();
                    System.out.print(Messages.waiting_en);
                    break;
                case "2":
                    addAnimal(req);
                    counter.plus();
                    Register.flush();
                    System.out.print(Messages.waiting_en);
                    break;
                case "3":
                    changeClass(req);
                    Register.pullTxt();
                    System.out.print(Messages.waiting_en);
                    break;
                case "4":
                    seeCommands(req);
                    System.out.print(Messages.waiting_en);
                    break;
                case "5":
                    teachNewCommand(req);
                    Register.flush();
                    System.out.print(Messages.waiting_en);
                    break;
                case "6":
                    deleteAnimal(req);
                    counter.minus();
                    Register.flush();
                    System.out.print(Messages.waiting_en);
                    break;
                case "7":
                    seeMenuAgain();
                    break;
                case "8":
                    req.close();
                    System.out.println(Messages.bye_en);
                    flag = false;
                    break;
                default:
                    System.out.println(Messages.notAbleToResponse_en);
                    System.out.print(Messages.waiting_en);
                    break;
            }
        }
    }

    private static void greetings() {

        System.out.println(Messages.welcome_en);
        System.out.println(Messages.menu_en);
    }

    private static void seeList() {

        int i = 1;
        for (String animal : Register.getTxt()) {
            System.out.println(i++ + ": " + animal);
        }
    }

    private static void addAnimal(BufferedReader req) throws IOException {

        boolean addMore = true;
        System.out.println(Messages.addNewAnimal_en);

        while (addMore) {

            switch (req.readLine().toLowerCase()) {

                case "cat":
                    Cat cat = new Cat();
                    collectData(cat, req);
                    Register.addList(cat);
                    Register.flush();
                    if (ask(req, Messages.askMore_en)) {
                        System.out.println(Messages.whichAnimal_en);
                    } else
                        addMore = false;
                    break;

                case "dog":
                    Dog dog = new Dog();
                    collectData(dog, req);
                    Register.addList(dog);
                    Register.flush();
                    if (ask(req, Messages.askMore_en)) {
                        System.out.println(Messages.whichAnimal_en);
                    } else
                        addMore = false;
                    break;

                case "hamster":
                    Hamster hamster = new Hamster();
                    collectData(hamster, req);
                    Register.addList(hamster);
                    Register.flush();
                    if (ask(req, Messages.askMore_en)) {
                        System.out.println(Messages.whichAnimal_en);
                    } else
                        addMore = false;
                    break;

                case "horse":
                    Horse horse = new Horse();
                    collectData(horse, req);
                    Register.addList(horse);
                    Register.flush();
                    if (ask(req, Messages.askMore_en)) {
                        System.out.println(Messages.whichAnimal_en);
                    } else
                        addMore = false;
                    break;

                case "camel":
                    Camel camel = new Camel();
                    collectData(camel, req);
                    Register.addList(camel);
                    Register.flush();
                    if (ask(req, Messages.askMore_en)) {
                        System.out.println(Messages.whichAnimal_en);
                    } else
                        addMore = false;
                    break;

                case "donkey":
                    Donkey donkey = new Donkey();
                    collectData(donkey, req);
                    Register.addList(donkey);
                    Register.flush();
                    if (ask(req, Messages.askMore_en)) {
                        System.out.println(Messages.whichAnimal_en);
                    } else
                        addMore = false;
                    break;

                case "":
                    System.out.println("Right back");
                    addMore = false;
                    break;

                default:
                    System.out.println(Messages.notAbleToResponse_en);
                    System.out.println(Messages.whichAnimal_en);
                    break;
            }
        }
    }

    private static Animal chooseAnimal(BufferedReader req) throws IOException {

        seeList();
        System.out.println(Messages.chooseAnimal_en);
        try {
            int index = Integer.parseInt(req.readLine()) - 1;
            return Register.getAnimal(index);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return chooseAnimal(req);
        }
    }

    private static void seeCommands(BufferedReader req) throws IOException {

        Animal target = chooseAnimal(req);
        System.out.printf("\n%s can do %s\n", target.getName(),
                target.getCommands().isEmpty() ? "Nothing(" : target.getCommands().replaceAll(" ", " and "));
    }

    private static void teachNewCommand(BufferedReader req) throws IOException {

        Animal target = chooseAnimal(req);
        System.out.println(Messages.teachNewCommands_en);
        String newCommands = req.readLine();
        if (!target.setCommands(newCommands)) {
            System.out.println(Messages.bigCommandsIssue_en);
            target.setCommandsForce(newCommands, req.readLine());
        }
    }

    private static void changeClass(BufferedReader req) throws IOException {

        String newClass = new String();
        System.out.println(Messages.whichClass_en);
        boolean classCorrect = false;
        while (!classCorrect) {
            try {
                newClass = Register.castAnimal(req.readLine().toLowerCase()).getClass().toString().substring(30);
                classCorrect = true;
            } catch (RuntimeException e) {
                System.out.println(Messages.notAbleToResponse_en);
            }
        }
        Register.changeTxt(Register.getIndex(chooseAnimal(req)), newClass);
    }

    private static void deleteAnimal(BufferedReader req) throws IOException {

        Animal target = chooseAnimal(req);
        if (ask(req, Messages.askDelete_en)) {
            Register.delete(target);
        } else
            System.out.println("Abortion");
    }

    private static void collectData(Animal animal, BufferedReader sc) throws IOException {

        System.out.println(Messages.inputDataPlease_en);
        int day = 0;
        int month = 0;
        int year = 0;
        boolean flag = false;
        String name = sc.readLine();

        while (!animal.setName(name)) {
            System.out.println(Messages.incorrectName_en);
            name = sc.readLine();
        }
        animal.setCommands(sc.readLine());
        String birthday = sc.readLine();

        while (!flag) {
            try {
                day = Integer.parseInt(birthday.split(" ")[0]);
                if (!animal.birthday.setDay(day)) {
                    throw new RuntimeException();
                }
                month = Integer.parseInt(birthday.split(" ")[1]);
                if (!animal.birthday.setMonth(month))
                    throw new RuntimeException();
                year = Integer.parseInt(birthday.split(" ")[2]);
                if (!animal.birthday.setYear(year))
                    throw new RuntimeException();
                flag = true;
            } catch (RuntimeException e) {
                System.out.println(Messages.incorrectBirthday_en);
                birthday = sc.readLine();
            }
        }
    }

    private static boolean ask(BufferedReader req, String msg) throws IOException {

        System.out.println(msg);
        String answer = req.readLine();
        if (answer.toLowerCase().equals("yes"))
            return true;
        return false;
    }

    private static void seeMenuAgain() {

        System.out.println(Messages.menu_en);
        System.out.print(Messages.waiting_en);
    }
}