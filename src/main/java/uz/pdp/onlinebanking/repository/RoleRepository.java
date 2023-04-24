package uz.pdp.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.onlinebanking.entity.Roles;
import uz.pdp.onlinebanking.entity.enums.RoleName;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

    Roles findByRoleName(RoleName roleAdmin);
}
