package function;

import stream.MyStream;

/**
 * @Author wfnuser
 * @Date 2019/5/26
 */
@FunctionalInterface
public interface EvalFunction<T> {
    MyStream<T> apply();
}
