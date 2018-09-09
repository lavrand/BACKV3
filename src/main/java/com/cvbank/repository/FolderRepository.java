package com.cvbank.repository;


import com.cvbank.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    @Query("select f.id, f.nameFolder " +
            "from Folder f " +
            "where f.profileId = :profileId")
    List<Folder> findAllFoldersByProfileId(@Param("profileId") Long profileId);

    /*@Query("select f " +
            "from Folder f " +
            "where f.profileId = :profileId")*/
    List<Folder> findFolderByProfileId(Long l);
}
