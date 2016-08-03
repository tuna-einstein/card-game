package com.usp.kiss.shared.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class EpisodeTest extends AppEngineTestCase {

    private Episode model = new Episode();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
