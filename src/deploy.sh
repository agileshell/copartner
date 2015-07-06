#!/bin/bash
mvn install
echo "deploy success."
scp copartner-manager/target/copartner-manager.war root@wjz:/root/copartner/jetty/apps/
echo "sync success."
