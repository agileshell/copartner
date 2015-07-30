#!/bin/bash
mvn install
echo "deploy success."
#scp -r copartner-manager/target/copartner-manager.war root@wjz:/root/copartner/jetty/apps/
scp -r copartner-manager/target/copartner-manager/* root@wjz:/root/dev/tc7-co-mgr/webapps/ROOT/
echo "sync success."
