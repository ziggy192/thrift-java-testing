package myImplementation;

import examples.CalculatorHandlerAsync;
import org.apache.thrift.TBaseProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import tutorial.Calculator;

public class JavaServer {
    public static void main(String[] args) {
        try {

//            TProcessor processor = new Calculator.Processor<>(new CalculatorHandler());
            TProcessor processor  = new Calculator.AsyncProcessor<>(new CalculatorHandlerAsync());


            TServerTransport tServerSocket = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TServer.Args(tServerSocket).processor(processor));

            System.out.println("waiting for client..");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
