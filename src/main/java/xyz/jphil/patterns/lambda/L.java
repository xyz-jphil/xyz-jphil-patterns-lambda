/*
 * Copyright 2017 Ivan Velikanova ivan@jphil.xyz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.jphil.patterns.lambda;

import java.util.function.Consumer;

/**
 *
 * @author Ivan Velikanova ivan@jphil.xyz
 */
public class L {
    public static int parseIntifError(SupplierWithException<String> input, int valueOnError, final ExceptionHandler lm){
        int returnValue = valueOnError;
        if(input==null)return valueOnError;
        String val = "<notset>";
        try{
            val = input.get();
            if(val==null)return valueOnError;
            return Integer.parseInt(val);
        }catch(Exception a){
            if(lm!=null)lm.exception(a,val);
        }
        return returnValue;
    }
    public static int parseIntifError(String input, int valueOnError, final ExceptionHandler lm){
        if(input==null)return valueOnError;
        return parseIntifError(()->input, valueOnError, lm);
    }
    
    public static long parseLongifError(String input, long valueOnError,ExceptionHandler lm){
        long returnValue = valueOnError;
        if(input==null)return valueOnError;
        try{
            return Long.parseLong(input);
        }catch(Exception a){
            if(lm!=null)lm.exception(a,input);
        }
        return returnValue;
    }
    
    public interface Tryable<E extends Exception>{
        public void tryable()throws E;
    }
    
    public static <E extends Exception> void TrySilently(Tryable<E> d){
        try{
            d.tryable();
        }catch(Exception s){
            
        }
    }
    
    public static <E extends Exception> void TryPrintOnFail(Tryable<E> d){
        try{
            d.tryable();
        }catch(Exception s){
            s.printStackTrace();
        }
    }
    
    public static <O> O ifError(SupplierWithException<O> s,
            O valueOnError, Consumer<Exception> errorHandler){
        if(errorHandler==null)errorHandler = D;
        O returnValue = valueOnError;
        try{
            returnValue = s.get();
        }catch(Exception a){
            errorHandler.accept(a);
        }return returnValue;
    }
    
    public static <O> O ifNull(){
        return null;
    }
    
    public static final Consumer<Exception> D = (e)->{};
    
    public static final Consumer<Exception> ERR = (e)->{
        e.printStackTrace(System.err);
    };
    
    
    
    public static final Consumer<Exception> OUT = (e)->{
        e.printStackTrace(System.out);
    };
    
    public static SupplierWithException<Long> parseLong(String input){
        return ()->{
            return Long.parseLong(input);
        };        
    }

    public static SupplierWithException<Integer> parseInt(String input){
        return ()->{
            return Integer.parseInt(input);
        };        
    }
    
    public static int parseInt(SupplierWithException<String>t,
            int defaultValue,Consumer<Exception> D){
        try{
            return Integer.parseInt(t.get());
        }catch(Exception a){
            if(D!=null)D.accept(a);
        }return defaultValue;
    }
    
    public static long parseLong(SupplierWithException<String>t,
            long defaultValue,Consumer<Exception> D){
        try{
            return Long.parseLong(t.get());
        }catch(Exception a){
            if(D!=null)D.accept(a);
        }return defaultValue;
    }
    
    public static ExceptionalResult<Long> parseLong(SupplierWithException<String>t,
            long defaultValue){
        ExceptionProvider expd = ExceptionProvider.NewExceptionProvider();
        try{
            return new ExceptionalResult<>(expd,Long.parseLong(t.get()),defaultValue);
        }catch(Exception a){
            if(expd!=null)expd.accept(a);
        }return new ExceptionalResult<>(expd,defaultValue,defaultValue);
    }
    
    public static double parseDouble(SupplierWithException<String>t,
            double defaultValue,Consumer<Exception> D){
        try{
            return Double.parseDouble(t.get());
        }catch(Exception a){
            if(D!=null)D.accept(a);
        }return defaultValue;
    }
    
    public static Boolean parseBoolean(SupplierWithException<String>t,
            Consumer<Exception> D){
        return parseBoolean(t, null, D);
    }
    
    public static Boolean parseBoolean(SupplierWithException<String>t, 
            Boolean defaultValue, Consumer<Exception> D){
        try{
            return Boolean.parseBoolean(t.get());
        }catch(Exception a){
            if(D!=null)D.accept(a);
        }return defaultValue;
    }
    
    
    
}
