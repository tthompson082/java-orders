package com.lambdaschool.javaorders.model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ORDNUM;

    @Column()
    private double ORDAMOUNT;
    private double ADVANCEAMOUNT;
    private String ORDDESCRIPTION;

    @ManyToOne
    @JoinColumn(name = "CUSTCODE", nullable = false)
    private Customers customers;

    public Orders()
    {
    }

    public Orders(double ORDAMOUNT, double ADVANCEAMOUNT, String ORDDESCRIPTION, Customers customers)
    {
        this.ORDAMOUNT = ORDAMOUNT;
        this.ADVANCEAMOUNT = ADVANCEAMOUNT;
        this.ORDDESCRIPTION = ORDDESCRIPTION;
        this.customers = customers;
    }

    public long getORDNUM()
    {
        return ORDNUM;
    }

    public void setORDNUM(long ORDNUM)
    {
        this.ORDNUM = ORDNUM;
    }

    public double getORDAMOUNT()
    {
        return ORDAMOUNT;
    }

    public void setORDAMOUNT(double ORDAMOUNT)
    {
        this.ORDAMOUNT = ORDAMOUNT;
    }

    public double getADVANCEAMOUNT()
    {
        return ADVANCEAMOUNT;
    }

    public void setADVANCEAMOUNT(double ADVANCEAMOUNT)
    {
        this.ADVANCEAMOUNT = ADVANCEAMOUNT;
    }

    public String getORDDESCRIPTION()
    {
        return ORDDESCRIPTION;
    }

    public void setORDDESCRIPTION(String ORDDESCRIPTION)
    {
        this.ORDDESCRIPTION = ORDDESCRIPTION;
    }

    public Customers getCustomers()
    {
        return customers;
    }

    public void setCustomers(Customers customers)
    {
        this.customers = customers;
    }
}
