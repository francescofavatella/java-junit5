package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TestJUnit512MockitoExtension {

    @Mock
    private Db db;

    @InjectMocks
    private Service service;

    @Test
    void testMockito() {
        service.insertValue("name");
        verify(db).insert("name");
    }
}

interface Db {
    void insert(String value);
}

class Service {
    private final Db db;

    Service(Db db) {
        this.db = db;
    }

    public void insertValue(String value) {
        db.insert(value);
    }
}