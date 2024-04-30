package aiss.grupo6.youtubeMiner.repository;

import aiss.grupo6.youtubeMiner.database.VMChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<VMChannel, String> {
}
