package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.model.Agents;
import com.lambdaschool.javaorders.model.Customers;
import com.lambdaschool.javaorders.repos.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "agents")
public class AgentsServiceImpl implements AgentsService
{
    @Autowired
    private AgentsRepository agentrepos;

    @Override
    public List<Agents> findAll()
    {
        List<Agents> list = new ArrayList<>();
        agentrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Agents findAgentById(long id)
    {
        return agentrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (agentrepos.findById(id).isPresent())
        {
            agentrepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Agents save(Agents agents)
    {
        Agents newAgents = new Agents();

        newAgents.setAGENTCODE(agents.getAGENTCODE());
        newAgents.setAGENTNAME(agents.getAGENTNAME());
        newAgents.setWORKINGAREA(agents.getWORKINGAREA());
        newAgents.setCOMMISSION(agents.getCOMMISSION());
        newAgents.setPHONE(agents.getPHONE());
        newAgents.setCOUNTRY(agents.getCOUNTRY());

        for (Customers c : agents.getCustomers())
        {
            newAgents.getCustomers().add(new Customers(c.getCUSTNAME(), c.getCUSTCITY(), c.getWORKINGAREA(), c.getCUSTCOUNTRY(), c.getGRADE(), c.getOPENINGAMT(), c.getRECEIVEAMT(), c.getPAYMENTAMT(), c.getOUTSTANDINGAMT(), c.getPHONE(), c.getAgents()));
        }

        return agentrepos.save(newAgents);
    }
}
