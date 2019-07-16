package com.pramati.cache;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Unit test for LRU Cache.
 */
public class CacheTest extends TestCase
{
	Cache cache = new Cache(3);
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CacheTest( String testName )
    {
        super( testName );
    }
	@Test
	public void testCache() {
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
