# playwright-java

## Summary
This repo contains an example end-to-end test framework using:
- Playwright - e2e test framework
- Testng - Test runner
- Java
- Maven
- GitHub actions

## Running tests 

### Locally

Running from the terminal:

```shell
# Set Environment variable:  
export ENVIRONMENT=local 
# Runs tests with firefox
mvn test -Dbrowser=firefox
```

If you want to run on headless mode, modify the `.local.env` file, so that `HEADLESS` is set to `true`

Screenshots are captured to `target/screenshots` if any tests fail.

### Pipeline

Tests are automatically triggered using GitHub actions when:
- PR is raised/updated
- Changes are pushed to the main branch
- Tests are configured to run in parallel and on firefox, chromium and webkit
- Static code validation is executed

## Static code analysis

PMD source code analyzer is configured to run and produce a report on CI

It can also be run from the terminal:

`mvn pmd:check`

## Application under test  
Tests are written against the [Restful-booker-platform](https://github.com/mwinteringham/restful-booker-platform), from [Mark Winteringham](https://github.com/mwinteringham)

## Structure overview

### Test Scenarios


