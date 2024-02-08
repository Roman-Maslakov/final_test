package Animal_register;

public class Counter implements AutoCloseable {
    
    private boolean close;
    private int count;

    public Counter() {

        this.close = false;
        this.count = 0;
    }

    public void countList() {

        this.count = Register.getSize();
    }

    public void plus() {

        this.count++;
    }

    public void minus() {

        this.count--;
    }

    public void close() {

        this.count = 0;
        if (!isClose()) {
            this.close = true;
        } else throw new RuntimeException();
    }

    public int getCount() {

        return this.count;
    }

    public boolean isClose() {

        return this.close;
    }
}
