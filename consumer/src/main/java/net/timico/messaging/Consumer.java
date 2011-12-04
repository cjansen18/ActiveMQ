package net.timico.messaging;

import javax.jms.*;

public class Consumer implements MessageListener {

    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;

    public Consumer(ConnectionFactory factory, String queueOrTopicName, boolean isTopic) {

        this.factory = factory;

        try
        {
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            if(isTopic) {
                Topic topic = session.createTopic(queueOrTopicName);
                consumer = session.createConsumer(topic);
            } else {
                Destination destination = session.createQueue(queueOrTopicName);
                consumer = session.createConsumer(destination);
            }

            consumer.setMessageListener(this);
        }
        catch (Exception e)
        {
            System.out.println("Caught:" + e);
            e.printStackTrace();
        }

    }

    public void onMessage(Message message)
    {
        try
        {
            if (message instanceof TextMessage)
            {
                TextMessage txtMessage = (TextMessage)message;
                System.out.println("Message received: " + txtMessage.getText());
            }
            else
            {
                System.out.println("Invalid message received.");
            }
        }
        catch (JMSException e)
        {
            System.out.println("Caught:" + e);
            e.printStackTrace();
        }
    }
}
