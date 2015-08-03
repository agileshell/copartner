#!/bin/bash
mvn clean && mvn install
echo "deploy success."
#rsync -a8P --delete copartner-manager/target/copartner-manager.war root@wjz:/root/copartner/jetty/apps/
rsync -a8P --delete copartner-manager/target/copartner-manager/* root@wjz:/root/dev/tc7-co-mgr/webapps/ROOT/
echo "sync success."
