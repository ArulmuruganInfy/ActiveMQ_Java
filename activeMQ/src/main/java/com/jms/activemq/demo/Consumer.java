package com.jms.activemq.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer implements Runnable{
    public void run() {
        Connection connection = null;
        Session session = null;
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            connection = factory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination queue = session.createQueue("TestQueue");

            MessageConsumer consumer = session.createConsumer(queue);
            Message message = consumer.receive(1000);
            if(message instanceof TextMessage) {
                TextMessage msg = (TextMessage)message;
                String msge = msg.getText();
                System.out.println(msge);
            }

        }catch(Exception e) {
            System.out.println("exception "+e);
        }
        finally {
            if(connection != null) {
                try{
                    connection.close();
                }catch(Exception conExp) {
                    System.out.println("Unable to close the connection, "+conExp);
                }
            }
            if(session != null) {
                try{
                    session.close();
                }catch(Exception sessExp) {
                    System.out.println("Unable to close the session, "+sessExp);
                }
            }
        }
    }
}
