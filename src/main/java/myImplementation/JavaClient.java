package myImplementation;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import tutorial.Calculator;
import tutorial.InvalidOperation;
import tutorial.Operation;
import tutorial.Work;

public class JavaClient {

    public static void main(String[] args) {
        TTransport transport = new TSocket("localhost", 9090);
        TProtocol protocol = new TBinaryProtocol(transport);
        Calculator.Client client = new Calculator.Client(protocol);
        new Calculator.AsyncClient()
        try {
            transport.open();
            process(client);
        } catch (TException e) {
            e.printStackTrace();
        }

    }

    private static void process(Calculator.Client client) throws TException {

        client.ping();

        int value;
        System.out.println(String.format("2+3=%s", client.add(2, 3)));

        try {
            value = client.calculate(1, new Work(5, 0, Operation.DIVIDE));
            System.out.println("You can divide by 0 now: 5/0="+value);
        } catch (InvalidOperation invalidOperation) {
            System.out.println(String.format("Invalid OP:%s,Reason:%s",invalidOperation.whatOp, invalidOperation.why));
        } catch (TException e) {
            e.printStackTrace();
        }
        client.zip();


    }
}
