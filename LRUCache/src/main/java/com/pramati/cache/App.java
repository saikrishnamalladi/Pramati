package com.pramati.cache;

/**
 * Main class to test the LRU cache
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Cache cache = new Cache(3);
        cache.access("2");
        cache.cacheState();
        cache.access("10");
        cache.cacheState();
        cache.access("2");
        cache.cacheState();
        cache.access("2");
        cache.cacheState();
        cache.access("9");
        cache.cacheState();
        cache.access("2");
        cache.cacheState();
        cache.access("10");
        cache.cacheState();
        cache.access("20");
        cache.cacheState();
    }
}
