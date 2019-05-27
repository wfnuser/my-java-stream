package function;

@FunctionalInterface
public interface Function<R,T> {

    /**
     * x => f(x)
     * */
    R apply(T t);
}
