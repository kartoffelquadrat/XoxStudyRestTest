#!/bin/bash

# invoke the api unit test library script
source rest-tools.sh

# set server base URLs
APIROOT='http://127.0.0.1:8080/xox'

# Verifies the resource methods with response bodies are empty. (e.g. when model is not yet initialized / was reset / game over.)
function verifyBlank {

	# Reset result stats array
	CHECKSARRAY=()
	
	# Start testing the server...
	echo "Testing the servers REST tree..."

  # verify xox is reachable, returns nothing when no game yet initialized
	TESTCOUNT="1.1"
	ARGS=(-X GET)
	testMethod "$APIROOT" "200"

  # verify the response body for the /xox/board resource is empty (does not containe cell information / fill infoprmation), if game is uninitialized
	TESTCOUNT="1.2"
	testMethod "$APIROOT/board" "200"
	assertnotexists "board" $PAYLOAD

  # verify the response body for the /xox/players resource is empty (does not containe cell information / fill infoprmation), if game is uninitialized
	TESTCOUNT="1.3"
	testMethod "$APIROOT/players" "200"
	assertnotexists "player" $PAYLOAD

  # verify the response body for the /xox/players/foo/actions resource is empty (does not contain any actions), if game is uninitialized / player does not exist
	TESTCOUNT="1.4"
	testMethod "$APIROOT/players/foo/actions" "200"
	assertnotexists "action" $PAYLOAD
}

# Sents init request to server, using PLAYER1/PLAYER2 for game parameters
function initGame
{
	TESTCOUNT="2.1"
	ARGS=(-X PUT --header 'Content-Type: application/json' --data '{"players":[{"name":"$PLAYER1","preferredColour":"#CAFFEE"},{"name":"PLAYER2","preferredColour":"#1C373A"}],"creator":"$PLAYER1"}

')
	testMethod "$APIROOT" "200"
}

function verifySetup
{
	echo "Not yet implemented."
}



## Actual test sequence starts here

# Verify all resources are blank on startup
verifyBlank

# Initialize a game with default players
PLAYER1=Bettina
PLAYER2=Joerg
initGame
verifySetup

## Try to override game when already game in progress (must be ignored)
PLAYER2=Maximilian
initGame
PLAYER2=Joerg
verifySetup

printstats 1

