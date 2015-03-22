#! /bin/bash

mvn -X package -Djavax.net.ssl.trustStore=/Library/Java/JavaVirtualMachines/jdk1.8.0_40.jdk/Contents/Home/jre/lib/security/cacerts 

