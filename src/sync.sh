#!/bin/bash
rsync -a8P --delete copartner-manager/src/main/webapp/WEB-INF/templates/* root@wjz:~/dev/tc7-co-mgr/webapps/ROOT/WEB-INF/templates/
rsync -a8P --delete copartner-manager/src/main/webapp/WEB-INF/assets/* root@wjz:~/dev/tc7-co-mgr/webapps/ROOT/WEB-INF/assets/
echo "sync success."
