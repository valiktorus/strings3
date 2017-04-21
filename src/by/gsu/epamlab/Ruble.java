package by.gsu.epamlab;

public final class Ruble {
    private final int rubls;

    public int getRubls() {
        return rubls;
    }

    public Ruble(int rubls) {
        this.rubls = rubls;
    }

    @Override
    public String toString() {
       return String.valueOf(rubls);
    }
}
