Find the number of Islands
==============

Given 2D matrix with integer values, find the number of islands.

A group of connected cells forms an island. For example, the below matrix contains 3 islands:

{1, 1, 1, 0, 1, 1}\
{1, 0, 1, 0, 1, 1}\
{1, 1, 1, 0, 0, 0}\
{0, 0, 0, 0, 1, 0}\
{1, 1, 1, 1, 0, 0}

A cell in 2D matrix can be connected to 8 neighbors.

Remarks on the Code
-------------------

The task was solved using the [disjoint-set data structure](https://en.wikipedia.org/wiki/Disjoint-set_data_structure).

**Generator-service** used to create a random map or post the predefined one to the kafka topic.\
**Solver-service** reads a map from the kafka and log the number of islands to the stdout.

How to Run
----------

1. Start zookeeper, kafka and postgres: 
    ~~~~
    cd numberOfIslands
    docker-compose up
    ~~~~
2. Start generator-service.
3. Start solver-service.
4. Use REST endpoints:
    * Generate new random map passing rows and cols parameters to the generator-service
        ~~~~
        http post localhost:8080/api/islands/generate rows==5 cols==8
        ~~~~
    * Send predefined map to the generator-service
        ~~~~
        http post localhost:8080/api/islands data:='[[1,0],[0,0]]'
        ~~~~
