package stream;

import function.*;

public interface Stream<T> {
    /**
     * Map
     *
     * @param mapper T->R
     * @return Stream
     */
    <R> MyStream<R> map(Function<R, T> mapper);

    /**
     * Reduce
     *
     * @param initVal
     * @param accumulator <R, R, T> are types for accumalator return val, initVal &  stream head value
     * @return Stream
     */
    <R> R reduce(R initVal, BiFunction<R, R, T> accumulator);

    int count();

    public static <T> MyStream<T> makeEmptyStream() {
        return new MyStream.Builder<T>().isEnd(true).build();
    }
}
