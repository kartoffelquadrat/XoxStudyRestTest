
BASEDIR=$(pwd)
CODEDIR="/Users/schieder/Code/XoxManuallyRestified"
STARTUPGRACE=6

## Kills the process running on port 8080, if there is one.
function killApp8080 {

  # Get ID of process running on 8080, if there is one
  PID=$(lsof -ti:8080)

  # If there is a service running, kill it
  if [[ -n "$PID" ]]; then
    kill "$PID"
  fi
}

function restartBackend {
    cd $CODEDIR
    killApp8080
    mvn spring-boot:run >/dev/null 2>/dev/null & 
    sleep $STARTUPGRACE
    cd $BASEDIR
}

echo "All tests, without R verification"

restartBackend
mvn -Dtest=XoxTest#testXoxGet test
restartBackend
mvn -Dtest=XoxTest#testXoxPost test
restartBackend
mvn -Dtest=XoxTest#testXoxIdGet test
restartBackend
mvn -Dtest=XoxTest#testXoxIdDelete test
restartBackend
mvn -Dtest=XoxTest#testXoxIdBoardGet test
restartBackend
mvn -Dtest=XoxTest#testXoxIdPlayersGet test
restartBackend
mvn -Dtest=XoxTest#testXoxIdPlayersIdActionsGet
restartBackend
mvn -Dtest=XoxTest#testXoxIdPlayersIdActionsPost
restartBackend

echo "Tests of CUD with R verification"

mvn -Dtest=XoxTest#testXoxGet test -Dreadverif=true
restartBackend
mvn -Dtest=XoxTest#testXoxPost test -Dreadverif=true
restartBackend
mvn -Dtest=XoxTest#testXoxIdGet test -Dreadverif=true
restartBackend
mvn -Dtest=XoxTest#testXoxIdDelete test -Dreadverif=true
restartBackend
mvn -Dtest=XoxTest#testXoxIdBoardGet test -Dreadverif=true
restartBackend
mvn -Dtest=XoxTest#testXoxIdPlayersGet test -Dreadverif=true
restartBackend
mvn -Dtest=XoxTest#testXoxIdPlayersIdActionsGet test -Dreadverif=true
restartBackend
mvn -Dtest=XoxTest#testXoxIdPlayersIdActionsPost test -Dreadverif=true
killApp8080

