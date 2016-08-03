package com.usp.kiss.server.service;

import org.slim3.tester.ServletTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GetEpisodeServiceImplTest extends ServletTestCase {

    private GetEpisodeServiceImpl service = new GetEpisodeServiceImpl();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
