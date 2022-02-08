{
    "createdAt": "2020-10-01T14:53:41.964Z",
    "kind": "ARC#ProjectExport",
    "projects": [
        {
            "created": 1592934675301,
            "description": "A Unit test like workflow, to test all access methods defined in the BookStore's RIF.",
            "key": "0c6612ab-3abe-4aac-9564-838d6570152e",
            "kind": "ARC#ProjectData",
            "name": "bookstore",
            "order": 0,
            "requests": [
                "c4587621-417a-4d24-8a11-b1c03d15c17b",
                "40d63157-86dc-42c0-9ea0-bbf88ea52fff",
                "f5cf1a11-d19a-404d-b2bd-0da5d1618000",
                "c095dc14-9d92-46a1-a875-18411e0dcc6f",
                "ecbb9aec-d38f-4770-be60-58a3f29b7d2a",
                "d7837bf1-051a-46a1-9b69-5ade25e28c6d",
                "b674f9c2-0e00-4e24-a483-e634806c4650",
                "b9e54df6-849e-4752-bfb3-2c1bceaa2c2e",
                "c8ac2f88-33d1-4331-9cdd-1d37b3a992dc",
                "610e7762-a70a-4946-bfe1-51c4e8977276",
                "bce59b28-099a-4b8f-a71e-3ad3324b08c5",
                "6b1cdb88-8a2f-4b37-8120-4a9e562da002"
            ],
            "updated": 1592934675301
        }
    ],
    "requests": [
        {
            "created": 1592916170337,
            "description": "# Blue [4]\n\n * Retrieve full stock details for all Books available at the Lyon book store.",
            "headers": "",
            "key": "6b1cdb88-8a2f-4b37-8120-4a9e562da002",
            "kind": "ARC#RequestData",
            "method": "GET",
            "name": "Get /bookstore/stocks/Lyon/",
            "payload": "",
            "projects": [
                "0c6612ab-3abe-4aac-9564-838d6570152e"
            ],
            "type": "saved",
            "updated": 1601563940968,
            "url": "http://127.0.0.1:8080/bookstore/stocks/Lyon/"
        },
        {
            "created": 1592916170337,
            "description": "# Red [3]\n\n * Retrieve list of all Bookstore locations.",
            "headers": "",
            "key": "bce59b28-099a-4b8f-a71e-3ad3324b08c5",
            "kind": "ARC#RequestData",
            "method": "GET",
            "name": "Get /bookstore/stocks",
            "payload": "",
            "projects": [
                "0c6612ab-3abe-4aac-9564-838d6570152e"
            ],
            "type": "saved",
            "updated": 1601560629100,
            "url": "http://127.0.0.1:8080/bookstore/stocks"
        },
        {
            "created": 1592916170337,
            "description": "# Blue [2]\n\n * Update the amount of the Harry Potter book in stock at the Lyon Bookstore to 42 copies.\n ",
            "headers": "Content-Type: application/json",
            "key": "610e7762-a70a-4946-bfe1-51c4e8977276",
            "kind": "ARC#RequestData",
            "method": "POST",
            "name": "Post /bookstore/stocks/Lyon/ (9780739360385 -> 42)",
            "payload": "{\n\"9780739360385\": 42\n}",
            "projects": [
                "0c6612ab-3abe-4aac-9564-838d6570152e"
            ],
            "type": "saved",
            "updated": 1601563927506,
            "url": "http://127.0.0.1:8080/bookstore/stocks/Lyon"
        },
        {
            "created": 1592916170337,
            "description": "# Blue [1]\n\n * Look up how many copies of the Harry Potter book are in stock at the Lyon Bookstore.\n ",
            "headers": "",
            "key": "c8ac2f88-33d1-4331-9cdd-1d37b3a992dc",
            "kind": "ARC#RequestData",
            "method": "GET",
            "name": "Get /bookstore/stocks/Lyon (isbn=9780739360385)",
            "payload": "",
            "projects": [
                "0c6612ab-3abe-4aac-9564-838d6570152e"
            ],
            "type": "saved",
            "updated": 1601563912452,
            "url": "http://127.0.0.1:8080/bookstore/stocks/Lyon?isbn=9780739360385"
        },
        {
            "created": 1592916170337,
            "description": "# Green [4]\n\n * Remove all comments for the Harry Potter book.",
            "headers": "",
            "key": "b9e54df6-849e-4752-bfb3-2c1bceaa2c2e",
            "kind": "ARC#RequestData",
            "method": "DELETE",
            "name": "Del /bookstore/isbns/9780739360385/comments",
            "payload": "",
            "projects": [
                "0c6612ab-3abe-4aac-9564-838d6570152e"
            ],
            "type": "saved",
            "updated": 1601563894592,
            "url": "http://127.0.0.1:8080/bookstore/isbns/9780739360385/comments"
        },
        {
            "created": 1592916170337,
            "description": "# Green [5]\n\n * Modify the \"A must read comment\" for the Harry Potter book.",
            "headers": "",
            "key": "b674f9c2-0e00-4e24-a483-e634806c4650",
            "kind": "ARC#RequestData",
            "method": "POST",
            "name": "Post /bookstore/isbns/9780739360385/comments/533279",
            "payload": "Not just a must read, but a must read right away!!!",
            "projects": [
                "0c6612ab-3abe-4aac-9564-838d6570152e"
            ],
            "type": "saved",
            "updated": 1601563875982,
            "url": "http://127.0.0.1:8080/bookstore/isbns/9780739360385/comments/533279"
        },
        {
            "created": 1592916170337,
            "description": "# Green [3]\n\n * Remove the \"Amazing Book\" comment from the Harry Potter book.",
            "headers": "",
            "key": "d7837bf1-051a-46a1-9b69-5ade25e28c6d",
            "kind": "ARC#RequestData",
            "method": "DELETE",
            "name": "Del /bookstore/isbns/9780739360385/comments/933065",
            "payload": "",
            "projects": [
                "0c6612ab-3abe-4aac-9564-838d6570152e"
            ],
            "type": "saved",
            "updated": 1601563863593,
            "url": "http://127.0.0.1:8080/bookstore/isbns/9780739360385/comments/933065"
        },
        {
            "created": 1592916170337,
            "description": "# Green [2]\n\n * Add comment for Harry Potter book (9780739360385): \"So much better than the movie !!\"",
            "headers": "",
            "key": "ecbb9aec-d38f-4770-be60-58a3f29b7d2a",
            "kind": "ARC#RequestData",
            "method": "POST",
            "name": "Post /bookstore/isbns/9780525576709/comments",
            "payload": "So much better than the movie !!",
            "projects": [
                "0c6612ab-3abe-4aac-9564-838d6570152e"
            ],
            "type": "saved",
            "updated": 1601563851949,
            "url": "http://127.0.0.1:8080/bookstore/isbns/9780739360385/comments"
        },
        {
            "created": 1592916170337,
            "description": "# Green [1]\n\n * Retrieve original comments for harry potter book, retrieved by isbn\n ",
            "headers": "",
            "key": "c095dc14-9d92-46a1-a875-18411e0dcc6f",
            "kind": "ARC#RequestData",
            "method": "GET",
            "name": "Get /bookstore/isbns/9780525576709/comments",
            "payload": "",
            "projects": [
                "0c6612ab-3abe-4aac-9564-838d6570152e"
            ],
            "type": "saved",
            "updated": 1601563840562,
            "url": "http://127.0.0.1:8080/bookstore/isbns/9780739360385/comments"
        },
        {
            "created": 1592916731214,
            "description": "# Red [3]\n\n * Index a new book:\n   * Pass the bookdetails as body payload",
            "headers": "Content-Type: application/json",
            "key": "f5cf1a11-d19a-404d-b2bd-0da5d1618000",
            "kind": "ARC#RequestData",
            "method": "PUT",
            "name": "Put /bookstore/isbns/9780525576709",
            "payload": "{\n  \"isbn\": 9780525576709,\n  \"title\": \"The Uninhabitable Earth\",\n  \"author\": \"David Wallace-Wells\",\n  \"priceInCents\": 2447,\n  \"bookAbstract\": \"It is worse, much worse, than you think. The slowness of climate change is a fairy tale, perhaps as pernicious as the one that says it isn\u2019t happening at all, and comes to us bundled with several others in an anthology of comforting delusions: that global warming is an Arctic saga, unfolding remotely; that it is strictly a matter of sea level and coastlines, not an enveloping crisis sparing no place and leaving no life un-deformed.\"\n}",
            "projects": [
                "0c6612ab-3abe-4aac-9564-838d6570152e"
            ],
            "type": "saved",
            "updated": 1601563825537,
            "url": "http://127.0.0.1:8080/bookstore/isbns/9780525576709"
        },
        {
            "created": 1592916226447,
            "description": "# Red [2]\n\n * Retreive details for Harry Potter book, using the ISBN.",
            "headers": "cookie: JSESSIONID=54B2765BECFB1BBFE5BE8B1442E45782,JSESSIONID=54B2765BECFB1BBFE5BE8B1442E45782\nuser-agent: advanced-rest-client\naccept: */*",
            "key": "40d63157-86dc-42c0-9ea0-bbf88ea52fff",
            "kind": "ARC#RequestData",
            "method": "GET",
            "name": "Get /bookstore/isbns/9780739360385",
            "payload": "",
            "projects": [
                "0c6612ab-3abe-4aac-9564-838d6570152e"
            ],
            "type": "saved",
            "updated": 1601563812162,
            "url": "http://127.0.0.1:8080/bookstore/isbns/9780739360385"
        },
        {
            "created": 1592916170337,
            "description": "# Red [1]\n\n * Retrieve original list of ISBNs\n ",
            "headers": "cookie: JSESSIONID=54B2765BECFB1BBFE5BE8B1442E45782\nuser-agent: advanced-rest-client\naccept: */*",
            "key": "c4587621-417a-4d24-8a11-b1c03d15c17b",
            "kind": "ARC#RequestData",
            "method": "GET",
            "name": "Get /bookstore/isbns",
            "payload": "",
            "projects": [
                "0c6612ab-3abe-4aac-9564-838d6570152e"
            ],
            "type": "saved",
            "updated": 1601563802282,
            "url": "http://127.0.0.1:8080/bookstore/isbns"
        }
    ],
    "version": "15.0.7"
}
