package com.mangium;

import java.util.ArrayList;
import java.util.Iterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@ManagedBean
@RequestScoped
public class StoreData {
    
    
    private Car car;
    private ArrayList cr;

    public ArrayList getCr() {
        return cr;
    }

    public void setCr(ArrayList cr) {
        this.cr = cr;
    }
    
    
    @ManagedProperty(value="#{car.carid}")
    private String carid;
    
    @ManagedProperty(value="#{car.carname}")
    private String carname;
    
    @ManagedProperty(value="#{car.carcost}")
    private String carcost;

    public Car getCar() {
        return car;
    }


    public String getCarid() {
        
        car = new Car();
        car.setCarid(this.carid);
        car.setCarname(this.carname);
        car.setCarcost(this.carcost);
        
        this.car = car;
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getCarcost() {
        return carcost;
    }

    public void setCarcost(String carcost) {
        this.carcost = carcost;
    }
    
    
    
    
    public ArrayList store(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sf = cfg.buildSessionFactory();
        Session hs =sf.openSession();
        
        String hqlquery =  "from com.mangium.Car";
        Query query = hs.createQuery(hqlquery);
        ArrayList list = (ArrayList)query.list();
        Iterator i = list.iterator();
        
        cr = new ArrayList();
        
        while(i.hasNext()){
            Car c = (Car)i.next();
            System.out.println(c.getCarid());
            System.out.println(c.getCarname());
            System.out.println(c.getCarcost());
            System.out.println(c.getCartype());
            cr.add(c);
        }
        hs.close();
        sf.close();
        
        System.out.println(cr);
        setCr(cr);
        return cr;
    }
}
