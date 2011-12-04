package net.timico.messaging;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class App
{
    public static String brokerURL = "tcp://localhost:61616";

    public static void main( String[] args )
    {
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);

        Consumer consumer =  new Consumer(factory,"test",false);

        Consumer topicConsumer =  new Consumer(factory,"Simple.Test.Topic1",true);

    }
}
