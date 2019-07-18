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
        Customers customers = custrepos.findByCustname(name);

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

    @Transactional
    @Override
    public Customers save(Customers customers)
    {
        Customers newCustomers = new Customers();

        newCustomers.setCustname(customers.getCustname());
        newCustomers.setCustcity(customers.getCustcity());
        newCustomers.setWorkingarea(customers.getWorkingarea());
        newCustomers.setCustcountry(customers.getCustcountry());
        newCustomers.setGrade(customers.getGrade());
        newCustomers.setOpeningamt(customers.getOpeningamt());
        newCustomers.setReceiveamt(customers.getReceiveamt());
        newCustomers.setPaymentamt(customers.getPaymentamt());
        newCustomers.setOutstandingamt(customers.getOutstandingamt());
        newCustomers.setPhone(customers.getPhone());
        newCustomers.setAgents(customers.getAgents());

        for (Orders o : customers.getOrders())
        {
            newCustomers.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), o.getCustomers(), o.getOrddescription()));
        }

        return custrepos.save(newCustomers);
    }

    @Transactional
    @Override
    public Customers update(Customers customers, long id)
    {
        Customers currentCustomer = custrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (customers.getCustname() != null)
        {
            currentCustomer.setCustname(customers.getCustname());
        }

        if (customers.getCustcity() != null)
        {
            currentCustomer.setCustcity(customers.getCustcity());
        }

        if (customers.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(customers.getWorkingarea());
        }

        if (customers.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(customers.getCustcountry());
        }

        if (customers.getGrade() != null)
        {
            currentCustomer.setGrade(customers.getGrade());
        }

        if (customers.getOpeningamt() != 0)
        {
            currentCustomer.setOpeningamt(customers.getOpeningamt());
        }

        if (customers.getReceiveamt() != 0)
        {
            currentCustomer.setReceiveamt(customers.getReceiveamt());
        }

        if (customers.getPaymentamt() != 0)
        {
            currentCustomer.setPaymentamt(customers.getPaymentamt());
        }

        if (customers.getOutstandingamt() != 0)
        {
            currentCustomer.setOutstandingamt(customers.getOutstandingamt());
        }

        if (customers.getPhone() != null)
        {
            currentCustomer.setPhone(customers.getPhone());
        }

        if (customers.getAgents() != null)
        {
            currentCustomer.setAgents(customers.getAgents());
        }

        return custrepos.save(currentCustomer);
    }
}
