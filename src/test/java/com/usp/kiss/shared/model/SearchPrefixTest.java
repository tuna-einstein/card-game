package com.usp.kiss.shared.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SearchPrefixTest extends AppEngineTestCase {

    private SearchPrefix model = new SearchPrefix();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
