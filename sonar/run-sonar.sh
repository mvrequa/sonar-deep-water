#! /bin/bash

CLASSPATH="$(< myfile.txt)"
CLASSPATH=$CLASSPATH:/Users/michael/git-repos/sonar-deep-water/sonar/target/sonar-1.0-SNAPSHOT.jar
echo $CLASSPATH
java -cp $CLASSPATH org.requa.app.App

#java -cp target/sonar-1.0-SNAPSHOT.jar:/Users/michael/.m2/repository/com/amazonaws/aws-java-sdk-core/1.9.6/aws-java-sdk-core-1.9.6.jar:/Users/michael/.m2/repository/org/apache/geronimo/specs/geronimo-jms_1.1_spec/1.1.1/geronimo-jms_1.1_spec-1.1.1.jar:/Users/michael/.m2/repository/com/amazonaws/amazon-sqs-java-messaging-lib/1.0.0/amazon-sqs-java-messaging-lib-1.0.0.jar:/Users/michael/.m2/repository/com/amazonaws/aws-java-sdk-sqs/1.9.6/aws-java-sdk-sqs-1.9.6.jar:/Users/michael/.m2/repository/commons-logging//commons-logging/1.1.3/commons-logging-1.1.3.jar:/Users/michael/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.3.2/jackson-databind-2.3.2.jar org.requa.app.App
