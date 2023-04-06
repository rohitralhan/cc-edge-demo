#!/bin/sh

cat edge-demo-amq.yml | oc apply -f -
sleep 20
cat edge-demo-kafka.yml | oc apply -f -
sleep 20
cat edge-demo-apps.yml | oc apply -f -