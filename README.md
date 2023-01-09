# Xox REST Test

Unit REST tests for the Xox REST API backend.

## About

[Xox](https://github.com/m5c/XoxInternals) is a test project for service
RESTification.  
RESTification success can be measured by comparing the actual API behaviour to the interface
specification.

* This repository hosts unit tests for a systematic evaluation of the REST API correctness.
* Every Resource+CRUD combination is covered by a dedicated test.
* Running this test bundle tells you the correctness ratio of a given Xox REST implementation.

> Note: The tests should not be run sequentially without intermediate full reset of the tested
> backend. See [Testing section](#testing) for further details.

## Usage

* Clone this repo
* Invoke the unit tests:
    * Manually, using the ```test.sh``` script: Tests a single backend of your choice.
    * Automated, using the ```RestifyAnalyzier``` script: Batched testing of multiple submissions.

> Note: This project requires access to beans
> of [```XoxInternals```](https://github.com/m5c/XoxInternals) as a maven dependency.
> Satisfy them by a preliminary build
> of [```XoxInternals```](https://github.com/m5c/XoxInternals),
> with ```mvn clean install```, to inject the XoxInternals dependency into your local maven
> repository.

## Implementation Details

* There is one dedicated (```@Test``` annotated) unit test per REST endpoint (resource + CRUD
  operation)
* All queries are realized with [UniRest](http://kong.github.io/unirest-java/)
* Server replies are verified for containment of the status code "200/OK" in the header.
* Queries modifying state (*Put* / *Post* / *Delete*)...
    * Do not operate on default sample game but on random new game-id to avoid collisions /
      blemished state on test re-run.
    * Are followed by a subsequent *Get* request to verify the state change was correctly applied.
* Queries containing a body payload (*Put* / *Post*) specify the body encoding with a header
  field: ```Content-Type: application/json```.

## Testing

It is possible to test the correctness of the unit tests,
using [a reference implementation Xox backend](https://github.com/m5c/XoxStudyManuallyRestified).

* Clone the reference implementation.
* Run the ```test.sh``` script.
    * The script performs a full restart of the backend before every test. This is needed to reach
      full independency of individual tests (tests may alter backend state, leaving it corrupted).
    * The script tests both testing modes, with read validation of state changes (Create, Update,
      Delete calls) and without.
* Verify that all tests pass.

## Contact / Pull Requests

* Author: Maximilian Schiedermeier ![email](markdown/email.png)
* Github: [m5c](https://github.com/m5c)
* Webpage: https://www.cs.mcgill.ca/~mschie3
* License: [MIT](https://opensource.org/licenses/MIT)
