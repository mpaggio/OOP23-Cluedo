package it.unibo.cluedo.utilities;

/**
 * This class represents a pair of objects.
 * @param <K> the type of the first object.
 * @param <V> the type of the second object.
 */

public class Pair<K, V> {

    private final K first;
    private final V second;

    /**
     * Constructor for the Pair class.
     * @param first the fist object.
     * @param second the second object.
     */
    public Pair(final K first, final V second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Method to get the first object.
     * @return the first object.
     */
    public K getFirst() {
        return this.first;
    }

    /**
     * Method to get the second object.
     * @return the second object.
     */
    public V getSecond() {
        return this.second;
    }
}
