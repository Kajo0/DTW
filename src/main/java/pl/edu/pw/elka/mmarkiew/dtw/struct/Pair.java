package pl.edu.pw.elka.mmarkiew.dtw.struct;

public class Pair<T> {

    public T first;
    public T second;

    public Pair() {

    }

    public Pair(final T first, final T second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Pair<?> == false)
            return false;

        Pair<?> pair = (Pair<?>) obj;

        if (first == null || second == null)
            return false;

        return first.equals(pair.first) && second.equals(pair.second);
    }

}