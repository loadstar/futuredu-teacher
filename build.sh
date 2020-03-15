#!/usr/bin/env bash

BD_BIN="${BASH_SOURCE-$0}"
BD_BIN="$(dirname "${BD_BIN}")"
BD_BIN="$(cd "${BD_BIN}"; pwd)"
BD_DIR="${BD_BIN}"
OUT_DIR="$BD_DIR/output"
TG_DIR="$BD_DIR/target"
#go in build dir
cd $BD_DIR
#build
echo "Starting building"

PROFILE=prod
if [ ! -z "$1" ] ; then
   PROFILE=$1
fi

mvn clean package -P$PROFILE -DskipTests -U;
mkdir -p $OUT_DIR/;
rm -rf $OUT_DIR/*;
cp $TG_DIR/futuredu-teacher.jar $OUT_DIR/futuredu-teacher.jar;
cp -r $TG_DIR/futuredu-teacher/* $OUT_DIR/
cd $OUT_DIR/


if [ ! "$(ls -v  $OUT_DIR)"  ] ; then
  echo "Building error"
  exit 1
fi
echo "Building finished"
