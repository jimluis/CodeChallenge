# CodeChallenge

Overview:

Assumption: The Producer and Consumer will be deployed in a Linux box. 

- Producer
    Simulates raw data provided from an eye tracker, and sends messages to the consumer (at 60 to ~1000 messages per second, configurable value),
    through the TransportManager
    
- TransportManager:
    This is an API that wraps the transport configuration, and the RabbitMQ logic to send and receive messages.
    TransportManager exposes methods to send messages, to initialize configurations, and allows the implementation of the logic to process 
    the messages after they are dequeued.
    If a different messaging broker/library such as Kafka or any other needs to be implemented, the Producer and the Consumer will not be required
    any code changes, only configuration changes will be needed.
    
- Consumer:
    Receives and process messages by writing them to a text file (eyeTrackerData.txt) 
    
- ValueObjects
    Contains the Message object    
    
-  The transport broker used in the project is RabbitMQ  


General instructions:

1. Install RabbitMQ
2. Start the RabbitMQ server
3. Deploy the Producer and Consumer in a Linux box
4. Run the Producer and Consumer



How to install RabbitMQ in a Linux box:

Linux Steps:
1. install homebrew. If it is already installed, run: brew update 
   if not run: /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
    
2. Once homebrew is installed, install rabbitMQ. Run: brew install rabbitmq

3. To start rabbitmq and restart at login run:
   brew services start rabbitmq
   Or, if you don't want/need a background service you can just run:
   rabbitmq-server

4. in the browser go to: http://localhost:15672/
   RabbitMQ server should be runnig

5. User: guest, Password: guest


How to run the Producer and Consumer:

After deploying Producer and Consumer in the Linux box: 

To start the Producer go to /Producer/bin and run ./Producer.sh start
To stop the Producer go to /Producer/bin and run ./Producer.sh stop
The log file will be generated under /Producer/logs/producer.log
Property files will be under /Producer/etc

To start the Consumer go to /Consumer/bin and run ./Consumer.sh start
To stop the Consumer go to /Consumer/bin and run ./Consumer.sh stop
The log file will be generated under /Consumer/logs/consumer.log
The output file will be generated under /Consumer/eyeTrackerData.txt
Property files will be under /Consumer/etc  
  
After starting the Producer, it will be generating ~1000 per second. The number of messages per second
can be modified in the /Producer/etc/Producer.properties when changing the value of the property period=1,
after changing that value, bounce the application.

After starting the Consumer, it will be writing the messages dequeued into the /Consumer/eyeTrackerData.txt

The queue information such as depth and queue performance can be monitored in the RabbitMQ console http://localhost:15672/
the queue name is EYETRACKER.MESSAGES


References:

To install homebrew:
https://github.com/homebrew/install

Rabbitmq install
https://www.rabbitmq.com/install-homebrew.html
