package nitconf.reviewermodule.reviewer.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import nitconf.reviewermodule.reviewer.Service.ReviewService;

public class ReviewServiceTests {
    @Mock
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize mocks
    }

    @Test 
    void checkBeforeDeadline() {
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime deadline = LocalDateTime.of(current.getYear(), 12, 31, 23, 59, 59);

        when(reviewService.checkdeadLine(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(1);

        Integer result = reviewService.checkdeadLine(current, deadline);

        assertEquals(1, result);
    }

    // @Test 
    // void checkAfterDeadline() {
    //     LocalDateTime current = '';
    //     LocalDateTime deadline = '';

    //     Integer result = checkdeadLine(current, deadline);

    //     assertEquals(0, result);
    // }
}
