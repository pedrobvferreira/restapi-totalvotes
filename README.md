## REST API: Total Votes
A restaurant rating application collects ratings or votes from its users and stores them in a database. They want to allow users to retrieve the total vote count for
restaurants in a city.
Implement a function, getVoteCount. Given a city name and the estimated cost for the outlet, make a GET request to the API
at https://jsonmock.hackerrank.com/api/food outlets?city=<cityName>&<estimated_cost=<estimatedCost> where <cityName> and <estimatedCost> are the parameters passed to the function.

The response is a JSON object with the following 5 fields:
- page: The current page of the results. (Number)
- per_page: The maximum number of results returned per page. (Number)
- total: The total number of results. (Number)
- total_pages: The total number of pages with results. (Number)
- data: Either an empty array or an array that contains the food outlets' records.

In data, each food outlet has the following schema:
- id - outlet id (Number)
- name: The name of the outlet (String)
- city: The city in which the outlet is located (String)
- estimated_cost: The estimated cost of the food in the particular outlet (Number).
- user_rating: An object containing the user ratings for the outlet. The object has the following schema:
- average_rating: The average user rating for the outlet (Number)
- votes: The number of people who voted the outlet (Number)

If there are no matching records returned, the data array will be empty. In that case, the getVoteCount function should return -1.
An example of a food outlet record is as follows:
```
{
  "city": "Seattle",
  "name": "Cafe Juanita",
  "estimated_cost": 160,
  "user_rating": {
    "average_rating": 4.9,
    "votes": 16203
  },
  "id": 41
}
```

Use the votes property of each outlet to calculate the total vote count of all the matching outlets.

### Function Description
Complete the getVoteCount function in the editor below.

getVoteCount has the following parameters:
string cityName: the city to query int estimatedCost: the cost to query

### Returns
int: the sum of votes for matching outlets or -1

### Constraints
•No query will return more than 10 records.

Note: Please review the header in the code stub to see available libraries for API requests in the selected language. Required libraries can be imported in order to solve the question. Check our full list of supported libraries at https://www.hackerrank.com/environment.

### Input Format For Custom Testing
In the first line, there is cityName.
In the second line, there is the estimated Cost.

### Sample Case 0
Sample Input For Custom Testing
```
Seattle
110
```
Sample Output
```
2116
```

Explanation
First a call is made to API
https://jsonmock.hackerrank.com/api/food outlets? city=Seattle&estimated cost=110 to fetch the only matching outlet. The sum of votes is calculated and returned.

### Sample Case 1
Sample Input For Custom Testing
```
Delaware
150
```
Sample Output
```
-1
```

### Explanation
An API call is made to
https://jsonmock.hackerrank.com/api/food outlets? city=Delaware&estimated cost=150 but the data field is
an empty array.
