#!/bin/bash
# Expected to be executed within a buildkite pipeline step

echo
echo "Uploading unit test xml files..."

for n in $(find -L ./bazel-testlogs | grep test.xml)
do
  echo "   ${n}"
  curl -X POST \
    -H "Authorization: Token token=cDvYP4uqcSLPgAAFqsnUeDGo" \
    -F "format=junit" \
    -F "data=@${n}" \
    -F "run_env[CI]=buildkite" \
    -F "run_env[key]=$BUILDKITE_BUILD_ID" \
    -F "run_env[number]=$BUILDKITE_BUILD_NUMBER" \
    -F "run_env[job_id]=$BUILDKITE_JOB_ID" \
    -F "run_env[branch]=$BUILDKITE_BRANCH" \
    -F "run_env[commit_sha]=$BUILDKITE_COMMIT" \
    -F "run_env[message]=$BUILDKITE_MESSAGE" \
    -F "run_env[url]=$BUILDKITE_BUILD_URL" \
    https://analytics-api.buildkite.com/v1/uploads
done