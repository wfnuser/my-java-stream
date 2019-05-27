package stream;

import function.EvalFunction;

/**
 * @Author wfnuser
 * @Date 2019/5/26
 *
 * Evaluate Next Item of Stream
 */
public class EvalNextProcess {

    /**
     * 求值方法
     * */
    private EvalFunction evalFunction;

    public EvalNextProcess(EvalFunction evalFunction) {
        this.evalFunction = evalFunction;
    }

    MyStream eval(){
        return evalFunction.apply();
    }
}
