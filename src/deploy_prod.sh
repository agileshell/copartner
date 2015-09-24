#!/bin/bash
mvn clean && mvn install
echo "build success."
rsync -a8P --delete copartner-manager/target/copartner-manager/* root@copartner:/root/app/mgr/webapps/ROOT/
echo "sync mgr success."
#rsync -a8P --delete copartner-api/target/copartner-api/* root@copartner:/root/app/api/webapps/ROOT/
echo "sync api success."


