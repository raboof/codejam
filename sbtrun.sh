#!/bin/sh

sbt "run $1 `basename $1 .in`.out"
