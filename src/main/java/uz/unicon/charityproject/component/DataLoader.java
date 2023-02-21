package uz.unicon.charityproject.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.unicon.charityproject.entity.Role;
import uz.unicon.charityproject.entity.User;
import uz.unicon.charityproject.entity.enums.RoleName;
import uz.unicon.charityproject.repository.RoleRepository;
import uz.unicon.charityproject.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String mode; //always

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl; //create

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always") && ddl.equals("create")) {
            Role admin = new Role();
            admin.setRoleName(RoleName.ROLE_ADMIN);
            Role superadmin = new Role();
            superadmin.setRoleName(RoleName.ROLE_SUPER_ADMIN);
            Role user_role = new Role();
            user_role.setRoleName(RoleName.ROLE_USER);
            Role moderator = new Role();
            moderator.setRoleName(RoleName.MODERATOR);

            roleRepository.save(admin);
            roleRepository.save(superadmin);
            roleRepository.save(user_role);
            roleRepository.save(moderator);



          /*  User user = new User();
            user.setEmail("khodirjob@gmail.com");
            user.setRoles(Collections.singleton(user_role));
            user.setPassword(passwordEncoder.encode("1111"));
            userRepository.save(user);
*/

            /*User user1 = new User();
            user1.setEmail("feruzbek@gmail.com");
            user1.setRoles(Collections.singleton(admin));
            user1.setPassword(passwordEncoder.encode("1111"));
            userRepository.save(user1);*/

//            User user2 = new User();
//            user2.setUsername("mirzabek");
//            user2.setRoles(Collections.singleton(superadmin));
//            user2.setPassword(passwordEncoder.encode("1111"));
//            userRepository.save(user2);

        }
    }
}
