# Expenses Register 3.0

## Testing

#### In order to run all tests and generate reports for all modules test coverage run the following command:

#### In order to run all tests and generate coverage reports run the following command:

```./gradlew build connectedAndroidTest jacocoTestReport```

*note: The module 'app' has instrumented tests, so we will end up with two separate reports, one for
the instrumented tests and another for the unit tests.*

Test coverage reports locations:  
```app/build/reports/coverage/androidTest/debug/connected/index.html```  
```app/build/jacoco/jacocoHtml/index.html```  
```viewmodel/build/jacoco/jacocoHtml/index.html```  
```data/build/jacoco/jacocoHtml/index.html```  
```domain/build/reports/jacoco/test/html/index.html```  
```entity/build/reports/jacoco/test/html/index.html```  