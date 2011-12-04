package net.timico.messaging;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.ConnectionFactory;

public class App
{
    public static String brokerURL = "tcp://localhost:61616";

    public static void main( String[] args ) throws Exception
    {
        // setup the connection to ActiveMQ
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);

        //Queue
        Producer producer = new Producer(factory, "test",false);
        producer.run();
        producer.close();

        //Topic
        producer = new Producer(factory, "Simple.Test.Topic1",true);
        producer.run();
        producer.close();
    }
}
