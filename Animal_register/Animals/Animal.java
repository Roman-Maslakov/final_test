package Animal_register.Animals;

public abstract class Animal {

    private String name;

    private String commands;

    public Birthday birthday = new Birthday();

    public Animal() {
    }

    public boolean setName(String newName) {

        for (int i = 0; i < newName.length(); i++) {
            if (!Character.isLetter(newName.charAt(i))) {
                return false;
            }
        }
        this.name = newName;
        return true;
    }

    public boolean setCommands(String commands) {

        for (String command : commands.split(" ")) {
            if (command.length() > 10) {
                return false;
            }
        }
        this.commands = commands;
        return true;
    }

    public void setCommandsForce(String commands, String answer) {

        if (answer.toLowerCase().equals("yes")) {
            this.commands = commands;
        } else {
            System.out.println("Aborting");
        }
    }

    public String getName() {

        return this.name;
    }

    public String getCommands() {

        return this.commands;
    }

    public Birthday getBirthday() {

        return this.birthday;
    }
}
