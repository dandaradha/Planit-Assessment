# Planit-Assessment


Please run following to execute all tests through command line

mvn test


Please run following to execute test with tag

mvn test -Dcucumber.filter.tags=“@cart"


Please run following command to execute two or more tests using tags

mvn verify -Dcucumber.filter.tags="@cart or @shop"
