#!/bin/bash
mvn clean && mvn install
echo "build success."
option="${1}"
echo "deploy ${option}"
case ${option} in
   -api)
		rsync -a8P --delete copartner-api/target/copartner-api/* root@copartner:/root/app/api/webapps/ROOT/
		echo "sync api success."
      ;;
   -mgr)
		rsync -a8P --delete copartner-manager/target/copartner-manager/* root@copartner:/root/app/mgr/webapps/ROOT/
		echo "sync mgr success."
      ;;
    -mobile)
                rsync -a8P --delete copartner-mobile/target/copartner-mobile-1.0-SNAPSHOT/* root@copartner:/root/app/mobile/webapps/ROOT/
                echo "sync mobile success."
      ;;
   -all) 
		rsync -a8P --delete copartner-api/target/copartner-api/* root@copartner:/root/app/api/webapps/ROOT/
		echo "sync api success."
		rsync -a8P --delete copartner-manager/target/copartner-manager/* root@copartner:/root/app/mgr/webapps/ROOT/
		echo "sync mgr success."
      ;;
esac


