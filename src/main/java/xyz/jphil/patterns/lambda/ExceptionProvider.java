package xyz.jphil.patterns.lambda;

import java.util.function.Consumer;

/**
 *
 * @author Ivan Velikanova ivan@jphil.xyz
 */
public class ExceptionProvider  implements Consumer<Exception> {
    public static ExceptionProvider NewExceptionProvider(){
        return new ExceptionProvider();
    }
    
    private Exception exception = null; 
    @Override public void accept(Exception t) {
        if(exception==null)
            exception = t;
        else 
            exception.addSuppressed(t);
    }
    public Exception getException() {
        return exception;
    }
}
