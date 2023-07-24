#!/bin/bash

set -xv

find -L ./bazel-testlogs | grep test.xml #| xargs ./upload_junit_xml.sh

set +xv