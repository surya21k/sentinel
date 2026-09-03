package com.sentinel.api;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RateLimiterServiceTest {

    @Mock
    private StringRedisTemplate redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @InjectMocks
    private RateLimiterService rateLimiterService;

    @BeforeEach
    void setUp() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void allowsRequestWhenUnderLimit() {
        when(valueOperations.increment("rate_limit:abc123")).thenReturn(1L);

        boolean result = rateLimiterService.isAllowed("abc123", 4, 30);

        assertTrue(result);
    }

    @Test
    void blocksRequestWhenOverLimit() {
        when(valueOperations.increment("rate_limit:abc123")).thenReturn(5L);

        boolean result = rateLimiterService.isAllowed("abc123", 4, 30);

        assertFalse(result);
    }
    @Test
    void setsExpiryOnlyOnFirstRequest() {
        when(valueOperations.increment("rate_limit:abc123")).thenReturn(1L);

        rateLimiterService.isAllowed("abc123", 4, 30);

        verify(redisTemplate, times(1)).expire(eq("rate_limit:abc123"), any(Duration.class));
    }

    @Test
    void doesNotResetExpiryOnLaterRequests() {
        when(valueOperations.increment("rate_limit:abc123")).thenReturn(3L);

        rateLimiterService.isAllowed("abc123", 4, 30);

        verify(redisTemplate, never()).expire(anyString(), any(Duration.class));
    }

}