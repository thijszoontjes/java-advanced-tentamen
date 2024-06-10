package nl.inholland.exam.thijs.repository;

import nl.inholland.exam.thijs.model.CrewMember;
import nl.inholland.exam.thijs.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {
    long countByShip(Ship ship);
}
