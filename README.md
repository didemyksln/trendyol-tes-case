# Trendyol Selenium Test


## Requirements

 - Java 8
 - docker
 - docker-compose

## How to run

```bash
$ docker-compose up -d --scale chrome=3 --scale firefox=3 --no-recreate
# access to selenium-grid http://localhost:4444

$ ./mvnw clean test

# access reports /target/reports/ExtentReportResults.html
# access response /target/case1/case-1-response-code.csv
```