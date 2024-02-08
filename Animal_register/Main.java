package Animal_register;


import Animal_register.Navigation.*;


public class Main {


    public static void main(String[] args) {

        try (Counter counter = new Counter()) {

            Register.pullTxt();
            counter.countList();
            Menu.startMenu(counter);
            Register.flush();

            System.out.println(counter.getCount());

        } catch (RuntimeException e) {

            System.out.println(Messages.counterErr);

        } catch (Exception e) {

            System.out.println(Messages.criticalError);
            e.printStackTrace();
        }
    }
}
