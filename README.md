# Xox REST Test

Unit Tests for the Xox REST API

## About

[Xox](https://github.com/kartoffelquadrat/XoxInternals) is a test project for service RESTification.  
RESTification success can be measured by comparing the actual API behaviour to the interface specification.

 * This repository hosts unit tests for a systematic evaluation of the REST API correctness.
 * Every Resource+CRUD combination is covered by a dedicated test.
 * Running this test bundle tells you the correctness ratio of a given Xox REST implementation.

## Usage

 * Clone this repo
 * Open it with IntelliJ
 * Run the tests, verify the success rate
   * Maven command: ```mvn clean test```
   * IntelliJ test menu:  
![rate](markdown/rate.png)

## MISC

 * There is one dedicated (```@Test``` annotated) unit test per REST endpoint (resource + CRUD operation)
 * All queries are realized with [UniRest](http://kong.github.io/unirest-java/)
 * Server replies are verified for containment of the status code "200/OK" in the header.
 * Queries modifying state (*Put* / *Post* / *Delete*)...
   * Do not operate on default sample game but on random new game-id to avoid collisions / blemished state on test re-run.
   * Are followed by a subsequent *Get* request to verify the state change was correctly applied.
 * Queries containing a body payload (*Put* / *Post*) specify the body encoding with a header field: ```Content-Type: application/json```.
 * An initial test version, written in BASH [is archived here](xox-units).
 
## Contact / Pull Requests

 * Author: Maximilian Schiedermeier ![email](markdown/email.png)
 * Github: Kartoffelquadrat
 * Webpage: https://www.cs.mcgill.ca/~mschie3
 * License: [MIT](https://opensource.org/licenses/MIT)
