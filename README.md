# Insight Data Engineering Coding Challenge

My Solutions to [Insight Data Engineering Coding Challenge 2015](https://github.com/InsightDataScience/cc-example)


###Solution
--------
##### work count
To implement the first feature of the coding challenge, I have create a file under `/src` directory called `WordCount.java`. It reads all the files in the `/tweet_input` directory as a string stream and then parse them, counting all the word frequency in it and then output the result into `/tweet_output/ft1.txt`  as a txt file.

More detailedly, I read in all the files as a string stream and then use a `TreeMap` collection to store the unique key value pairs and use `Treeset` collection to keep the result sorted in order.

In aspect of algorithm asymptotic analysis, the time complexity for lookup is `O(logN)` where N is the number of words, and space complexity is `O(N)`.

##### get median

I have create a file under `/src` directory called `GetMedian.java`. It dynamically reads all the files in the `/tweet_input` directory, put the unique words into self-defined `MedianHeap` class and output the current running median into `/tweet_output/ft2.txt`  as a txt file.

More detailedly, I use two heaps to implement the `MedianHeap` class, one is default `MinHeap` implemented by `PriorityQueue` and the other one is `MaxHeap` implemented by a comparator. This **two heap technique** is efficient when used to get the running median by an input stream. The `MinHeap `keeps the numbers greater then current median number and the `MaxHeap` keeps numbers less than the current median number. After reading in the element, just treat the result in two cases by even numbers and odd numbers. This technique is the best way I can use in prospective of time complexity. 

In aspect of algorithm asymptotic analysis, the time complexity for lookup is `O(1)` and pushing in new element into heap is `O(logN)` where N is the number of words, and space complexity is `O(N)`.


###Environment
--------
* Java 1.7+
* Mac OS X/Linux/Windows

###Usage
--------
To execute the code, go to the file directory and just run the command `sh run.sh`. The usage can be seen in the screenshot section below.

###Screenshot
--------

Run the code:

![alt text](https://github.com/stevenlordiam/InsightDataEngineeringCodingChallenge/blob/master/screenshot/RunFile.png "run file")

Word count output:

![alt text](https://github.com/stevenlordiam/InsightDataEngineeringCodingChallenge/blob/master/screenshot/ft1.png "ft1")

Get median output:

![alt text](https://github.com/stevenlordiam/InsightDataEngineeringCodingChallenge/blob/master/screenshot/ft2.png "ft2")


###Future work
--------
* more language support
* use Twitter API to fetch data files