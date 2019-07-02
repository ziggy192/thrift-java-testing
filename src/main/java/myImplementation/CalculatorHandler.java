package myImplementation;

import org.apache.thrift.TException;
import shared.SharedStruct;
import tutorial.Calculator;
import tutorial.InvalidOperation;
import tutorial.Operation;
import tutorial.Work;

import java.util.HashMap;

public class CalculatorHandler implements Calculator.Iface {

    HashMap<Integer, SharedStruct> log = new HashMap<>();
    @Override
    public void ping() throws TException {
        System.out.println("Ping()");
    }

    @Override
    public int add(int num1, int num2) throws TException {
        return num1 + num2;
    }

    @Override
    public int calculate(int logid, Work w) throws InvalidOperation, TException {
        int value = 0;
        int num1 = w.getNum1();
        int num2 = w.getNum2();
        switch (w.getOp()) {
            case ADD:
                value = num1 + num2;
                break;
            case DIVIDE:
                if (num2 != 0) {
                    value = num1 / num2;
                } else {
                    
                    throw new InvalidOperation(Operation.DIVIDE.getValue(), "You cannot devide by 0");
                }

                break;
            case MULTIPLY:
                value = num1 * num2;
                break;
            case SUBTRACT:
                value = num1 - num2;
                break;
        }

        return value;
    }

    @Override
    public void zip() throws TException {

    }

    @Override
    public SharedStruct getStruct(int key) throws TException {
        return log.get(key);
    }
}
