News API
-----------------------------

This application provides a GET API based on the GNews API. It is created using Java 11, Spring Boot and Maven.

How to run the application
-----------------------------
There are multiple options to run the application such as:
- After importing the project in IntelliJ IDEA, click on the Run button.
- Or Run mvn spring-boot:run command 

API Documentation
-----------------------------
**GET /news?query=string&searchByTitle=boolean&size=integer**

You can search articles via a query and choose to fetch n amounts of articles.
By enabling the searchByTitle parameter, you can search for the articles 
in which the title contains the query that you're searching for.

> Searching by author functionality is not added as the response of GNews API doesn't contain author information.

> Caching is applied on the response of GNews API. It lasts for 1 minute so a second GET request sent within 1 minute 
> and with the same parameters will return the data from cache.

### Request 
In order to get news article, a GET request needs to be sent to "/news" endpoint. 
The endpoint has three query parameters as:
- **query (string):** 
  - It should be set to the keyword that is wanted to be searched. It is not a mandatory attribute. 
  - If nothing is set, then API returns articles without searching via keyword.

- **searchByTitle (boolean):** 
  - When it is set as true, it searches the query in the title of articles.
  - When it is set as false, then query is searched in the content of articles.
  - The default value is false so if no value is given the API will behave as the parameter is set to false.

- **size (integer):**
  - It should be set as the number of articles that is wanted to be fetched.
  - Its minimum value can be 1 and maximum value can be 10.
  - The default value is 10 if a value is not provided.

**Following is an example of request:**
> localhost:8080/news?query=Apple&size=9&searchByTitle=false

### Response
Response includes 8 fields:
- **numberOfArticles:** The total number of articles containing the query
- **title:** The title of the article
- **description:** The description of the article
- **content:** The content of the article
- **url:** The URL that belongs to the article
- **publishedAt:** The date that the article is published
- **source.name:** The name of the source
- **source.url:** The website of the source

**Following is an example response:**
```json
{
  "numberOfArticles": 153683,
  "data": [
    {
      "title": "How to use Apple Music Sing karaoke feature",
      "description": "This detailed guide with images covers how to use Apple Music Sing karaoke feature with iPhone in iOS 16.2.",
      "content": "After announcing its fun new karaoke feature last week, Apple Music Sing is now live with iOS 16.2. Follow along for a hands-on look at how to use Apple Music Sing karaoke including adjusting vocal volume, seeing the Duet view, finding supported song... [2670 chars]",
      "url": "https://9to5mac.com/2022/12/13/how-to-use-apple-music-sing-karaoke-feature/",
      "publishedAt": "2022-12-13T19:45:00",
      "source": {
        "name": "9to5Mac",
        "url": "https://9to5mac.com"
      }
    }
  ]
}
```

Error Codes:
-----------------------------
- 502: Bad Gateway - An error occurred when retrieving data from GNews API.
- 500: Internal Server Error: There is a problem in the application (For example: size parameter might have been sent as less than 1 or more than 10)
- 400: Bad Request - Request is invalid. (For example: Query parameters might have been provided wrongly).
