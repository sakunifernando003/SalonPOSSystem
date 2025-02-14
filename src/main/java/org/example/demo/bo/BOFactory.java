package org.example.demo.bo;


import org.example.demo.bo.custom.Impl.AppointmentBOImpl;
import org.example.demo.bo.custom.Impl.CustomerBOImpl;
import org.example.demo.bo.custom.Impl.EmployeeBOImpl;
import org.example.demo.bo.custom.Impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory=
                new BOFactory():boFactory;

    }
    public enum BOTypes{
        Employee, User, CUSTOMERPROFILE, APPOINTMENT, CUSTOMER
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case Employee:
                return new EmployeeBOImpl();
            case User:
                return new UserBOImpl();
            case APPOINTMENT:
                return new AppointmentBOImpl();


            default:
                return null;
        }
    }
}
