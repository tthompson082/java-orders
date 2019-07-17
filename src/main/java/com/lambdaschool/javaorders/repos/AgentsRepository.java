package com.lambdaschool.javaorders.repos;

import com.lambdaschool.javaorders.model.Agents;
import org.springframework.data.repository.CrudRepository;

public interface AgentsRepository extends CrudRepository<Agents, Long>
{
}
