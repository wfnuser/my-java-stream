package stream;

import function.*;

public class MyStream<T> implements Stream<T> {

    private T head;
    private EvalNextProcess evalNextProcess;
    private boolean isEnd;

    public static class Builder<T> {
        private MyStream<T> stream;

        public Builder() {
            this.stream = new MyStream<>();
        }

        public Builder<T> head(T head) {
            stream.head = head;
            return this;
        }

        Builder<T> isEnd(boolean isEnd) {
            stream.isEnd = isEnd;
            return this;
        }

        public Builder<T> nextItemEvalProcess(EvalNextProcess evalNextProcess) {
            stream.evalNextProcess = evalNextProcess;
            return this;
        }

        public MyStream<T> build() {
            return stream;
        }
    }

    public MyStream<T> eval() {
        return this.evalNextProcess.eval();
    }

    private boolean isEmptyStream() {
        return this.isEnd;
    }


    @Override
    public <R> MyStream<R> map(Function<R, T> mapper) {

        EvalNextProcess lastEvalNextProcess = this.evalNextProcess;

        this.evalNextProcess = new EvalNextProcess(() -> {
            MyStream stream = lastEvalNextProcess.eval();
            return map(mapper, stream);
        });

        return new MyStream.Builder<R>().nextItemEvalProcess(this.evalNextProcess).build();
    }

    private static <R, T> MyStream<R> map(Function<R, T> mapper, MyStream<T> myStream) {
        if (myStream.isEmptyStream()) {
            return Stream.makeEmptyStream();
        }

        R head = mapper.apply(myStream.head);

        return new MyStream.Builder<R>()
                .head(head)
                .nextItemEvalProcess(new EvalNextProcess(() -> map(mapper, myStream.eval())))
                .build();
    }

    @Override
    public <R> R reduce(R initVal, BiFunction<R, R, T> accumulator) {
        return reduce(initVal, accumulator, this.eval());
    }

    private static <R, T> R reduce(R initVal, BiFunction<R, R, T> accumulator, MyStream<T> stream) {
        if (stream.isEmptyStream()) {
            return initVal;
        }

        T head = stream.head;
        // It's actually bad in Functional Programming;
        // WHY?
        // HOW TO SOLVE IT?
        initVal = accumulator.apply(initVal, head);
        return reduce(initVal, accumulator, stream.eval());

    }
}
