LRU Cache
-----------------
It is Least recently used cache.
It initalises the cache with the intial capacity
And it supports two operations access and cacheState
Access will insert into the key with the following properties

1) If the key is already available remove the key from the existing location and moves to the end of the list as most recently used key
2) If the key is not found it will inserted at the end of the list
3) If the cache is full , It will remove the front node of the list and inserts the provided key at the end of the list

LRU chache can be tested as below
1) Load the maven project into the eclise workspace
2) Run the main programme to verify the scenario provided in the problem statement
3) Alternatively can be tested with the provided junit test 

Distribute Key-Value Store
--------------------------------
It consists of 4 modules 
1) KVStore (Key-value store running on one process)
2) KVStore1(Key-value store running on the second process)
3) Cache (Rest api's exposed for setting key and getting key for KVStore)
4) Cache1(Rest api exposed for geeting key from KVStore1 which replicated as slave when the master KVStore is inserted with key value pair through Cache)

Following is the Architecture for key value store

KVStore is the master store which will use in memory and persistent store
When the the Rest api with url http:localhost:8080/set/key is called(It requires value for the key as post parameter) 
Connection will be established to KVStore and it will add entry to in-memory store
And in the Asynchronously slave(KVStore1) will be replicated with the entry.
Using scheduler in the both process entries will be written to persistent store(At present writing to flat file in the project module itself)

And the same can get from Rest api get through Cache (http://localhost:8080/get/key) and Rest api through Cache1(which is connected to KVStore1) using the url(http://localhost:8081/get/key).

It can be verified as below

1) Load the above specified 4 modules into eclipse
2) Build the projects using maven
3) Run the modules as specified in the below order
  -> 1) Run the KvStoreApplication main programme under KVStore1
  -> 2) Run the KvStoreApplication main programme under KVStore
  -> 3) Run the CacheApplication main programme under Cache1
  -> 4) Run the CacheApplication main programme under Cache
4) Hit the url through postman or curl http://localhost:8080/set/key with post param name value
5) Hit the url through postman or curl http://localhost:8080/get/key to check the previous value
6) Hit the url through postman or curl http://localhost:8081/get/key to check the previous value from the replicated store(KVStore1)

Techologies used : 
Spring Boot, Java,Maven.

