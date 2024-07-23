# playwright-java

### Summary
This repo contains an example structure using:
- Playwright - e2e test framework
- Testng - Test runner
- Java
- Maven
- GitHub actions

### Running tests

Running from the terminal:

`mvn clean test`

You can also specify which browser to use by running:

`mvn test -Dbrowser=firefox`


#### Pipeline

Tests are automatically triggered using GitHub actions when:
- PR is raised/updated
- Changes are pushed to the main branch
- Tests are configured to run in parallel and on firefox, chromium and webkit

### Structure overview



