package net.timico.messaging;

import javax.jms.*;

public class Producer
{
    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageProducer producer;

    public Producer(ConnectionFactory factory, String queueOrTopicName, boolean isTopic) throws JMSException
    {
        this.factory = factory;
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        if(isTopic) {

            Topic topic = session.createTopic(queueOrTopicName);

            producer = session.createProducer(topic);
            producer.setTimeToLive(10000);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        } else {

            Destination destination = session.createQueue(queueOrTopicName);
            producer = session.createProducer(destination);

        }
    }

    public void run() throws JMSException
    {
        for (int i = 0; i < 100; i++)
        {
            System.out.println("Creating Message " + i);
            Message message = session.createTextMessage("Hello World Message number "+i);
            producer.send(message);
        }
    }

    public void close() throws JMSException
    {
        if (connection != null)
        {
            connection.close();
        }
    }

}
