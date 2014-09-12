/*
 * Copyright 2014 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gs.collections.impl.multimap.bag;

import com.gs.collections.api.collection.MutableCollection;
import com.gs.collections.api.multimap.Multimap;
import com.gs.collections.api.tuple.Pair;
import com.gs.collections.impl.factory.Bags;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.multimap.AbstractMutableMultimapTestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of {@link HashBagMultimap}.
 */
public class HashBagMultimapTest extends AbstractMutableMultimapTestCase
{
    @Override
    public <K, V> HashBagMultimap<K, V> newMultimap()
    {
        return HashBagMultimap.newMultimap();
    }

    @Override
    public <K, V> HashBagMultimap<K, V> newMultimapWithKeyValue(K key, V value)
    {
        HashBagMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key, value);
        return mutableMultimap;
    }

    @Override
    public <K, V> HashBagMultimap<K, V> newMultimapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        HashBagMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key1, value1);
        mutableMultimap.put(key2, value2);
        return mutableMultimap;
    }

    @Override
    protected <V> MutableCollection<V> createCollection(V... args)
    {
        return Bags.mutable.of(args);
    }

    @Override
    public <K, V> Multimap<K, V> newMultimap(Pair<K, V>... pairs)
    {
        return HashBagMultimap.newMultimap(pairs);
    }

    @Override
    protected <K, V> Multimap<K, V> newMultimapFromPairs(Iterable<Pair<K, V>> inputIterable)
    {
        return HashBagMultimap.newMultimap(inputIterable);
    }

    @Override
    public <K, V> HashBagMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3)
    {
        HashBagMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key1, value1);
        mutableMultimap.put(key2, value2);
        mutableMultimap.put(key3, value3);
        return mutableMultimap;
    }

    @Override
    public <K, V> HashBagMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4)
    {
        HashBagMultimap<K, V> mutableMultimap = this.newMultimap();
        mutableMultimap.put(key1, value1);
        mutableMultimap.put(key2, value2);
        mutableMultimap.put(key3, value3);
        mutableMultimap.put(key4, value4);
        return mutableMultimap;
    }

    @Override
    @Test
    public void selectKeysValues()
    {
        HashBagMultimap<String, Integer> multimap = HashBagMultimap.newMultimap();
        multimap.putAll("One", FastList.newListWith(1, 2, 3, 4, 4));
        multimap.putAll("Two", FastList.newListWith(2, 3, 4, 5, 3, 2));
        HashBagMultimap<String, Integer> selectedMultimap = multimap.selectKeysValues((key, value) -> ("Two".equals(key) && (value % 2 == 0)));
        HashBagMultimap<String, Integer> expectedMultimap = HashBagMultimap.newMultimap();
        expectedMultimap.putAll("Two", FastList.newListWith(2, 4, 2));
        Assert.assertEquals(expectedMultimap, selectedMultimap);
    }

    @Override
    @Test
    public void rejectKeysValues()
    {
        HashBagMultimap<String, Integer> multimap = HashBagMultimap.newMultimap();
        multimap.putAll("One", FastList.newListWith(1, 2, 3, 4, 1));
        multimap.putAll("Two", FastList.newListWith(2, 3, 4, 5));
        HashBagMultimap<String, Integer> rejectedMultimap = multimap.rejectKeysValues((key, value) -> ("Two".equals(key) || (value % 2 == 0)));
        HashBagMultimap<String, Integer> expectedMultimap = HashBagMultimap.newMultimap();
        expectedMultimap.putAll("One", FastList.newListWith(1, 3, 1));
        Assert.assertEquals(expectedMultimap, rejectedMultimap);
    }
}
