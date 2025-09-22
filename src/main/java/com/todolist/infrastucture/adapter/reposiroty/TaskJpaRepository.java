package com.todolist.infrastucture.adapter.reposiroty;


import com.todolist.infrastucture.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// adaptadores - conexão com banco de dados
@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {}
