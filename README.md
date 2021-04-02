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
# access response /target/cases/boutique-image-load-time-response-code.csv
# access response /target/cases/boutique-load-response-code.csv
```

 - CSV Report of boutique links and their response code  placed in /target/cases/boutique-load-response-code.csv
 - CSV Report of boutique image links, their response time and response code placed in /target/cases/boutique-load-response-code.csv
 - Login test case results placed in /target/reports/ExtentReportResults.html as an html file.