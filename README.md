# playwright-java

### Summary
This repo contains an example end-to-end test framework using:
- Playwright - e2e test framework
- Testng - Test runner
- Java
- Maven
- GitHub actions

### Running tests

Running from the terminal:

`mvn test -Dbrowser=firefox`

#### Pipeline

Tests are automatically triggered using GitHub actions when:
- PR is raised/updated
- Changes are pushed to the main branch
- Tests are configured to run in parallel and on firefox, chromium and webkit
- Static code validation is executed

### Static code analysis

PMD source code analyzer is configured to run and produce a report on CI

It can also be run from the terminal:

`mvn pmd:check`

### Structure overview



