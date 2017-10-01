#!/usr/bin/env bash

echo "start to commit"

git add .

git commit -m"daily commit"

echo "success commit"

echo "start push"

git push

echo "success push"
