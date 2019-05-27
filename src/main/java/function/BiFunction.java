package function;

@FunctionalInterface
public interface BiFunction<R, T, U> {

    /**
     * (x, y) => f(x, y)
     */
    R apply(T t, U u);
}
