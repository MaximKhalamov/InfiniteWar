#!/bin/bash

echo "Current path is $PWD"

./gradlew forge:build

# Load variables
if [ -f ./scripts/env.sh ]; then
  source ./scripts/env.sh
else
  echo "File env.sh not found. Create it using template env.template.sh"
  exit 1
fi

JAR_PATH=$(find ./forge/build/libs/ -name '*.jar' | grep -vE '(-dev-shadow|-sources)\.jar$' | tr '\n' ' ')
echo "$JAR_PATH"
cp $JAR_PATH $MOD_DEST_PATH

$MULTIMC_PATH --launch $INSTANCE_NAME

echo "Finished"
