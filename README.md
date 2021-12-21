<h2>API that returns the file count and the total number of lines, grouped by file extension, of a given public GitHub repository.</h2>

Return example:

```json
[
  {
    "extension": "java",
    "count": 4, 
    "lines": 360
  },
  {
    "extension": "xml",
    "count": 2, 
    "lines": 45
  }
]
```

Specifications:

* Used Java 8 or newer;
* Data is retrieved from the GitHub website using web scraping techniques.
* API supports thousands of simultaneous requests;
* Handling of timeout errors;
* Implementation on SOLID principles;
* Creation of automated tests using Junit;
* [Deploy to Heroku] (https://data-scraping-api.herokuapp.com);
* Documentation available after project execution (http://localhost:8080/swagger-ui/index.html).

To run the project in the terminal, type the following command:

```shell script
mvn spring-boot:run 
```

To run the developed test suite, just run the following command:

```shell script
mvn clean test
```

After executing the above command, just open the following address and view the project execution:

```
http://localhost:8080/api/scraping/github
```

It is necessary to pass the request parameters: user and the name of the repository from GitHub.