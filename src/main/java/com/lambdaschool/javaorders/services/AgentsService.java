package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.model.Agents;

import java.util.List;

public interface AgentsService
{
    List<Agents> findAll();

    Agents findAgentById(long id);

    void delete(long id);

    Agents save(Agents agents);
}
