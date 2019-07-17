package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.model.Customers;
import com.lambdaschool.javaorders.model.Orders;
import com.lambdaschool.javaorders.repos.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customers")
public class CustomersServiceImpl implements CustomersService
{
    @Autowired
    private CustomersRepository custrepos;

    @Override
    public List<Customers> findAll()
    {
        List<Customers> list = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customers findCustomerById(long id)
    {
        return custrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Customers findCustomerByName(String name)
    {
        Customers customers = custrepos.findByName(name);

        if (customers == null)
        {
            throw new EntityNotFoundException("Customer " + name + " not fount!");
        }

        return customers;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (custrepos.findById(id).isPresent())
        {
            custrepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Customers save(Customers customers)
    {
        Customers newCustomer = new Customers();

        newCustomer.setCUSTNAME(customers.getCUSTNAME());
        newCustomer.setCUSTCITY(customers.getCUSTCITY());
        newCustomer.setWORKINGAREA(customers.getWORKINGAREA());
        newCustomer.setCUSTCOUNTRY(customers.getCUSTCOUNTRY());
        newCustomer.setGRADE(customers.getGRADE());
        newCustomer.setOPENINGAMT(customers.getOPENINGAMT());
        newCustomer.setRECEIVEAMT(customers.getRECEIVEAMT());
        newCustomer.setPAYMENTAMT(customers.getPAYMENTAMT());
        newCustomer.setOUTSTANDINGAMT(customers.getOUTSTANDINGAMT());
        newCustomer.setPHONE(customers.getPHONE());
        newCustomer.setAgents(customers.getAgents());

        for (Orders o : customers.getOrders())
        {
            newCustomer.getOrders().add(new Orders(o.getORDAMOUNT(), o.getADVANCEAMOUNT(), o.getCustomers(), o.getORDDESCRIPTION()));
        }

        return custrepos.save(newCustomer);
    }

    @Override
    public Customers update(Customers customers, long id)
    {
        Customers currentCustomer = custrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (customers.getCUSTNAME() != null)
        {
            currentCustomer.setCUSTNAME(customers.getCUSTNAME());
        }

        if (customers.getCUSTCITY() != null)
        {
            currentCustomer.setCUSTCITY(customers.getCUSTCITY());
        }

        if (customers.getWORKINGAREA() != null)
        {
            currentCustomer.setWORKINGAREA(customers.getWORKINGAREA());
        }

        if (customers.getCUSTCOUNTRY() != null)
        {
            currentCustomer.setCUSTCOUNTRY(customers.getCUSTCOUNTRY());
        }

        if (customers.getGRADE() != null)
        {
            currentCustomer.setGRADE(customers.getGRADE());
        }

        if (customers.getOPENINGAMT() != 0)
        {
            currentCustomer.setOPENINGAMT(customers.getOPENINGAMT());
        }

        if (customers.getRECEIVEAMT() != 0)
        {
            currentCustomer.setRECEIVEAMT(customers.getRECEIVEAMT());
        }

        if (customers.getPAYMENTAMT() != 0)
        {
            currentCustomer.setPAYMENTAMT(customers.getPAYMENTAMT());
        }

        if (customers.getOUTSTANDINGAMT() != 0)
        {
            currentCustomer.setOUTSTANDINGAMT(customers.getOUTSTANDINGAMT());
        }

        if (customers.getPHONE() != null)
        {
            currentCustomer.setPHONE(customers.getPHONE());
        }

        if (customers.getAgents() != null)
        {
            currentCustomer.setAgents(customers.getAgents());
        }

        return custrepos.save(currentCustomer);
    }
}
