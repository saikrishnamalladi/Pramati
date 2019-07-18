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



