package examples;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import shared.SharedStruct;
import tutorial.Calculator;
import tutorial.Work;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalculatorHandlerAsync implements Calculator.AsyncIface {
    Calculator.Iface realHandler;
    ExecutorService pool;

    public CalculatorHandlerAsync(Calculator.Iface realHandler) {
        this.realHandler = realHandler;
        pool = Executors.newFixedThreadPool(1);

    }

    public CalculatorHandlerAsync() {
        realHandler = new CalculatorHandler();
        pool = Executors.newFixedThreadPool(1);

    }

    public Calculator.Iface getRealHandler() {
        return realHandler;
    }


    public void setRealHandler(Calculator.Iface realHandler) {
        this.realHandler = realHandler;
    }

    @Override
    public void ping(AsyncMethodCallback<Void> resultHandler) throws TException {
        pool.execute(() -> {
            try {
                realHandler.ping();
                resultHandler.onComplete(null);
            } catch (TException e) {
                resultHandler.onError(e);

            }
        });
    }

    @Override
    public void add(int num1, int num2, AsyncMethodCallback<Integer> resultHandler) throws TException {
        pool.execute(( ) -> {
            try {
                resultHandler.onComplete(realHandler.add(num1, num2));
            } catch (TException e) {
                resultHandler.onError(e);

            }
        });
    }

    @Override
    public void calculate(int logid, Work w, AsyncMethodCallback<Integer> resultHandler) throws TException {
        pool.execute(() -> {
            try {
                resultHandler.onComplete(realHandler.calculate(logid, w));
            } catch (TException e) {
                resultHandler.onError(e);

            }
        });
    }

    @Override
    public void zip(AsyncMethodCallback<Void> resultHandler) throws TException {
        pool.execute(() -> {
            try {
                realHandler.zip();
                resultHandler.onComplete(null);
            } catch (TException e) {
                resultHandler.onError(e);

            }
        });
    }

    @Override
    public void getStruct(int key, AsyncMethodCallback<SharedStruct> resultHandler) throws TException {
        pool.execute(() -> {
            try {

                resultHandler.onComplete(realHandler.getStruct(key));
            } catch (TException e) {
                resultHandler.onError(e);

            }
        });
    }
}
