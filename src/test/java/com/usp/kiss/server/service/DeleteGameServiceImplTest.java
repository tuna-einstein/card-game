package com.usp.kiss.server.service;

import org.slim3.tester.ServletTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class DeleteGameServiceImplTest extends ServletTestCase {

    private DeleteGameServiceImpl service = new DeleteGameServiceImpl();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
