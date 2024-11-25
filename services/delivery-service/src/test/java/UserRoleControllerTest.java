import org.example.*;
import org.example.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRoleControllerTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private UserClient userClient;

    @Mock
    private PizzaClient pizzaClient;

    @InjectMocks
    private DeliveryController userRoleController;

    @Test
    void getUserRole() {
        when(userClient.getAllUsers())
                .thenReturn(List.of(
                        User.builder()
                                .id(4L)
                                .build(),
                        User.builder()
                                .id(5L)
                                .build(),
                        User.builder()
                                .id(6L)
                                .build()
                ));
        when(userRoleRepository.findByUserId(anyLong()))
                .thenReturn(UserRole.builder()
                        .roleName(Role.USER)
                        .build());

        UserRole actualRole = userRoleController.getUserRole(4L);

        assertThat(actualRole.getRoleName()).isEqualTo(Role.USER);
    }
}
