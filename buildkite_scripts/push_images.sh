#!/bin/bash
# Expected to be executed within a buildkite pipeline step

echo
echo "Pushing updated images..."

aws ecr get-login-password --region us-west-2 | crane auth login 091232730366.dkr.ecr.us-west-2.amazonaws.com -u AWS --password-stdin

bazel query "//..." | grep ":push-image$" > targetsToRun.txt

targets=`cat targetsToRun.txt`

for line in $targets
do
  echo "Pushing ${line}"
  bazel run ${line}
done