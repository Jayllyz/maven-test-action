# maven-test-action

![GitHub last commit (by committer)](https://img.shields.io/github/last-commit/jayllyz/setupWSL)
[![Maven CI](https://github.com/Jayllyz/maven-test-action/actions/workflows/maven.yml/badge.svg)](https://github.com/Jayllyz/maven-test-action/actions/workflows/maven.yml)

This action runs `mvn test` on a maven project.
I've seen a lot of actions using "setup-java" and then running maven, but I wanted something faster so I used the `maven` docker image to run the tests.

## Usage

```yaml
---
name: Maven CI

on:
  push:
    branches:
      - main
  pull_request:

permissions:
  contents: read
   
jobs:
  build-and-test:
    runs-on: ubuntu-latest
    container: maven:3.9-eclipse-temurin-22-alpine

    steps:
      - uses: actions/checkout@v4

      - name: Run tests with Maven
        run: mvn -B test --file pom.xml -Dstyle.color=always
...
```
