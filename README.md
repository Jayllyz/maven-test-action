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
  pull_request:

jobs:
  build-and-test:
    runs-on: ubuntu-latest
    container: maven:3.9-eclipse-temurin-21

    steps:
      - uses: actions/checkout@v4

      - name: Run tests with Maven
        run: mvn -B test --file pom.xml

      - name: Upload test results
        uses: actions/upload-artifact@v4
        with:
          name: test-results
          path: ${{ github.workspace }}/target

      # Temp fix for Test Reporter
      # https://github.com/dorny/test-reporter/issues/226
      - name: Set safe directory for git 
        run: git config --global --add safe.directory /__w/maven-test-action/maven-test-action

      - name: Report Test Reports
        uses: dorny/test-reporter@v1
        if: always()
        with:
          name: Test Reports
          path: ${{ github.workspace }}/target/surefire-reports/*.xml
          reporter: java-junit
          fail-on-error: true
...
```
