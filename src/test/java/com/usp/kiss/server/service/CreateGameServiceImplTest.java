package com.usp.kiss.server.service;

import org.slim3.tester.ServletTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CreateGameServiceImplTest extends ServletTestCase {

    private CreateGameServiceImpl service = new CreateGameServiceImpl();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
