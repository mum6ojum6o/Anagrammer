package com.mumbojumbo.annagrammer;

import com.mumbojumbo.annagrammer.businesslogic.SearchAnagrams;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    List<String> searchSpace;
    SearchAnagrams searchAnagrams;
    @Before
    public void setup(){
        searchAnagrams = new SearchAnagrams();
        searchSpace = new ArrayList<>();
        searchSpace.add("acme");
        searchSpace.add("cane");
        searchSpace.add("acre");
        searchSpace.add("care");
        searchSpace.add("race");
        searchSpace.add("ales");
        searchSpace.add("leas");
        searchSpace.add("seal");
        //searchSpace.add("");
        //searchSpace.add("");
    }

    @Test
    public void isAnagram_valid(){
        String searchWord = "sale";
        Assert.assertEquals(true,searchAnagrams.isAnagram(searchWord,"aels"));
    }

    @Test
    public void isAnagram_invalid_lengthmismatch(){
        String searchWord = "sale";
        Assert.assertEquals(false,searchAnagrams.isAnagram(searchWord,"aelss"));
    }


    @Test
    public void isAnagram_invalid_charmismatch(){
        String searchWord = "sale";
        Assert.assertEquals(false,searchAnagrams.isAnagram(searchWord,"aela"));
    }
}