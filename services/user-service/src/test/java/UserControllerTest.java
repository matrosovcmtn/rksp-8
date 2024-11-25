import org.example.User;
import org.example.UserController;
import org.example.UserRepository;
import org.example.UserRole;
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
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;


    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(User.builder().id(1L).build()));

        List<User> actualUsers = userController.getAllUsers();

        assertThat(actualUsers).hasSize(1);
        assertThat(actualUsers.getFirst().getId()).isEqualTo(1L);
    }

    @Test
    void getUserInfo() {
        when(userFullnameClient.getUserFullname(anyLong()))
                .thenReturn(UserFullname.builder().fullname("testFullName").build());
        when(userRoleClient.getUserRole(anyLong()))
                .thenReturn(UserRole.builder().roleName("ROLENAME").build());

        UserInfoDto actualInfo = userController.getUserInfo(1L);

        assertThat(actualInfo).isNotNull();
        assertThat(actualInfo.fullname().getFullname()).isEqualTo("testFullName");
        assertThat(actualInfo.userRole().getRoleName()).isEqualTo("ROLENAME");
    }
}
