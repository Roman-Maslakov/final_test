package Animal_register.Animals;

public class Birthday {
    
    private int day;

    private int month;

    private int year;

    protected Birthday() {}

    public boolean setDay(int day) {

        if (day > 0 && day <= 31) {
            this.day = day;
            return true;
        }
        return false;
    }

    public boolean setMonth(int month) {

        if (month > 0 && month <= 12) {
            this.month = month;
            return true;
        }
        return false;
    }

    public boolean setYear(int year) {

        if (year >= 2000 && year <= 2024) {
            this.year = year;
            return true;
        }
        return false;
    }

    public int getDay() {

        return this.day;
    }

    public int getMonth() {

        return this.month;
    }

    public int getYear() {

        return this.year;
    }
}
