#!/bin/bash
# Expected to be executed within a buildkite pipeline step

echo
echo "Pushing updated images..."

bazel query "//..." | grep ":push-image\b" > targetsToRun.txt

targets=`cat targetsToRun.txt`

for line in $targets
do
  echo "Pushing ${line}"
  bazel run ${line}
done