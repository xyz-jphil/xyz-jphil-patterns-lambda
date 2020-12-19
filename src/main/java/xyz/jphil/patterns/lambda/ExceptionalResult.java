package xyz.jphil.patterns.lambda;

import java.util.function.Consumer;

/**
 *
 * @author Ivan Velikanova <ivan@jphil.xyz>
 */
public class ExceptionalResult<R> {
    private final ExceptionProvider ep;
    private final R result;
    private final R defaultResult;

    public ExceptionalResult(ExceptionProvider ep, R result, R defaultResult) {
        this.ep = ep;
        this.result = result;
        this.defaultResult = defaultResult;
    }

    public ExceptionProvider getExceptionProvider() {
        return ep;
    }

    public R getResult() {
        return result;
    }
    
    public Chain<R> check(){
        return new Chain<R>(this);
    }
    
    public static class Chain<R> {
        private final ExceptionalResult<R> r;

        public Chain(ExceptionalResult<R> exceptionalResult) {
            this.r = exceptionalResult;
        }
        public boolean isValidResult(){
            if(r.ep==null || r.ep.getException()==null) { 
                if(r.result!=r.defaultResult){
                    return true;
                }
            }
            return false;
        }
        public boolean isDefaultResult(){
            return r.result==r.defaultResult;
        }
        public Chain<R> ifDefaultResult( Consumer<R> resultConsumer ){
            if(isDefaultResult()){
                resultConsumer.accept(r.result);
            }
            return this;
        }
        public Chain<R> ifValidResult( Consumer<R> resultConsumer ){
            if(isValidResult()){
                resultConsumer.accept(r.result);
            }
            return this;
        }
        public boolean isException(){
            return (r.ep!=null && r.ep.getException()!=null);
        }
        public Chain<R> ifException( Consumer<Exception> exceptionConsumer ){
            if(isException()) { 
                exceptionConsumer.accept(r.ep.getException());
            }
            return this;
        }
    }
}
